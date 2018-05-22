/**
 * 
 */
package com.tds171a.soboru.persistence.comentario;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.Comentario;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class ComentarioPersistence implements IDAO<Comentario> {
	
	private ComentarioDAO dao;
	
	public ComentarioPersistence(Session session) {
		dao = new ComentarioDAO(session);
	}

	@Override
	public boolean incluir(Comentario model) {
		return dao.incluir(model);
	}

	@Override
	public List<Comentario> listar() {
		return dao.listar();
	}

	@Override
	public boolean atualizar(Comentario model) {
		return dao.atualizar(model);
	}

	@Override
	public boolean remover(Comentario model) {
		return dao.remover(model);
	}

	@Override
	public Comentario selecionar(int modelId) {
		return dao.selecionar(modelId);
	}

}
