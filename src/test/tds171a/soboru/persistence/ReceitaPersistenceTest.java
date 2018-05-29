package test.tds171a.soboru.persistence;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
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
import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.models.Utensilio;
import com.tds171a.soboru.persistence.categoria.CategoriaDAO;
import com.tds171a.soboru.persistence.ingrediente.IngredienteDAO;
import com.tds171a.soboru.persistence.medida.MedidaDAO;
import com.tds171a.soboru.persistence.receita.ReceitaDAO;
import com.tds171a.soboru.persistence.usuario.UsuarioDAO;
import com.tds171a.soboru.persistence.utensilio.UtensilioDAO;

import test.tds171a.soboru.utils.Utils;

class ReceitaPersistenceTest {
	
	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");

		Session session = Utils.getSession();
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

		Session session = Utils.getSession();
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

		Session session = Utils.getSession();
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
		Session session = Utils.getSession();
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
		Session session = Utils.getSession();
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
		Session session = Utils.getSession();
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
		Session session = Utils.getSession();
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
		
		Session session = Utils.getSession();
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
		
		Session session = Utils.getSession();
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
		
		Session session = Utils.getSession();
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
	void testGetPontuacao() {
		Set<Pontuacao> pontuacoes = new HashSet<Pontuacao>();
		
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);
		
		assertNotNull(receita);
		
		pontuacoes = receita.getPontuacoes();
		
		assertNotNull(pontuacoes);
		
		assertTrue(pontuacoes.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	void testGetComentario() {
		Set<Comentario> comentarios = new HashSet<Comentario>();
		
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);
		
		assertNotNull(receita);
		
		comentarios = receita.getComentarios();
		
		assertNotNull(comentarios);
		
		assertTrue(comentarios.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testGetCategoria() {
		Session session = Utils.getSession();
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
	void testGetUsuario() {
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);

		assertNotNull(receita);
		
		Usuario usuario = receita.getUsuario();
		
		assertNotNull(usuario);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testGetReceitaIngredientes() {
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);

		assertNotNull(receita);
		
		Set<ReceitaIngrediente> receitaIngredientes = receita.getReceitaIngredientes();
		
		assertNotNull(receitaIngredientes);
		
		assertTrue(receitaIngredientes.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testIncluirReceitaIngrediente() {
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);

		assertNotNull(receita);
		
		Set<ReceitaIngrediente> receitaIngredientes = receita.getReceitaIngredientes();
		
		assertNotNull(receitaIngredientes);
		
		int numReceitaIngredientes = receitaIngredientes.size();
		
		IngredienteDAO ingredienteDAO = new IngredienteDAO(session);
		
		Ingrediente ingrediente = ingredienteDAO.selecionar(7);
		
		MedidaDAO medidaDAO = new MedidaDAO(session);
		
		Medida medida = medidaDAO.selecionar(1);

		ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();
		
		receitaIngrediente.setIngrediente(ingrediente);
		receitaIngrediente.setReceita(receita);
		receitaIngrediente.setMedida(medida);
		receitaIngrediente.setQty(5);
		
		receitaIngredientes.add(receitaIngrediente);
		
		receita.setReceitaIngredientes(receitaIngredientes);
		
		assertTrue(receitaDAO.atualizar(receita));
		
		receitaIngredientes = receita.getReceitaIngredientes();
		
		assertNotNull(receitaIngredientes);
		
		assertTrue(receitaIngredientes.size() == (numReceitaIngredientes+1));
		
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	void testGetUsuariosQueFavoritaram() {
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);

		assertNotNull(receita);
		
		Set<Usuario> usuariosFavoritaram = receita.getUsuariosQueFavoritaram();
		
		assertNotNull(usuariosFavoritaram);
		
		assertTrue(usuariosFavoritaram.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	void testGetUsuariosQueReportaram() {
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(2);

		assertNotNull(receita);
		
		Set<Usuario> usuariosReportaram = receita.getUsuariosQueReportaram();
		
		assertNotNull(usuariosReportaram);
		
		assertTrue(usuariosReportaram.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	void testGetUtensilios() {
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);

		assertNotNull(receita);
		
		Set<Utensilio> utensilios = receita.getUtensilios();
		
		assertNotNull(utensilios);
		
		assertTrue(utensilios.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testIncluirUtensilio() {
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(1);

		assertNotNull(receita);
		
		Set<Utensilio> utensilios = receita.getUtensilios();
		
		assertNotNull(utensilios);
		
		int numUtensilios = utensilios.size();
		
		UtensilioDAO utensilioDAO = new UtensilioDAO(session);
		
		Utensilio utensilio = utensilioDAO.selecionar(3);
		
		assertNotNull(utensilio);
		
		utensilios.add(utensilio);
		
		receita.setUtensilios(utensilios);
		
		assertTrue(receitaDAO.atualizar(receita));
		
		utensilios = receita.getUtensilios();
		
		assertNotNull(utensilios);
		
		assertTrue(utensilios.size() == (numUtensilios+1));
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testGetPorUsuario() {
		System.out.println("testGetPorUsuario");
		
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		List<Receita> receitas = receitaDAO.selecionarPorUsuario(1);

		assertNotNull(receitas);
		
		assertTrue(receitas.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	void testGetPorFavoritoDeUsuario() {
		System.out.println("testGetPorFavoritoDeUsuario");
		
		Session session = Utils.getSession();
		session.beginTransaction();

		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		List<Receita> receitas = receitaDAO.selecionarPorFavoritosDeUsuario(2);

		assertNotNull(receitas);
		
		assertTrue(receitas.size() > 0);
		
		session.getTransaction().rollback();
		session.close();
	}
}
