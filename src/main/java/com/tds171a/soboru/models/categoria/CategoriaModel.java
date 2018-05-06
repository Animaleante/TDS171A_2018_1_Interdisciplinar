package com.tds171a.soboru.models.categoria;

import java.util.List;

import com.tds171a.soboru.models.ModelBase;
import com.tds171a.soboru.vos.Categoria;

/**
 * Classe de Model do Categoria
 * @author Diogo
 *
 */
public class CategoriaModel extends ModelBase<Categoria> {

    /**
     * Construtor da classe CategoriaModel
     */
    public CategoriaModel() {
        dao = new CategoriaDAO();
    }
    
    /**
     * Lista todas as categorias que podem
     * ser selecionadas pelo usuário.
     * @return
     */
    public List<Categoria> listarSelecionaveis() {
    	return ((CategoriaDAO)dao).listarSelecionaveis();
    }
    
    /**
     * Lista todas as categorias que são grupos
     * (não selecionaveis)
     * @return
     */
	public List<Categoria> listarGrupos() {
		return ((CategoriaDAO)dao).listarGrupos();
	}
}
