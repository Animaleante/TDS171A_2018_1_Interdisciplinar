/**
 * 
 */
package com.tds171a.soboru.persistence.ingrediente;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.tds171a.soboru.models.Ingrediente;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class IngredienteDAO implements IDAO<Ingrediente>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5352874532730143636L;
	
	private Session session;

	public IngredienteDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Ingrediente model) {
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
	public List<Ingrediente> listar() {
		return this.session.createCriteria(Ingrediente.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public boolean atualizar(Ingrediente model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(Ingrediente model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Ingrediente selecionar(int modelId) {
		return (Ingrediente) this.session.get(Ingrediente.class, modelId);
	}

}
