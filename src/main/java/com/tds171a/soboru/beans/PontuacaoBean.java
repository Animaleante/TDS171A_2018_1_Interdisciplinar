package com.tds171a.soboru.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.tds171a.soboru.controllers.PontuacaoController;
import com.tds171a.soboru.controllers.ReceitaController;
import com.tds171a.soboru.vos.Pontuacao;

@Named("pontuacaoBean")
@SessionScoped
/**
 * bean de pontuacao
 *
 */
public class PontuacaoBean extends BeanBase<Pontuacao> {

	/**
	 * criando o serial do bean
	 */
	private static final long serialVersionUID = -8215281509767049355L;
	
	private ReceitaController receitaController;
	
	/**
     *Construtor setando a rota e qual
     *será passado para o navegador.
     */
	public PontuacaoBean() {
		route_base = "/cadastro/pontuacao/";
		controller = new PontuacaoController();
		receitaController = new ReceitaController();
		setVo(new Pontuacao());
	}

	/**
	 * Override do método deletar
	 * verifica se foi possível deletar ou não
	 * a pontuação e retorna a mensagem.
	 */
	@Override
	public String deletar() {
        FacesContext context = FacesContext.getCurrentInstance();

        if(((PontuacaoController)controller).remover(getVo().getReceitaId(), getVo().getUsuarioId())) {
        	receitaController.atualizarPontuacaoMedia(getVo().getReceitaId());
            context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado com sucesso!", null));
        } else {
            context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel deletar.", null));
            return route_base + DELETAR_PAGE;
        }

        limparVo();

        return listar();
	}

	/**
	 * Método que limpa os campos
	 * para evitar erro de dados errados.
	 */
	@Override
	public void limparVo() {
		setVo(new Pontuacao());
	}
}
