/**
 * 
 */
package com.tds171a.soboru.persistence.comentario;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.tds171a.soboru.models.Comentario;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class ComentarioDAO implements IDAO<Comentario>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3864502298336539212L;
	
	private Session session;

	public ComentarioDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Comentario model) {
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
	public List<Comentario> listar() {
		return this.session.createCriteria(Comentario.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public boolean atualizar(Comentario model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(Comentario model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Comentario selecionar(int modelId) {
		return (Comentario) this.session.get(Comentario.class, modelId);
	}

}
