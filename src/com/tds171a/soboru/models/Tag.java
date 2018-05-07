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
 * Classe de ValueObject do Tag
 * @author Diogo
 *
 */

/**
 * Trandormando a model em entidade.
 * @author Luiz
 *
 */
@Entity
@Table(name = "TAGS")
public class Tag implements Serializable, Cloneable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5269971351362984994L;

	/**
     * Par�metro id do Tag
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_tag")
	@SequenceGenerator(name = "entity_sequence_generator_tag", sequenceName = "tag_seq")
	@Column(name = "id", nullable = false)
    private int id;

    /**
     * Par�metro nome do Tag
     */
	@Column(name = "NOME", length = 80, nullable = false)
    private String nome;

    /**
     * Construtor da classe de Tag
     */
    public Tag() {

        id = -1;
        nome = "";
    }

    /**
     * Construtor da classe de Tag que recebe o nome
     * @param nome
     */


    public Tag(String nome) {
        this.nome = nome;
    }

    /**
     * Construtor da classe de Tag que recebe o id e o nome
     * @param id
     * @param nome
     */
    public Tag(int id, String nome) {
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
