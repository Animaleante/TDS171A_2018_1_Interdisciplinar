/**
 * 
 */
package com.tds171a.soboru.persistence.utensilio;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.Utensilio;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class UtensilioPersistence implements IDAO<Utensilio> {

	private UtensilioDAO dao;
	
	public UtensilioPersistence(Session session) {
		dao = new UtensilioDAO(session);
	}

	@Override
	public boolean incluir(Utensilio model) {
		return dao.incluir(model);
	}

	@Override
	public List<Utensilio> listar() {
		return dao.listar();
	}

	@Override
	public boolean atualizar(Utensilio model) {
		return dao.atualizar(model);
	}

	@Override
	public boolean remover(Utensilio model) {
		return dao.remover(model);
	}

	@Override
	public Utensilio selecionar(int modelId) {
		return dao.selecionar(modelId);
	}
}
