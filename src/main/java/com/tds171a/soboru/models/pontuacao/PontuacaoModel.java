/**
 * 
 */
package com.tds171a.soboru.models.pontuacao;

import com.tds171a.soboru.models.ModelBase;
import com.tds171a.soboru.vos.Pontuacao;

/**
 * @author Diogo
 *
 */
public class PontuacaoModel extends ModelBase<Pontuacao> {
	
	/**
	 * Construtor iniciando a DAO
	 */
	public PontuacaoModel() {
		dao = new PontuacaoDAO();
	}

	/**
	 * Remove a pontuação
	 * @param receitaId
	 * @param usuarioId
	 * @return
	 */
	public boolean remover(int receitaId, int usuarioId) {
        return ((PontuacaoDAO)dao).remover(receitaId, usuarioId);
    }

}
