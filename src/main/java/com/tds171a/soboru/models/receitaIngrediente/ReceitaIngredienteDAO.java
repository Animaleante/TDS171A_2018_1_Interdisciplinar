/**
 *
 */
package com.tds171a.soboru.models.receitaIngrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tds171a.soboru.models.IDAO;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Ingrediente;
import com.tds171a.soboru.vos.Medida;
import com.tds171a.soboru.vos.ReceitaIngrediente;

/**
 * @author Diogo
 *
 */
public class ReceitaIngredienteDAO implements IDAO<ReceitaIngrediente> {

    /**
     * Par�metro com nome da tabela referente a esse DAO
     */
    private String tableName = "receitas_ingredientes";

	@Override
	public boolean incluir(ReceitaIngrediente vo) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("insert into "+tableName+" values(receita_ingrediente_seq.NEXTVAL, ?, ?, ?, ?, ?)");
            sttm.setInt(1, vo.getId_receita());
            sttm.setInt(2, vo.getId_ingrediente());
            sttm.setInt(3, vo.getId_medida());
            sttm.setString(4, vo.getSub_sessao());
            sttm.setDouble(5, vo.getQty());

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
	 * M�todo que lista todos os ingredientes
	 * linkados a uma receita
	 */
	@Override
	public List<ReceitaIngrediente> listar() {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("select * from "+tableName);

            ResultSet rs = sttm.executeQuery();

            List<ReceitaIngrediente> list = new ArrayList<ReceitaIngrediente>();
            ReceitaIngrediente receitaIngrediente;
            while(rs.next()) {
            	receitaIngrediente = new ReceitaIngrediente();
            	receitaIngrediente.setId(rs.getInt("id"));
            	receitaIngrediente.setId_receita(rs.getInt("id_receita"));
            	receitaIngrediente.setId_ingrediente(rs.getInt("id_ingrediente"));
            	receitaIngrediente.setId_medida(rs.getInt("id_medida"));
            	receitaIngrediente.setSub_sessao(rs.getString("sub_sessao"));
            	receitaIngrediente.setQty(rs.getDouble("qty"));

                list.add(receitaIngrediente);
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
	 * Atualiza dados do vinculo do
	 * ingredeinte com a receita.
	 */
	@Override
	public boolean atualizar(ReceitaIngrediente vo) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("update "+tableName+" set id_receita = ?, id_ingrediente = ?, id_medida = ?, sub_sessao = ?, qty = ? where id = ?");
            sttm.setInt(1, vo.getId_receita());
            sttm.setInt(2, vo.getId_ingrediente());
            sttm.setInt(3, vo.getId_medida());
            sttm.setString(4, vo.getSub_sessao());
            sttm.setDouble(5, vo.getQty());
            sttm.setInt(6, vo.getId());

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
	 * Remove o v�nculo de um ingrediente
	 * com uma receita.
	 */
	@Override
	public boolean remover(int voId) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("delete from "+tableName+" where id = ?");
            sttm.setInt(1, voId);

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
	 * Seleciona os v�nculos e apresenta retorna a aplica��o.
	 */
	@Override
	public ReceitaIngrediente selecionar(int voId) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("select * from "+tableName+" where id = ?");
            sttm.setInt(1, voId);

            ResultSet rs = sttm.executeQuery();

            ReceitaIngrediente receitaIngrediente = null;
            while(rs.next()) {
            	receitaIngrediente = new ReceitaIngrediente();
            	receitaIngrediente.setId(rs.getInt("id"));
            	receitaIngrediente.setId_receita(rs.getInt("id_receita"));
            	receitaIngrediente.setId_ingrediente(rs.getInt("id_ingrediente"));
            	receitaIngrediente.setId_medida(rs.getInt("id_medida"));
            	receitaIngrediente.setSub_sessao(rs.getString("sub_sessao"));
            	receitaIngrediente.setQty(rs.getDouble("qty"));
            }

            if (sttm != null)
                sttm.close();

            sttm = null;

            return receitaIngrediente;
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
	 * Tr�s uma lista com os v�nculos efetuando a
	 * pesquisa por um id de receita.
	 * @param receitaId
	 * @return
	 */
	public List<ReceitaIngrediente> selecionarPorReceita(int receitaId) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement(
            		"select ri.id, ri.id_receita, ri.id_ingrediente, ri.id_medida, ri.sub_sessao, ri.qty, m.id medida_id, m.nome medida_nome, m.abreviacao, i.id ingrediente_id, i.nome ingrediente_nome from "+tableName+" ri "
            				+ "inner join medidas m on m.id = ri.id_medida "
            				+ "inner join ingredientes i on i.id = ri.id_ingrediente "
            				+ "where id_receita = ?");
            sttm.setInt(1, receitaId);

            ResultSet rs = sttm.executeQuery();

            List<ReceitaIngrediente> list = new ArrayList<ReceitaIngrediente>();
            ReceitaIngrediente receitaIngrediente;
            while(rs.next()) {
            	receitaIngrediente = new ReceitaIngrediente();
            	receitaIngrediente.setId(rs.getInt("id"));
            	receitaIngrediente.setId_receita(rs.getInt("id_receita"));
            	receitaIngrediente.setId_ingrediente(rs.getInt("id_ingrediente"));
            	receitaIngrediente.setId_medida(rs.getInt("id_medida"));
            	receitaIngrediente.setSub_sessao(rs.getString("sub_sessao"));
            	receitaIngrediente.setQty(rs.getDouble("qty"));

            	receitaIngrediente.setIngrediente(new Ingrediente());
            	receitaIngrediente.getIngrediente().setId(rs.getInt("ingrediente_id"));
            	receitaIngrediente.getIngrediente().setNome(rs.getString("ingrediente_nome"));

            	receitaIngrediente.setMedida(new Medida());
            	receitaIngrediente.getMedida().setId(rs.getInt("medida_id"));
            	receitaIngrediente.getMedida().setNome(rs.getString("medida_nome"));
            	receitaIngrediente.getMedida().setAbreviacao(rs.getString("abreviacao"));

                list.add(receitaIngrediente);
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
	 * M�todo que inclui a lista de ingredientes cadastrados
	 * na receita.
	 * @param receitaId
	 * @param lista
	 */
	public void incluirLista(int receitaId, List<ReceitaIngrediente> lista) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("delete from receitas_ingredientes where id_receita = ?");
			sttm.setInt(1, receitaId);

			sttm.executeUpdate();

			if (sttm != null)
				sttm.close();

			for(ReceitaIngrediente ri : lista) {
				sttm = connection.prepareStatement("insert into receitas_ingredientes values (receita_ingrediente_seq.NEXTVAL, ?, ?, ?, ?, ?)");
				sttm.setInt(1, receitaId);
				sttm.setInt(2, ri.getId_ingrediente());
				sttm.setInt(3, ri.getId_medida());
				sttm.setString(4, ri.getSub_sessao());
				sttm.setDouble(5, ri.getQty());

				int rowsAffected = sttm.executeUpdate();

				if (sttm != null)
					sttm.close();

				if(rowsAffected == 0) {
					throw new Exception("N�o foi possivel cadastras todos os relacionamentos com essa receita.");
				}
			}

			sttm = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
