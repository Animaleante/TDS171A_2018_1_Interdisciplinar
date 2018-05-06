package com.tds171a.soboru.models.ingrediente;

/**
 * Classe de ValueObject do Ingrediente
 * @author Diogo
 *
 */
public class Ingrediente {

	/**
	 * Parametro id do Ingrediente
	 */
	private int id;

	/**
	 * Parametro nome do Ingrediente
	 */
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
