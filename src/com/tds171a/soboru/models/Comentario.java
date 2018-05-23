/**
 * 
 */
package com.tds171a.soboru.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@Lob
	@Column(name = "BODY", nullable = false)
	private String body;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_receita")
	private Receita receita;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

}
