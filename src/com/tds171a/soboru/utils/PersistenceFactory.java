package com.tds171a.soboru.utils;

import com.tds171a.soboru.persistence.categoria.CategoriaPersistence;
import com.tds171a.soboru.persistence.comentario.ComentarioPersistence;
import com.tds171a.soboru.persistence.ingrediente.IngredientePersistence;
import com.tds171a.soboru.persistence.medida.MedidaPersistence;
import com.tds171a.soboru.persistence.pontuacao.PontuacaoPersistence;
import com.tds171a.soboru.persistence.receita.ReceitaPersistence;
import com.tds171a.soboru.persistence.receitaingrediente.ReceitaIngredientePersistence;
import com.tds171a.soboru.persistence.role.RolePersistence;
import com.tds171a.soboru.persistence.usuario.UsuarioPersistence;
import com.tds171a.soboru.persistence.utensilio.UtensilioPersistence;

public class PersistenceFactory {

	public static CategoriaPersistence getCategoriaPersistenceFactory() {
		return new CategoriaPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static ComentarioPersistence getComentarioPersistenceFactory() {
		return new ComentarioPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static IngredientePersistence getIngredientePersistenceFactory() {
		return new IngredientePersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static MedidaPersistence getMedidaPersistenceFactory() {
		return new MedidaPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static PontuacaoPersistence getPontuacaoPersistenceFactory() {
		return new PontuacaoPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static ReceitaPersistence getReceitaPersistenceFactory() {
		return new ReceitaPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static ReceitaIngredientePersistence getReceitaIngredientePersistenceFactory() {
		return new ReceitaIngredientePersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static RolePersistence getRolePersistenceFactory() {
		return new RolePersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static UsuarioPersistence getUsuarioPersistenceFactory() {
		return new UsuarioPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static UtensilioPersistence getUtensilioPersistenceFactory() {
		return new UtensilioPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}

}