package com.jzctb.mis.action;

import com.jzctb.mis.db.SessionDao;
import com.jzctb.mis.bean.SessionBean;
import java.util.*;

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
        list = sessionDao.Search(pageNum,pageSize,username,orderField,orderDirection);
		totalRows = sessionDao.totalRows;
		return SUCCESS;
	}
	
	private String username="";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
