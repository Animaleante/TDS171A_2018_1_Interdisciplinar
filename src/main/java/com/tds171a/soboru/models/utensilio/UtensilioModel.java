package com.tds171a.soboru.models.utensilio;

import java.util.List;

import com.tds171a.soboru.models.ModelBase;
import com.tds171a.soboru.vos.Utensilio;

/**
 * Classe de MOdel do Utensilio
 * @author Diogo
 *
 */
public class UtensilioModel extends ModelBase<Utensilio> {

	/**
	 * Construtor da classe de controller do Utensilio
	 */
	public UtensilioModel() {
		dao = new UtensilioDAO();
	}

	/**
	 * trás a lista de utensilios de uma 
	 * determinada receita.
	 * @param receitaId
	 * @return
	 */
	public List<Utensilio> selecionarPorReceita(int receitaId) {
		return ((UtensilioDAO) dao).selecionarPorReceita(receitaId);
	}
}
