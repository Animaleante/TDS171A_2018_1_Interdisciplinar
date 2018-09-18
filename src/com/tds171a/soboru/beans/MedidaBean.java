package com.tds171a.soboru.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.tds171a.soboru.models.Medida;
import com.tds171a.soboru.utils.PersistenceFactory;

@Named("medidaBean")
@SessionScoped
/**
 * Cria��o do bean herando de beanbase passando
 * o vo utilizada.
 */
public class MedidaBean extends BeanBase<Medida> {

	/**
     *criando o serial do bean
     */
	private static final long serialVersionUID = 8410408634179869866L;

	/**
     *Construtor setando a rota e qual
     *ser� passado para o navegador.
     */
	public MedidaBean() {
		route_base = "/cadastro/medida/";
        setModel(new Medida());
	}
    
    /**
     * 
     */
    @Override
    public String listar() {
    	controller = PersistenceFactory.getMedidaPersistenceFactory();
    	return super.listar();
    }

	/**
     * Override do deletar, onde verifica a sessao, 
     * se existe um �tem v�lido e se n�o houver, retorna a 
     * pagina de cria��o.
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
	 * Verifica os dados da pagina de intera��o e se faltar algum dado 
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

	/**
	 * Cria uma nova vo para limpar os campos para um novo registro
	 * sem interferencia de dados cadastrados anteriormente.
	 */
	@Override
	public void limparModel() {
		setModel(new Medida());
	}
}
