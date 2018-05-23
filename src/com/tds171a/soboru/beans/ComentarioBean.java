/**
 * 
 */
package com.tds171a.soboru.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.tds171a.soboru.models.Comentario;
import com.tds171a.soboru.persistence.categoria.CategoriaPersistence;
import com.tds171a.soboru.persistence.comentario.ComentarioPersistence;
import com.tds171a.soboru.utils.PersistenceFactory;

@Named("comentarioBean")
@RequestScoped
/**
 * Cria��o do bean herando de beanbase passando
 * o vo utilizada.
 */
public class ComentarioBean extends BeanBase<Comentario> {

	/**
     *criando o serial do bean
     */	
	private static final long serialVersionUID = -4213587420111577038L;
	/**
     *Construtor setando a rota e qual
     *ser� passado para o navegador.
     */
    public ComentarioBean() {
    	route_base = "/cadastro/Categoria/";
//        controller = PersistenceFactory.getCategoriaPersistanceFactory();
        setModel(new Comentario());
    }
	
	/**
	 * Override do m�todo incluir(GET)
	 * Recebe o id da receita para setar no coment�rio. 
	 * @param receitaId
	 * @return
	 */
	public String incluir(int receitaId){
		getModel().setReceitaId(receitaId);
		return incluir();
	}
	
	/**
	 * M�todo que puxa o usu�rio logado e 
	 * retorna para o incluir do super.
	 * depois faz um redirect para a p�gina da 
	 * receita.
	 */
	@Override
	public String incluir(){
		getModel().setUsuarioId(SessionContext.getInstance().getUsuarioLogado().getId());
		
		super.incluir();
		
		return "/receita/"+EXIBIR_PAGE+FACES_REDIRECT;
	}

	/**
     * Override do deletar, onde verifica a sessao, 
     * se existe um �tem v�lido e se n�o houver, retorna a 
     * pagina de cria��o.
     */
	@Override
	public String deletar() {
        FacesContext context = FacesContext.getCurrentInstance();
        controller = PersistenceFactory.getComentarioPersistanceFactory();
    	
        if(((ComentarioPersistence)controller).remover(getModel())) {
            context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado com sucesso!", null));
        } else {
            context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel deletar.", null));
            return route_base + DELETAR_PAGE;
        }

        limparModel();

        return listar();
	}

	/**
	 * Cria uma nova vo para limpar os campos para um novo registro
	 * sem interferencia de dados cadastrados anteriormente.
	 */	@Override
	public void limparModel() {
		// TODO Auto-generated method stub
		
	}
}
