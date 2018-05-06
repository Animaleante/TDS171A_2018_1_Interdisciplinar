/**
 * 
 */
package com.tds171a.soboru.models.comentario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tds171a.soboru.models.IDAO;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Comentario;
import com.tds171a.soboru.vos.Pontuacao;
import com.tds171a.soboru.vos.Usuario;

/**
 * Dao de comentários.
 * @author luiz.palte
 *
 */
public class ComentarioDAO implements IDAO<Comentario> {
	
	private String tableName = "comentarios";

	/**
	 * Inclui um comentário passando
	 * o body e a receita.
	 */
	@Override
	public boolean incluir(Comentario vo) {
		
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement(
				"insert into "+tableName+" values(comentario_seq.NEXTVAL,?,?,?)");
			sttm.setInt(1, vo.getReceitaId());
			sttm.setInt(2, vo.getUsuarioId());
			sttm.setString(3, vo.getBody());

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
	 * Método que lista os comentários existentes para admin.
	 */
	@Override
	public List<Comentario> listar() {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement(
					"select c.id, c.id_usuario, c.id_receita, c.body, u.nome nome_usuario, r.nome nome_receita from "+tableName+" c "
							+ "inner join usuarios u on c.id_usuario = u.id inner join receitas r on c.id_receita = r.id");

			ResultSet rs = sttm.executeQuery();

			List<Comentario> list = new ArrayList<Comentario>();
			Comentario comentario = null;
			while(rs.next()) {
				comentario = new Comentario();

				comentario.setId(rs.getInt("id"));
				comentario.setReceitaId(rs.getInt("id_receita"));
				comentario.setUsuarioId(rs.getInt("id_usuario"));
				comentario.setBody(rs.getString("body"));
				comentario.setReceitaNome(rs.getString("nome_receita"));
				comentario.setUsuarioNome(rs.getString("nome_usuario"));
				
				list.add(comentario);
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
	 * Método não implementado para não ter
	 * alterações externas.
	 */
	@Override
	public boolean atualizar(Comentario vo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Método não implementado para não ter
	 * alterações externas.
	 */
	@Override
	public boolean remover(int voId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Remove um determinado comentário
	 * de uma receita.
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
	 * Método não implementado para não ter
	 * alterações externas.
	 */
	@Override
	public Comentario selecionar(int voId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Trás os comentários de uma determinada
	 * receita.
	 * @param receitaId
	 * @return
	 */
	public List<Comentario> selecionarPorReceita(int receitaId) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("select c.id, c.id_receita, c.id_usuario, c.body, u.nome from "+tableName+" c inner join usuarios u on c.id_usuario = u.id where c.id_receita = ? order by c.id");
//            PreparedStatement sttm = connection.prepareStatement("select c.id, c.id_receita, c.id_usuario, c.body, c.created_at, u.nome, u.avatar_path from "+tableName+" c inner join usuarios u on c.id_usuario = u.id where c.id_receita = ?");
            sttm.setInt(1, receitaId);
            
            ResultSet rs = sttm.executeQuery();

            List<Comentario> list = new ArrayList<Comentario>();
            Comentario comentario;
            while(rs.next()) {
            	comentario = new Comentario();
            	comentario.setId(rs.getInt("id"));
            	comentario.setReceitaId(rs.getInt("id_receita"));
            	comentario.setUsuarioId(rs.getInt("id_usuario"));
            	comentario.setBody(rs.getString("body"));
            	
            	comentario.setUsuario(new Usuario());
            	comentario.getUsuario().setId(rs.getInt("id_usuario"));
            	comentario.getUsuario().setNome(rs.getString("nome"));

                list.add(comentario);
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

}
