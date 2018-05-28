package com.tds171a.soboru.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

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
	private static final long serialVersionUID = -5207223117680367930L;

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
	
	@Column(name = "NOTIFICACAO_EMAIL", precision = 1, nullable = false)
	private boolean notificacaoEmail;
	
	private String senhaConfirmacao;
	
	// FOREIGN KEYS --------------------------------------------------------------
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_role")
	private Role role;

	// REFERENCED IN FOREIGN KEYS --------------------------------------------------------------
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
	private Set<Receita> receitas;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
	@Where(clause="aprovado = true")
	private Set<Receita> receitasAprovadas;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
	private Set<Pontuacao> pontuacoes;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
	private Set<Comentario> comentarios;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "reports", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = { @JoinColumn(name = "id_receita") })
	private Set<Receita> receitasReportadas;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "receitas_fav", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = { @JoinColumn(name = "id_receita") })
	private Set<Receita> receitasFavoritadas;

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
	 * @return the receitas
	 */
	public Set<Receita> getReceitas() {
		return receitas;
	}

	/**
	 * @param receitas the receitas to set
	 */
	public void setReceitas(Set<Receita> receitas) {
		this.receitas = receitas;
	}

	/**
	 * @return the pontuacoes
	 */
	public Set<Pontuacao> getPontuacoes() {
		return pontuacoes;
	}

	/**
	 * @param pontuacoes the pontuacoes to set
	 */
	public void setPontuacoes(Set<Pontuacao> pontuacoes) {
		this.pontuacoes = pontuacoes;
	}

	/**
	 * @return the comentarios
	 */
	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @return the receitasReportadas
	 */
	public Set<Receita> getReceitasReportadas() {
		return receitasReportadas;
	}

	/**
	 * @param receitasReportadas the receitasReportadas to set
	 */
	public void setReceitasReportadas(Set<Receita> receitasReportadas) {
		this.receitasReportadas = receitasReportadas;
	}

	/**
	 * @return the receitasFavoritadas
	 */
	public Set<Receita> getReceitasFavoritadas() {
		return receitasFavoritadas;
	}

	/**
	 * @param receitasFavoritadas the receitasFavoritadas to set
	 */
	public void setReceitasFavoritadas(Set<Receita> receitasFavoritadas) {
		this.receitasFavoritadas = receitasFavoritadas;
	}
}
