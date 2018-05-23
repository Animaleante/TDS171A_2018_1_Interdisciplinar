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

import com.tds171a.soboru.controllers.RoleController;
import com.tds171a.soboru.controllers.UsuarioController;
import com.tds171a.soboru.utils.Utils;
import com.tds171a.soboru.vos.Role;
import com.tds171a.soboru.vos.Usuario;

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
	private RoleController roleController;
	private List<Role> roles;

	/**
	 * Construtor setando a rota e qual será passado para o navegador.
	 */
	public UsuarioBean() {
		route_base = "/cadastro/usuario/";
		controller = new UsuarioController();
		roleController = new RoleController();
		setVo(new Usuario());
	}

	/**
	 * Método GEt onde cria uma lista dos 
	 * perfis de usuário disponíveis e
	 * gera a tela de criação
	 */
	@Override
	public String criar() {
		setRoles(roleController.listar());

		return super.criar();
	}
	
	/**
	 * Cria uma lista de perfis disponíveis
	 * e abre a tela de editar.
	 */
	@Override
	public String editar(Usuario vo) {
		setRoles(roleController.listar());

		return super.editar(vo);
	}
	/**
	 * Método POST onde verifica os dados
	 * e tenta criar o usuário. Retornando para índex
	 */
	public String incluir() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (!getVo().getSenha().equals(getVo().getSenhaConfirmacao())) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas nao sao identicas!", null));
		} else {
			Date formattedDate;
			try {
				formattedDate = Utils.formataData(getNasc());
			} catch (ParseException e) {
				e.printStackTrace();
				getVo().setSenha("");
				getVo().setSenhaConfirmacao("");
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel registrar um usuario!", null));
				return listar();
			}
			getVo().setNasc(formattedDate);

			if (controller.incluir(getVo())) {
				
				limparVo();

				return listar();
			} else {
				getVo().setSenha("");
				getVo().setSenhaConfirmacao("");
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

		if (getVo().getId() == -1) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item nao pode ser vazio!", null));
			return route_base + CRIAR_PAGE;
		}

		if (controller.remover(getVo().getId())) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado com sucesso!", null));
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel deletar.", null));
			return route_base + DELETAR_PAGE;
		}

		limparVo();

		return listar();
	}

	/**
	 * Verifica os dados da pagina de interação e se faltar algum dado informa
	 * ao cliente.
	 */
	@Override
	public boolean validarDados() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (getVo().getNome().isEmpty()) {
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
	public void limparVo() {
		setVo(new Usuario());
		setNasc("");
		
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
