/**
 * 
 */
package com.tds171a.soboru.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * @author Diogo
 *
 */

@Entity
@Table(name = "PONTUACOES")
public class Pontuacao implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6034009323929753926L;
	
	@EmbeddedId
	private PontuacaoId id;

	@ManyToOne(fetch=FetchType.LAZY)
	@MapsId("id_receita")
	private Receita receita;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@MapsId("id_usuario")
	private Usuario usuario;

	@Column(name = "QTY", length = 1, nullable = false)
	private Double qty;

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
	 * @return the receita
	 */
	public Receita getReceita() {
		return receita;
	}

	/**
	 * @param receita the receita to set
	 */
	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
