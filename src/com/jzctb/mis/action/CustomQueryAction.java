package com.jzctb.mis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jzctb.mis.db.CustomQueryDao;


public class CustomQueryAction extends MisAction {
	
	private static final long serialVersionUID = -8004359458011654761L;
	
	public CustomQueryAction(){
		ip       = "";    
		port     = "1521";
	    username = "";    
        password = "";  
        sql      = "";
	}
	
	@Override    
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		if(!validateSuccess)
			return INPUT;
		logger.debug("     sql = ["+sql+"]");
		
		CustomQueryDao customQueryDao = new CustomQueryDao();
		customQueryDao.query(username, password, ip, Integer.parseInt(port), dbName, sql, pageNum, pageSize);
		
		return super.execute();
	}
	
	
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		logger.debug("  dbname = ["+dbName+"]");
		logger.debug("      IP = ["+ip+"]");
		logger.debug("    port = ["+port+"]");
		logger.debug("username = ["+username+"]");
		logger.debug("password = ["+"********"+"]");
		
		validateSuccess = false;
		
		if("".equals(ip.trim())||"".equals(dbName.trim())||"".equals(username.trim())){
			return;
		}
		
		try{
			if("".equals(port))
				port = "1521";
			int p = Integer.parseInt(port);
			
		}catch(NumberFormatException e){
			logger.error("无效端口号");
			return;
		}
		
		validateSuccess = true;
		super.validate();
	}

	public String getIp         (){return this.ip      ;}
	public String getPort       (){return this.port    ;}
	public String getUsername   (){return this.username;}
	public String getPassword   (){return this.password;}
	public String getSql        (){return this.sql     ;}

	public void setIp         (String value){this.ip       = value;}
	public void setPort       (String value){this.port     = value;}
	public void setUsername   (String value){this.username = value;}
	public void setPassword   (String value){this.password = value;}
	public void setSql        (String value){this.sql      = value;}

	private String ip;
	private String port;
	private String username;
	private String password;
	private String sql;

	private boolean validateSuccess = false;
}
