package com.tds171a.soboru.utils;

import com.tds171a.soboru.persistence.categoria.CategoriaPersistence;
import com.tds171a.soboru.persistence.comentario.ComentarioPersistence;
import com.tds171a.soboru.persistence.ingrediente.IngredientePersistence;
import com.tds171a.soboru.persistence.medida.MedidaPersistence;
import com.tds171a.soboru.persistence.pontuacao.PontuacaoPersistence;
import com.tds171a.soboru.persistence.receita.ReceitaPersistence;
import com.tds171a.soboru.persistence.role.RolePersistence;
import com.tds171a.soboru.persistence.usuario.UsuarioPersistence;
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
	
	public static ReceitaPersistence getReceitaPersistanceFactory() {
		return new ReceitaPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static RolePersistence getRolePersistanceFactory() {
		return new RolePersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static UsuarioPersistence getUsuarioPersistanceFactory() {
		return new UsuarioPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}
	
	public static UtensilioPersistence getUtensilioPersistanceFactory() {
		return new UtensilioPersistence(HibernateUtil.getSessionFactory().getCurrentSession());
	}

}