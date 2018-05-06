/**
 * 
 */
package com.tds171a.soboru.models.pontuacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tds171a.soboru.models.IDAO;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Pontuacao;
import com.tds171a.soboru.vos.Receita;

/**
 * @author Diogo
 *
 */
public class PontuacaoDAO implements IDAO<Pontuacao> {
	/**
	 * Variável com o nome da tabela.
	 */
	private String tableName = "pontuacoes";
	
	/**
	 * Não implementado para não haver alterações externas.
	 */
	@Override
	public boolean incluir(Pontuacao vo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Lista todas as pontuações existentes.
	 */
	@Override
	public List<Pontuacao> listar() {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement(
					"select p.id_usuario, p.id_receita, p.qty, u.nome nome_usuario, r.nome nome_receita from "+tableName+" p inner join usuarios u on p.id_usuario = u.id inner join receitas r on p.id_receita = r.id");

			ResultSet rs = sttm.executeQuery();

			List<Pontuacao> list = new ArrayList<Pontuacao>();
			Pontuacao pontuacao = null;
			while(rs.next()) {
				pontuacao = new Pontuacao();

				pontuacao.setReceitaId(rs.getInt("id_receita"));
				pontuacao.setUsuarioId(rs.getInt("id_usuario"));
				pontuacao.setQty(rs.getDouble("qty"));
				pontuacao.setReceitaNome(rs.getString("nome_receita"));
				pontuacao.setUsuarioNome(rs.getString("nome_usuario"));
				
				list.add(pontuacao);
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

	/**
	 * Não implementado para não haver alterações externas.
	 */
	@Override
	public boolean atualizar(Pontuacao vo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Não implementado para não haver alterações externas.
	 */
	@Override
	public boolean remover(int voId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Remove a pontuação da receita.
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
	 * Não implementado para não haver alterações externas.
	 */
	@Override
	public Pontuacao selecionar(int voId) {
		return null;
	}

}
