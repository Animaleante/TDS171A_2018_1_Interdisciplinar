package com.tds171a.soboru.persistence.categoria;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.Categoria;
import com.tds171a.soboru.persistence.IDAO;

public class CategoriaPersistence implements IDAO<Categoria> {
	
	private IDAO<Categoria> dao;
	
	public CategoriaPersistence(Session session) {
		dao = new CategoriaDAO(session);
	}

	@Override
	public boolean incluir(Categoria model) {
		System.out.println("2 - Slug: " + model.getSlug());
		return ((CategoriaDAO) dao).incluir(model);
	}

	@Override
	public List<Categoria> listar() {
		return ((CategoriaDAO) dao).listar(); 
	}

	@Override
	public boolean atualizar(Categoria model) {
		return ((CategoriaDAO) dao).atualizar(model);
	}

	@Override
	public boolean remover(int modelId) {
		return ((CategoriaDAO) dao).remover(modelId);
	}

	@Override
	public Categoria selecionar(int modelId) {
		return ((CategoriaDAO) dao).selecionar(modelId);
	}
    
    /**
     * Retorna uma lista de categorias selecionaveis
     * modelo por cast.
     * @return
     */
    public List<Categoria> listarSelecionaveis() {
    	return ((CategoriaDAO) dao).listarSelecionaveis();
    }

    /**
     * Retorna uma lista de grupo de categoria
     * modelo por cast.
     * @return
     */
	public List<Categoria> listarGrupos() {
		return ((CategoriaDAO) dao).listarGrupos();
	}

}
