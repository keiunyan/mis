package com.jzctb.mis.action;

public class EncryptAction extends MisAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4737964557450098388L;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("string1 = ["+string1+"]");
		logger.debug("   mode = ["+mode+"]");
		logger.debug("    key = ["+key+"]");
		if(!("".equals(string1)||null==string1)){
			string2 = passwd(string1, key, Integer.parseInt(mode));
			logger.debug("string2 = ["+string2+"]");
		}
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		clearErrorsAndMessages();
		if("".equals(string1)||null==string1){
			logger.debug("请输入要加密或解密的源字符串");
			this.addFieldError("string1", "请输入要加密或解密的源字符串");
		}		
		super.validate();
	}



	public String getString1() {
		return string1;
	}
	public void setString1(String string1) {
		this.string1 = string1;
	}

	public String getString2() {
		return string2;
	}

	public void setString2(String string2) {
		this.string2 = string2;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private String string1 = "";
	private String string2 = "";
	private String key     = "";
	private String mode    = "0";

}
