/**
 * 
 */
package com.tds171a.soboru.persistence.receita;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class ReceitaDAO implements IDAO<Receita>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -510359741335961663L;
	
	private Session session;

	public ReceitaDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Receita model) {
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
	public List<Receita> listar() {
		return this.session.createCriteria(Receita.class).add(Restrictions.eq("aprovado", 1)).addOrder(Order.asc("nome")).list();
	}

	@Override
	public boolean atualizar(Receita model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(Receita model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Receita selecionar(int modelId) {
		return (Receita) this.session.get(Receita.class, modelId);
	}

}
