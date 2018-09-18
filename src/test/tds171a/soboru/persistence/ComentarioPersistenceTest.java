/**
 * 
 */
package test.tds171a.soboru.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tds171a.soboru.models.Comentario;
import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.comentario.ComentarioDAO;
import com.tds171a.soboru.persistence.receita.ReceitaDAO;
import com.tds171a.soboru.persistence.usuario.UsuarioDAO;

import test.tds171a.soboru.utils.Utils;

/**
 * @author Diogo
 *
 */
class ComentarioPersistenceTest {
	
	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");

		Session session = Utils.getSession();
		session.beginTransaction();

		session.createSQLQuery("drop sequence comentario_seq").executeUpdate();
		
		session.createSQLQuery("create sequence comentario_seq start with 3 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("Before Each Done");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After Each");

		Session session = Utils.getSession();
		session.beginTransaction();
		
		session.createSQLQuery("drop sequence comentario_seq").executeUpdate();
		
		session.createSQLQuery("create sequence comentario_seq start with 3 nocache").executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();

		System.out.println("After Each Done");
	}

	@Test
	void testIncluirComentario() {
		Session session = Utils.getSession();
		session.beginTransaction();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(session);
		
		Usuario usuario = usuarioDAO.selecionar(2);
		
		ReceitaDAO receitaDAO = new ReceitaDAO(session);
		
		Receita receita = receitaDAO.selecionar(2);
		
		Comentario comentario = new Comentario();
		
		comentario.setReceita(receita);
		comentario.setUsuario(usuario);
		
		comentario.setBody("Mensagem de teste!");
		
		ComentarioDAO comentarioDAO = new ComentarioDAO(session);
		
		assertTrue(comentarioDAO.incluir(comentario));
		
		session.getTransaction().commit();
		session.close();
	}

}
