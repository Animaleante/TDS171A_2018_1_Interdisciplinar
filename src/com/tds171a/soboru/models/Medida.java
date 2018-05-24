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

@Entity
@Table(name = "MEDIDAS")
public class Medida implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3374455157823392002L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_medida")
	@SequenceGenerator(name = "entity_sequence_generator_medida", sequenceName = "medida_seq")
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;

	@Column(name = "abreviacao", length = 80, nullable = false)
	private String abreviacao;

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
	 * @return the abreviacao
	 */
	public String getAbreviacao() {
		return abreviacao;
	}

	/**
	 * @param abreviacao the abreviacao to set
	 */
	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}
}
