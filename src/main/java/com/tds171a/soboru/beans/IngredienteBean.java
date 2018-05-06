package com.tds171a.soboru.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.tds171a.soboru.controllers.IngredienteController;
import com.tds171a.soboru.vos.Ingrediente;

@Named("ingredienteBean")
@SessionScoped
/**
 * Criação do bean herando de beanbase passando
 * o vo utilizada.
 */
public class IngredienteBean extends BeanBase<Ingrediente> {

	 /**
     *criando o serial do bean
     */
	private static final long serialVersionUID = 8410408634179869866L;

	/**
     *Construtor setando a rota e qual
     *será passado para o navegador.
     */
	public IngredienteBean() {
		route_base = "/cadastro/ingrediente/";
		controller = new IngredienteController();
		setVo(new Ingrediente());
	}

	/**
     * Override do deletar, onde verifica a sessao, 
     * se existe um ítem válido e se não houver, retorna a 
     * pagina de criação.
     */
	@Override
	public String deletar() {
	    FacesContext context = FacesContext.getCurrentInstance();

	    if(getVo().getId() == -1) {
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item nao pode ser vazio!", null));
	        return route_base + CRIAR_PAGE;
	    }

		if(controller.remover(getVo().getId())) {
	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado com sucesso!", null));
	    } else {
	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel deletar.", null));
            return route_base + DELETAR_PAGE;
		}

		limparVo();

	    return listar();
	}
	
	/**
	 * Verifica os dados da pagina de interação e se faltar algum dado 
	 * informa ao cliente.
	 */
	@Override
	public boolean validarDados() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(getVo().getNome().isEmpty()) {
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome nao pode ser vazio!", null));
	        return false;
	    }
		
		return true;
	}

	/**
	 * Cria uma nova vo para limpar os campos para um novo registro
	 * sem interferencia de dados cadastrados anteriormente.
	 */
	@Override
	public void limparVo() {
		setVo(new Ingrediente());
	}
}
