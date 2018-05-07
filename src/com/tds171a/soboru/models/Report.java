/**
 * 
 */
package com.tds171a.soboru.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "reports")
public class Report implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5064897314637013653L;
	
	@Column(name = "ID_RECEITA", precision= 11, nullable = false)
	private int receitaId;
	
	@Column(name = "ID_USUARIO", precision= 11, nullable = false)
	private int usuarioId;
	
	
	private String receitaNome;
	
	
	private String usuarioNome;
	
	
	
	public Report() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the receitaId
	 */
	public int getReceitaId() {
		return receitaId;
	}
	/**
	 * @param receitaId the receitaId to set
	 */
	public void setReceitaId(int receitaId) {
		this.receitaId = receitaId;
	}
	/**
	 * @return the usuarioId
	 */
	public int getUsuarioId() {
		return usuarioId;
	}
	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	/**
	 * @return the receitaNome
	 */
	public String getReceitaNome() {
		return receitaNome;
	}
	/**
	 * @param receitaNome the receitaNome to set
	 */
	public void setReceitaNome(String receitaNome) {
		this.receitaNome = receitaNome;
	}
	/**
	 * @return the usuarioNome
	 */
	public String getUsuarioNome() {
		return usuarioNome;
	}
	/**
	 * @param usuarioNome the usuarioNome to set
	 */
	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}
}
