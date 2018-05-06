package com.tds171a.soboru.models.ingrediente;

import com.tds171a.soboru.models.ModelBase;
import com.tds171a.soboru.vos.Ingrediente;

/**
 * Classe de Model do Ingrediente
 * @author Diogo
 *
 */
public class IngredienteModel extends ModelBase<Ingrediente> {
	
	/**
	 * Construtor da classe IngredienteModel
	 */
	public IngredienteModel() {
		dao = new IngredienteDAO();
	}
}
