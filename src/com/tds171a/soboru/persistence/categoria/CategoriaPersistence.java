package com.tds171a.soboru.persistence.categoria;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.Categoria;
import com.tds171a.soboru.persistence.IDAO;

public class CategoriaPersistence implements IDAO<Categoria> {
	
	private CategoriaDAO dao;
	
	public CategoriaPersistence(Session session) {
		dao = new CategoriaDAO(session);
	}

	@Override
	public boolean incluir(Categoria model) {
		return dao.incluir(model);
	}

	@Override
	public List<Categoria> listar() {
		return dao.listar(); 
	}

	@Override
	public boolean atualizar(Categoria model) {
		return dao.atualizar(model);
	}

	@Override
	public boolean remover(Categoria model) {
		return dao.remover(model);
	}

	@Override
	public Categoria selecionar(int modelId) {
		return dao.selecionar(modelId);
	}
    
    /**
     * Retorna uma lista de categorias selecionaveis
     * modelo por cast.
     * @return
     */
    public List<Categoria> listarSelecionaveis() {
    	return dao.listarSelecionaveis();
    }

    /**
     * Retorna uma lista de grupo de categoria
     * modelo por cast.
     * @return
     */
	public List<Categoria> listarGrupos() {
		return dao.listarGrupos();
	}

}
