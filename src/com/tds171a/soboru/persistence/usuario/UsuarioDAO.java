/**
 * 
 */
package com.tds171a.soboru.persistence.usuario;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class UsuarioDAO implements IDAO<Usuario>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8346202388524455720L;
	
	private Session session;

	public UsuarioDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Usuario model) {
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
	public List<Usuario> listar() {
		return this.session.createCriteria(Usuario.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public boolean atualizar(Usuario model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(Usuario model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Usuario selecionar(int modelId) {
		return (Usuario) this.session.get(Usuario.class, modelId);
	}

}
