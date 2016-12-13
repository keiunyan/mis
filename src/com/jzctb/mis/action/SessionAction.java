package com.jzctb.mis.action;

import com.google.gson.Gson;
import com.jzctb.mis.db.SessionDao;
import com.jzctb.mis.bean.SessionBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class SessionAction extends MisAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7256052388619081115L;
	
	public SessionAction(){
       
	}

	private List<SessionBean> list = null;
	public List<SessionBean> getList(){return list;}

	public String execute() throws Exception
	{
		return "input";
	}
	
	public String Search() throws Exception{
		Map<String,String> params = getRequestParams();
		orderField = params.get("orderField");
		if(orderField==null) orderField="";
		orderDirection = params.get("orderDirection");
		if(orderDirection==null) orderDirection="";
		SessionDao sessionDao = new SessionDao(dbName);
        list = sessionDao.Search(pageNum,pageSize,username.trim(),orderField,orderDirection);
		totalRows = sessionDao.totalRows;
		return SUCCESS;
	}
	
	/**
	 * kill session: alter system kill session 'sid,serial#';
	 */
	public void killSession() throws IOException{
		HttpServletRequest  req = ServletActionContext.getRequest();
		String params = req.getParameter("sid");
		String sid = params.split(",")[0];
		String serial = params.split(",")[1];
		logger.debug("kill session: sid = ["+sid+"] serial = ["+serial+"]");

		SessionDao sessionDao = new SessionDao(dbName);
		String msg = sessionDao.kill(sid,serial);
		
		Gson gson = new Gson();
		Map<String,String> m = new LinkedHashMap<String,String>();
		if(msg.startsWith("0000")){
			m.put("statusCode", "200");
			m.put("message", msg);
		}else{
			m.put("statusCode", "300");
			m.put("message", msg);
		}
		HttpServletResponse rsp = ServletActionContext.getResponse();
		rsp.setCharacterEncoding("UTF-8");
		PrintWriter out = rsp.getWriter();
		out.write(gson.toJson(m));
		out.close();
		
	}
	
	private String username="";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
