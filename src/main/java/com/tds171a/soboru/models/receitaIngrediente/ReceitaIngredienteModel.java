package com.tds171a.soboru.models.receitaIngrediente;

import java.util.List;

import com.tds171a.soboru.models.ModelBase;
import com.tds171a.soboru.vos.ReceitaIngrediente;

/**
 * Modelo que irá tratar com os ingredientes anexados
 * na receita.
 * @author luiz.palte
 *
 */
public class ReceitaIngredienteModel extends ModelBase<ReceitaIngrediente> {
	public ReceitaIngredienteModel() {
		dao = new ReceitaIngredienteDAO();
	}

	/**
	 * Lista de ingredientes anexados na receita
	 * @param receitaId
	 * @return
	 */
	public List<ReceitaIngrediente> selecionarPorReceita(int receitaId) {
		return ((ReceitaIngredienteDAO) dao).selecionarPorReceita(receitaId);
	}

	/**
	 * Método que salva a lista de ingredientes 
	 * registrados na tela de receitas.
	 * @param receitaId
	 * @param lista
	 */
	public void incluirLista(int receitaId, List<ReceitaIngrediente> lista) {
		((ReceitaIngredienteDAO) dao).incluirLista(receitaId, lista);
	}
}
