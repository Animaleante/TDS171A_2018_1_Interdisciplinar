/**
 * 
 */
package test.tds171a.soboru.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tds171a.soboru.models.Categoria;
import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.persistence.categoria.CategoriaDAO;
import com.tds171a.soboru.persistence.categoria.CategoriaPersistence;
import com.tds171a.soboru.utils.HibernateUtil;

import test.tds171a.soboru.utils.Utils;

/**
 * @author Diogo
 *
 */
class CategoriaPersistenceTest {

	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");

		Session session = Utils.getSession();
		session.beginTransaction();

		session.createSQLQuery("drop sequence categoria_seq").executeUpdate();
		
		session.createSQLQuery("create sequence categoria_seq start with 7 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("Before Each Done");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After Each");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		session.createSQLQuery("drop sequence categoria_seq").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("After Each Done");
	}
	
	@Test
	void testIncluirCategoria() {
		Categoria categoria = new Categoria();
		
		categoria.setNome("Categoria teste");
		categoria.setSelecionavel(false);

		Session session = Utils.getSession();
		session.beginTransaction();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(session);
		
		assertTrue(categoriaDAO.incluir(categoria));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testAtualizarCategoria() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(session);
		
		Categoria categoria = categoriaDAO.selecionar(1);
		
		assertNotNull(categoria);
		
		categoria.setNome("Nome Teste");
		
		assertTrue(categoriaDAO.atualizar(categoria));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testExcluirCategoria() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(session);
		
		Categoria categoria = categoriaDAO.selecionar(1);
		
		assertNotNull(categoria);
		
		assertTrue(categoriaDAO.remover(categoria));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testListarCategoria() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(session);
		
		List<Categoria> categorias = categoriaDAO.listar();
		
		assertNotNull(categorias);
		
		assertTrue(categorias.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testSelecionarCategoria() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(session);
		
		Categoria categoria = categoriaDAO.selecionar(1);
		
		assertNotNull(categoria);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testGetReceitas() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(session);
		
		Categoria categoria = categoriaDAO.selecionar(1);
		
		assertNotNull(categoria);
		
		Set<Receita> receitas = categoria.getReceitas();
		
		assertNotNull(receitas);
		
		assertTrue(receitas.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
}
