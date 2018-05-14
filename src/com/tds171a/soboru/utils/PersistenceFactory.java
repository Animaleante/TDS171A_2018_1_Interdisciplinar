package com.tds171a.soboru.utils;

import com.tds171a.soboru.persistence.categoria.CategoriaPersistence;

public class PersistenceFactory {

	public static CategoriaPersistence getCategoriaPersistanceFactory() {
		return new CategoriaPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
//		return new CategoriaPersistence(HibernateUtil.getSessionFactory().openSession());
	}
}
