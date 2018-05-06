package com.tds171a.soboru.models.report;

import com.tds171a.soboru.models.ModelBase;
import com.tds171a.soboru.vos.Report;

/**
 * Classe que trabalha com os reports
 * do cliente.
 * @author luiz.palte
 *
 */
public class ReportModel extends ModelBase<Report> {

	/**
	 *Construtor que inicia a Dao
	 */
	public ReportModel() {
		dao = new ReportDAO();
	}

	/**
	 * Método que remove o report.
	 * @param receitaId
	 * @param usuarioId
	 * @return
	 */
	public boolean remover(int receitaId, int usuarioId) {
        return ((ReportDAO)dao).remover(receitaId, usuarioId);
    }
}
