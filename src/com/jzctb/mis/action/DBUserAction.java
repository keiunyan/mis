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
        list = dbUserDao.Search(pageNum, pageSize, userName.trim(), accountStatus);
        totalRows = dbUserDao.totalRows;

		return "success";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String value) {
		this.userName = value;
	}
	public String getAccountStatus(){return this.accountStatus;}
	public void setAccountStatus(String value){this.accountStatus=value;}
	
	private List<DBUserBean> list = null;
	private String userName;
	private String accountStatus;
}
