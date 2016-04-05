package com.jzctb.mis.bean;

public class TablespaceBean {
	
	public void setTablespaceName(String value){this.tablespaceName=value;}
	public void setDatafiles(String value){this.datafiles=value;}
	public void setBytes(String value){this.bytes=value;}
	public void setUsedBytes(String value){this.usedbytes=value;}
	public void setFreeBytes(String value){this.freebytes=value;}
	public void setMaxBytes(String value){this.maxbytes=value;}
	public void setBlockSize(String value){this.block_size=value;}
	public void setStatus(String value){this.status=value;}
	public void setLogging(String value){this.logging=value;}
	
	public String getTablespaceName(){return tablespaceName;}
	public String getDatafiles(){return datafiles;}
	public String getBytes(){return bytes;}
	public String getUsedBytes(){return usedbytes;}
	public String getFreeBytes(){return freebytes;}
	public String getMaxBytes(){return maxbytes;}
	public String getBlockSize(){return block_size;}
	public String getStatus(){return status;}
	public String getLogging(){return logging;}

	private String tablespaceName;
	private String datafiles;
	private String bytes;
	private String usedbytes;
	private String freebytes;
	private String maxbytes;
	private String block_size;
	private String status;
	private String logging;

}
