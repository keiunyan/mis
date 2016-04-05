package com.jzctb.mis.action;

import java.io.PrintWriter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class TestAction extends MisAction {

	private static final long serialVersionUID = 3571925190917039162L;

	public TestAction(){
		
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		this.info = "";
		return super.execute();
	}
	
	public void Search() throws Exception {
		logger.debug("INFO = ["+this.info+"]");
		this.info = "this is a information!";
		this.getRequestParams();
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String json = "{\"statusCode\":\"200\",\"message\":\"太好了，操作成功了！\",\"confirmMsg\":\"啦啦啦\",\"info\":\"this is a information!\"}";
		writer.write(json);
		//writer.print(this.info);
		writer.flush();
		writer.close();  

		//return SUCCESS;
	}
	
	public String getInfo(){
		return this.info;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
	
	private String info = "";
}
