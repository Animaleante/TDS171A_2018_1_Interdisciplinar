/**
 * 
 */
package com.tds171a.soboru.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.tds171a.soboru.models.Ingrediente;
import com.tds171a.soboru.models.Medida;
import com.tds171a.soboru.models.Receita;
import com.tds171a.soboru.models.ReceitaIngrediente;

@Named("receitaIngredienteBean")
@SessionScoped
/**
 * @author PC970
 *
 */
public class ReceitaIngredienteBean implements Serializable {

	/**
	 * Cria��o do serial �nico
	 */
	private static final long serialVersionUID = -8599053328988864924L;

	/**
	 * Cria��o de vari�veis e controllers 
	 * utilizadas no bean.
	 */
	private Receita receita;
	private List<ReceitaIngrediente> lista;
	private List<Ingrediente> listaIngredientes;
	private List<Medida> listaMedidas;

	/**
	 * Construtor da bean onde � iniciado as controllers
	 * que ser�o utilizadas, al�m das listas.
	 */
	public ReceitaIngredienteBean() {
		setLista(new ArrayList<ReceitaIngrediente>());
		setListaIngredientes(new ArrayList<Ingrediente>());
		setListaMedidas(new ArrayList<Medida>());
	}

	/**
	 * M�todo GET da receita onde � criada
	 * as listas de ingredientes e medidas
	 * e criada a tela para o cliente criar a receita.
	 * @param receitaId
	 * @return
	 */
	public String criar(int receitaId) {
//		setReceita(receitaController.selecionar(receitaId));
//		setLista(receitaController.listarIngredientes(getReceita()));
//		setListaIngredientes(ingredienteController.listar());
//		setListaMedidas(medidaController.listar());

		return "/cadastro/receita-ingrediente/criar?faces-redirect=true";
	}

	/**
	 * Metodo que inclui um campo para um novo ingrediente.
	 */
	public void adicionar() {
		lista.add(new ReceitaIngrediente());
		
	}

	/**
	 * M�todo que remove um ingrediente cadastrado.
	 * @param receitaIngrediente
	 */
	public void remover(ReceitaIngrediente receitaIngrediente) {
		lista.remove(receitaIngrediente);
	}

	/**
	 * M�todo que salva o v�nculo entre receita e ingrediente
	 *  incluindo a lista de ingredientes e passado
	 *  na tela de receita.
	 * @return
	 */
	public String salvar() {
//		receitaIngredienteController.incluirLista(getReceita().getId(), getLista());
		return "/cadastro/receita/"+BeanBase.INDEX_PAGE+BeanBase.FACES_REDIRECT;
	}

	/**
	 * @return the receita
	 */
	public Receita getReceita() {
		return receita;
	}

	/**
	 * @param receita the receita to set
	 */
	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	/**
	 * @return the lista
	 */
	public List<ReceitaIngrediente> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<ReceitaIngrediente> lista) {
		this.lista = lista;
	}

	/**
	 * @return the listaIngredientes
	 */
	public List<SelectItem> getListaIngredientes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
	    for (Ingrediente i : this.listaIngredientes) {
	        items.add(new SelectItem(i.getId(), i.getNome()));
	    }
	    return items;
	}

	/**
	 * @param listaIngredientes the listaIngredientes to set
	 */
	public void setListaIngredientes(List<Ingrediente> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}

	/**
	 * @return the listaMedidas
	 */
	public List<SelectItem> getListaMedidas() {
		List<SelectItem> items = new ArrayList<SelectItem>();
	    for (Medida m : this.listaMedidas) {
	        items.add(new SelectItem(m.getId(), m.getNome()));
	    }
	    return items;
	}

	/**
	 * @param listaMedidas the listaMedidas to set
	 */
	public void setListaMedidas(List<Medida> listaMedidas) {
		this.listaMedidas = listaMedidas;
	}
}
