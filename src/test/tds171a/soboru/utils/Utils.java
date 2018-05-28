/**
 * 
 */
package test.tds171a.soboru.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

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

/**
 * @author Diogo
 *
 */
@SuppressWarnings("deprecation")
public class Utils {

	public static Session getSession() {
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
