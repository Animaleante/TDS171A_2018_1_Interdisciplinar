package com.tds171a.soboru.utils;

import com.tds171a.soboru.persistence.categoria.CategoriaPersistence;
import com.tds171a.soboru.persistence.comentario.ComentarioPersistence;
import com.tds171a.soboru.persistence.ingrediente.IngredientePersistence;
import com.tds171a.soboru.persistence.medida.MedidaPersistence;
import com.tds171a.soboru.persistence.pontuacao.PontuacaoPersistence;
import com.tds171a.soboru.persistence.receita.ReceitaPersistance;
import com.tds171a.soboru.persistence.role.RolePersistance;
import com.tds171a.soboru.persistence.usuario.UsuarioPersistance;
import com.tds171a.soboru.persistence.utensilio.UtensilioPersistence;

public class PersistenceFactory {

	public static CategoriaPersistence getCategoriaPersistanceFactory() {
		return new CategoriaPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static ComentarioPersistence getComentarioPersistanceFactory() {
		return new ComentarioPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static IngredientePersistence getIngredientePersistanceFactory() {
		return new IngredientePersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static MedidaPersistence getMedidaPersistanceFactory() {
		return new MedidaPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static PontuacaoPersistence getPontuacaoPersistanceFactory() {
		return new PontuacaoPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static ReceitaPersistance getReceitaPersistanceFactory() {
		return new ReceitaPersistance(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static RolePersistance getRolePersistanceFactory() {
		return new RolePersistance(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static UsuarioPersistance getUsuarioPersistanceFactory() {
		return new UsuarioPersistance(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static UtensilioPersistence getUtensilioPersistanceFactory() {
		return new UtensilioPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}

}