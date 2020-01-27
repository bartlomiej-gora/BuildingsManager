package pl.bgora.utils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Servlet Filter implementation class SetEncodingFilter
 */
public class SetEncodingFilter implements Filter {

	private String encoding;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		encoding = null;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}

}
