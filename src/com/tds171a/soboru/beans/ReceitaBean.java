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

import com.tds171a.soboru.models.Categoria;
import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.persistence.categoria.CategoriaPersistence;
import com.tds171a.soboru.persistence.receita.ReceitaPersistence;
import com.tds171a.soboru.utils.PersistenceFactory;
import com.tds171a.soboru.utils.Utils;

@Named("receitaBean")
@SessionScoped
/**
 * Cria��o do bean herando de beanbase passando o vo utilizada.
 */
public class ReceitaBean extends BeanBase<Receita> {

	/**
	 * criando o serial do bean
	 */
	private static final long serialVersionUID = 1877717137441387967L;

	/**
	 * Inst�ncia da controller Categoria
	 */
	private CategoriaPersistence categoriaPersistence;
	
	/**
	 * Int�ncia da controller Usuario
	 */
//	private UsuarioPersistence usuarioPersistence;
	
	private List<Categoria> categorias;

	/**
	 * Vari�vel que recebe o arquivo de imagem
	 * para anexar receita
	 */
	private Part imgFile;

	/**
	 * Construtor onde � inciado as controllers usadas e
	 * seta a rota e qual ser� passado para o navegador.
	 */
	public ReceitaBean() {
		route_base = "/cadastro/receita/";
		setModel(new Receita());
	}

	/**
	 * Override do m�todo ao qual efetua a limpeza dos campos
	 * faz cast da controller e efetua um m�todo exclusivo para
	 * admin, onde mostra todas as receitas. 
	 */
	@Override
	public String listar() {
		limparModel();
		
		controller = PersistenceFactory.getReceitaPersistenceFactory();
		
		setLista(((ReceitaPersistence) controller).listar());
		return route_base + INDEX_PAGE + FACES_REDIRECT;
	}

	/**
	 * recebe listas de categorias dispon�vels e passa ao cliente
	 * selecionar quais anexar na receita.
	 * Envia ao super para enviar a tela de criar receita.
	 */
	@Override
	public String criar() {
		controller = PersistenceFactory.getReceitaPersistenceFactory();
		categoriaPersistence = PersistenceFactory.getCategoriaPersistenceFactory();
		
		setCategorias(categoriaPersistence.listar());

		return super.criar();
	}

	/**
	 * POST do criar, onde � verificado a sess�o e se v�lida gera a inclus�o da
	 * receita.
	 */
	@Override
	public String incluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		getModel().setUsuario(SessionContext.getInstance().getUsuarioLogado());
		getModel().setSlug(Utils.toSlug(getModel().getNome()));
		getModel().setAprovado(true);

		try (InputStream input = imgFile.getInputStream()) {
			File file = File.createTempFile("receita_", ".jpg", Utils.getImagerDir());
			Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			getModel().setImgPath(file.getName());
		} catch (IOException e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro ao tentar fazer upload da imagem: " + e.getMessage(), null));
			return route_base + CRIAR_PAGE;
		}

		controller = PersistenceFactory.getReceitaPersistenceFactory();

		return super.incluir();
	}

	/**
	 * Override do exibir, onde recebe o id de todas as controllers e exibe a
	 * receita ao cliente.
	 */
	@Override
	public String exibir(Receita vo) {
		categoriaPersistence = PersistenceFactory.getCategoriaPersistenceFactory();
//		usuarioPersistence = PersistenceFactory.getUsuarioPersistenceFactory();
		
		if (vo.getCategoria() == null)
//			vo.setCategoria(categoriaPersistence.selecionar(vo.getCategoriaId()));
			vo.setCategoria(vo.getCategoria());

		if (vo.getUsuario() == null)
//			vo.setUsuario(usuarioPersistence.selecionar(vo.getUsuarioId()));
			vo.setUsuario(vo.getUsuario());

		controller = PersistenceFactory.getReceitaPersistenceFactory();
		
		return super.exibir(vo);
	}

	/**
	 * Override do m�todo editar(GET)
	 * cria a lista de categorias v�lidas e 
	 * envia ao editar do super.
	 */
	@Override
	public String editar(Receita vo) {
		categoriaPersistence = PersistenceFactory.getCategoriaPersistenceFactory();

		setCategorias(categoriaPersistence.listar());

		controller = PersistenceFactory.getReceitaPersistenceFactory();
		
		return super.editar(vo);
	}

	/**
	 * Override do m�todo editar(POST)
	 * Pega o id do usu�rio logado, tenta criar a imagem
	 * da receita pegando a imagem original, criando uma 
	 * tempor�ria, copia ela para a pasta(se existir alguma com o mesmo nome
	 * ele substitui, e ent�o envia para o super editar.
	 */
	@Override
	public String editar() {
		FacesContext context = FacesContext.getCurrentInstance();

		getModel().setSlug(Utils.toSlug(getModel().getNome()));
		
		if(imgFile != null) {
			try (InputStream input = imgFile.getInputStream()) {
				File file = File.createTempFile("receita_", ".jpg", Utils.getImagerDir());
				Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				getModel().setImgPath(file.getName());
			} catch (IOException e) {
				e.printStackTrace();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Ocorreu um erro ao tentar fazer upload da imagem: " + e.getMessage(), null));
				return route_base + CRIAR_PAGE;
			}
		}

		controller = PersistenceFactory.getReceitaPersistenceFactory();
		
		return super.editar();
	}

	/**
	 * Override do deletar, onde verifica a sessao, se existe um �tem v�lido e
	 * se n�o houver, retorna a pagina de cria��o.
	 */
	@Override
	public String deletar() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (getModel().getId() == -1) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item nao pode ser vazio!", null));
			return route_base + DELETAR_PAGE;
		}

		controller = PersistenceFactory.getReceitaPersistenceFactory();

		if (controller.remover(getModel())) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado com sucesso!", null));
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel deletar. Verifique se existem ingredientes"
					+ ",utensilios, comentarios ou pontua��es anexados.", null));
			return route_base + DELETAR_PAGE;
		}

		limparModel();

		return listar();
	}

	/**
	 * Verifica os dados da pagina de intera��o e se faltar algum dado informa
	 * ao cliente.
	 */
	@Override
	public boolean validarDados() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (getModel().getNome().isEmpty()) {
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
	public void limparModel() {
		// TODO Auto-generated method stub
		
	}

	public String isAprovado(Receita receita) {
		if (!receita.isAprovado())
			return "N�o";
		return "Sim";
	}

	/**
	 * @return the categorias
	 */
	public List<SelectItem> getCategorias() {
		List<SelectItem> items = new ArrayList<SelectItem>();
//		for (Categoria c : categoriaPersistence.listar()) {
		for (Categoria c : categorias) {
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
