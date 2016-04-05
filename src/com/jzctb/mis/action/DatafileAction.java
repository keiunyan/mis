package com.jzctb.mis.action;

import com.jzctb.mis.bean.DatafileBean;
import com.jzctb.mis.db.DatafileDao;

import java.util.*;

public class DatafileAction extends MisAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1747234201208149377L;
	
	public DatafileAction(){
		super();
	}

	public List<DatafileBean> getList(){return list;}

	public String execute() throws Exception
	{
		return "input";
	}
	
	public String Search() throws Exception{
		DatafileDao datafileDao = new DatafileDao(dbName);
        list = datafileDao.Search(pageNum, pageSize, tablespaceName.trim());
        totalRows = datafileDao.totalRows;

		return "success";
	}
	
	public String getTablespaceName() {
		return tablespaceName;
	}

	public void setTablespaceName(String tablespaceName) {
		this.tablespaceName = tablespaceName;
	}

	private List<DatafileBean> list = null;
	
	
	private String tablespaceName = "";

}
