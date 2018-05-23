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

import com.tds171a.soboru.controllers.IngredienteController;
import com.tds171a.soboru.controllers.MedidaController;
import com.tds171a.soboru.controllers.ReceitaController;
import com.tds171a.soboru.controllers.ReceitaIngredienteController;
import com.tds171a.soboru.vos.Ingrediente;
import com.tds171a.soboru.vos.Medida;
import com.tds171a.soboru.vos.Receita;
import com.tds171a.soboru.vos.ReceitaIngrediente;

@Named("receitaIngredienteBean")
@SessionScoped
/**
 * @author PC970
 *
 */
public class ReceitaIngredienteBean implements Serializable {

	/**
	 * Criação do serial único
	 */
	private static final long serialVersionUID = -8599053328988864924L;

	/**
	 * Criação de variáveis e controllers 
	 * utilizadas no bean.
	 */
	private Receita receita;
	private ReceitaController receitaController;
	private IngredienteController ingredienteController;
	private MedidaController medidaController;
	private ReceitaIngredienteController receitaIngredienteController;
	private List<ReceitaIngrediente> lista;
	private List<Ingrediente> listaIngredientes;
	private List<Medida> listaMedidas;

	/**
	 * Construtor da bean onde é iniciado as controllers
	 * que serão utilizadas, além das listas.
	 */
	public ReceitaIngredienteBean() {
		receitaController = new ReceitaController();
		ingredienteController = new IngredienteController();
		medidaController = new MedidaController();
		receitaIngredienteController = new ReceitaIngredienteController();
		setLista(new ArrayList<ReceitaIngrediente>());
		setListaIngredientes(new ArrayList<Ingrediente>());
		setListaMedidas(new ArrayList<Medida>());
	}

	/**
	 * Método GET da receita onde é criada
	 * as listas de ingredientes e medidas
	 * e criada a tela para o cliente criar a receita.
	 * @param receitaId
	 * @return
	 */
	public String criar(int receitaId) {
		setReceita(receitaController.selecionar(receitaId));
		setLista(receitaController.listarIngredientes(getReceita()));
		setListaIngredientes(ingredienteController.listar());
		setListaMedidas(medidaController.listar());

		return "/cadastro/receita-ingrediente/criar?faces-redirect=true";
	}

	/**
	 * Metodo que inclui um campo para um novo ingrediente.
	 */
	public void adicionar() {
		lista.add(new ReceitaIngrediente());
		
	}

	/**
	 * Método que remove um ingrediente cadastrado.
	 * @param receitaIngrediente
	 */
	public void remover(ReceitaIngrediente receitaIngrediente) {
		lista.remove(receitaIngrediente);
	}

	/**
	 * Método que salva o vínculo entre receita e ingrediente
	 *  incluindo a lista de ingredientes e passado
	 *  na tela de receita.
	 * @return
	 */
	public String salvar() {
		receitaIngredienteController.incluirLista(getReceita().getId(), getLista());
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
