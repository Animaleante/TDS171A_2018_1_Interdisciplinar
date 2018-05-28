package com.tds171a.soboru.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tds171a.soboru.utils.Utils;

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
	private static final long serialVersionUID = 3247170678572830838L;

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
    private Integer idSuperCategoria;
    
	/**
	 * Cria a slug do endereço
	 */
	@Column(name = "slug", length = 80, nullable = false)
    private String slug;
	
	/**
	 * 
	 */
	@OneToMany(mappedBy="categoria")
	private Set<Receita> receitas;

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
        
        this.setSlug(Utils.toSlug(this.nome));
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
	public Integer getIdSuperCategoria() {
		return idSuperCategoria;
	}

	/**
	 * @param idSuperCategoria the idSuperCategoria to set
	 */
	public void setIdSuperCategoria(Integer idSuperCategoria) {
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

	public Set<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(Set<Receita> receitas) {
		this.receitas = receitas;
	}
}
