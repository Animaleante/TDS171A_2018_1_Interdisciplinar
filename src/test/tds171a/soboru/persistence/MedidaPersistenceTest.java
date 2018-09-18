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

import com.tds171a.soboru.models.Medida;
import com.tds171a.soboru.persistence.medida.MedidaDAO;

import test.tds171a.soboru.utils.Utils;

/**
 * @author Diogo
 *
 */
class MedidaPersistenceTest {

	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");

		Session session = Utils.getSession();
		session.beginTransaction();

		session.createSQLQuery("drop sequence medida_seq").executeUpdate();
		
		session.createSQLQuery("create sequence medida_seq start with 13 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("Before Each Done");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After Each");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		session.createSQLQuery("drop sequence medida_seq").executeUpdate();
		
		session.createSQLQuery("create sequence medida_seq start with 13 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("After Each Done");
	}
	
	@Test
	void testIncluirMedida() {
		Medida medida = new Medida();
		
		medida.setNome("Medida teste");
		medida.setAbreviacao("Med");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		MedidaDAO medidaDAO = new MedidaDAO(session);
		
		assertTrue(medidaDAO.incluir(medida));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testAtualizarMedida() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		MedidaDAO medidaDAO = new MedidaDAO(session);
		
		Medida medida = medidaDAO.selecionar(1);
		
		assertNotNull(medida);
		
		medida.setNome("Nome Teste");
		
		assertTrue(medidaDAO.atualizar(medida));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testExcluirMedida() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		MedidaDAO medidaDAO = new MedidaDAO(session);
		
		Medida medida = medidaDAO.selecionar(1);
		
		assertNotNull(medida);
		
		assertTrue(medidaDAO.remover(medida));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testListarMedida() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		MedidaDAO medidaDAO = new MedidaDAO(session);
		
		List<Medida> medidas = medidaDAO.listar();
		
		assertNotNull(medidas);
		
		assertTrue(medidas.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testSelecionarMedida() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		MedidaDAO medidaDAO = new MedidaDAO(session);
		
		Medida medida = medidaDAO.selecionar(1);
		
		assertNotNull(medida);
		
		session.getTransaction().rollback();
		session.close();
	}
}
