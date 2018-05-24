/**
 *
 */
package com.tds171a.soboru.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.tds171a.soboru.models.Categoria;
import com.tds171a.soboru.persistence.categoria.CategoriaPersistence;
import com.tds171a.soboru.utils.PersistenceFactory;
import com.tds171a.soboru.utils.Utils;

@Named("CategoriaBean")
@RequestScoped
/**
 * Cria��o do bean herando de beanbase passando
 * o vo utilizada.
 */
public class CategoriaBean extends BeanBase<Categoria> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3257426613093184087L;
	/**
     *criando o serial do bean
     */
    

	private List<Categoria> Categorias;
	
    /**
     *Construtor setando a rota e qual
     *ser� passado para o navegador.
     */
    public CategoriaBean() {
    	route_base = "/cadastro/Categoria/";
        setModel(new Categoria());
    }
    /**
     * Override do m�todo criar(GET)
     * efetuando cast da basecontroller para CategoriaController
     * para ser poss�vel criar a lista de Categorias.
     */
    @Override
    public String criar() {
        controller = PersistenceFactory.getCategoriaPersistanceFactory();
    	setCategorias(((CategoriaPersistence) controller).listarGrupos());

//    	return route_base + CRIAR_PAGE + FACES_REDIRECT;
	    return route_base + CRIAR_PAGE;
    }
    
    /**
     * Override da fun��o incluir(POST)
     * seta o Slug, e verifica se � uma Categoria
     * selecion�vel ou n�o, e manda para o
     * m�todo do super criar.
     */
    @Override
    public String incluir() {
    	getModel().setSlug(Utils.toSlug(getModel().getNome()));
    	if(!getModel().getSelecionavel())
    		getModel().setIdSuperCategoria(-1);

        controller = PersistenceFactory.getCategoriaPersistanceFactory();
    	return super.incluir();
    }
    
    /**
     * Override do m�todo Editar(GET)
     * efetua um cast no controller para gerar
     * a lista de Categorias.
     */
    @Override
    public String editar(Categoria vo) {
        controller = PersistenceFactory.getCategoriaPersistanceFactory();
    	setCategorias(((CategoriaPersistence) controller).listarGrupos());
    	
    	return super.editar(vo);
    }
    
    /**
     * Override do Edtitar(POST)
     * seta o Slug, e verifica se � uma Categoria
     * selecion�vel ou n�o, e manda para o
     * m�todo do super editar. 
     */
    @Override
    public String editar() {
    	getModel().setSlug(Utils.toSlug(getModel().getNome()));
    	if(!getModel().getSelecionavel())
    		getModel().setIdSuperCategoria(-1);

        controller = PersistenceFactory.getCategoriaPersistanceFactory();
    	return super.editar();
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

        controller = PersistenceFactory.getCategoriaPersistanceFactory();
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
		
		if(getModel().getSelecionavel() && getModel().getIdSuperCategoria() <= 0) {
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SubCategoria tem de ter uma super Categoria!", null));
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
		setModel(new Categoria());
	}
	
	/**
	 * Verifica se a op��o selecion�vel est� marcada
	 * e se tiver, retorna sim.
	 * @param Categoria
	 * @return
	 */
	public String isGrupo(Categoria Categoria) {
		if(Categoria.getSelecionavel())
			return "N�o";
		return "Sim";
	}

	/**
	 * @return the Categorias
	 */
	public List<SelectItem> getCategorias() {
		List<SelectItem> items = new ArrayList<SelectItem>();
	    for (Categoria c : this.Categorias) {
	        items.add(new SelectItem(c.getId(), c.getNome()));
	    }
	    return items;
	}

	/**
	 * @param Categorias the Categorias to set
	 */
	public void setCategorias(List<Categoria> Categorias) {
		this.Categorias = Categorias;
	}
}
