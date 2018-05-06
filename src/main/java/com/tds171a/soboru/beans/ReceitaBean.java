package com.tds171a.soboru.beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.tds171a.soboru.controllers.CategoriaController;
import com.tds171a.soboru.controllers.ReceitaController;
import com.tds171a.soboru.controllers.UsuarioController;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Categoria;
import com.tds171a.soboru.vos.Receita;

@Named("receitaBean")
@SessionScoped
/**
 * Criação do bean herando de beanbase passando o vo utilizada.
 */
public class ReceitaBean extends BeanBase<Receita> {

	/**
	 * criando o serial do bean
	 */
	private static final long serialVersionUID = 1877717137441387967L;

	/**
	 * Instância da controller Categoria
	 */
	private CategoriaController categoriaController;
	/**
	 * Intância da controller Usuario
	 */
	private UsuarioController usuarioController;

	/**
	 * Lista que recebe objetos do tipo categoria
	 */
	private List<Categoria> categorias;

	/**
	 * Variável que recebe o arquivo de imagem
	 * para anexar receita
	 */
	private Part imgFile;

	/**
	 * Construtor onde é inciado as controllers usadas e
	 * seta a rota e qual será passado para o navegador.
	 */
	public ReceitaBean() {
		route_base = "/cadastro/receita/";
		controller = new ReceitaController();

		categoriaController = new CategoriaController();
		usuarioController = new UsuarioController();
		setVo(new Receita());
	}

	/**
	 * Override do método ao qual efetua a limpeza dos campos
	 * faz cast da controller e efetua um método exclusivo para
	 * admin, onde mostra todas as receitas. 
	 */
	@Override
	public String listar() {

		limparVo();
		setLista(((ReceitaController) controller).listarAdmin());
		return route_base + INDEX_PAGE + FACES_REDIRECT;

	}

	/**
	 * recebe listas de categorias disponívels e passa ao cliente
	 * selecionar quais anexar na receita.
	 * Envia ao super para enviar a tela de criar receita.
	 */
	@Override
	public String criar() {
		setCategorias(categoriaController.listar());

		return super.criar();
	}

	/**
	 * POST do criar, onde é verificado a sessão e se válida gera a inclusão da
	 * receita.
	 */
	@Override
	public String incluir() {
		FacesContext context = FacesContext.getCurrentInstance();

		getVo().setUsuarioId(SessionContext.getInstance().getUsuarioLogado().getId());
		getVo().setSlug(Utils.toSlug(getVo().getNome()));
		getVo().setAprovado(true);

		try (InputStream input = imgFile.getInputStream()) {
			File file = File.createTempFile("receita_", ".jpg", Utils.getImagerDir());
			Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			getVo().setImgPath(file.getName());
		} catch (IOException e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro ao tentar fazer upload da imagem: " + e.getMessage(), null));
			return route_base + CRIAR_PAGE;
		}

		return super.incluir();
	}

	/**
	 * Override do exibir, onde recebe o id de todas as controllers e exibe a
	 * receita ao cliente.
	 */
	@Override
	public String exibir(Receita vo) {
		if (vo.getCategoria() == null)
			vo.setCategoria(categoriaController.selecionar(vo.getCategoriaId()));

		if (vo.getUsuario() == null)
			vo.setUsuario(usuarioController.selecionar(vo.getUsuarioId()));

		return super.exibir(vo);
	}

	/**
	 * Override do método editar(GET)
	 * cria a lista de categorias válidas e 
	 * envia ao editar do super.
	 */
	@Override
	public String editar(Receita vo) {

		setCategorias(categoriaController.listar());

		return super.editar(vo);
	}

	/**
	 * Override do método editar(POST)
	 * Pega o id do usuário logado, tenta criar a imagem
	 * da receita pegando a imagem original, criando uma 
	 * temporária, copia ela para a pasta(se existir alguma com o mesmo nome
	 * ele substitui, e então envia para o super editar.
	 */
	@Override
	public String editar() {
		FacesContext context = FacesContext.getCurrentInstance();

		getVo().setSlug(Utils.toSlug(getVo().getNome()));
		
		if(imgFile != null) {
			try (InputStream input = imgFile.getInputStream()) {
				File file = File.createTempFile("receita_", ".jpg", Utils.getImagerDir());
				Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				getVo().setImgPath(file.getName());
			} catch (IOException e) {
				e.printStackTrace();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Ocorreu um erro ao tentar fazer upload da imagem: " + e.getMessage(), null));
				return route_base + CRIAR_PAGE;
			}
		}
		return super.editar();
	}

	/**
	 * Override do deletar, onde verifica a sessao, se existe um ítem válido e
	 * se não houver, retorna a pagina de criação.
	 */
	@Override
	public String deletar() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (getVo().getId() == -1) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item nao pode ser vazio!", null));
			return route_base + DELETAR_PAGE;
		}

		if (controller.remover(getVo().getId())) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado com sucesso!", null));
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel deletar. Verifique se existem ingredientes"
					+ ",utensilios, comentarios ou pontuações anexados.", null));
			return route_base + DELETAR_PAGE;
		}

		limparVo();

		return listar();
	}

	/**
	 * Verifica os dados da pagina de interação e se faltar algum dado informa
	 * ao cliente.
	 */
	@Override
	public boolean validarDados() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (getVo().getNome().isEmpty()) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome nao pode ser vazio!", null));
			return false;
		}

		return true;
	}

	/**
	 * Cria uma nova vo para limpar os campos para um novo registro sem
	 * interferencia de dados cadastrados anteriormente.
	 */
	@Override
	public void limparVo() {
		setVo(new Receita());
	}

	public String isAprovado(Receita receita) {
		if (!receita.getAprovado())
			return "Não";
		return "Sim";
	}

	/**
	 * @return the categorias
	 */
	public List<SelectItem> getCategorias() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Categoria c : this.categorias) {
			items.add(new SelectItem(c.getId(), c.getNome()));
		}
		return items;
	}

	/**
	 * @param categorias
	 *            the categorias to set
	 */
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	/**
	 * @return the imgFile
	 */
	public Part getImgFile() {
		return imgFile;
	}

	/**
	 * @param imgFile
	 *            the imgFile to set
	 */
	public void setImgFile(Part imgFile) {
		this.imgFile = imgFile;
	}
}
