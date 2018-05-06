package com.tds171a.soboru.models;

/**
 * Classe de ValueObject do Tag
 * @author Diogo
 *
 */
public class Tag {

    /**
     * Par�metro id do Tag
     */
    private int id;

    /**
     * Par�metro nome do Tag
     */
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
