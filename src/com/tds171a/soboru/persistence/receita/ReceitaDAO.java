/**
 * 
 */
package com.tds171a.soboru.persistence.receita;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.tds171a.soboru.models.Ingrediente;
import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.IDAO;

/**
 * @author opet
 * 
 * @since maio 21, 2018
 * 
 * @version 1.0
 *
 */
public class ReceitaDAO implements IDAO<Receita>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -510359741335961663L;
	
	private Session session;

	public ReceitaDAO(Session session) {
		this.session = session;
	}

	@Override
	public boolean incluir(Receita model) {
		try {
			this.session.save(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Receita> listar() {
		return this.session.createCriteria(Receita.class)
				.add(Restrictions.eq("aprovado", true))
				.addOrder(Order.asc("nome"))
				.list();
	}

	@Override
	public boolean atualizar(Receita model) {
		try {
			this.session.update(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remover(Receita model) {
		try {
			this.session.delete(model);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Receita selecionar(int modelId) {
		return (Receita) this.session.get(Receita.class, modelId);
	}

	@SuppressWarnings("unchecked")
	public List<Receita> selecionarPorNomeEIngredientes(String termoBusca, List<Ingrediente> lista) {
		try {
			return this.session.createCriteria(Receita.class, "receita")
				.createAlias("receita.receitaIngredientes", "receitaIngrediente")
				.add(Restrictions.in("receitaIngrediente.ingrediente", lista))
			    .add(Restrictions.sqlRestriction("lower({alias}.nome) like lower(?)", "%"+termoBusca+"%", StandardBasicTypes.STRING) )
				.add(Restrictions.eq("aprovado", true))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Receita> selecionarPorNome(String termoBusca) {
		try {
			return this.session.createCriteria(Receita.class)
				.add(Restrictions.eq("aprovado", true))
			    .add(Restrictions.sqlRestriction("lower({alias}.nome) like lower(?)", "%"+termoBusca+"%", StandardBasicTypes.STRING))
				.addOrder(Order.asc("nome"))
				.list();
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Receita> selecionarPorIngredientes(List<Ingrediente> lista) {
		try {
			return this.session.createCriteria(Receita.class, "receita")
				.createAlias("receita.receitaIngredientes", "receitaIngrediente")
				.add(Restrictions.in("receitaIngrediente.ingrediente", lista))
				.add(Restrictions.eq("aprovado", true))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Receita> selecionarPorUsuario(int userId) {
		try {
			return this.session.createCriteria(Receita.class)
				.add(Restrictions.eq("aprovado", true))
				.add(Restrictions.sqlRestriction("id_usuario = " + userId))
				.addOrder(Order.asc("nome"))
				.list();
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Receita> selecionarPorFavoritosDeUsuario(int userId) {
		try {
			return this.session.createCriteria(Receita.class, "receita")
				.createAlias("receita.usuariosQueFavoritaram", "usuarioQueFavoritou")
				.add(Restrictions.eq("usuarioQueFavoritou.id", userId))
				.add(Restrictions.eq("aprovado", true))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean receitaJaFoiReportada(Receita model, Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean receitaJaFoiPontuada(Receita model, Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean receitaFavoritada(Receita model, Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}
}
