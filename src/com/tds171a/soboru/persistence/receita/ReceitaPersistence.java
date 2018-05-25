/**
 * 
 */
package com.tds171a.soboru.persistence.receita;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.Ingrediente;
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
public class ReceitaPersistence implements IDAO<Receita> {
	
	private ReceitaDAO dao;
	
	public ReceitaPersistence(Session session) {
		dao = new ReceitaDAO(session);
	}

	@Override
	public boolean incluir(Receita model) {
		return dao.incluir(model);
	}

	@Override
	public List<Receita> listar() {
		return dao.listar();
	}

	@Override
	public boolean atualizar(Receita model) {
		return dao.atualizar(model);
	}

	@Override
	public boolean remover(Receita model) {
		return dao.remover(model);
	}

	@Override
	public Receita selecionar(int modelId) {
		return dao.selecionar(modelId);
	}

	public List<Receita> selecionarPorNomeEIngredientes(String termoBusca, List<Ingrediente> lista) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Receita> selecionarPorNome(String termoBusca) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Receita> selecionarPorIngredientes(List<Ingrediente> lista) {
		// TODO Auto-generated method stub
		return null;
	}

}
