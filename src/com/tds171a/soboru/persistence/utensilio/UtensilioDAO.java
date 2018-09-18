package com.tds171a.soboru.persistence.utensilio;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.tds171a.soboru.models.Utensilio;
import com.tds171a.soboru.persistence.IDAO;

public class UtensilioDAO implements IDAO<Utensilio>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2791733917629321993L;
	
	private Session session;

	public UtensilioDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Utensilio model) {
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
	public List<Utensilio> listar() {
		return this.session.createCriteria(Utensilio.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public boolean atualizar(Utensilio model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(Utensilio model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Utensilio selecionar(int modelId) {
		return (Utensilio) this.session.get(Utensilio.class, modelId);
	}

}
