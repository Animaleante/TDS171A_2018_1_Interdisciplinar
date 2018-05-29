/**
 * 
 */
package com.tds171a.soboru.persistence.receita;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.Ingrediente;
import com.tds171a.soboru.models.Receita;
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
		return dao.selecionarPorNomeEIngredientes(termoBusca, lista);
	}

	public List<Receita> selecionarPorNome(String termoBusca) {
		return dao.selecionarPorNome(termoBusca);
	}

	public List<Receita> selecionarPorIngredientes(List<Ingrediente> lista) {
		return dao.selecionarPorIngredientes(lista);
	}

	public List<Receita> selecionarPorUsuario(int userId) {
		return dao.selecionarPorUsuario(userId);
	}

	public List<Receita> selecionarPorFavoritosDeUsuario(int userId) {
		return dao.selecionarPorFavoritosDeUsuario(userId);
	}

	public boolean receitaJaFoiReportada(Receita model, Usuario usuario) {
		return dao.receitaJaFoiReportada(model, usuario);
	}

	public boolean receitaJaFoiPontuada(Receita model, Usuario usuario) {
		return dao.receitaJaFoiPontuada(model, usuario);
	}

	public boolean receitaFavoritada(Receita model, Usuario usuario) {
		return dao.receitaFavoritada(model, usuario);
	}

}
