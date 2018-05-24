/**
 * 
 */
package com.tds171a.soboru.persistence.receitaingrediente;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.ReceitaIngrediente;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author Diogo
 *
 */
public class ReceitaIngredientePersistence implements IDAO<ReceitaIngrediente> {
	
	ReceitaIngredienteDAO dao;
	
	public ReceitaIngredientePersistence(Session session) {
		dao = new ReceitaIngredienteDAO(session);
	}

	@Override
	public boolean incluir(ReceitaIngrediente model) {
		return dao.incluir(model);
	}

	@Override
	public List<ReceitaIngrediente> listar() {
		return dao.listar();
	}

	@Override
	public boolean atualizar(ReceitaIngrediente model) {
		return dao.atualizar(model);
	}

	@Override
	public boolean remover(ReceitaIngrediente model) {
		return dao.remover(model);
	}

	@Override
	public ReceitaIngrediente selecionar(int modelId) {
		return dao.selecionar(modelId);
	}

}
