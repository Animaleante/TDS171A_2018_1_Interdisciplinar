package com.tds171a.soboru.models;

import java.io.Serializable;
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
	private static final long serialVersionUID = -7436593408826235662L;

	/**
	 * Par�metro de id do Utensilio
	 */
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

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "receita_utensilio", joinColumns = { @JoinColumn(name = "id_utensilio") }, inverseJoinColumns = { @JoinColumn(name = "id_receita") })
	private Set<Receita> receitas;

	/**
	 * Construtor da classe de Utensilio
	 */
	
	public Utensilio() {

	    id = -1;
        nome = "";
	}

	/**
	 * Construtor da classe de Utensilio que recebe o nome
	 * @param nome
	 */
	public Utensilio(String nome) {
		this.nome = nome;
	}

	/**
	 * Construtor da classe de Utensilio que recebe a id e o nome
	 * @param id
	 * @param nome
	 */
	public Utensilio(int id, String nome) {
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
