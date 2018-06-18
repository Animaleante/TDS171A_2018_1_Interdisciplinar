package com.tds171a.soboru.beans;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.tds171a.soboru.models.Role;
import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.role.RolePersistence;
import com.tds171a.soboru.utils.PersistenceFactory;
import com.tds171a.soboru.utils.Utils;

@Named("usuarioBean")
@SessionScoped
/**
 * Criação do bean herando de beanbase passando o vo utilizada.
 */
public class UsuarioBean extends BeanBase<Usuario> {

	/**
	 * criando o serial do bean
	 */
	private static final long serialVersionUID = 4490606218207822710L;

	//Declaração de variáveis
	private String nasc;
	private List<Role> roles;

	/**
	 * Construtor setando a rota e qual será passado para o navegador.
	 */
	public UsuarioBean() {
		route_base = "/cadastro/usuario/";
		setModel(new Usuario());
	}
    
    /**
     * 
     */
    @Override
    public String listar() {
    	controller = PersistenceFactory.getUsuarioPersistenceFactory();
    	return super.listar();
    }

	/**
	 * Método GEt onde cria uma lista dos 
	 * perfis de usuário disponíveis e
	 * gera a tela de criação
	 */
	@Override
	public String criar() {
		controller = PersistenceFactory.getUsuarioPersistenceFactory();
		RolePersistence rolePersistence = PersistenceFactory.getRolePersistenceFactory();
		
		setRoles(rolePersistence.listar());
		return super.criar();
	}
	
	/**
	 * Cria uma lista de perfis disponíveis
	 * e abre a tela de editar.
	 */
	@Override
	public String editar(Usuario vo) {
		controller = PersistenceFactory.getUsuarioPersistenceFactory();
		RolePersistence rolePersistence = PersistenceFactory.getRolePersistenceFactory();
		
		setRoles(rolePersistence.listar());
		return super.editar(vo);
	}
	/**
	 * Método POST onde verifica os dados
	 * e tenta criar o usuário. Retornando para índex
	 */
	public String incluir() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (!getModel().getSenha().equals(getModel().getSenhaConfirmacao())) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas nao sao identicas!", null));
		} else {
			Date formattedDate;
			
			try {
				formattedDate = Utils.formataData(getNasc());
			} catch (ParseException e) {
				e.printStackTrace();
				getModel().setSenha("");
				getModel().setSenhaConfirmacao("");
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel registrar um usuario!", null));
				return listar();
			}
			
			getModel().setNasc(formattedDate);

			controller = PersistenceFactory.getUsuarioPersistenceFactory();
			if (controller.incluir(getModel())) {
				limparModel();

				return listar();
			} else {
				getModel().setSenha("");
				getModel().setSenhaConfirmacao("");
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel registrar um usuario!", null));
			}
		}

		return route_base + "index";
	}

	/**
	 * Override do deletar, onde verifica a sessao, se existe um ítem válido e
	 * se não houver, retorna a pagina de criação.
	 */
	@Override
	public String deletar() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (getModel().getId() == -1) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item nao pode ser vazio!", null));
			return route_base + CRIAR_PAGE;
		}

		controller = PersistenceFactory.getUsuarioPersistenceFactory();
		if (controller.remover(getModel())) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado com sucesso!", null));
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel deletar.", null));
			return route_base + DELETAR_PAGE;
		}

		limparModel();

		return listar();
	}

	/**
	 * Verifica os dados da pagina de interação e se faltar algum dado informa
	 * ao cliente.
	 */
	@Override
	public boolean validarDados() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (getModel().getNome().isEmpty()) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome nao pode ser vazio!", null));
			return false;
		}

		return true;
	}

	/**
	 * Cria uma nova vo para limpar os campos para um novo registro sem
	 * interferencia de dados cadastrados anteriormente.
	 */
	@Override
	public void limparModel() {
	}

	/**
	 * Pega uma lista com os perfis de usuário
	 * disponíveis.
	 * @return
	 */
	public List<SelectItem> getRoles() {
		List<SelectItem> items = new ArrayList<SelectItem>();
	    for (Role r : this.roles) {
	        items.add(new SelectItem(r.getId(), r.getNome()));
	    }
	    return items;
	}
	
	
	/**
	 * Cria uma lista com os ítens válidos na tabela
	 * @param roles
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	/**
	 * @return the nasc
	 */
	public String getNasc() {
		return nasc;
	}


	/**
	 * @param nasc the nasc to set
	 */
	public void setNasc(String nasc) {
		this.nasc = nasc;
	}
}
