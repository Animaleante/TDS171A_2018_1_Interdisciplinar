/**
 * 
 */
package com.tds171a.soboru.persistence.pontuacao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.tds171a.soboru.models.Pontuacao;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class PontuacaoDAO implements IDAO<Pontuacao>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 761730344384066168L;
	
	private Session session;

	public PontuacaoDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Pontuacao model) {
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
	public List<Pontuacao> listar() {
		return this.session.createCriteria(Pontuacao.class).addOrder(Order.asc("receita.id")).list();
	}

	@Override
	public boolean atualizar(Pontuacao model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(Pontuacao model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Pontuacao selecionar(int modelId) {
		return (Pontuacao) this.session.get(Pontuacao.class, modelId);
	}

}
