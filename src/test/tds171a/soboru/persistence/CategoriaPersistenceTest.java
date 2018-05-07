/**
 * 
 */
package test.tds171a.soboru.persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.tds171a.soboru.models.Categoria;
import com.tds171a.soboru.persistence.categoria.CategoriaPersistence;
import com.tds171a.soboru.utils.HibernateUtil;

/**
 * @author Diogo
 *
 */
class CategoriaPersistenceTest {

	@Test
	void testIncluir() {
		CategoriaPersistence persistence = new CategoriaPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
		Categoria testModel = new Categoria();
		testModel.setNome("Teste");
		assertTrue(persistence.incluir(testModel));
	}

}
