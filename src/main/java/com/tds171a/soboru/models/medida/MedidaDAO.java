package com.tds171a.soboru.models.medida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tds171a.soboru.models.IDAO;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Medida;

/**
 * Classe de DAO da Medida
 * @author Diogo
 *
 */
public class MedidaDAO implements IDAO<Medida> {

	/**
	 * Par�metro com nome da tabela referente a esse DAO
	 */
	private String tableName = "medidas";

	/**
	 * M�todo para incluir uma nova Medida
	 * @param medida
	 * @return
	 */
	@Override
	public boolean incluir(Medida medida) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("insert into "+tableName+" values(medida_seq.NEXTVAL, ?,?)");
			sttm.setString(1, medida.getNome());
			sttm.setString(2, medida.getAbreviacao());

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
	 * M�todo para trazer uma lista de todos as Medida
	 * @return
	 */
	@Override
	public List<Medida> listar() {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("select * from "+tableName + " order by nome");

			ResultSet rs = sttm.executeQuery();

			List<Medida> list = new ArrayList<Medida>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String abreveacao = rs.getString("abreviacao");

				Medida m = new Medida(id, nome, abreveacao);

				list.add(m);
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
	 * M�todo para atualizar uma Medida j� registrado
	 * @param ingrediente
	 * @return
	 */
	@Override
	public boolean atualizar(Medida	medida) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("update "+tableName+" set nome = ?, abreviacao = ? where id = ?");
			sttm.setString(1, medida.getNome());
			sttm.setString(2, medida.getAbreviacao());
			sttm.setInt(3, medida.getId());

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
	 * M�todo para remover uma Medida
	 * @param medidaId
	 * @return
	 */
	@Override
	public boolean remover(int medidaId) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("delete from "+tableName+" where id = ?");
			sttm.setInt(1, medidaId);

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
	 * Seleciona uma medida e cria o objeto
	 */
	@Override
	public Medida selecionar(int voId) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("select * from "+tableName+" where id = ?");
			sttm.setInt(1, voId);

			ResultSet rs = sttm.executeQuery();

			Medida medida = null;
			while(rs.next()) {
				medida = new Medida();
				medida.setId(rs.getInt("id"));
				medida.setNome(rs.getString("nome"));
				medida.setAbreviacao(rs.getString("abreviacao"));
			}

			if (sttm != null)
				sttm.close();

			sttm = null;

			return medida;
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
