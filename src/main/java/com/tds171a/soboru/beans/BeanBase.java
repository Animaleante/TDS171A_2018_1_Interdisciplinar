/**
 * 
 */
package com.tds171a.soboru.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tds171a.soboru.controllers.ControllerBase;

/**
 * @author Diogo
 *
 */
public abstract class BeanBase<T> implements Serializable {
	/**
	 * Criação do serial único.
	 */
	private static final long serialVersionUID = -4706861623339777245L;
	
	/**
	 * Setado as estáticas com o final da rota
	 * para facilitar a manipulação dos métodos.
	 */
	protected static final String INDEX_PAGE = "index";
	protected static final String CRIAR_PAGE = "criar";
	protected static final String EXIBIR_PAGE = "exibir";
	protected static final String EDITAR_PAGE = "editar";
	protected static final String DELETAR_PAGE = "deletar";
	protected static final String FACES_REDIRECT = "?faces-redirect=true";
	
	/**
	 * Variável para receber a rota do bean que implementou.
	 */
	protected String route_base;
	
	/**
	 * Variável que recebe a controler que irá auxiliar o bean.
	 */
	protected ControllerBase<T> controller;
	/**
	 * Variável que irá receber o vo que está sendo usado.
	 */
	private T vo;
	/**
	 * Lista que irá receber os itens para apresentação na página.
	 */
	private List<T> lista;
	
	/**
	 * Método que lista todos os itens cadastrados referente a controller que está sendo utilizada.
	 */
	public String listar() {
	    limparVo();
	    
		setLista(controller.listar());

		return route_base + INDEX_PAGE + FACES_REDIRECT;
	}

	/**
	 * Método que irá chamar a tela de criação, referente a controller que está sendo utilizada.
	 */
	public String criar() {
		limparVo();
	    return route_base + CRIAR_PAGE + FACES_REDIRECT;
	}

//	public abstract String incluir();
	/**
	 * Método que irá adicionar o item, referente a controller que está sendo utilizada.
	 */
	public String incluir() {
		FacesContext context = FacesContext.getCurrentInstance();

	    if(!validarDados())
	    	return route_base + CRIAR_PAGE;

	    if(controller.incluir(getVo())) {
	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso!", null));
	    } else {
	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel fazer o cadastro!", null));
            return route_base + CRIAR_PAGE;
	    }

	    limparVo();

	    return listar();
	}

	/**
	 * Método que chamar a página para exibir o ítem.
	 */
	public String exibir(T vo) {
		setVo(vo);
	    return route_base + EXIBIR_PAGE + FACES_REDIRECT;
	}

	/**
	 * Método para chamar a página para editar o ítem.
	 */
	public String editar(T vo) {
		setVo(vo);
		return route_base + EDITAR_PAGE + FACES_REDIRECT;
	}

	/**
	 * POST
	 * Método que edita o ítem e retorna para a lista se der certo. 
	 */
//	public abstract String editar();
	public String editar() {
		FacesContext context = FacesContext.getCurrentInstance();

	    if(!validarDados())
	    	return route_base + EDITAR_PAGE;

	    if(controller.atualizar(getVo())) {
	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizada com sucesso!", null));
	    } else {
	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel fazer a atualizacao.", null));
            return route_base + EDITAR_PAGE;
		}

	    limparVo();

	    return listar();
	}

	/**
	 * Método que chama a página para apagar um ítem.
	 */
	public String deletar(T vo) {
		setVo(vo);
		return route_base + DELETAR_PAGE + FACES_REDIRECT;
	}

	/**
	 * POST
	 * Será dado um Override no Bean que implementa. 
	 */
	public abstract String deletar();
	
	/**
	 * Método para validar os dados incluídos. 
	 * Será dado um Override no Bean que implementa.
	 */
	public boolean validarDados() {
		return true;
	}
	
	/**
	 * Método para limpar os campos de interação.
	 * Será dado um Override no Bean que implementa.
	 */
	public abstract void limparVo();

	/**
	 * Método que aquisição do VO
	 * @return vo
	 */
	public T getVo() {
		return vo;
	}

	/**
	 * Método para setar o vo
	 * @param vo para vo para setar
	 */
	public void setVo(T vo) {
		this.vo = vo;
	}

	/**
	 * Méroto para adiquirir uma lista de ítens.
	 * @return a lista
	 */
	public List<T> getLista() {
		return lista;
	}

	/**
	 * Método para puxar uma lista de ítens do banco
	 * @param lista the lista to set
	 */
	public void setLista(List<T> lista) {
		this.lista = lista;
	}
}
