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

import com.tds171a.soboru.controllers.ReceitaIngredientePersistence;
import com.tds171a.soboru.models.Categoria;
import com.tds171a.soboru.models.Comentario;
import com.tds171a.soboru.models.Ingrediente;
import com.tds171a.soboru.models.Medida;
import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.models.ReceitaIngrediente;
import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.models.Utensilio;
import com.tds171a.soboru.persistence.categoria.CategoriaPersistence;
import com.tds171a.soboru.persistence.comentario.ComentarioPersistence;
import com.tds171a.soboru.persistence.ingrediente.IngredientePersistence;
import com.tds171a.soboru.persistence.medida.MedidaPersistence;
import com.tds171a.soboru.persistence.receita.ReceitaPersistence;
import com.tds171a.soboru.persistence.usuario.UsuarioPersistence;
import com.tds171a.soboru.persistence.utensilio.UtensilioPersistence;
import com.tds171a.soboru.utils.PersistenceFactory;
import com.tds171a.soboru.utils.Utils;

@Named("receitaSiteBean")
@SessionScoped
/**
 * @author Diogo
 *
 */
public class ReceitaSiteBean extends BeanBase<Receita> {

	/**
	 *Criação do serial único 
	 */
	private static final long serialVersionUID = -5362181648251196256L;
	
	//Declaração das variáveis e controllers utilizadas.
	private CategoriaPersistence categoriaPersistence;
	private UsuarioPersistence usuarioPersistence;
	private IngredientePersistence ingredientePersistence;
	private MedidaPersistence medidaPersistence;
	private ReceitaIngredientePersistence receitaIngredientePersistence;
	private UtensilioPersistence utensilioPersistence;
	private ComentarioPersistence comentarioPersistence;
	
	//Definição das listas utilizadas.
	private List<ReceitaIngrediente> listaIngredientes;
	private List<Utensilio> listaUtensilios;

	private List<Categoria> categorias;
	private List<Ingrediente> ingredientes;
	private List<Medida> medidas;
	private List<Utensilio> utensilios;
	
	private Comentario comentario;
	
	/**
	 * Variável de manipulação de imagem.
	 */
	private Part imgFile;

	/**
	 * Construtor setando a rota e qual será passado para o navegador.
	 */
	public ReceitaSiteBean() {
		route_base = "/receita/";
		controller = PersistenceFactory.getReceitaPersistanceFactory();

		categoriaPersistence = PersistenceFactory.getCategoriaPersistanceFactory();
		usuarioPersistence = PersistenceFactory.getUsuarioPersistanceFactory();
		ingredientePersistence = PersistenceFactory.getIngredientePersistanceFactory();
		medidaPersistence = PersistenceFactory.getMedidaPersistanceFactory();
		receitaIngredientePersistence = PersistenceFactory.getReceitaIngredientePersistanceFactory();
		utensilioPersistence = PersistenceFactory.getUtensilioPersistanceFactory();
		comentarioPersistence = PersistenceFactory.getComentarioPersistanceFactory();

		setModel(new Receita());
		
		setListaIngredientes(new ArrayList<ReceitaIngrediente>());
		setIngredientes(new ArrayList<Ingrediente>());
		setMedidas(new ArrayList<Medida>());
		
		setComentario(new Comentario());
	}

	/**
	 * recebe listas de controllers especificas para passar ao cliente e ele
	 * selecionar quais anexar na receita.
	 */
	@Override
	public String criar() {
		if(SessionContext.getInstance().isLogado()) {
			setCategorias(categoriaPersistence.listarSelecionaveis());
			setIngredientes(ingredientePersistence.listar());
			setMedidas(medidaPersistence.listar());
			setUtensilios(utensilioPersistence.listar());
			return super.criar();
		}
		return "/login/"+BeanBase.INDEX_PAGE+BeanBase.FACES_REDIRECT;
	}

	/**
	 * Método que adiciona uma linha para incluir
	 * ingredientes.
	 */
	public void adicionarReceitaIngrediente() {
		listaIngredientes.add(new ReceitaIngrediente());
		
	}

	/**
	 * Método que remove um ingrediente cadastrado
	 * @param receitaIngrediente
	 */
	public void removerReceitaIngrediente(ReceitaIngrediente receitaIngrediente) {
		listaIngredientes.remove(receitaIngrediente);
	}

	/**
	 * Método que trás a lista de utensilios 
	 * e cria uma linha.
	 */
	public void adicionarUtensilio() {
		listaUtensilios.add(new Utensilio());
		
	}

