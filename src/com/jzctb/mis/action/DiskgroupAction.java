package com.jzctb.mis.action;

import com.jzctb.mis.bean.DiskgroupBean;
import com.jzctb.mis.db.DiskgroupDao;

import java.util.*;

public class DiskgroupAction extends MisAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1747234201208149377L;
	
	public DiskgroupAction(){
		super();
	}

	public List<DiskgroupBean> getList(){return list;}

	public String execute() throws Exception
	{
		return "input";
	}
	
	public String Search() throws Exception{
		DiskgroupDao diskgroupDao = new DiskgroupDao(dbName);
        list = diskgroupDao.Search(pageNum, pageSize);
        totalRows = diskgroupDao.totalRows;

		return "success";
	}

	private List<DiskgroupBean> list = null;
    
}
