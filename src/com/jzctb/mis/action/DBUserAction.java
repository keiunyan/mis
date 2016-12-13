package com.jzctb.mis.action;

import com.jzctb.mis.bean.DBUserBean;
import com.jzctb.mis.db.DBUserDao;

import java.util.*;

public class DBUserAction extends MisAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1747234201208149377L;
	
	public DBUserAction(){
		super();
	}

	public List<DBUserBean> getList(){return list;}

	public String execute() throws Exception
	{
		return "input";
	}
	
	public String Search() throws Exception{
		DBUserDao dbUserDao = new DBUserDao(dbName);
        list = dbUserDao.Search(pageNum, pageSize, username.trim());
        totalRows = dbUserDao.totalRows;

		return "success";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private List<DBUserBean> list = null;
	private String username;
}
