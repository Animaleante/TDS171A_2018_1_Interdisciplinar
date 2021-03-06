/**
 * 
 */
package com.tds171a.soboru.persistence.medida;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.tds171a.soboru.models.Medida;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class MedidaDAO implements IDAO<Medida>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7588522592030777192L;
	
	private Session session;

	public MedidaDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Medida model) {
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
	public List<Medida> listar() {
		return this.session.createCriteria(Medida.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public boolean atualizar(Medida model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(Medida model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Medida selecionar(int modelId) {
		return (Medida) this.session.get(Medida.class, modelId);
	}

}
