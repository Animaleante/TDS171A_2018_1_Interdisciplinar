package com.tds171a.soboru.models.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tds171a.soboru.models.IDAO;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Categoria;

/**
 * Classe de DAO do Categoria
 * @author Diogo
 *
 */
public class CategoriaDAO implements IDAO<Categoria> {

    /**
     * Parï¿½metro com nome da tabela referente a esse DAO
     */
    private String tableName = "categorias";

    /**
     * Metodo para incluir um novo Categoria
     * @param categoria
     * @return
     */
    @Override
    public boolean incluir(Categoria categoria) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("insert into "+tableName+" values(categoria_seq.NEXTVAL, ?, ?, ?, ?)");
            sttm.setString(1, categoria.getNome());
            sttm.setInt(2,  categoria.getIdSuperCategoria());
            sttm.setBoolean(3, categoria.getSelecionavel());
            sttm.setString(4, categoria.getSlug());

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
     * Metodo para trazer uma lista de todos os Categorias
     * @return
     */
    @Override
    public List<Categoria> listar() {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("select * from "+tableName);

            ResultSet rs = sttm.executeQuery();

            List<Categoria> list = new ArrayList<Categoria>();
            Categoria categoria;
            while(rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setIdSuperCategoria(rs.getInt("id_super_categoria"));
                categoria.setSelecionavel(rs.getBoolean("selecionavel"));
                categoria.setSlug(rs.getString("slug"));

                list.add(categoria);
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
     * Metodo para atualizar um Categoria jï¿½ registrado
     * @param categoria
     * @return
     */
    @Override
    public boolean atualizar(Categoria categoria) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("update "+tableName+" set nome = ?, id_super_categoria = ?, selecionavel = ?, slug = ? where id = ?");
            sttm.setString(1, categoria.getNome());
            sttm.setInt(2,  categoria.getIdSuperCategoria());
            sttm.setBoolean(3, categoria.getSelecionavel());
            sttm.setString(4, categoria.getSlug());
            sttm.setInt(5, categoria.getId());

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
     * Metodo para remover um Categoria
     * @param categoriaId
     * @return
     */
    @Override
    public boolean remover(int categoriaId) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttmCheck = connection.prepareStatement("select * from "+tableName+" where id_super_categoria = ?");
            sttmCheck.setInt(1, categoriaId);

            ResultSet rs = sttmCheck.executeQuery();
            if(rs.next()) {
                if (sttmCheck != null)
                	sttmCheck.close();

                sttmCheck = null;
                
            	return false;
            }
            
            if (sttmCheck != null)
            	sttmCheck.close();

            sttmCheck = null;

            PreparedStatement sttm = connection.prepareStatement("delete from "+tableName+" where id = ?");
            sttm.setInt(1, categoriaId);

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
     * Monta um objeto de categoria
     * pegando os dados do banco.
     */
	@Override
	public Categoria selecionar(int voId) {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("select * from "+tableName+" where id = ?");
            sttm.setInt(1, voId);

            ResultSet rs = sttm.executeQuery();

            Categoria categoria = null;
            while(rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setIdSuperCategoria(rs.getInt("id_super_categoria"));
                categoria.setSelecionavel(rs.getBoolean("selecionavel"));
                categoria.setSlug(rs.getString("slug"));
            }

            if (sttm != null)
                sttm.close();

            sttm = null;

            return categoria;
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
	 * Lista todos as categorias selecionaveis
	 * @return
	 */
	public List<Categoria> listarSelecionaveis() {
		Connection connection = null;
		try {
			connection = Utils.createConnection();
			
			PreparedStatement sttm = connection.prepareStatement("select * from "+tableName+" where selecionavel = 1");
			
			ResultSet rs = sttm.executeQuery();
			
			List<Categoria> list = new ArrayList<Categoria>();
			Categoria categoria;
			while(rs.next()) {
				categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setNome(rs.getString("nome"));
				categoria.setIdSuperCategoria(rs.getInt("id_super_categoria"));
				categoria.setSelecionavel(rs.getBoolean("selecionavel"));
				categoria.setSlug(rs.getString("slug"));
				
				list.add(categoria);
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
	 * Lista todos as categorias que não
	 * são selecionaveis.
	 * @return
	 */
	public List<Categoria> listarGrupos() {
        Connection connection = null;
        try {
            connection = Utils.createConnection();

            PreparedStatement sttm = connection.prepareStatement("select * from "+tableName+" where selecionavel = 0");

            ResultSet rs = sttm.executeQuery();

            List<Categoria> list = new ArrayList<Categoria>();
            Categoria categoria;
            while(rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setIdSuperCategoria(rs.getInt("id_super_categoria"));
                categoria.setSelecionavel(rs.getBoolean("selecionavel"));
                categoria.setSlug(rs.getString("slug"));

                list.add(categoria);
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
