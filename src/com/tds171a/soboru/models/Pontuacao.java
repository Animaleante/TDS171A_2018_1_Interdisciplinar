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
@Table(name = "PONTUACOES")
public class Pontuacao implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4630807191096222379L;
	
	@Column(name = "id_receita", length = 11, nullable = false)	
	private int receitaId;
	
	@Column(name = "id_usuario", length = 11, nullable = false)
	private int usuarioId;
	
	@Column(name = "QTY", length = 1, nullable = false)
	private Double qty;
	
	private String receitaNome;
	private String usuarioNome;
	
	public Pontuacao() {
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
	 * @return the qty
	 */
	public Double getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(Double qty) {
		this.qty = qty;
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
