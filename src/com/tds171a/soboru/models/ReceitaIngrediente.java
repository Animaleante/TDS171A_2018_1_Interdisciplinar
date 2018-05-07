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
	private static final long serialVersionUID = -2634424320049000000L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_receita_ingrediente")
	@SequenceGenerator(name = "entity_sequence_generator_receita_ingrediente", sequenceName = "receita_ingrediente_seq")
	@Column(name = "id", nullable = false)
	private int id;
	
	/**
	 * 
	 */
	@Column(name = "ID_RECEITA", precision= 11, nullable = false)
	private int id_receita;
	
	/**
	 * 
	 */
	@Column(name = "ID_INGREDIENTE", precision= 11, nullable = false)
	private int id_ingrediente;
	
	/**
	 * 
	 */
	@Column(name = "ID_MEDIDA", precision= 11, nullable = false)
	private int id_medida;
	
	/**
	 * 
	 */
	@Column(name = "SUB_SESSAO", length=80, nullable = false)
	private String sub_sessao;
	
	/**
	 * 
	 */
	@Column(name = "QTY", precision=11 , scale=2, nullable = false)
	private double qty;
	
	/**
	 * 
	 */
	
	private Medida medida;
	
	/**
	 * 
	 */
	private Ingrediente ingrediente;
	
	/**
	 * 
	 */
	private Receita receita;
	
	/**
	 * 
	 */
	public ReceitaIngrediente() {
		id = -1;
		id_receita = -1;
		id_ingrediente = -1;
		id_medida = -1;
		sub_sessao = null;
		qty = 0;
		medida = null;
		ingrediente = null;
		receita = null;
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
	 * @return the id_receita
	 */
	public int getId_receita() {
		return id_receita;
	}

	/**
	 * @param id_receita the id_receita to set
	 */
	public void setId_receita(int id_receita) {
		this.id_receita = id_receita;
	}

	/**
	 * @return the id_ingrediente
	 */
	public int getId_ingrediente() {
		return id_ingrediente;
	}

	/**
	 * @param id_ingrediente the id_ingrediente to set
	 */
	public void setId_ingrediente(int id_ingrediente) {
		this.id_ingrediente = id_ingrediente;
	}

	/**
	 * @return the id_medida
	 */
	public int getId_medida() {
		return id_medida;
	}

	/**
	 * @param id_medida the id_medida to set
	 */
	public void setId_medida(int id_medida) {
		this.id_medida = id_medida;
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
