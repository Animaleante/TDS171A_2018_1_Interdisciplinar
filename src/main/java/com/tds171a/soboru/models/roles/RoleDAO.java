/**
 * 
 */
package com.tds171a.soboru.models.roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tds171a.soboru.models.IDAO;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Role;

/**
 * @author Diogo
 *
 */
public class RoleDAO implements IDAO<Role> {

	/**
	 * Parametro com nome da tabela referente a esse DAO
	 */
	private String tableName = "roles";

	/**
	 * Metodo para incluir um novo perfil(Role)
	 * @param role
	 * @return
	 */
	@Override
	public boolean incluir(Role role) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("insert into "+tableName+" (id, nome, is_admin) values(role_seq.NEXTVAL, ?, ?)");
			sttm.setString(1, role.getNome());
			sttm.setBoolean(2, role.getIsAdmin());

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
	 * Metodo para trazer uma lista de todos os Roles
	 * @return
	 */
	@Override
	public List<Role> listar() {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("select * from "+tableName);

			ResultSet rs = sttm.executeQuery();

			List<Role> list = new ArrayList<Role>();
			Role role = null;
			while(rs.next()) {
				role = new Role();
				role.setId(rs.getInt("id"));
				role.setNome(rs.getString("nome"));
				role.setIsAdmin(rs.getBoolean("is_admin"));

				list.add(role);
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
	 * Metodo para atualizar um Role ja registrado
	 * @param role
	 * @return
	 */
	@Override
	public boolean atualizar(Role role) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("update "+tableName+" set nome = ?, is_admin = ? where id = ?");
			sttm.setString(1, role.getNome());
			sttm.setBoolean(2, role.getIsAdmin());
			sttm.setInt(2, role.getId());

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
	 * Metodo para remover um Role
	 * @param roleId
	 * @return
	 */
	@Override
	public boolean remover(int roleId) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("delete from "+tableName+" where id = ?");
			sttm.setInt(1, roleId);

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
	 * Método que trás as informações do id pesquisado para anexar
	 *  no cliente.
	 */
	@Override
	public Role selecionar(int voId) {
		Connection connection = null;
		try {
			connection = Utils.createConnection();

			PreparedStatement sttm = connection.prepareStatement("select * from "+tableName+" where id = ?");
			sttm.setInt(1, voId);

			ResultSet rs = sttm.executeQuery();

			Role role = null;
			while(rs.next()) {
				role = new Role();
				role.setId(rs.getInt("id"));
				role.setNome(rs.getString("nome"));
				role.setIsAdmin(rs.getBoolean("is_admin"));
			}

			if (sttm != null)
				sttm.close();

			sttm = null;

			return role;
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
