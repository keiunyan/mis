package com.jzctb.mis.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.jzctb.mis.bean.AlipaySumBean;
import com.jzctb.mis.db.AlipaySumDao;

public class AlipaySumAction extends MisAction{
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
	
	public AlipaySumAction(){
		super();
	}

	private List<AlipaySumBean> list = null;
	public List<AlipaySumBean> getList(){return list;}
	
	public String Search() throws Exception{
		AlipaySumDao alipaySumDao = new AlipaySumDao();
        list       = alipaySumDao.Search(pageNum, pageSize, beginDate, endDate);
		paySum     = alipaySumDao.getAmt1();
		refundSum  = alipaySumDao.getAmt2();
		balanceSum = alipaySumDao.getAmt3();
		bishu1Sum  = alipaySumDao.getBishu1Sum();
		bishu2Sum  = alipaySumDao.getBishu2Sum();
		totalRows  = alipaySumDao.totalRows;
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
				}else if(d>30){
					this.addActionError("日期区间不能大于1个月（31天）");
				}
			}catch(ParseException e){
				this.addActionError("无效日期格式");
			}
		}
	}

	public String getPaySum(){return paySum;}         // 支付金额
	public String getRefundSum(){return refundSum;}   // 退货金额
	public String getBalanceSum(){return balanceSum;} // 差额：支付-退货
	public int    getBishu1Sum(){return bishu1Sum;}   // 支付笔数
	public int    getBishu2Sum(){return bishu2Sum;}   // 退货笔数

	private String paySum;
	private String refundSum;
	private String balanceSum;
	private int    bishu1Sum;
	private int    bishu2Sum;
	
}
