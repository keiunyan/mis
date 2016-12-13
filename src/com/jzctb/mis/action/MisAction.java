package com.jzctb.mis.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.jzctb.mis.db.MisDao;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.log4j.Logger;

import com.jzctb.mis.encrypt.*;

public class MisAction extends ActionSupport {

	private static final long serialVersionUID = -224872381410688760L;
	
	public MisAction(){
		pageNum    = 1;
		pageSize   = 20;
		totalRows  = 0;
		totalPages = 0;
		
	}	

	public String execute() throws Exception{
		return INPUT;
	}
	
	protected int pageNum;     // 当前页
	protected int pageSize;    // 每页显示多少条
    protected int totalRows;   // 总记录数
    protected int totalPages;  // 总页数
    
	public void setPageNum(int value){pageNum=value;}
	public int getPageNum(){return pageNum;}
	
	/**
	 * 设置分页大小
	 * @param value 分页大小
	 */
	public void setPageSize(int value){
		pageSize=value;
		/*
		request = ServletActionContext.getRequest();
		request.getSession().setAttribute("pageSize", pageSize);
		*/
	}
	
	/**
	 * 获取分页大小
	 * @return 分页大小
	 */
	public int getPageSize(){
		return pageSize;
	}
	
	/**
	 * 获取分页后的总页数
	 * @return 查询结果分页后的总页数
	 */
    public int getTotalPages(){
    	totalPages = totalRows / pageSize;
    	if(totalRows % pageSize > 0){
    		totalPages++;
		}
    	return totalPages;
    }
	
    public int getTotalRows(){
    	return totalRows;
    }
    
    /**
     * 计算两个日期参数之间相差的天数。
     * @param date1 - 日期1
     * @param date2 - 日期2
     * @param datefmt - 日期格式：yyyyMMdd
     * @return 两个日期相差的天数
     * @throws ParseException 
     */
    public static long datecmp(String date1, String date2, String datefmt) throws ParseException{
    	DateFormat df = new SimpleDateFormat(datefmt);
    	Calendar cal = Calendar.getInstance();
    	long time1=0,time2=0;
    	try {
    		cal.setTime(df.parse(date1));
    		time1 = cal.getTimeInMillis()/1000;
    		
    		cal.setTime(df.parse(date2));
    		time2 = cal.getTimeInMillis()/1000;
        } catch (ParseException e) {
            //e.printStackTrace();
            logger.error(e.toString());
            throw e;
        }
    	return (time1-time2)/86400;
    }
    
    /**
     * 取出页面提交的参数名和参数值的map
     * @return key-value对应的Map
     */
    public Map<String,String> getRequestParams(){
    	Map<String,String> params = new HashMap<String,String>();
    	
		Map<String,String[]> paraMap = ServletActionContext.getRequest().getParameterMap();
		Set<String> keys = paraMap.keySet();
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext())	{
			String key = it.next();
			String value = paraMap.get(key)[0].trim();
			logger.debug("params: "+key+"=["+value+"]");
			if(!("".equals(value)||null==value)){
				params.put(key, value);
			}
		}
    	return params;
    }
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * 使用DES算法加解密字符串
	 * @param str - 需要加密或解密的字符串
	 * @param key - 加密用的key
	 * @param mode - 加解密模式 0加密 1解密
	 * @return 加密或解密后字符串
	 */
	public String passwd(String str, String key, int mode){
		
/*		try {
			DESUtil.main(null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/		
		DESUtil des = new DESUtil(key);
		try{
			if(mode==0)
				return des.encryptStr(str);
			else
				return des.decryptStr(str);
		}catch(RuntimeException e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public void setBeginDate(String value){this.beginDate=value;}
	public void setEndDate(String value){this.endDate=value;}
	public String getBeginDate(){return beginDate;}   // 起始日期
	public String getEndDate(){return endDate;}       // 结束日期

	public static Logger logger = Logger.getLogger(MisAction.class); 
	
	protected HttpServletRequest request;
	protected MisDao misDao;
	
	protected String beginDate = "";
	protected String endDate = "";
	protected String dbName = "";
	
	protected String orderField = "";
	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	protected String orderDirection = "";
}


