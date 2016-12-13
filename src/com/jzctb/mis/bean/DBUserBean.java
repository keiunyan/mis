package com.jzctb.mis.bean;

public class DBUserBean {
	
	public void setUsername              (String value){this.username            =value;}
	public void setAccount_status        (String value){this.account_status      =value;}
	public void setExpiry_date           (String value){this.expiry_date         =value;}
	public void setDefault_tablespace    (String value){this.default_tablespace  =value;}
	public void setTemporary_tablespace  (String value){this.temporary_tablespace=value;}
	public void setProfile               (String value){this.profile             =value;}
	public void setCreated               (String value){this.created             =value;}
	public void setUser_type             (String value){this.user_type           =value;}

	public String getUsername              (){return this.username            ;}
	public String getAccount_status        (){return this.account_status      ;}
	public String getExpiry_date           (){return this.expiry_date         ;}
	public String getDefault_tablespace    (){return this.default_tablespace  ;}
	public String getTemporary_tablespace  (){return this.temporary_tablespace;}
	public String getProfile               (){return this.profile             ;}
	public String getCreated               (){return this.created             ;}
	public String getUser_type             (){return this.user_type           ;}

	private String username                ;
	private String account_status          ;
	private String expiry_date             ;
	private String default_tablespace      ;
	private String temporary_tablespace    ;
	private String profile                 ;
	private String created                 ;
	private String user_type               ;

}
