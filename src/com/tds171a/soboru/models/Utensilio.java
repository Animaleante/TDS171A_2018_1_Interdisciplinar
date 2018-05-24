package com.tds171a.soboru.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe de ValueObject do Utensilio
 * @author Diogo
 *
 */

/**
 * Trandormando a model em entidade.
 * @author Luiz
 *
 */
@Entity
@Table(name = "UTENSILIOS")
public class Utensilio implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8681475129359748070L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_utensilio")
	@SequenceGenerator(name = "entity_sequence_generator_utensilio", sequenceName = "utensilio_seq")
	@Column(name = "id", nullable = false)
	private int id;

	/**
	 * Par�metro de nome do Utensilio
	 */
	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;

	@ManyToMany(mappedBy = "utensilios")
	private Set<Receita> receitas;

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
}
