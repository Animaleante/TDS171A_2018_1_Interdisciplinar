/**
 * 
 */
package test.tds171a.soboru.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tds171a.soboru.models.Role;
import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.role.RoleDAO;
import com.tds171a.soboru.persistence.usuario.UsuarioDAO;

import test.tds171a.soboru.utils.Utils;

/**
 * @author Diogo
 *
 */
class UsuarioPersistenceTest {

	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");

		Session session = Utils.getSession();
		session.beginTransaction();

		session.createSQLQuery("drop sequence usuario_seq").executeUpdate();
		
		session.createSQLQuery("create sequence usuario_seq start with 3 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("Before Each Done");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After Each");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		session.createSQLQuery("drop sequence usuario_seq").executeUpdate();
		
		session.createSQLQuery("create sequence usuario_seq start with 3 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("After Each Done");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void testIncluirUsuario() {
		System.out.println("testIncluirUsuario");

		Usuario usuario = new Usuario();
		
		usuario.setNome("Usuario teste");
		usuario.setEmail("email@teste.com");
		usuario.setNasc(new Date(1988,11,7));
		usuario.setSenha("123456");
		usuario.setSexo(1);

		Session session = Utils.getSession();
		session.beginTransaction();
		
		RoleDAO roleDAO = new RoleDAO(session); 
		
		Role role = roleDAO.selecionar(1);

		usuario.setRole(role);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(session);
		
		assertTrue(usuarioDAO.incluir(usuario));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testAtualizarUsuario() {
		System.out.println("testAtualizarUsuario");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(session);
		
		Usuario usuario = usuarioDAO.selecionar(2);
		
		assertNotNull(usuario);
		
		usuario.setNome("Nome Teste");
		
		assertTrue(usuarioDAO.atualizar(usuario));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testExcluirUsuario() {
		System.out.println("testExcluirUsuario");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(session);
		
		Usuario usuario = usuarioDAO.selecionar(2);
		
		assertNotNull(usuario);
		
		assertTrue(usuarioDAO.remover(usuario));
		
		usuario = usuarioDAO.selecionar(usuario.getId());
		
		assertNull(usuario);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testListarUsuario() {
		System.out.println("testListarUsuario");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(session);
		
		List<Usuario> usuarios = usuarioDAO.listar();
		
		assertNotNull(usuarios);
		
		assertTrue(usuarios.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testSelecionarUsuario() {
		System.out.println("testSelecionarUsuario");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(session);
		
		Usuario usuario = usuarioDAO.selecionar(1);
		
		assertNotNull(usuario);
		
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	void testGetRole() {
		// TODO - Implement
		fail("Not yet implemented");
	}
	
	@Test
	void testGetReceitas() {
		// TODO - Implement
		fail("Not yet implemented");
	}
	
	@Test
	void testGetReceitasAprovadas() {
		// TODO - Implement
		fail("Not yet implemented");
	}
	
	@Test
	void testGetPontuacoes() {
		// TODO - Implement
		fail("Not yet implemented");
	}
	
	@Test
	void testGetComentarios() {
		// TODO - Implement
		fail("Not yet implemented");
	}
	
	@Test
	void testGetReceitasReportadas() {
		// TODO - Implement
		fail("Not yet implemented");
	}
	
	@Test
	void testGetReceitasFavoritadas() {
		// TODO - Implement
		fail("Not yet implemented");
	}
	
	@Test
	void testLoginUsuario() {
		System.out.println("testLoginUsuario");
		
		String email = "teste@teste.com";
		String senha = "123456";

		Session session = Utils.getSession();
		session.beginTransaction();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(session);
		
		Usuario usuario = usuarioDAO.login(email, senha);
		
		assertNotNull(usuario);
		
		session.getTransaction().rollback();
		session.close();
	}
}
