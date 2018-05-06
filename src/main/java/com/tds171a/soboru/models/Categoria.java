package com.tds171a.soboru.models;

/**
 * Classe de ValueObject do Categoria
 * @author Diogo
 *
 */
public class Categoria {

    /**
     * Par�metro id do Categoria
     */
    private int id;

    /**
     * Par�metro nome do Categoria
     */
    private String nome;
    
    private boolean selecionavel;
    
    private int idSuperCategoria;
    
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
