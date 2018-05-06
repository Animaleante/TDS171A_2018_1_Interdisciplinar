package com.tds171a.soboru.models;

import java.util.Date;

/**
 * 
 * @author Diogo
 *
 */
public class Usuario {
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String tipo;
	private Date nasc;
	private int sexo;
	private int roleId;
	private boolean notificacaoEmail;
	private Role role;
	private String senhaConfirmacao;
	
	
	public Usuario() {
		id = -1;
		sexo = 3;
		roleId = -1;
		notificacaoEmail = true;
		
		setRole(null);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the nasc
	 */
	public Date getNasc() {
		return nasc;
	}

	/**
	 * @param nasc the nasc to set
	 */
	public void setNasc(Date nasc) {
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
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
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
}
