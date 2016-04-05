package com.jzctb.mis.bean;

public class DiskgroupBean {
	public void setName(String value){this.name=value;}
	public void setState(String value){this.state=value;}
	public void setType(String value){this.type=value;}
	public void setSectorSize(String value){this.sectorSize=value;}
	public void setBlockSize(String value){this.blockSize=value;}
	public void setAllocUnitSize(String value){this.allocUnitSize=value;}
	public void setTotal(String value){this.total=value;}
	public void setFree(String value){this.free=value;}
	public void setReqFree(String value){this.reqFree=value;}
	public void setUsableFile(String value){this.usableFile=value;}
	public void setOfflineDisks(String value){this.offlineDisks=value;}
	public void setVotingFiles(String value){this.votingFiles=value;}
	
	public String getName(){return name;}
	public String getState(){return state;}
	public String getType(){return type;}
	public String getSectorSize(){return sectorSize;}
	public String getBlockSize(){return blockSize;}
	public String getAllocUnitSize(){return allocUnitSize;}
	public String getTotal(){return total;}
	public String getFree(){return free;}
	public String getReqFree(){return reqFree;}
	public String getUsableFile(){return usableFile;}
	public String getOfflineDisks(){return offlineDisks;}
	public String getVotingFiles(){return votingFiles;}


	private String name;
	private String state;
	private String type;
	private String sectorSize;
	private String blockSize;
	private String allocUnitSize;
	private String total;
	private String free;
	private String reqFree;
	private String usableFile;
	private String offlineDisks;
	private String votingFiles;

}
