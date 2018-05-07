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
@Table(name = "ROLES")
public class Role implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5205218445919955814L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_role")
	@SequenceGenerator(name = "entity_sequence_generator_role", sequenceName = "role_seq")
	@Column(name = "id", nullable = false)
	private int id;
	
	/**
	 * 
	 */
	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;
	
	@Column(name = "IS_ADMIN", precision= 1, nullable = false)
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
