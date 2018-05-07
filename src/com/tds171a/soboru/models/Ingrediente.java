package com.tds171a.soboru.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe de ValueObject do Ingrediente
 * @author Diogo
 *
 */

/**
 * Trandormando a model em entidade.
 * @author Luiz
 *
 */
@Entity
@Table(name = "INGREDIENTES")
public class Ingrediente implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -270052700065757659L;

	/**
	 * Parametro id do Ingrediente
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_ingrediente")
	@SequenceGenerator(name = "entity_sequence_generator_ingrediente", sequenceName = "ingrediente_seq")
	@Column(name = "id", nullable = false)
	private int id;

	/**
	 * Parametro nome do Ingrediente
	 */
	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;

	/**
	 * Construtor da classe de Ingrediente
	 */
	public Ingrediente() {
	    id = -1;
	    nome = "";
	}

	/**
	 * Construtor da classe de Ingrediente que recebe o nome
	 * @param nome
	 */


	public Ingrediente(String nome) {
		this.nome = nome;
	}

	/**
	 * Construtor da classe de Ingrediente que recebe o id e o nome
	 * @param id
	 * @param nome
	 */
	public Ingrediente(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	/**
	 * @return o id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id o id para setar
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return o nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome o nome para setar
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}
