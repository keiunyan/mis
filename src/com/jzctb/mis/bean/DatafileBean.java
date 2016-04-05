package com.jzctb.mis.bean;

public class DatafileBean {
    public String getTablespace() {return tablespace;}
	public void setTablespace(String value) {this.tablespace = value;}
	
	public String getSize() {return size;}
	public void setSize(String value) {this.size = value;}

	public String getStatus() {return status;}
	public void setStatus(String value) {this.status = value;}

	public String getUsed() {return used;}
	public void setUsed(String value) {this.used = value;}

	public String getUsedPre() {return usedPre;}
	public void setUsedPre(String value) {this.usedPre = value;}

	public String getFileName() {return fileName;}
	public void setFileName(String value) {this.fileName = value;}

	public String getAutoExtend() {return autoExtend;}
	public void setAutoExtend(String value) {this.autoExtend = value;}

	private String tablespace;
	private String status;
	private String size;
	private String used;
	private String usedPre;
	private String fileName;
	private String autoExtend;

}
