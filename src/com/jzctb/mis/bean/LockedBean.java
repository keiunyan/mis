package com.jzctb.mis.bean;

public class LockedBean {
    public void setProcess(String process){this.process=process;}
	public void setSid(String sid){this.sid=sid;}
	public void setSerial(String serial){this.serial=serial;}
	public void setObjectName(String objectName){this.objectName=objectName;}
	public void setOwner(String owner){this.owner=owner;}
	public void setObjectType(String objectType){this.objectType=objectType;}
	public void setOraUName(String oraUName){this.oraUName=oraUName;}
	public void setOsUName(String osUName){this.osUName=osUName;}
	public void setLockedMode(String lockedMode){this.lockedMode=lockedMode;}
	
	public String getProcess(){return process;}
	public String getSid(){return sid;}
	public String getSerial(){return serial;}
	public String getObjectName(){return objectName;}
	public String getOwner(){return owner;}
	public String getObjectType(){return objectType;}
	public String getOraUName(){return oraUName;}
	public String getOsUName(){return osUName;}
	public String getLockedMode(){return lockedMode;}


	private String process="";
	private String sid="";
	private String serial="";
	private String objectName="";
	private String owner="";
	private String objectType="";
	private String oraUName="";
	private String osUName="";
	private String lockedMode="";
}
