package com.tds171a.soboru.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "ingredientes")
public class Ingrediente implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4883318201857102796L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_ingrediente")
	@SequenceGenerator(name = "entity_sequence_generator_ingrediente", sequenceName = "ingrediente_seq")
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "nome", length = 80, nullable = false)
	private String nome;
	
	@OneToMany(mappedBy="ingrediente")
	private Set<ReceitaIngrediente> receitasIngredientes;

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
	 * @return the receitasIngredientes
	 */
	public Set<ReceitaIngrediente> getReceitasIngredientes() {
		return receitasIngredientes;
	}
	/**
	 * @param receitasIngredientes the receitasIngredientes to set
	 */
	public void setReceitasIngredientes(Set<ReceitaIngrediente> receitasIngredientes) {
		this.receitasIngredientes = receitasIngredientes;
	}
}
