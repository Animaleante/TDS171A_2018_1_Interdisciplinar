/**
 * 
 */
package com.tds171a.soboru.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tds171a.soboru.vos.Usuario;

/**
 * @author Diogo
 *
 */
public class LoginFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession sessao = ((HttpServletRequest) request).getSession();
		// if(!SessionContext.getInstance().isLogado()) {
		if(sessao.getAttribute("usuarioLogado") == null) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/login/");
		} else if(!((Usuario)sessao.getAttribute("usuarioLogado")).getRole().getIsAdmin()) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/404.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}
}
