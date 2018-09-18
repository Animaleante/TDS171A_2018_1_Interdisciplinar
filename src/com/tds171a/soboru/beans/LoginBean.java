package com.tds171a.soboru.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.usuario.UsuarioPersistence;
import com.tds171a.soboru.utils.PersistenceFactory;

@ManagedBean(eager = true)
@SessionScoped
/**
 * Bean de login
 */
public class LoginBean implements Serializable {
	
	/**
     *criando o serial do bean
     */
	private static final long serialVersionUID = 2228574273025361556L;
	
	/**
	 * variavel recebe a rota da aplicação.
	 */
	private String route_base = "/login/";
	
	/**
	 * variável responsável para manipular o
	 * email do cliente. 
	 */
	private String email;
	
	/**
	 * variável que irá receber a senha do cliente
	 */
	private String senha;

	/**
	 * Instancia de persistencia de Usuario
	 */
	private UsuarioPersistence controller;

	/**
	 * contrutor que cria um novo usuário controller
	 * para manipulação dos dados.
	 */
	public LoginBean() {
		setEmail("");
		setSenha("");
	}
	
	/**
	 * método que informa a rota da índex
	 * @return
	 */
	public String index() {
		if(SessionContext.getInstance().isLogado())
			return "/index";
		return route_base + "index?faces-redirect=true";
	}
	
	/**
	 * método que efetua o login do cliente e cria uma sessao
	 * @return
	 */
	public String login() {
		
		controller = PersistenceFactory.getUsuarioPersistenceFactory();
		Usuario usuario = controller.loginUsuario(getEmail(), getSenha());
		
		if(usuario == null) {
		    FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique o e-mail/senha enviados", null));
//			FacesContext.getCurrentInstance().validationFailed();
			return "";
		}
		
		SessionContext.getInstance().setUsuarioLogado(usuario);
		
		return "/index.xhtml?faces-redirect=true";
	}
	
	/**
	 * método para dar logoff na sessão
	 * @return
	 */
	public String logout() {
		SessionContext.getInstance().encerrarSessao();
		return route_base + "index?faces-redirect=true";
	}
	
	/**
	 * método que verifica se a sessão está válida
	 */ 
	public boolean isLogado() {
		return SessionContext.getInstance().isLogado();
	}
	
	/**
	 * método que verifica se a sessão está válida e o usuario é admin
	 */ 
	public boolean isAdminLogado() {
		return SessionContext.getInstance().isAdminLogado();
	}

	/**
	 * adiquirir o email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * seta o email
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * adiquirir a senha
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * setar a senha.
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
