/**
 * 
 */
package com.tds171a.soboru.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Classe para criar uma a conexão entre o arquivo de configuração e a conexão
 * com o Banco de dados.
 * 
 * @author Baracho
 * 
 * @since 01/02/2012
 * 
 * @version 1.0
 * 
 */

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		ServiceRegistry serviceRegistry = null;

		try {

			Configuration configuration = new Configuration();

			configuration.configure("hibernate.cfg.xml");

			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();

			return configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable e) {
			System.out.println("Criação inicial do objeto sessionfactory falhou. Erro: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;
	}
}
