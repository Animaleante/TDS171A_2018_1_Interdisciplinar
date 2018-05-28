package com.tds171a.soboru.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.role.RolePersistence;
import com.tds171a.soboru.persistence.usuario.UsuarioPersistence;
import com.tds171a.soboru.utils.PersistenceFactory;
import com.tds171a.soboru.utils.Utils;

@Named("registroBean")
@SessionScoped
/**
 * Criação do bean herando de beanbase passando
 * o vo utilizada.
 */
public class RegistroBean extends BeanBase<Usuario> implements Serializable {
	
	/**
     *criando o serial do bean
     */
	private static final long serialVersionUID = -3690960600864076027L;
	
	/**
	 * Recebe a rota. 
	 */
	private String route_base = "/registrar/";
	
	/**
	 * controller do usuário 
	 */
	private UsuarioPersistence controller;
	
	//Declaração de variáveis
	private String nome;
	private String email;
	private String senha;
	private String senhaConfirmacao;
	private String nasc;
	private int sexo;
	private boolean notificacaoEmail;

	/**
     *Construtor setando a todas as variáveis
     *como vazio para não ter exception de null.
     */
	public RegistroBean() {
		setNome("");
		setEmail("");
		setSenha("");
		setSenhaConfirmacao("");
		setNasc(null);
		setSexo(1);
		setNotificacaoEmail(true);
		
	}
	
	/**
	 * método para exibir o índex
	 */
	public String index() {
		return route_base + "index?faces-redirect=true";
	}
	
	/**
	 * Método para verificar os dados no cadastro do usuário. Verifica os dados, se tiver erro
	 * ele apresenta para o cliente,
	 */
	public String incluir() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    
		if(!getSenha().equals(getSenhaConfirmacao())) {
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas nao sao identicas!", null));
		} else {
			Date formattedDate;
			try {
				formattedDate = Utils.formataData(getNasc());
			} catch (ParseException e) {
				e.printStackTrace();
				setSenha("");
				setSenhaConfirmacao("");
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel registrar um usuario!", null));
				return route_base + "index";
			}
			
			controller = PersistenceFactory.getUsuarioPersistenceFactory();
			
			RolePersistence rolePersistence = PersistenceFactory.getRolePersistenceFactory();
			
//			return super. incluir();

			Usuario usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setSenha(senha);
			usuario.setNasc(formattedDate);
			usuario.setSexo(sexo);
			usuario.setRole(rolePersistence.selecionar(2));
			usuario.setNotificacaoEmail(notificacaoEmail);
			
			if(controller.incluir(usuario)) {
				setNome("");
				setEmail("");
				setSenha("");
				setSenhaConfirmacao("");
				setNasc(null);
				setSexo(1);
				setNotificacaoEmail(true);
				
				return "/login/index";
			} else {
				setSenha("");
				setSenhaConfirmacao("");
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel registrar um usuario!", null));
			}
		}
		
		return route_base + "index";
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the senhaConfirmacao
	 */
	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	/**
	 * @param senhaConfirmacao the senhaConfirmacao to set
	 */
	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

	/**
	 * @return the nasc
	 */
	public String getNasc() {
		return nasc;
	}

	/**
	 * @param nasc the nasc to set
	 */
	public void setNasc(String nasc) {
		this.nasc = nasc;
	}

	/**
	 * @return the sexo
	 */
	public int getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the notificacaoEmail
	 */
	public boolean isNotificacaoEmail() {
		return notificacaoEmail;
	}

	/**
	 * @param notificacaoEmail the notificacaoEmail to set
	 */
	public void setNotificacaoEmail(boolean notificacaoEmail) {
		this.notificacaoEmail = notificacaoEmail;
	}

	@Override
	public String deletar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void limparModel() {
		// TODO Auto-generated method stub
		
	}

}
