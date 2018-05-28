/**
 * 
 */
package test.tds171a.soboru.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tds171a.soboru.models.Utensilio;
import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.persistence.utensilio.UtensilioDAO;

import test.tds171a.soboru.utils.Utils;

/**
 * @author Diogo
 *
 */
class UtensilioPersistenceTest {

	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");

		Session session = Utils.getSession();
		session.beginTransaction();

		session.createSQLQuery("drop sequence utensilio_seq").executeUpdate();
		
		session.createSQLQuery("create sequence utensilio_seq start with 11 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("Before Each Done");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After Each");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		session.createSQLQuery("drop sequence utensilio_seq").executeUpdate();
		
		session.createSQLQuery("create sequence utensilio_seq start with 11 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("After Each Done");
	}
	
	@Test
	void testIncluirUtensilio() {
		Utensilio utensilio = new Utensilio();
		
		utensilio.setNome("Utensilio teste");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		UtensilioDAO utensilioDAO = new UtensilioDAO(session);
		
		assertTrue(utensilioDAO.incluir(utensilio));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testAtualizarUtensilio() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		UtensilioDAO utensilioDAO = new UtensilioDAO(session);
		
		Utensilio utensilio = utensilioDAO.selecionar(1);
		
		assertNotNull(utensilio);
		
		utensilio.setNome("Nome Teste");
		
		assertTrue(utensilioDAO.atualizar(utensilio));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testExcluirUtensilio() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		UtensilioDAO utensilioDAO = new UtensilioDAO(session);
		
		Utensilio utensilio = utensilioDAO.selecionar(1);
		
		assertNotNull(utensilio);
		
		assertTrue(utensilioDAO.remover(utensilio));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testListarUtensilio() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		UtensilioDAO utensilioDAO = new UtensilioDAO(session);
		
		List<Utensilio> utensilios = utensilioDAO.listar();
		
		assertNotNull(utensilios);
		
		assertTrue(utensilios.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testSelecionarUtensilio() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		UtensilioDAO utensilioDAO = new UtensilioDAO(session);
		
		Utensilio utensilio = utensilioDAO.selecionar(1);
		
		assertNotNull(utensilio);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testGetReceitas() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		UtensilioDAO utensilioDAO = new UtensilioDAO(session);
		
		Utensilio utensilio = utensilioDAO.selecionar(2);
		
		assertNotNull(utensilio);
		
		Set<Receita> receitas = utensilio.getReceitas();
		
		assertNotNull(receitas);
		
		assertTrue(receitas.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
}
