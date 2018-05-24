/**
 * 
 */
package com.tds171a.soboru.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Sony
 *
 */

/**
 * Trandormando a model em entidade.
 * @author Luiz
 *
 */
@Entity
@Table(name = "RECEITAS_INGREDIENTES")
public class ReceitaIngrediente implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5631346205285080486L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_receita_ingrediente")
	@SequenceGenerator(name = "entity_sequence_generator_receita_ingrediente", sequenceName = "receita_ingrediente_seq")
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "SUB_SESSAO", length=80, nullable = false)
	private String sub_sessao;
	
	@Column(name = "QTY", precision=11 , scale=2, nullable = false)
	private double qty;
	
	// FOREIGN KEYS --------------------------------------------------------------

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_medida")
	private Medida medida;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_ingrediente")
	private Ingrediente ingrediente;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_receita")
	private Receita receita;

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
	 * @return the sub_sessao
	 */
	public String getSub_sessao() {
		return sub_sessao;
	}

	/**
	 * @param sub_sessao the sub_sessao to set
	 */
	public void setSub_sessao(String sub_sessao) {
		this.sub_sessao = sub_sessao;
	}

	/**
	 * @return the qty
	 */
	public double getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(double qty) {
		this.qty = qty;
	}

	/**
	 * @return the medida
	 */
	public Medida getMedida() {
		return medida;
	}

	/**
	 * @param medida the medida to set
	 */
	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	/**
	 * @return the ingrediente
	 */
	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	/**
	 * @param ingrediente the ingrediente to set
	 */
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
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
}
