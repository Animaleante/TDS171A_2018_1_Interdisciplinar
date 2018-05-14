package com.tds171a.soboru.persistence.categoria;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tds171a.soboru.models.Categoria;
import com.tds171a.soboru.persistence.IDAO;

public class CategoriaDAO implements IDAO<Categoria>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6654754808265621136L;
	
	private Session session;

	public CategoriaDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Categoria model) {
		try {
			this.session.save(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listar() {
		return this.session.createCriteria(Categoria.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public boolean atualizar(Categoria model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(Categoria model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Categoria selecionar(int modelId) {
		return (Categoria) this.session.get(Categoria.class, modelId);
	}

	/**
	 * Lista todos as categorias selecionaveis
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Categoria> listarSelecionaveis() {
		return this.session.createCriteria(Categoria.class)
				.add(Restrictions.eq("selecionavel", true)).list();
	}

	/**
	 * Lista todos as categorias que não são selecionaveis.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Categoria> listarGrupos() {
		return this.session.createCriteria(Categoria.class)
				.add(Restrictions.eq("selecionavel", false)).list();
	}
}
