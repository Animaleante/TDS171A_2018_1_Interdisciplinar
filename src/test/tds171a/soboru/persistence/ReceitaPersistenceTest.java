package test.tds171a.soboru.persistence;

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
import com.tds171a.soboru.persistence.ingrediente.IngredienteDAO;
import com.tds171a.soboru.persistence.receita.ReceitaDAO;

@SuppressWarnings("deprecation")
class ReceitaPersistenceTest {

	@Test
	void testPesquisaComIngrediente() {
		List<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();
		List<Receita> listaReceita = new ArrayList<Receita>();
		
		Session session = getSession();

		IngredienteDAO ingredienteDAO = new IngredienteDAO(session);
		Ingrediente ingrediente1 = ingredienteDAO.selecionar(1);
		Ingrediente ingrediente2 = ingredienteDAO.selecionar(2);
		
		listaIngrediente.add(ingrediente1);
		listaIngrediente.add(ingrediente2);
		
		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		listaReceita = receitaDAO.selecionarPorIngredientes(listaIngrediente);
		
		assertNotNull(listaReceita);
		assertTrue(listaReceita.size() > 0);
	}

	@Test
	void testPesquisaComNome() {
		List<Receita> listaReceita = new ArrayList<Receita>();
		
		String termoBusca = "arroz";
		
		Session session = getSession();
		
		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		listaReceita = receitaDAO.selecionarPorNome(termoBusca);
		
		assertNotNull(listaReceita);
		assertTrue(listaReceita.size() > 0);
	}

	@Test
	void testPesquisaComNomeEIngredientes() {
		List<Receita> listaReceita = new ArrayList<Receita>();

		List<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();
		String termoBusca = "arroz";
		
		Session session = getSession();

		IngredienteDAO ingredienteDAO = new IngredienteDAO(session);
		Ingrediente ingrediente1 = ingredienteDAO.selecionar(1);
		Ingrediente ingrediente2 = ingredienteDAO.selecionar(2);
		
		listaIngrediente.add(ingrediente1);
		listaIngrediente.add(ingrediente2);
		
		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		listaReceita = receitaDAO.selecionarPorNomeEIngredientes(termoBusca, listaIngrediente);
		
		assertNotNull(listaReceita);
		assertTrue(listaReceita.size() > 0);
	}
	
	@Test
	public void testGetPontuacaoDeReceita() {
		Set<Pontuacao> pontuacoes = new HashSet<Pontuacao>();
		
		Session session = getSession();

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
	}

	@Test
	public void testGetComentarioDeReceita() {
		Set<Comentario> comentarios = new HashSet<Comentario>();
		
		Session session = getSession();

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
		configuration.setProperty("hibernate.connection.username", "soboru");
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