	/**
	 * Remove a um utensilio cadastrado.
	 * @param utensilio
	 */
	public void removerUtensilio(Utensilio utensilio) {
		listaUtensilios.remove(utensilio);
	}

	/**
	 * POST do criar, onde é verificado a sessão e se válida gera a inclusão da
	 * receita.
	 */
	@Override
	public String incluir() {
		FacesContext context = FacesContext.getCurrentInstance();

		getModel().setUsuarioId(SessionContext.getInstance().getUsuarioLogado().getId());
		getModel().setSlug(Utils.toSlug(getModel().getNome()));
		getModel().setAprovado(false);

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
		
	    if(!validarDados())
	    	return route_base + CRIAR_PAGE;

	    if(controller.incluir(getModel())) {
	    	try {
		    	int receitaId = ((ReceitaPersistence) controller).selecionarUltimoIdInserido();
		    	System.out.println("ReceitaId: " + receitaId);
				receitaIngredientePersistence.incluirLista(receitaId, getListaIngredientes());
				((ReceitaPersistence) controller).registrarUtensilios(receitaId, getListaUtensilios());
	    	} catch (Exception e) {
				e.printStackTrace();
			}
			
	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso!", null));
	    } else {
	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel fazer o cadastro!", null));
            return route_base + CRIAR_PAGE;
	    }

	    limparModel();

	    return listar();
	}

	/**
	 * Override do exibir, onde recebe o id de todas as controllers e exibe a
	 * receita ao cliente.
	 */
	@Override
	public String exibir(Receita vo) {
		vo = controller.selecionar(vo.getId());

		if (vo.getCategoria() == null)
			vo.setCategoria(categoriaPersistence.selecionar(vo.getCategoriaId()));

		if (vo.getUsuario() == null)
			vo.setUsuario(usuarioPersistence.selecionar(vo.getUsuarioId()));
			
		if (vo.getUtensilios() == null)
 			vo.setUtensilios(utensilioPersistence.selecionarPorReceita(vo.getId()));
 
 		if (vo.getReceitaIngredientes() == null)
 			vo.setReceitaIngredientes(receitaIngredientePersistence.selecionarPorReceita(vo.getId()));
 
 		if (vo.getComentarios() == null)
 			vo.setComentarios(comentarioPersistence.selecionarPorReceita(vo.getId()));

		if (vo.getUsuariosFavoritaram() == null)
			vo.setUsuariosFavoritaram(usuarioPersistence.selecionarUsuariosQueFavoritaram(vo.getId()));

		if (SessionContext.getInstance().isLogado()) {
			Usuario usuario = SessionContext.getInstance().getUsuarioLogado();
			vo.setReportou(usuarioPersistence.reportou(usuario.getId(), vo.getId()));
			vo.setPontuou(usuarioPersistence.pontuou(usuario.getId(), vo.getId()));	
		}
		
		return super.exibir(vo);
	}

