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
 * Classe de ValueObject do Categoria
 * @author Diogo
 *
 */

/**
 * Trandormando a model em entidade.
 * @author Luiz
 *
 */
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable, Cloneable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8522841498909464541L;

	/**
     * Parï¿½metro id do Categoria
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_categoria")
	@SequenceGenerator(name = "entity_sequence_generator_categoria", sequenceName = "categoria_seq")
	@Column(name = "id", nullable = false)
    private int id;

    /**
     * Parï¿½metro nome do Categoria
     */
	@Column(name = "nome", length = 80, nullable = false)
    private String nome;
    
	/**
	 * Informa que está selecionável
	 */
	@Column(name = "selecionavel", length = 1, nullable = false)
    private boolean selecionavel;
    
	/**
	 * Informa o id da Super
	 */
	@Column(name = "id_super_categoria", length = 11, nullable = true)
    private int idSuperCategoria;
    
	/**
	 * Cria a slug do endereço
	 */
	@Column(name = "slug", length = 80, nullable = false)
    private String slug;

    /**
     * Construtor da classe de Categoria
     */
    public Categoria() {
        id = -1;
        nome = "";
        selecionavel = true;
        idSuperCategoria = -1;
        slug = "";
    }

    /**
     * Construtor da classe de Categoria que recebe o id e o nome
     * @param id
     * @param nome
     */
    public Categoria(int id, String nome) {
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

	/**
	 * @return the selecionavel
	 */
	public boolean getSelecionavel() {
		return selecionavel;
	}

	/**
	 * @param selecionavel the selecionavel to set
	 */
	public void setSelecionavel(boolean selecionavel) {
		this.selecionavel = selecionavel;
	}

	/**
	 * @return the idSuperCategoria
	 */
	public int getIdSuperCategoria() {
		return idSuperCategoria;
	}

	/**
	 * @param idSuperCategoria the idSuperCategoria to set
	 */
	public void setIdSuperCategoria(int idSuperCategoria) {
		this.idSuperCategoria = idSuperCategoria;
	}

	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}
}
