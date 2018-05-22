/**
 * 
 */
package com.tds171a.soboru.persistence.role;

import java.util.List;

import org.hibernate.Session;

import com.tds171a.soboru.models.Role;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class RolePersistance implements IDAO<Role> {
	
	private RoleDAO dao;
	
	public RolePersistance(Session session) {
		dao = new RoleDAO(session);
	}

	@Override
	public boolean incluir(Role model) {
		return dao.incluir(model);
	}

	@Override
	public List<Role> listar() {
		return dao.listar();
	}

	@Override
	public boolean atualizar(Role model) {
		return dao.atualizar(model);
	}

	@Override
	public boolean remover(Role model) {
		return dao.remover(model);
	}

	@Override
	public Role selecionar(int modelId) {
		return dao.selecionar(modelId);
	}
}
