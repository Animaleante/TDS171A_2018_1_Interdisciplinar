/**
 * 
 */
package com.tds171a.soboru.models.roles;

/**
 * @author Diogo
 *
 */
public class Role {
	/**
	 * 
	 */
	private int id;
	
	/**
	 * 
	 */
	private String nome;
	
	private boolean isAdmin;
	
	public Role() {
	    id = -1;
	    nome = "";
	    isAdmin = false;
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
	 * @return the isAdmin
	 */
	public boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
