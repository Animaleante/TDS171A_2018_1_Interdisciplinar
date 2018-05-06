package com.tds171a.soboru.models.ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tds171a.soboru.models.IDAO;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Ingrediente;

/**
 * Classe de DAO do Ingrediente
 * @author Diogo
 *
 */
public class IngredienteDAO implements IDAO<Ingrediente> {

	/**
	 * Parametro com nome da tabela referente a esse DAO
	 */
	private String tableName = "ingredientes";

	/**
	 * Metodo para incluir um novo Ingrediente
	 * @param ingrediente
	 * @return
	 */
	@Override
	public boolean incluir(Ingrediente ingrediente) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("insert into "+tableName+" (id, nome) values(ingrediente_seq.NEXTVAL, ?)");
			sttm.setString(1, ingrediente.getNome());

			int rowsAffected = sttm.executeUpdate();

			if (sttm != null)
				sttm.close();

			sttm = null;

			return rowsAffected > 0;
		} /*catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			// TODO - Warn user that name already exists
		}*/ catch (SQLException e) {
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
	 * Metodo para trazer uma lista de todos os Ingredientes
	 * @return
	 */
	@Override
	public List<Ingrediente> listar() {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("select * from "+tableName+" order by nome asc");

			ResultSet rs = sttm.executeQuery();

			List<Ingrediente> list = new ArrayList<Ingrediente>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");

				Ingrediente i = new Ingrediente(id, nome);

				list.add(i);
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
	 * Metodo para atualizar um Ingrediente ja registrado
	 * @param ingrediente
	 * @return
	 */
	@Override
	public boolean atualizar(Ingrediente ingrediente) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("update "+tableName+" set nome = ? where id = ?");
			sttm.setString(1, ingrediente.getNome());
			sttm.setInt(2, ingrediente.getId());

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
	 * Metodo para remover um Ingrediente
	 * @param ingredienteId
	 * @return
	 */
	@Override
	public boolean remover(int ingredienteId) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("delete from "+tableName+" where id = ?");
			sttm.setInt(1, ingredienteId);

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
	 * Seleciona um ingrediente pelo ID e monta
	 * o objeto
	 */
	@Override
	public Ingrediente selecionar(int voId) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("select * from "+tableName+" where id = ?");
			sttm.setInt(1, voId);

			ResultSet rs = sttm.executeQuery();

			Ingrediente ingrediente = null;
			while(rs.next()) {
				ingrediente = new Ingrediente();
				ingrediente.setId(rs.getInt("id"));
				ingrediente.setNome(rs.getString("nome"));
			}

			if (sttm != null)
				sttm.close();

			sttm = null;

			return ingrediente;
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
