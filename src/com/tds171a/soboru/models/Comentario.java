/**
 * 
 */
package com.tds171a.soboru.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Diogo
 *
 */

/**
 * Trandormando a model em entidade.
 * @author Luiz
 *
 */
@Entity
@Table(name = "COMENTARIOS")
public class Comentario implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7272795930725483134L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_comentario")
	@SequenceGenerator(name = "entity_sequence_generator_comentario", sequenceName = "comentario_seq")
	@Column(name = "id", nullable = false)
	private int id;
	
	/**
	 * Id da receita
	 */
	@Column(name = "ID_RECEITA", length = 11, nullable = false)
	private int receitaId;
	
	/**
	 * Id do usuário
	 */
	@Column(name = "ID_USUARIO", length = 11, nullable = false)
	private int usuarioId;
	
	/**
	 * Corpo do comentário
	 */
	//TODO: Perguntar ao Baracho 
	@Lob
	@Column(name = "BODY", nullable = false)
	private String body;
		
	@ManyToOne
	private Receita receita;
	@ManyToOne
	private Usuario usuario;
	
	/**
	 * Construtor padrão que seta todos os dados nulos.
	 */
	public Comentario() {
		id = -1;
		receitaId = -1;
		usuarioId = -1;
		receita = null;
		usuario = null;
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
	 * @return the receitaId
	 */
	public int getReceitaId() {
		return receitaId;
	}
	/**
	 * @param receitaId the receitaId to set
	 */
	public void setReceitaId(int receitaId) {
		this.receitaId = receitaId;
	}
	/**
	 * @return the usuarioId
	 */
	public int getUsuarioId() {
		return usuarioId;
	}
	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the receita
	 */
	public Receita getReceita() {
		return receita;
	}

	/**
	 * @param receita the receita to set
	 */
	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
