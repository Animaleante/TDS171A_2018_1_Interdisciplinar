package com.tds171a.soboru.models.utensilio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tds171a.soboru.models.IDAO;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Utensilio;

/**
 * Classe de DAO do Utensilio
 * @author Diogo
 *
 */
public class UtensilioDAO implements IDAO<Utensilio> {

	/**
	 * Parï¿½metro com nome da tabela referente a esse DAO
	 */
	private String tableName = "utensilios";

	/**
	 * Mï¿½todo para incluir um novo Utensilio
	 * @param utensilio
	 * @return
	 */
	@Override
	public boolean incluir(Utensilio utensilio) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("insert into "+tableName+" values(utensilio_seq.NEXTVAL, ?)");
			sttm.setString(1, utensilio.getNome());

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
	 * Mï¿½todo para trazer uma lista de todos os Utensilios
	 * @return
	 */
	@Override
	public List<Utensilio> listar() {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("select * from "+tableName+" order by nome");

			ResultSet rs = sttm.executeQuery();

			List<Utensilio> list = new ArrayList<Utensilio>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");

				Utensilio u = new Utensilio(id, nome);

				list.add(u);
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
	 * Mï¿½todo para atualizar um Utensilio jï¿½ registrado
	 * @param utensilio
	 * @return
	 */
	@Override
	public boolean atualizar(Utensilio utensilio) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("update "+tableName+" set nome = ? where id = ?");
			sttm.setString(1, utensilio.getNome());
			sttm.setInt(2, utensilio.getId());

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
	 * Mï¿½todo para remover um Utensilio
	 * @param utensilioId
	 * @return
	 */
	@Override
	public boolean remover(int utensilioId) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("delete from "+tableName+" where id = ?");
			sttm.setInt(1, utensilioId);

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
	 * Trás o objeto do banco.
	 * @param voId
	 * @return
	 */
	@Override
	public Utensilio selecionar(int voId) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("select * from "+tableName+" where id = ?");
			sttm.setInt(1, voId);

			ResultSet rs = sttm.executeQuery();

			Utensilio utensilio = null;
			while(rs.next()) {
				utensilio = new Utensilio();
				utensilio.setId(rs.getInt("id"));
				utensilio.setNome(rs.getString("nome"));
			}

			if (sttm != null)
				sttm.close();

			sttm = null;

			return utensilio;
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
	 * Seleciona os utensilios de uma receita.
	 * @param receitaId
	 * @return
	 */
	public List<Utensilio> selecionarPorReceita(int receitaId) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            
            PreparedStatement sttm = connection.prepareStatement("select u.id, u.nome from "+tableName+" u inner join receitas_utensilios ru on ru.id_utensilio = u.id where ru.id_receita = ?");
            //PreparedStatement sttm = connection.prepareStatement("select u.id, u.nome from "+tableName+" u inner join receitas_utensilios ru on ru.id_receita = ?");
            sttm.setInt(1, receitaId);

            ResultSet rs = sttm.executeQuery();

            List<Utensilio> list = new ArrayList<Utensilio>();
            Utensilio utensilio;
            while(rs.next()) {
            	utensilio = new Utensilio();
            	utensilio.setId(rs.getInt("id"));
            	utensilio.setNome(rs.getString("nome"));
                list.add(utensilio);
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
