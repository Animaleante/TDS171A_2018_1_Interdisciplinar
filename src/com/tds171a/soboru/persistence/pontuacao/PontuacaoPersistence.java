/**
 * 
 */
package com.tds171a.soboru.persistence.pontuacao;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.Pontuacao;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class PontuacaoPersistence implements IDAO<Pontuacao> {
	
	private PontuacaoDAO dao;
	
	public PontuacaoPersistence(Session session) {
		dao = new PontuacaoDAO(session);
	}

	@Override
	public boolean incluir(Pontuacao model) {
		return dao.incluir(model);
	}

	@Override
	public List<Pontuacao> listar() {
		return dao.listar();
	}

	@Override
	public boolean atualizar(Pontuacao model) {
		return dao.atualizar(model);
	}

	@Override
	public boolean remover(Pontuacao model) {
		return dao.remover(model);
	}

	@Override
	public Pontuacao selecionar(int modelId) {
		return dao.selecionar(modelId);
	}

}
