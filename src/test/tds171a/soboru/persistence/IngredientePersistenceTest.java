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

import com.tds171a.soboru.models.Ingrediente;
import com.tds171a.soboru.persistence.ingrediente.IngredienteDAO;

import test.tds171a.soboru.utils.Utils;

/**
 * @author Diogo
 *
 */
class IngredientePersistenceTest {

	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");

		Session session = Utils.getSession();
		session.beginTransaction();

		session.createSQLQuery("drop sequence ingrediente_seq").executeUpdate();
		
		session.createSQLQuery("create sequence ingrediente_seq start with 25 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("Before Each Done");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After Each");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		session.createSQLQuery("drop sequence ingrediente_seq").executeUpdate();
		
		session.createSQLQuery("create sequence ingrediente_seq start with 25 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("After Each Done");
	}
	
	@Test
	void testIncluirIngrediente() {
		Ingrediente ingrediente = new Ingrediente();
		
		ingrediente.setNome("Ingrediente teste");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		IngredienteDAO ingredienteDAO = new IngredienteDAO(session);
		
		assertTrue(ingredienteDAO.incluir(ingrediente));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testAtualizarIngrediente() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		IngredienteDAO ingredienteDAO = new IngredienteDAO(session);
		
		Ingrediente ingrediente = ingredienteDAO.selecionar(1);
		
		assertNotNull(ingrediente);
		
		ingrediente.setNome("Nome Teste");
		
		assertTrue(ingredienteDAO.atualizar(ingrediente));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testExcluirIngrediente() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		IngredienteDAO ingredienteDAO = new IngredienteDAO(session);
		
		Ingrediente ingrediente = ingredienteDAO.selecionar(1);
		
		assertNotNull(ingrediente);
		
		assertTrue(ingredienteDAO.remover(ingrediente));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testListarIngrediente() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		IngredienteDAO ingredienteDAO = new IngredienteDAO(session);
		
		List<Ingrediente> ingredientess = ingredienteDAO.listar();
		
		assertNotNull(ingredientess);
		
		assertTrue(ingredientess.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testSelecionarIngrediente() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		IngredienteDAO ingredienteDAO = new IngredienteDAO(session);
		
		Ingrediente ingrediente = ingredienteDAO.selecionar(1);
		
		assertNotNull(ingrediente);
		
		session.getTransaction().rollback();
		session.close();
	}
}
