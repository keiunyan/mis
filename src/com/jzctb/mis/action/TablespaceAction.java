package com.jzctb.mis.action;

import com.jzctb.mis.db.TablespaceDao;
import com.jzctb.mis.bean.TablespaceBean;
import java.util.*;

public class TablespaceAction extends MisAction{

	private static final long serialVersionUID = 3524451155616159218L;
	
	public TablespaceAction(){

	}

	private List<TablespaceBean> list = null;
	public List<TablespaceBean> getList(){return list;}

	public String execute() throws Exception
	{
		return "input";
	}
	
	public String Search() throws Exception{
		logger.debug("tablespaceName = ["+tablespaceName+"]");
		TablespaceDao tablespaceDao = new TablespaceDao(dbName);
		list = tablespaceDao.Search(pageNum, pageSize, tablespaceName);
		logger.debug("list.size = ["+list.size()+"]");
		totalRows  = tablespaceDao.totalRows;
		return "success";
	}
	
	public void addDatafile(){
		
	}

	public String getTablespaceName() {
		return tablespaceName;
	}

	public void setTablespaceName(String tablespaceName) {
		this.tablespaceName = tablespaceName;
	}
	
	private String tablespaceName = "";

}
