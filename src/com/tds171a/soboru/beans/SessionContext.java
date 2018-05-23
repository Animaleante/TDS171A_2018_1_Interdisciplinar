package com.tds171a.soboru.beans;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.tds171a.soboru.models.Usuario;

/**
 * Cria uma sessão
 * @author Palte
 *
 */
public class SessionContext {
	private static SessionContext instance;
	
	public static SessionContext getInstance() {
		if(instance == null) {
			instance = new SessionContext();
		}
		
		return instance;
	}
	
	private SessionContext() {}
	
	/**
	 * Verifica se foi feito login de forma correta
	 */
	private ExternalContext currentExternalContext() {
		if(FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext nao pode ser chamado fora de uma requisicao HTTP");
		} else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}
	
	/**
	 * faz logoff invalidando a sessão
	 */
	public void encerrarSessao() {
		currentExternalContext().invalidateSession();
	}
	
	/**
	 * recebe o usuário logado
	 */
	public Usuario getUsuarioLogado() {
		return (Usuario) getAttribute("usuarioLogado");
	}
	
	/**
	 * retorna se o cliente está logado.
	 */
	public boolean isLogado() {
		return getUsuarioLogado() != null;
	}
	
	/**
	 * retorna se o cliente está logado.
	 */
	public boolean isAdminLogado() {
		Usuario usuario = getUsuarioLogado();
		return usuario != null && usuario.getRole().getIsAdmin();
	}
	
	/**
	 * seta o usuáro
	 */
	public void setUsuarioLogado(Usuario usuario) {
		setAttribute("usuarioLogado", usuario);
	}
	
	
	public Object getAttribute(String nome) {
		return currentExternalContext().getSessionMap().get(nome);
	}
	
	public void setAttribute(String nome, Object valor) {
		currentExternalContext().getSessionMap().put(nome, valor);
	}
}
