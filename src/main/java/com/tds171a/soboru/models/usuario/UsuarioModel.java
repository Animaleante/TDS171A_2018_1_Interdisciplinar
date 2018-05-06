/**
 * 
 */
package com.tds171a.soboru.models.usuario;

import java.util.List;

import com.tds171a.soboru.models.ModelBase;
import com.tds171a.soboru.vos.Usuario;

/**
 * Model de usuários
 * @author luiz.palte
 *
 */
public class UsuarioModel extends ModelBase<Usuario> {

    /**
     * Construtor da classe UsuarioModel
     * iniciando a DAO.
     */
    public UsuarioModel() {
        dao = new UsuarioDAO();
    }

    /**
     * Método trás um boolean mostrando se o cliente
     * está logado.
     * @param email
     * @param senha
     * @return
     */
	public Usuario loginUsuario(String email, String senha) {
		return ((UsuarioDAO)dao).loginUsuario(email, senha);
	}

	/**
	 * Trás uma lista de usuários que favoritaram 
	 * determinada receita.
	 * @param receitaId
	 * @return
	 */
	public List<Usuario> selecionarUsuariosQueFavoritaram(int receitaId) {
		return ((UsuarioDAO) dao).selecionarUsuariosQueFavoritaram(receitaId);
	}

	/**
	 * Trás a informação se o usuário
	 * reportou determinada receita.
	 * @param usuarioId
	 * @param receitaId
	 * @return
	 */
	public boolean reportou(int usuarioId, int receitaId) {
		return ((UsuarioDAO) dao).reportou(usuarioId, receitaId);
	}

	/**
	 * Verifica que o usuário pontuou
	 * determinada receita.
	 * @param usuarioId
	 * @param receitaId
	 * @return
	 */
	public boolean pontuou(int usuarioId, int receitaId) {
		return ((UsuarioDAO) dao).pontuou(usuarioId, receitaId);
	}
}
