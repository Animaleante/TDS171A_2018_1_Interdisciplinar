package com.tds171a.soboru.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PontuacaoId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6148075948298862026L;

	@Column(name="id_receita")
	private long id_receita;

	@Column(name="id_usuario")
	private long id_usuario;
	
	@SuppressWarnings("unused")
	private PontuacaoId() {}
	
	public PontuacaoId(long idReceita, long idUsuario) {
		this.id_receita = idReceita;
		this.id_usuario = idUsuario;
	}

	/**
	 * @return the id_receita
	 */
	public long getId_receita() {
		return id_receita;
	}

	/**
	 * @param id_receita the id_receita to set
	 */
	public void setId_receita(long id_receita) {
		this.id_receita = id_receita;
	}

	/**
	 * @return the id_usuario
	 */
	public long getId_usuario() {
		return id_usuario;
	}

	/**
	 * @param id_usuario the id_usuario to set
	 */
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if (obj == null || getClass() != obj.getClass()) 
            return false;
 
        PontuacaoId that = (PontuacaoId) obj;
        return Objects.equals(id_receita, that.id_receita) && 
               Objects.equals(id_usuario, that.id_usuario);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id_receita, id_usuario);
	}
}
