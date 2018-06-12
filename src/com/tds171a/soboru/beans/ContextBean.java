/**
 * 
 */
package com.tds171a.soboru.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.tds171a.soboru.models.Usuario;
import com.tds171a.soboru.persistence.usuario.UsuarioPersistence;
import com.tds171a.soboru.utils.PersistenceFactory;

/**
 * Classe de contexto para identificar o usuário logado.
 * 
 * @author Baracho
 * 
 * @since 23/05/2015
 * 
 * @version 1.0
 * 
 */

@ManagedBean(name = "contextBean")
@SessionScoped
public class ContextBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8579898445046691422L;

	/*
	 * Variáveis de instância
	 */

	private Usuario usuarioLogado;

	/**
	 * 
	 */
	public ContextBean() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Métodos de acesso
	 */

	/**
	 * @return the usuarioLogado
	 */
	public Usuario getUsuarioLogado() {

		FacesContext context = FacesContext.getCurrentInstance();

		ExternalContext external = context.getExternalContext();

		String email = external.getRemoteUser();

		if (this.usuarioLogado == null || !email.equals(this.usuarioLogado.getEmail())) {
			if (email != null) {
				UsuarioPersistence usuarioPersistence = PersistenceFactory.getUsuarioPersistenceFactory();
				this.usuarioLogado = usuarioPersistence.selecionarPorEmail(email);

			}
		}

		return usuarioLogado;
	}

	/*
	 * Operações da classe
	 */

	/**
	 * @param usuarioLogado
	 *            the usuarioLogado to set
	 */
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((usuarioLogado == null) ? 0 : usuarioLogado.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ContextBean)) {
			return false;
		}
		ContextBean other = (ContextBean) obj;
		if (usuarioLogado == null) {
			if (other.usuarioLogado != null) {
				return false;
			}
		} else if (!usuarioLogado.equals(other.usuarioLogado)) {
			return false;
		}
		return true;
	}

}
