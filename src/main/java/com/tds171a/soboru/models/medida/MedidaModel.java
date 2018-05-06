package com.tds171a.soboru.models.medida;

import com.tds171a.soboru.models.ModelBase;
import com.tds171a.soboru.vos.Medida;

/**
 * Classe de Model da Medida
 * @author Diogo
 *
 */
public class MedidaModel extends ModelBase<Medida> {
	
	/**
	 * Construtor da classe MedidaModel
	 */
	public MedidaModel() {
		dao = new MedidaDAO();
	}
}
