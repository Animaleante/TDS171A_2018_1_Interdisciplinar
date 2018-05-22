/**
 * 
 */
package com.tds171a.soboru.persistence.medida;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.Medida;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class MedidaPersistence implements IDAO<Medida> {
	
	private MedidaDAO dao;
	
	public MedidaPersistence(Session session) {
		dao = new MedidaDAO(session);
	}

	@Override
	public boolean incluir(Medida model) {
		return dao.incluir(model);
	}

	@Override
	public List<Medida> listar() {
		return dao.listar();
	}

	@Override
	public boolean atualizar(Medida model) {
		return dao.atualizar(model);
	}

	@Override
	public boolean remover(Medida model) {
		return dao.remover(model);
	}

	@Override
	public Medida selecionar(int modelId) {
		return dao.selecionar(modelId);
	}

}
