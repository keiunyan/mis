package com.jzctb.mis.action;

import com.jzctb.mis.db.AlipayDetailDao;
import com.jzctb.mis.bean.AlipayDetailBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
*/

public class AlipayDetailAction extends MisAction{

	private static final long serialVersionUID = 1L;
	
	public AlipayDetailAction(){
		super();
	}

	public List<AlipayDetailBean> getList(){return list;}

	@Override
	public String execute() throws Exception {
		
		Date now = new Date(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		endDate = df.format(now);        // 默认取当前日期
		
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.add(5,-7);
		beginDate = df.format(gc.getTime());  // 7天前日期,默认查询最近一周的数据
		
		return super.execute();
	}

	public String Search(){
			
		Map<String,String> params = getRequestParams();
		logger.debug(params.size());
		
		AlipayDetailDao alipayDetailDao = new AlipayDetailDao();
        list = alipayDetailDao.Search(pageNum,pageSize,params);
        totalRows = alipayDetailDao.totalRows;
        
		return "success";
	}
	
	// 使用绑定变量查询
	public String Search2(){
		
		Map<String,String> params = getRequestParams();
		logger.debug(params.size());
		
		AlipayDetailDao alipayDetailDao = new AlipayDetailDao();
        list = alipayDetailDao.Search2(pageNum,pageSize,params);
        totalRows = alipayDetailDao.totalRows;
        
		return "success";
	}

	public void validateSearch(){ 
		if("".equals(beginDate)||"".equals(endDate))
		{
			this.addActionError("请输入起始日期和结束日期");
		}else {
			try{
				int d = (int) MisAction.datecmp(endDate, beginDate, "yyyyMMdd");
				if(d<0){
					this.addActionError("起始日期不能大于结束日期");
				}else if(d>365){
					this.addActionError("日期区间不能大于1年（365天）");
				}
			}catch(ParseException e){
				this.addActionError("无效日期格式");
			}
		}
		
	} 

	private List<AlipayDetailBean> list = null;

}
