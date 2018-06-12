/**
 * 
 */
package com.tds171a.soboru.beans;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.receita.ReceitaPersistence;
import com.tds171a.soboru.utils.ContextUtil;
import com.tds171a.soboru.utils.PersistenceFactory;

@Named("usuarioSiteBean")
@SessionScoped
/**
 * @author PC970
 *
 */
public class UsuarioSiteBean extends BeanBase<Usuario> {

	/**
	 * Cria��o do serial �nico
	 */
	private static final long serialVersionUID = 9063625419454028906L;
	
	//Declara��o de vari�veis.
	private List<Receita> listaReceitas;
	private List<Receita> listaFavoritos;

	/**
	 * Construtor que inicia as controllers
	 */
	public UsuarioSiteBean() {
		route_base = "/usuario/";
		
		setModel(new Usuario());
	}
	
	/**
	 * M�todo que cria a p�gina de cadastro
	 * de um novo usu�rio 
	 * @return
	 */
	public String exibir() {
		Usuario usuario = SessionContext.getInstance().getUsuarioLogado();
		if(usuario != null)
			return exibir(usuario);
		return "/login/"+BeanBase.INDEX_PAGE+BeanBase.FACES_REDIRECT;
	}
	
	public String exibirProprioPerfil() {
		Usuario usuario = ContextUtil.getContextBean().getUsuarioLogado();
		if(usuario != null) {
			setModel(usuario);

//			controller = PersistenceFactory.getUsuarioPersistenceFactory();
//			ReceitaPersistence receitaPersistence = PersistenceFactory.getReceitaPersistenceFactory();
			
//			setListaReceitas(receitaPersistence.selecionarPorUsuario(usuario.getId()));
//			setListaFavoritos(receitaPersistence.selecionarPorFavoritosDeUsuario(usuario.getId()));
			setListaReceitas(usuario.getReceitas());
			setListaFavoritos(usuario.getReceitasFavoritadas());
		}
		return route_base+BeanBase.EXIBIR_PAGE+BeanBase.FACES_REDIRECT;
	}
	
	/**
	 * M�todo POST que cria um usu�rio no banco.
	 * Passando o tipo de usu�rio e os dados.
	 */
	@Override
	public String exibir(Usuario vo) {
		vo = controller.selecionar(vo.getId());

		controller = PersistenceFactory.getUsuarioPersistenceFactory();
		ReceitaPersistence receitaPersistence = PersistenceFactory.getReceitaPersistenceFactory();
		
		setListaReceitas(receitaPersistence.selecionarPorUsuario(vo.getId()));
		setListaFavoritos(receitaPersistence.selecionarPorFavoritosDeUsuario(vo.getId()));
		
		return super.exibir(vo);
	}

	/**
	 * M�todo vazio para n�o ser burlado
	 * por invas�o
	 */
	@Override
	public String deletar() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * M�todo vazio para n�o ser burlado
	 * por invas�o
	 */
	@Override
	public void limparModel() {
		setModel(new Usuario());
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
