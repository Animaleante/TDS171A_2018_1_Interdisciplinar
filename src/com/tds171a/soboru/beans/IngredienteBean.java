package com.tds171a.soboru.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.tds171a.soboru.models.Ingrediente;

@Named("ingredienteBean")
@RequestScoped
/**
 * Criação do bean herando de beanbase passando
 * o vo utilizada.
 */
public class IngredienteBean extends BeanBase<Ingrediente> {

	/**
     *criando o serial do bean
     */	
	private static final long serialVersionUID = 2594806959400971783L;

	

	/**
     *Construtor setando a rota e qual
     *será passado para o navegador.
     */
	public IngredienteBean() {
		route_base = "/cadastro/ingrediente/";
//		controller = new IngredienteController();
		setModel(new Ingrediente());
	}

	/**
     * Override do deletar, onde verifica a sessao, 
     * se existe um ítem válido e se não houver, retorna a 
     * pagina de criação.
     */
	@Override
	public String deletar() {
	    FacesContext context = FacesContext.getCurrentInstance();

	    if(getModel().getId() == -1) {
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item nao pode ser vazio!", null));
	        return route_base + CRIAR_PAGE;
	    }

		if(controller.remover(getModel())) {
	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado com sucesso!", null));
	    } else {
	        context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel deletar.", null));
            return route_base + DELETAR_PAGE;
		}

		limparModel();

	    return listar();
	}
	
	/**
	 * Verifica os dados da pagina de interação e se faltar algum dado 
	 * informa ao cliente.
	 */
	@Override
	public boolean validarDados() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(getModel().getNome().isEmpty()) {
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome nao pode ser vazio!", null));
	        return false;
	    }
		
		return true;
	}
	@Override
	public void limparModel() {
		setModel(new Ingrediente());
	}
}
