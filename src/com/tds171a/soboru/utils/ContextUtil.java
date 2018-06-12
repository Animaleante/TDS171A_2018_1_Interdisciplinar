/**
 * 
 */
package com.tds171a.soboru.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.tds171a.soboru.beans.ContextBean;

/**
 * Classe para fornecer uma inst�ncia da classe ContextBean.
 * 
 * @author Baracho
 * 
 * @since 23/05/2015
 * 
 * @version 1.0
 * 
 */
public class ContextUtil {

	/*
	 * Fun��o construtora
	 */
	public ContextUtil() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Opera��es da classe
	 */

	public static ContextBean getContextBean() {

		FacesContext context = FacesContext.getCurrentInstance();

		ExternalContext external = context.getExternalContext();

		HttpSession httpSession = (HttpSession) external.getSession(true);

		ContextBean contextBean = (ContextBean) httpSession.getAttribute("contextBean");
		
		if(contextBean == null)
			httpSession.setAttribute("contextBean", new ContextBean());
		
		contextBean = (ContextBean) httpSession.getAttribute("contextBean");
		
		System.out.println("Has contextBean: " + (contextBean != null));

		return contextBean;
	}

}
