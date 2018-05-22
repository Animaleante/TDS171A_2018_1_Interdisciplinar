/**
 * 
 */
package com.tds171a.soboru.persistence.role;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.tds171a.soboru.models.Role;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class RoleDAO implements IDAO<Role>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1012179779613105822L;
	
	private Session session;

	public RoleDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Role model) {
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
	public List<Role> listar() {
		return this.session.createCriteria(Role.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public boolean atualizar(Role model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(Role model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Role selecionar(int modelId) {
		return (Role) this.session.get(Role.class, modelId);
	}

}
