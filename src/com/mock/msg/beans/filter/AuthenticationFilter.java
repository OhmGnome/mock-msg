package com.mock.msg.beans.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.mock.msg.beans.AuthBean;

/*
 * makes it so you can't go back after logging out
 * check the web.xml for more components
 */

@Component
@Scope("session")
public class AuthenticationFilter extends GenericFilterBean {
	
	@Autowired
	private AuthBean authBean;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(authBean.isLoggedIn()){
			chain.doFilter(request, response);
		} else {
			if(response instanceof HttpServletResponse){
				((HttpServletResponse) response).sendRedirect("/wot/login.xhtml");
			}
		}
	}

}
