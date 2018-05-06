/**
 * 
 */
package com.tds171a.soboru.models.pontuacao;

/**
 * @author Diogo
 *
 */
public class Pontuacao {
	private int receitaId;
	private int usuarioId;
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
