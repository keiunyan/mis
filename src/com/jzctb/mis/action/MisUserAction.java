package com.jzctb.mis.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jzctb.mis.db.MisUserDao;
import com.jzctb.mis.bean.MisUserBean;

public class MisUserAction extends MisAction {
	
	private static final long serialVersionUID = -8004359458011654761L;
	
	public MisUserAction(){
		user = new MisUserBean();
		
	}

	public String Login() throws Exception{
		request = ServletActionContext.getRequest();
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		user.setUserid(userid);
		user.setPassword(password);
		
		MisUserDao misUserDao = new MisUserDao();
		String result = misUserDao.LogonVerifi(user);

		if("1001".equals(result)){
			addActionError("用户名不存在！");
			request.getSession().setAttribute("user", null);
			return "logout";
		}else if("1002".equals(result)){
			addActionError("密码不正确！");
			request.getSession().setAttribute("user", null);
			return "logout";
		}else if("1003".equals(result)){
			addActionError("用户处于锁定状态，禁止登录！");
			request.getSession().setAttribute("user", null);
			return "logout";
		}
		
		request.getSession().setAttribute("user", user);
		return "login";
	}
	
	public void userIsExist() throws IOException{
		request = ServletActionContext.getRequest();
		String userid = request.getParameter("userid");
		logger.debug("userid = ["+userid+"]");
		boolean result = new MisUserDao().userIsExist(userid);
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(result){
			out.write("1");
		}else{
			out.write("0");
		}
	}
	public String changPasswd(){
		
		return SUCCESS;
	}
	
	public String Logout() throws Exception{
		ServletActionContext.getRequest().getSession().setAttribute("user", null);
		return "logout";
	}
	
	private MisUserBean user;

	public MisUserBean getUser() {
		return user;
	}

	public void setUser(MisUserBean user) {
		this.user = user;
	}
}
