/**
 * 
 */
package com.tds171a.soboru.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("contatoBean")
@SessionScoped
/**
 * Criação do bean herando de beanbase passando
 * o vo utilizada.
 */
public class ContatoBean implements Serializable {

	/**
	 * Criação do serial único
	 */
	private static final long serialVersionUID = -4706081572950852709L;
	
	/**
	 * variável do tipo String para guardar
	 * o nome do contato.
	 */
	private String nome;
	
	/**
	 * variável do tipo String para guardar
	 * o email do contato.
	 */
	private String email;
	
	/**
	 * variável do tipo Long para guardar
	 * o número do contato.
	 */
	private Long telefone;
	
	/**
	 * variável do tipo String para guardar
	 * o texto da mensagem.
	 */
	private String mensagem;
	
	/**
	 * variável que recebe a rota para o
	 * navegador
	 */
	private String route_base = "/contato/";

	/**
     *Construtor setando a rota e qual
     *será passado para o navegador.
     */
	public ContatoBean() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Método que direciona
	 * para o índex do contato.
	 * @return
	 */
	public String index() {
		return route_base + "index";
	}
	
	/**
	 * Método GET limpa os campos
	 * e envia os forms para o cliente.
	 * @return para o índex
	 */
	public String enviar() {
		// TODO - enviar mensagem
		
		setNome("");
		setEmail("");
		setTelefone(null);
		setMensagem("");
		
		return index();
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public Long getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
