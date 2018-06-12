/**
 * 
 */
package com.tds171a.soboru.persistence.usuario;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class UsuarioPersistence implements IDAO<Usuario> {

	private UsuarioDAO dao;
	
	public UsuarioPersistence(Session session) {
		dao = new UsuarioDAO(session);
	}

	@Override
	public boolean incluir(Usuario model) {
		return dao.incluir(model);
	}

	@Override
	public List<Usuario> listar() {
		return dao.listar();
	}

	@Override
	public boolean atualizar(Usuario model) {
		return dao.atualizar(model);
	}

	@Override
	public boolean remover(Usuario model) {
		return dao.remover(model);
	}

	@Override
	public Usuario selecionar(int modelId) {
		return dao.selecionar(modelId);
	}

	public Usuario loginUsuario(String email, String senha) {
		return dao.login(email, senha);
	}

	public Usuario selecionarPorEmail(String email) {
		return dao.selecionarPorEmail(email);
	}
}
