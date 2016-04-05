package com.jzctb.mis.filter;

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

import org.apache.log4j.Logger;

import com.jzctb.mis.bean.MisUserBean;

/**
 * 登录过滤器，对用户访问的页面进行验证，未登录的用户不能访问。
 * @author YXS
 *
 */
public class LoginFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ignore = "true".equals(filterConfig.getInitParameter("ignore"))?true:false;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if(ignore){
			chain.doFilter(request, response);
			return;
		}
					
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		
		String path = servletRequest.getRequestURI();
		logger.info("servletRequest.getRequestURI() = ["+path+"]");
		
		String contextPath = servletRequest.getContextPath();
		logger.info("servletRequest.getContextPath() = ["+contextPath+"]");
				
		MisUserBean user = (MisUserBean) session.getAttribute("user");
		if(user==null){
			servletResponse.sendRedirect(contextPath+"/login.jsp");
		}else if("1".equals(user.getRule()) &&  
				path.indexOf("/admin") >= 0){
			// 非管理用户禁止访问管理页面
			// 如果访问的是管理页面，但用户的角色为查询角色，则跳转到登录页
			servletResponse.sendRedirect(contextPath+"/login.jsp");
		}else{
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void destroy() {
	}
	
	private boolean ignore = false;
    public static Logger logger = Logger.getLogger(LoginFilter.class); 

}
