/**
 * 
 */
package test.tds171a.soboru.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tds171a.soboru.models.Role;
import com.tds171a.soboru.persistence.role.RoleDAO;

import test.tds171a.soboru.utils.Utils;

/**
 * @author Diogo
 *
 */
class RolePersistenceTest {

	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");

		Session session = Utils.getSession();
		session.beginTransaction();

		session.createSQLQuery("drop sequence role_seq").executeUpdate();
		
		session.createSQLQuery("create sequence role_seq start with 3 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("Before Each Done");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After Each");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		session.createSQLQuery("drop sequence role_seq").executeUpdate();
		
		session.createSQLQuery("create sequence role_seq start with 3 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("After Each Done");
	}
	
	@Test
	void testIncluirRole() {
		Role role = new Role();
		
		role.setNome("Role teste");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		RoleDAO roleDAO = new RoleDAO(session);
		
		assertTrue(roleDAO.incluir(role));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testAtualizarRole() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		RoleDAO roleDAO = new RoleDAO(session);
		
		Role role = roleDAO.selecionar(1);
		
		assertNotNull(role);
		
		role.setNome("Nome Teste");
		
		assertTrue(roleDAO.atualizar(role));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testExcluirRole() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		RoleDAO roleDAO = new RoleDAO(session);
		
		Role role = roleDAO.selecionar(1);
		
		assertNotNull(role);
		
		assertTrue(roleDAO.remover(role));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testListarRole() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		RoleDAO roleDAO = new RoleDAO(session);
		
		List<Role> roles = roleDAO.listar();
		
		assertNotNull(roles);
		
		assertTrue(roles.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testSelecionarRole() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		RoleDAO roleDAO = new RoleDAO(session);
		
		Role role = roleDAO.selecionar(1);
		
		assertNotNull(role);
		
		session.getTransaction().rollback();
		session.close();
	}
}
