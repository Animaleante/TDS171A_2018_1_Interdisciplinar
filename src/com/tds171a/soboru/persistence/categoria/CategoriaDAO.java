package com.tds171a.soboru.persistence.categoria;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.tds171a.soboru.models.Categoria;
import com.tds171a.soboru.persistence.IDAO;

public class CategoriaDAO implements IDAO<Categoria> {

	private Session session;

	public CategoriaDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Categoria model) {
		try {
			System.out.println("3 - Slug: " + model.getSlug());
			
			this.session.save(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Categoria> listar() {
		// TODO Auto-generated method stub
		return new ArrayList<Categoria>();
	}

	@Override
	public boolean atualizar(Categoria model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Categoria selecionar(int modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Lista todos as categorias selecionaveis
	 * 
	 * @return
	 */
	public List<Categoria> listarSelecionaveis() {
		// TODO Auto-generated method stub
		return new ArrayList<Categoria>();
	}

	/**
	 * Lista todos as categorias que não são selecionaveis.
	 * 
	 * @return
	 */
	public List<Categoria> listarGrupos() {
		// TODO Auto-generated method stub
		return new ArrayList<Categoria>();
	}
}
