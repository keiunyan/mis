package com.jzctb.mis.bean;

public class SessionBean{

    public SessionBean(){}

    public void setSid(String value){sid=value;}
    public void setSerial(String value){serial=value;}
    public void setSqlId(String value){sqlId=value;}
    public void setUserName(String value){userName=value;}
    public void setSecInWait(String value){secInWait=value;}
    public void setCommand(String value){command=value;}
    public void setMachine(String value){machine=value;}
    public void setOsUser(String value){osUser=value;}
    public void setStatus(String value){status=value;}
    public void setModule(String value){module=value;}
    public void setAction1(String value){action1=value;}
    public void setClientInfo(String value){clientInfo=value;}
    
	public String getSid(){return sid;}
    public String getSerial(){return serial;}
    public String getSqlId(){return sqlId;}
    public String getUserName(){return userName;}
    public String getSecInWait(){return secInWait;}
    public String getCommand(){return command;}
    public String getMachine(){return machine;}
    public String getOsUser(){return osUser;}
    public String getStatus(){return status;}
    public String getModule(){return module;}
    public String getAction1(){return action1;}
    public String getClientInfo(){return clientInfo;}
    

	private String sid="";
    private String serial="";
	private String sqlId="";
	private String userName="";
	private String secInWait="";
    private String command="";
    private String machine="";
    private String osUser="";
    private String status="";
    private String module="";
    private String action1="";
    private String clientInfo="";
     
}