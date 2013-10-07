package br.com.portalcom.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName="LoginFilter", urlPatterns="/portalcom/protected/*")
public class LoginFilter implements Filter {
	
	@Override
    public void destroy() {
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
 
        /*String userName = SecurityAssociation.getPrincipal().getName();
 
        System.out.println("Yeeey! Get me here and find me in the database: " + userName);*/
 
        filterChain.doFilter(servletRequest, servletResponse);
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
}
