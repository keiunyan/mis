package com.jzctb.mis.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ibm.mq.MQException;
import com.jzctb.mq.*;

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
	
	/**
	 * kill session: alter system kill session 'sid,serial#';
	 */
	public void killSession() throws IOException{
		HttpServletRequest  req = ServletActionContext.getRequest();
		HttpServletResponse rsp = ServletActionContext.getResponse();
		String sid = req.getParameter("sid");
		logger.debug("kill session: sid = ["+sid+"]");
		rsp.setCharacterEncoding("UTF-8");
		PrintWriter writer = rsp.getWriter();
		String json = "{\"statusCode\":\"200\",\"message\":\"结束会话成功\"}";
		writer.write(json);

	}
	
	public String getInfo(){
		return this.info;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
	
	public void mq(){
		MQSend mqsend = new MQSend();
		try {
			mqsend.send(new String[]{"1111111"});
		} catch (MQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MQRecv mqRecv= new MQRecv();
		try {
			mqRecv.recv();
		} catch (MQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String info = "";
}
