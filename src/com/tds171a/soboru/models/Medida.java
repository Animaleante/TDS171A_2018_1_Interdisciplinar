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
 * Classe de ValueObject da Medida
 * @author Diogo
 *
 */

/**
 * Trandormando a model em entidade.
 * @author Luiz
 *
 */
@Entity
@Table(name = "MEDIDAS")
public class Medida implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8142250613305285707L;

	/**
	 * Par�metro id da Medida
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_medida")
	@SequenceGenerator(name = "entity_sequence_generator_medida", sequenceName = "medida_seq")
	@Column(name = "id", nullable = false)
	private int id;

	/**
	 * Par�metro nome da Medida
	 */
	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;

	/**
	 * Par�metro abreviacao da Medida
	 */
	@Column(name = "abreviacao", length = 80, nullable = false)
	private String abreviacao;

	/**
	 * Construtor da classe de Medida
	 */
	public Medida() {
	    id = -1;
	    nome = "";
	    abreviacao = "";
	}

	/**
	 * Construtor da classe de Medida que recebe o nome e a abreviacao
	 * @param nome
	 * @param abreviacao
	 */
	public Medida(String nome, String abreviacao) {
		this.nome = nome;
		this.abreviacao = abreviacao;
	}

	/**
	 * Construtor da classe de Medida que recebe o id, nome e abreviacao
	 * @param id
	 * @param nome
	 * @param abreviacao
	 */
	public Medida(int id, String nome, String abreviacao) {
		this.id = id;
		this.nome = nome;
		this.abreviacao = abreviacao;
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

	/**
	 * @return o abreviacao
	 */
	public String getAbreviacao() {
		return abreviacao;
	}

	/**
	 * @param abreviacao a abreviacao para setar
	 */
	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}
}
