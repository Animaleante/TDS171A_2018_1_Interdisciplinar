package com.tds171a.soboru.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.tds171a.soboru.controllers.ReceitaController;
import com.tds171a.soboru.controllers.ReportController;
import com.tds171a.soboru.vos.Receita;
import com.tds171a.soboru.vos.Report;

@Named("reportBean")
@SessionScoped
/**
 * Criação do bean herando de beanbase passando
 * o vo utilizada.
 */
public class ReportBean extends BeanBase<Report> {

	/**
     *criando o serial do bean
     */
	private static final long serialVersionUID = 4730432362349049623L;

	/**
	 *Lista do tipo receita
	 */
	private List<Receita> receitas;

	/**
     *Construtor setando a rota e qual
     *será passado para o navegador.
     */
	public ReportBean() {
		route_base = "/cadastro/report/";
		controller = new ReportController();
		setVo(new Report());
	}

	/**
	 * Override do criar, onde é criado um controller do tipo receita
	 * e incluido no receitas uma lista da receitacontroller.
	 */
	@Override
	public String criar()
	{
	    ReceitaController receitaController = new ReceitaController();
	    setReceitas(receitaController.listar());
	    return super.criar();
	}

	/**
	 * override onde passa o usuário logado
	 * e gera o GET do report
	 */
	@Override
	public String incluir()
	{		
	    getVo().setUsuarioId(SessionContext.getInstance().getUsuarioLogado().getId());

	    return super.incluir();
	}
	
	@Override
	public String deletar(Report vo) {
		return super.deletar(vo);
	}

	/**
     * Override do deletar, onde verifica a sessao, 
     * se existe um ítem válido e se não houver, retorna a 
     * pagina de criação.
     */
	@Override
	public String deletar() {
        FacesContext context = FacesContext.getCurrentInstance();

        if(((ReportController)controller).remover(getVo().getReceitaId(), getVo().getUsuarioId())) {
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
		// TODO Auto-generated method stub
		return super.validarDados();
	}
	
	
	/**
	 * Cria uma nova vo para limpar os campos para um novo registro
	 * sem interferencia de dados cadastrados anteriormente.
	 */
	@Override
	public void limparVo() {
		setVo(new Report());
	}

	

    /**
     * @return the receitas
     */
    public List<SelectItem> getReceitas()
    {
        List<SelectItem> items = new ArrayList<SelectItem>();
        for (Receita r : this.receitas) {
            items.add(new SelectItem(r.getId(), r.getNome()));
        }
        return items;
    }

    /**
     * @param pReceitas the receitas to set
     */
    public void setReceitas(List<Receita> pReceitas)
    {
        receitas = pReceitas;
    }

}
