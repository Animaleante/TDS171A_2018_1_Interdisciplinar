/**
 * 
 */
package com.tds171a.soboru.persistence.ingrediente;

import java.util.List;

import org.hibernate.Session;

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
public class IngredientePersistence implements IDAO<Ingrediente> {
	
	private IngredienteDAO dao;
	
	public IngredientePersistence(Session session) {
		dao = new IngredienteDAO(session);
	}

	@Override
	public boolean incluir(Ingrediente model) {
		return dao.incluir(model);
	}

	@Override
	public List<Ingrediente> listar() {
		return dao.listar();
	}

	@Override
	public boolean atualizar(Ingrediente model) {
		return dao.atualizar(model);
	}

	@Override
	public boolean remover(Ingrediente model) {
		return dao.remover(model);
	}

	@Override
	public Ingrediente selecionar(int modelId) {
		return dao.selecionar(modelId);
	}

}
