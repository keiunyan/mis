package com.jzctb.mis.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
// 编码过滤器
public class EncodingFilter implements Filter {

	private String charset = null;
	private boolean ignore = false;

	public void init(FilterConfig filterConfig) throws ServletException {
		String charset = filterConfig.getInitParameter("charset");
		String ignore = filterConfig.getInitParameter("ignore");
		if (this.charset == null)
			this.charset = charset;
		if ("true".equals(ignore)) {
			this.ignore = true;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!ignore) {
				request.setCharacterEncoding(charset);
				response.setCharacterEncoding(charset);
//				response.setContentType("text/html; charset="+charset);
		}
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		charset = null;
		ignore = false;
	}
	public static Logger logger = Logger.getLogger(EncodingFilter.class); 
}
