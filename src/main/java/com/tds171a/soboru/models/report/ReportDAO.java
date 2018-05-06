/**
 *
 */
package com.tds171a.soboru.models.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tds171a.soboru.models.IDAO;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Report;

/**
 * Classe DAO que irá tratar da conexão
 * com o banco para os reports
 * @author luiz.palte
 *
 */
public class ReportDAO implements IDAO<Report> {

    /**
     * Parï¿½metro com nome da tabela referente a esse DAO
     */
    private String tableName = "reports";

    /**
     * Método que inclui no banco o report
     * do cliente.
     */
	@Override
	public boolean incluir(Report vo) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("insert into "+tableName+" values(?, ?)");
            sttm.setInt(1, vo.getUsuarioId());
            sttm.setInt(2, vo.getReceitaId());

            int rowsAffected = sttm.executeUpdate();

            if (sttm != null)
                sttm.close();

            sttm = null;

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return false;
	}

	/**
	 * Método que lista todos os reports
	 * existentes
	 */
	@Override
	public List<Report> listar() {
	    Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement(
                            "select r.id_usuario, r.id_receita, u.nome nome_usuario, rec.nome nome_receita from "+tableName+" r inner join usuarios u on r.id_usuario = u.id inner join receitas rec on r.id_receita = rec.id");

            ResultSet rs = sttm.executeQuery();

            List<Report> list = new ArrayList<Report>();
            Report report;
            while(rs.next()) {

                report = new Report();
                report.setReceitaId(rs.getInt("id_usuario"));
                report.setUsuarioId(rs.getInt("id_receita"));
                report.setUsuarioNome(rs.getString("nome_usuario"));
                report.setReceitaNome(rs.getString("nome_receita"));

                list.add(report);
            }

            if (sttm != null)
                sttm.close();

            sttm = null;

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return null;
	}

	@Override
	public boolean atualizar(Report vo) {
		return false;
	}

	@Override
	public boolean remover(int voId) {
	    return false;
	}

	/**
	 * Método que remove o report de uma receita.
	 * @param receitaId
	 * @param usuarioId
	 * @return
	 */
	public boolean remover(int receitaId, int usuarioId) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("delete from "+tableName+" where id_receita = ? and id_usuario = ?");
            sttm.setInt(1, receitaId);
            sttm.setInt(2, usuarioId);

            int rowsAffected = sttm.executeUpdate();

            if (sttm != null)
                sttm.close();

            sttm = null;

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return false;
	}

	/**
	 * Método não implantado para evitar
	 * alterações indesejadas.
	 */
	@Override
	public Report selecionar(int voId) {
		return null;
	}

}
