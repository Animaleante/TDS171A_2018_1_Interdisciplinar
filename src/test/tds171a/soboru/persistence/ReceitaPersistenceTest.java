package test.tds171a.soboru.persistence;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tds171a.soboru.models.Categoria;
import com.tds171a.soboru.models.Comentario;
import com.tds171a.soboru.models.Ingrediente;
import com.tds171a.soboru.models.Medida;
import com.tds171a.soboru.models.Pontuacao;
import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.models.ReceitaIngrediente;
import com.tds171a.soboru.models.Role;
import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.models.Utensilio;
import com.tds171a.soboru.persistence.categoria.CategoriaDAO;
import com.tds171a.soboru.persistence.ingrediente.IngredienteDAO;
import com.tds171a.soboru.persistence.receita.ReceitaDAO;
import com.tds171a.soboru.persistence.usuario.UsuarioDAO;

@SuppressWarnings("deprecation")
class ReceitaPersistenceTest {
	
	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");

		Session session = getSession();
		session.beginTransaction();

		session.createSQLQuery("drop sequence receita_seq").executeUpdate();
		
		session.createSQLQuery("create sequence receita_seq start with 5 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("Before Each Done");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After Each");

		Session session = getSession();
		session.beginTransaction();
		
		session.createSQLQuery("drop sequence receita_seq").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("After Each Done");
	}
	
	@Test
	void testIncluirReceita() {
		Receita receita = new Receita();
		
		receita.setNome("Receita Teste");
		receita.setTempoPreparo(1.0);
		receita.setPorcao(2);
		receita.setModoPreparo("Texto modo de preparo");
		receita.setSlug("receita-teste");
		receita.setAprovado(true);

		Session session = getSession();
		session.beginTransaction();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(session);
		
		Categoria categoria = categoriaDAO.selecionar(2);
		
		receita.setCategoria(categoria);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(session);
		
		Usuario usuario = usuarioDAO.selecionar(2);
		
		receita.setUsuario(usuario);
		
		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		assertTrue(receitaDAO.incluir(receita));
		
		List<Receita> receitas = receitaDAO.selecionarPorNome("receita teste");
		
		assertNotNull(receitas);
		
		assertTrue(receitas.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testAtualizarReceita() {
		Session session = getSession();
		session.beginTransaction();
		
		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);
		
		receita.setNome("Testando");
		
		assertTrue(receitaDAO.atualizar(receita));
		
		List<Receita> receitas = receitaDAO.selecionarPorNome("testando");
		
		assertNotNull(receitas);
		
		assertTrue(receitas.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testExcluirReceita() {
		Session session = getSession();
		session.beginTransaction();
		
		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);
		
		assertTrue(receitaDAO.remover(receita));
		
		receita = receitaDAO.selecionar(1);
		
		assertNull(receita);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testListaReceitas() {
		Session session = getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		List<Receita> receitas = receitaDAO.listar();

		assertNotNull(receitas);
		
		assertTrue(receitas.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testSelecionarReceita() {
		Session session = getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);

		assertNotNull(receita);
		
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	void testPesquisaComIngrediente() {
		List<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();
		List<Receita> listaReceita = new ArrayList<Receita>();
		
		Session session = getSession();
		session.beginTransaction();

		IngredienteDAO ingredienteDAO = new IngredienteDAO(session);
		Ingrediente ingrediente1 = ingredienteDAO.selecionar(1);
		Ingrediente ingrediente2 = ingredienteDAO.selecionar(2);
		
		listaIngrediente.add(ingrediente1);
		listaIngrediente.add(ingrediente2);
		
		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		listaReceita = receitaDAO.selecionarPorIngredientes(listaIngrediente);
		
		assertNotNull(listaReceita);
		assertTrue(listaReceita.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	void testPesquisaComNome() {
		List<Receita> listaReceita = new ArrayList<Receita>();
		
		String termoBusca = "arroz";
		
		Session session = getSession();
		session.beginTransaction();
		
		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		listaReceita = receitaDAO.selecionarPorNome(termoBusca);
		
		assertNotNull(listaReceita);
		assertTrue(listaReceita.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	void testPesquisaComNomeEIngredientes() {
		List<Receita> listaReceita = new ArrayList<Receita>();

		List<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();
		String termoBusca = "arroz";
		
		Session session = getSession();
		session.beginTransaction();

		IngredienteDAO ingredienteDAO = new IngredienteDAO(session);
		Ingrediente ingrediente1 = ingredienteDAO.selecionar(1);
		Ingrediente ingrediente2 = ingredienteDAO.selecionar(2);
		
		listaIngrediente.add(ingrediente1);
		listaIngrediente.add(ingrediente2);
		
		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		listaReceita = receitaDAO.selecionarPorNomeEIngredientes(termoBusca, listaIngrediente);
		
		assertNotNull(listaReceita);
		assertTrue(listaReceita.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testGetPontuacaoDeReceita() {
		Set<Pontuacao> pontuacoes = new HashSet<Pontuacao>();
		
		Session session = getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);
		
		assertNotNull(receita);
		
		pontuacoes = receita.getPontuacoes();
		
		assertNotNull(pontuacoes);
		
		Iterator<Pontuacao> itr = pontuacoes.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next().getQty());
		}
		
		assertTrue(pontuacoes.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	void testGetComentarioDeReceita() {
		Set<Comentario> comentarios = new HashSet<Comentario>();
		
		Session session = getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);
		
		assertNotNull(receita);
		
		comentarios = receita.getComentarios();
		
		assertNotNull(comentarios);
		
		Iterator<Comentario> itr = comentarios.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next().getBody());
		}
		
		assertTrue(comentarios.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testPegarCategoria() {
		Session session = getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);

		assertNotNull(receita);
		
		Categoria categoria = receita.getCategoria();
		
		assertNotNull(categoria);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testPegarUsuario() {
		Session session = getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);

		assertNotNull(receita);
		
		Usuario usuario = receita.getUsuario();
		
		assertNotNull(usuario);
		
		session.getTransaction().rollback();
		session.close();
	}

	public Session getSession() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.addAnnotatedClass(Receita.class)
			.addAnnotatedClass(Categoria.class)
			.addAnnotatedClass(Medida.class)
			.addAnnotatedClass(Usuario.class)
			.addAnnotatedClass(Role.class)
			.addAnnotatedClass(Comentario.class)
			.addAnnotatedClass(Pontuacao.class)
			.addAnnotatedClass(Utensilio.class)
			.addAnnotatedClass(ReceitaIngrediente.class)
			.addAnnotatedClass(Ingrediente.class);
		
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		configuration.setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
		configuration.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@//localhost:1521/xe");
		configuration.setProperty("hibernate.connection.username", "soboru_test");
		configuration.setProperty("hibernate.connection.password", "opet");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("javax.persistence.validation.mode", "none");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.use_sql_comments", "true");
		configuration.setProperty("hibernate.format_sql", "true");
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory.openSession();
	}
}
