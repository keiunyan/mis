package com.jzctb.mis.bean;

public class AlipaySumBean {
	
	public void setTrandate(String trandate){this.trandate=trandate;}
	public void setPay(String pay){this.pay=pay;}
	public void setRefund(String refund){this.refund=refund;}
	public void setBalance(String balance){this.balance=balance;}
	public void setBishu1(int bishu){this.bishu1=bishu;}
	public void setBishu2(int bishu){this.bishu2=bishu;}

	public String getTrandate(){return trandate;}
	public String getPay(){return pay;}
	public String getRefund(){return refund;}
	public String getBalance(){return balance;}
	public int    getBishu1(){return bishu1;}
	public int    getBishu2(){return bishu2;}

	private String trandate; // 交易日期
	private String pay;      // 支付
	private String refund;   // 退货
	private String balance;  // 支付-退货
	private int    bishu1;   // 支付笔数
	private int    bishu2;   // 退货笔数
	
}