	/**
	 * Override do deletar, onde verifica a sessao, se existe um ítem válido e se
	 * não houver, retorna a pagina de criação.
	 */
	@Override
	public String deletar() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (getModel().getId() == -1) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item nao pode ser vazio!", null));
			return route_base + DELETAR_PAGE;
		}

		if (controller.remover(getModel().getId())) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado com sucesso!", null));
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel deletar.", null));
			return route_base + DELETAR_PAGE;
		}

		limparModel();

		return listar();
	}

	/**
	 * Verifica os dados da pagina de interação e se faltar algum dado informa ao
	 * cliente.
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
		setModel(new Receita());
		setListaIngredientes(new ArrayList<ReceitaIngrediente>());
		adicionarReceitaIngrediente();
		setListaUtensilios(new ArrayList<Utensilio>());
		adicionarUtensilio();
	}
	
	/**
	 * Método que inclui um novo comentario nessa receita com o usuario logado
	 * @param receitaId
	 * @return
	 */
	public String incluirComentario(int receitaId) {
		getComentario().setUsuarioId(SessionContext.getInstance().getUsuarioLogado().getId());
		getComentario().setReceitaId(getModel().getId());

//		FacesContext context = FacesContext.getCurrentInstance();

	    if(comentarioPersistence.incluir(getComentario())) {
		    setComentario(new Comentario());
//	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso!", null));
	    } else {
//	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel fazer o cadastro!", null));
//			return exibir(getModel());
	    }
	    
//	    setComentario(new Comentario());
		
		return exibir(getModel());
	}

	/**
	 * Método verifica se
	 * usuário está logado e favorita a receita
	 * no perfil 
	 * @return
	 */
	public String favoritar() {
		if (SessionContext.getInstance().isLogado()) {
			if(!isReceitaFavoritada()) {
				((ReceitaPersistence) controller).incluirFavorito(getModel().getId(), SessionContext.getInstance().getUsuarioLogado().getId());
				getModel().getUsuariosFavoritaram().add(SessionContext.getInstance().getUsuarioLogado());
			} else {
				((ReceitaPersistence) controller).removerFavorito(getModel().getId(), SessionContext.getInstance().getUsuarioLogado().getId());
				// TODO - check if this is working
				getModel().getUsuariosFavoritaram().remove(SessionContext.getInstance().getUsuarioLogado());
			}

			return exibir(getModel());
		}

		return "/login/" + INDEX_PAGE + FACES_REDIRECT;
	}

	/**
	 * Método que verifica o usuário logado
	 * e cria um report.
	 * @return
	 */
	public String reportar() {
		if (SessionContext.getInstance().isLogado()) {
			((ReceitaPersistence) controller).incluirReport(getModel().getId(), SessionContext.getInstance().getUsuarioLogado().getId());
			return exibir(getModel());
		}

		return "/login/" + INDEX_PAGE + FACES_REDIRECT;
	}

	/**
	 * Método que verifica o ponto dado pelo cliente e 
	 * soma na média da receita.
	 * @param pontos
	 * @return
	 */
	public String pontuar(int pontos) {
		if (SessionContext.getInstance().isLogado()) {
			((ReceitaPersistence) controller).incluirPontuacao(getModel().getId(), SessionContext.getInstance().getUsuarioLogado().getId(), pontos);
			return exibir(getModel());
		}

		return "/login/" + INDEX_PAGE + FACES_REDIRECT;
	}
	
	/**
	 * Método verifica se a receita apresentada
	 * esá favoritada e retorna verdadeiro ou falso.
	 * @return
	 */
	public boolean isReceitaFavoritada() {
		if(SessionContext.getInstance().isLogado()) {
			boolean favoritada = false;
			int usuarioLogadoId = SessionContext.getInstance().getUsuarioLogado().getId();
			List<Usuario> usuariosFavoritaram = getModel().getUsuariosFavoritaram();
			for (int i = 0; i < getModel().getUsuariosFavoritaram().size(); i++) {
				if(usuariosFavoritaram.get(i).getId() == usuarioLogadoId) {
					favoritada = true;
					break;
				}
			}
			
			return favoritada;
		}
		return false;
	}
	
	/**
	 * Método que formata a apresentação da
	 * quantidade, se for número inteiro 
	 * não apresenta vírgula e zero.
	 * @param num
	 * @return
	 */
	public String formatDouble(Double num) {
		return Utils.formatDouble(num);
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

	/**
	 * @return the listaIngredientes
	 */
	public List<ReceitaIngrediente> getListaIngredientes() {
		return listaIngredientes;
	}

	/**
	 * @param listaIngredientes the listaIngredientes to set
	 */
	public void setListaIngredientes(List<ReceitaIngrediente> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}

	/**
	 * @return the listaUtensilios
	 */
	public List<Utensilio> getListaUtensilios() {
		return listaUtensilios;
	}

	/**
	 * @param listaUtensilios the listaUtensilios to set
	 */
	public void setListaUtensilios(List<Utensilio> listaUtensilios) {
		this.listaUtensilios = listaUtensilios;
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
	 * @return the ingredientes
	 */
	public List<SelectItem> getIngredientes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Ingrediente i : this.ingredientes) {
			items.add(new SelectItem(i.getId(), i.getNome()));
		}
		return items;
	}

	/**
	 * @param ingredientes the ingredientes to set
	 */
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	/**
	 * @return the medidas
	 */
	public List<SelectItem> getMedidas() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Medida m : this.medidas) {
			items.add(new SelectItem(m.getId(), m.getNome()));
		}
		return items;
	}

	/**
	 * @param medidas the medidas to set
	 */
	public void setMedidas(List<Medida> medidas) {
		this.medidas = medidas;
	}

	/**
	 * @return the utensilios
	 */
	public List<SelectItem> getUtensilios() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Utensilio u : this.utensilios) {
			items.add(new SelectItem(u.getId(), u.getNome()));
		}
		return items;
	}

	/**
	 * @param utensilios the utensilios to set
	 */
	public void setUtensilios(List<Utensilio> utensilios) {
		this.utensilios = utensilios;
	}

	/**
	 * @return the comentario
	 */
	public Comentario getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
}
