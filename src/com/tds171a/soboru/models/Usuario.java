package com.tds171a.soboru.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Diogo
 *
 */

/**
 * Trandormando a model em entidade.
 * @author Luiz
 *
 */
@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 538500355617517725L;
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_usuario")
	@SequenceGenerator(name = "entity_sequence_generator_usuario", sequenceName = "usuario_seq")
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;
	
	@Column(name = "EMAIL", length = 80, nullable = false)
	private String email;
	
	
	@Column(name = "SENHA", length = 80, nullable = false)
	private String senha;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "NASC", columnDefinition="DATE", nullable = false)
	private Date nasc;
	
	@Column(name = "SEXO", precision = 1, nullable = false)
	private int sexo;
	
	@Column(name = "ID_ROLE", precision = 11, nullable = false)
	private int roleId;
	
	@Column(name = "NOTIFICACAO_EMAIL", precision = 1, nullable = false)
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
