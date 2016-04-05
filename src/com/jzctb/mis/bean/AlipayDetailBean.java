package com.jzctb.mis.bean;

//支付宝交易明细
public class AlipayDetailBean{
    public AlipayDetailBean(){}

    public void setTrandate(String value){this.trandate=value;}
	public void setTrancode(String value){this.trancode=value;}
	public void setCardno(String value){this.cardno=value;}
	public void setSignno(String value){this.signno=value;}
	public void setTranamt(String value){this.tranamt=value;}
	public void setTranstat(String value){this.transtat=value;}
	public void setCharge(String value){this.charge=value;}
	public void setOrgothstan(String value){this.orgothstan=value;}
	public void setExtention(String value){this.extention=value;}
	public void setOthstan(String value){this.othstan=value;}
	public void setSrvstan(String value){this.srvstan=value;}
	public void setRspinf(String value){this.rspinf=value;}
	public void setHostrspcode(String value){this.hostrspcode=value;}

    public String getTrandate(){return trandate;};
	public String getTrancode(){return trancode;}
	public String getCardno(){return cardno;}
	public String getSignno(){return signno;}
	public String getTranamt(){return tranamt;}
	public String getTranstat(){return transtat;}
	public String getCharge(){return charge;}
	public String getOrgothstan(){return orgothstan;}
	public String getExtention(){return extention;}
	public String getOthstan(){return othstan;}
	public String getSrvstan(){return srvstan;}
	public String getRspinf(){return rspinf;}
	public String getHostrspcode(){return hostrspcode;}

	private String trandate="";
	private String trancode="";
	private String cardno="";
	private String signno="";
	private String tranamt="";
	private String transtat="";
	private String charge="";
	private String orgothstan="";
	private String extention="";
	private String othstan="";
	private String srvstan="";
	private String rspinf="";
	private String hostrspcode="";

	public void setTrandate1(String value){this.trandate1=value;}
	public void setTrandate2(String value){this.trandate2=value;}
	public String getTrandate1(){return trandate1;}
	public String getTrandate2(){return trandate2;}
	private String trandate1="";
	private String trandate2="";

}