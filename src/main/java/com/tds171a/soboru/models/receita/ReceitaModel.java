package com.tds171a.soboru.models.receita;

import java.util.List;

import com.tds171a.soboru.models.ModelBase;
import com.tds171a.soboru.vos.Ingrediente;
import com.tds171a.soboru.vos.Receita;
import com.tds171a.soboru.vos.ReceitaIngrediente;
import com.tds171a.soboru.vos.Tag;
import com.tds171a.soboru.vos.Utensilio;

public class ReceitaModel extends ModelBase<Receita> {
	
	/**
	 * Construtor da classe MedidaModel
	 */
	public ReceitaModel() {
		dao = new ReceitaDAO();
	}
	
	/**
	 * Trás o último id inserido da receita.
	 * @return
	 */
	public int selecionarUltimoIdInserido() {
		return ((ReceitaDAO) dao).selecionarUltimoIdInserido();
	}
	
	/**
	 * Método de listar receitas do admin
	 * onde apresenta todas as receitas.
	 * @return
	 */
	public List<Receita> listarAdmin() {
		return ((ReceitaDAO) dao).listarAdmin();
	}

	/**
	 * Método de incluir as tags na receita.
	 * @param receita
	 * @param tag
	 * @return
	 */
	public boolean incluirTag(Receita receita, Tag tag) {
		return ((ReceitaDAO) dao).incluirTag(receita, tag);
	}

	/**
	 * Método que lista os utensilios anexados a
	 * receita.
	 * @param receita
	 * @return
	 */
	public List<Utensilio> listarUtensilios(Receita receita) {
		return ((ReceitaDAO) dao).listarUtensilios(receita);
	}
	
	/**
	 * Método que recebe o parâmetro de
	 * busca e retorna a lista compatível
	 * @param termoBusca
	 * @return
	 */
	public List<Receita> selecionarPorNome(String termoBusca) {
		return ((ReceitaDAO) dao).selecionarPorNome(termoBusca);
	}
	
	/**
	 * Método que trás as receitas pesquisadas por
	 * ingredientes.
	 * @param ingredientes
	 * @return
	 */
	public List<Receita> selecionarPorIngredientes(List<Ingrediente> ingredientes) {
		return ((ReceitaDAO) dao).selecionarPorIngredientes(ingredientes);
	}

	/**
	 * Método que pesquisa por nome e
	 * ingredientes
	 * @param termoBusca
	 * @param ingredientes
	 * @return
	 */
	public List<Receita> selecionarPorNomeEIngredientes(String termoBusca, List<Ingrediente> ingredientes) {
		return ((ReceitaDAO) dao).selecionarPorNomeEIngredientes(termoBusca, ingredientes);
	}

	/**
	 * Método que registra os utensilios na
	 * receita.
	 * @param receitaId
	 * @param lista
	 */
	public void registrarUtensilios(int receitaId, List<Utensilio> lista) {
		((ReceitaDAO) dao).registrarUtensilios(receitaId, lista);
	}

	/**
	 * Método que trás uma lista de ingredientes
	 * cadastrados na receita
	 * @param receita
	 * @return
	 */
	public List<ReceitaIngrediente> listarIngredientes(Receita receita) {
		return ((ReceitaDAO) dao).listarIngredientes(receita);
	}

	/**
	 * Método que retorna os ingredientes de 
	 * um usuário
	 * @param usuarioId
	 * @return
	 */
	public List<Receita> selecionarPorUsuario(int usuarioId) {
		return ((ReceitaDAO) dao).selecionarPorUsuario(usuarioId);
	}

	/**
	 * Retorna a lista de favoritos de um usuário
	 * @param usuarioId
	 * @return
	 */
	public List<Receita> selecionarPorFavoritosDeUsuario(int usuarioId) {
		return ((ReceitaDAO) dao).selecionarPorFavoritosDeUsuario(usuarioId);
	}
	
	/**
	 * Método que inclui a receita no favorito
	 * @param receitaId
	 * @param usuarioId
	 * @return
	 */
	public boolean incluirFavorito(int receitaId, int usuarioId) {
		return ((ReceitaDAO) dao).incluirFavorito(receitaId, usuarioId);
	}

	/**
	 * Método que remove o favorito da receita
	 * @param receitaId
	 * @param usuarioId
	 * @return
	 */
	public boolean removerFavorito(int receitaId, int usuarioId) {
		return ((ReceitaDAO) dao).removerFavorito(receitaId, usuarioId);
	}

	/**
	 * Verifica se a receita está favoritada
	 * @param receitaId
	 * @param usuarioId
	 * @return
	 */
	public boolean isReceitaFavoritada(int receitaId, int usuarioId) {
		return ((ReceitaDAO) dao).isReceitaFavoritada(receitaId, usuarioId);
	}

	/**
	 * Método que cria um novo report para a
	 * receita
	 * @param receitaId
	 * @param usuarioId
	 * @return
	 */
	public boolean incluirReport(int receitaId, int usuarioId) {
		return ((ReceitaDAO) dao).incluirReport(receitaId, usuarioId);
	}

	/**
	 * Método que inclui uma nova pontuação para
	 * a receita.
	 * @param receitaId
	 * @param usuarioId
	 * @param pontos
	 * @return
	 */
	public boolean incluirPontuacao(int receitaId, int usuarioId, int pontos) {
		return ((ReceitaDAO) dao).incluirPontuacao(receitaId, usuarioId, pontos);
	}
	
	/**
	 * atualiza os dados do usuário de quantas
	 * receitas estão nos favoritos.
	 * @param receitaId
	 */
	public void atualizarFavs(int receitaId) {
		((ReceitaDAO) dao).atualizarFavs(receitaId);
	}

	/**
	 * Atualiza a pontuação média da receita.
	 * @param receitaId
	 */
	public void atualizarPontuacaoMedia(int receitaId) {
		((ReceitaDAO) dao).atualizarPontuacaoMedia(receitaId);
	}
}
