/**
 * 
 */
package com.tds171a.soboru.persistence.receitaingrediente;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.tds171a.soboru.models.ReceitaIngrediente;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author Diogo
 *
 */
public class ReceitaIngredienteDAO implements Serializable, IDAO<ReceitaIngrediente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4438326902866394274L;
	
	private Session session;

	public ReceitaIngredienteDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(ReceitaIngrediente model) {
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
	public List<ReceitaIngrediente> listar() {
		return this.session.createCriteria(ReceitaIngrediente.class).list();
	}

	@Override
	public boolean atualizar(ReceitaIngrediente model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(ReceitaIngrediente model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ReceitaIngrediente selecionar(int modelId) {
		return (ReceitaIngrediente) this.session.get(ReceitaIngrediente.class, modelId);
	}

}
