package com.jzctb.mis.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.jzctb.mis.bean.UnipaySumBean;
import com.jzctb.mis.db.UnipaySumDao;

public class UnipaySumAction extends MisAction{
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

	private static final long serialVersionUID = -2110421423914789116L;
	
	public UnipaySumAction(){
		super();
	}

	private List<UnipaySumBean> list = null;
	public List<UnipaySumBean> getList(){return list;}
	
	public String Search() throws Exception{
		UnipaySumDao unipaySumDao = new UnipaySumDao();
        list      = unipaySumDao.Search(pageNum, pageSize, beginDate, endDate,statMode);
		sum1      = unipaySumDao.getSum1();
		sum2      = unipaySumDao.getSum2();
		totalRows = unipaySumDao.totalRows;
		return "success";
	}

	public void validateSearch(){
		if("".equals(beginDate)||"".equals(endDate))
		{
			this.addActionError("请输入起始日期和结束日期");
		}else if(beginDate.length()!=8||endDate.length()!=8){
			this.addActionError("日期格式非法");
		}else {
			int d = 0;
			try {
				d = (int) MisAction.datecmp(endDate, beginDate, "yyyyMMdd");
				if(d<0){
					this.addActionError("起始日期不能大于结束日期");
				}else if(d>366){
					this.addActionError("日期区间不能大于1年（366天）");
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				this.addActionError("无效的日期格式");
			}
		}
	}

	public String getSum1(){return sum1;}   // 笔数合计
	public String getSum2(){return sum2;}   // 金额合计

	public String getStatMode() {
		return statMode;
	}

	public void setStatMode(String statMode) {
		this.statMode = statMode;
	}

	private String sum1 = "0";
	private String sum2 = "0.00";
	private String statMode = "01";  // 统计方式： 01按日，02按月
}
