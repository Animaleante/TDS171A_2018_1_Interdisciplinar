/**
 * 
 */
package com.tds171a.soboru.beans;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.receita.ReceitaPersistance;
import com.tds171a.soboru.utils.PersistenceFactory;

@Named("usuarioSiteBean")
@SessionScoped
/**
 * @author PC970
 *
 */
public class UsuarioSiteBean extends BeanBase<Usuario> {

	/**
	 * Criação do serial único
	 */
	private static final long serialVersionUID = 9063625419454028906L;
	
	//Declaração de variáveis.
	private ReceitaPersistance receitaPersistence;
	private List<Receita> listaReceitas;
	private List<Receita> listaFavoritos;

	/**
	 * Construtor que inicia as controllers
	 */
	public UsuarioSiteBean() {
		route_base = "/usuario/";
		controller = PersistenceFactory.getUsuarioPersistanceFactory();
		
		receitaPersistence = PersistenceFactory.getReceitaPersistanceFactory();
		
		setModel(new Usuario());
	}
	
	/**
	 * Método que cria a página de cadastro
	 * de um novo usuário 
	 * @return
	 */
	public String exibir() {
		Usuario usuario = SessionContext.getInstance().getUsuarioLogado();
		if(usuario != null)
			return exibir(usuario);
		return "/login/"+BeanBase.INDEX_PAGE+BeanBase.FACES_REDIRECT;
	}
	
	/**
	 * Método POST que cria um usuário no banco.
	 * Passando o tipo de usuário e os dados.
	 */
	@Override
	public String exibir(Usuario vo) {
		vo = controller.selecionar(vo.getId());
		
		setListaReceitas(receitaPersistence.selecionarPorUsuario(vo.getId()));
		setListaFavoritos(receitaPersistence.selecionarPorFavoritosDeUsuario(vo.getId()));
		
		return super.exibir(vo);
	}

	/**
	 * Método vazio para não ser burlado
	 * por invasão
	 */
	@Override
	public String deletar() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método vazio para não ser burlado
	 * por invasão
	 */
	@Override
	public void limparModel() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the listaReceitas
	 */
	public List<Receita> getListaReceitas() {
		return listaReceitas;
	}

	/**
	 * @param listaReceitas the listaReceitas to set
	 */
	public void setListaReceitas(List<Receita> listaReceitas) {
		this.listaReceitas = listaReceitas;
	}

	/**
	 * @return the listaFavoritos
	 */
	public List<Receita> getListaFavoritos() {
		return listaFavoritos;
	}

	/**
	 * @param listaFavoritos the listaFavoritos to set
	 */
	public void setListaFavoritos(List<Receita> listaFavoritos) {
		this.listaFavoritos = listaFavoritos;
	}

}
