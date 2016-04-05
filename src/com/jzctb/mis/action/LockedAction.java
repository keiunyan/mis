package com.jzctb.mis.action;

import com.jzctb.mis.bean.LockedBean;
import com.jzctb.mis.db.LockedDao;

import java.util.*;

public class LockedAction extends MisAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1747234201208149377L;
	
	public LockedAction(){
		super();
	}

	public List<LockedBean> getList(){return list;}

	public String execute() throws Exception
	{
		return "input";
	}
	
	public String Search() throws Exception{
		LockedDao lockedDao = new LockedDao(dbName);
        list = lockedDao.Search(pageNum, pageSize, owner.trim());
        totalRows = lockedDao.totalRows;

		return "success";
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	private List<LockedBean> list = null;
	private String owner;
}
