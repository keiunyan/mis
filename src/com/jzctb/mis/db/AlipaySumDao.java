package com.jzctb.mis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.jzctb.mis.bean.AlipaySumBean;

public class AlipaySumDao extends MisDao {
	
	public AlipaySumDao(){
		super();
	}
	
	public List<AlipaySumBean> Search(int pageNum, int pageSize, String beginDate, String endDate){
		
		logger.debug(" pageNum  = ["+pageNum+"]");
		logger.debug("pageSize  = ["+pageSize+"]");
		logger.debug("beginDate = ["+beginDate+"]");
		logger.debug("  endDate = ["+endDate+"]");
		
		String sql = "select trandate,pay,refund,balance,bishu1,bishu2 from v_alipaysum "+
					 "where trandate between ? and ? order by trandate desc ";
		logger.debug("SQL = ["+sql+"]");
				
		// 获取记录总行数、总金额、总笔数
		String sql1 = "select count(1),sum(pay),sum(refund),sum(balance),sum(bishu1),sum(bishu2) from ("+sql+") t";
		logger.debug("SQL1 = ["+sql1+"]");
		
		Connection conn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		List<AlipaySumBean> list = new ArrayList<AlipaySumBean>();
        try{  
        	conn = getConnection();
			psm = conn.prepareStatement(sql1);
			psm.setString(1, beginDate);
			psm.setString(2, endDate);

			rs = psm.executeQuery();
			if(rs.next()){
				totalRows = rs.getInt(1);
				DecimalFormat df = new DecimalFormat("#,##0.00");
				amt1 = df.format(rs.getDouble(2));
				amt2 = df.format(rs.getDouble(3));
				amt3 = df.format(rs.getDouble(4));
				bishu1Sum=rs.getInt(5);
				bishu2Sum=rs.getInt(6);
			}
			closeResultSet(rs);
			closePreparedStatement(psm);
			
			// 分页查询
			String sql2 = "select * from(select rownum rn, t.* from ("+sql+")t "+
								"where rownum < ?) where rn > ? ";
			logger.debug("SQL2 = ["+sql2+"]");
			psm = conn.prepareStatement(sql2);
			psm.setString(1, beginDate);
			psm.setString(2, endDate);
			psm.setInt(3, pageNum*pageSize+1);
			psm.setInt(4, (pageNum-1)*pageSize);
			
			DecimalFormat df = new DecimalFormat("0.00");
			rs = psm.executeQuery();
            while(rs.next()){
				AlipaySumBean obj = new AlipaySumBean();
				obj.setTrandate(rs.getString("trandate"));
				obj.setPay(df.format(rs.getDouble("pay")));
				obj.setRefund(df.format(rs.getDouble("refund")));
				obj.setBalance(df.format(rs.getDouble("balance")));
				obj.setBishu1(rs.getInt(6));
				obj.setBishu2(rs.getInt(7));
                list.add(obj);
            }
			closeResultSet(rs);
			closePreparedStatement(psm);
        }catch(SQLException e){
            //e.printStackTrace();
        	logger.error(e.toString());
        }finally{
			closeResultSet(rs);
			closePreparedStatement(psm);
			closeConnect(conn);
        }
		return list;
	}
	
	public String getAmt1(){return amt1;}
	public String getAmt2(){return amt2;}
	public String getAmt3(){return amt3;}
	public int    getBishu1Sum(){return bishu1Sum;}
	public int    getBishu2Sum(){return bishu2Sum;}
	
	private String amt1;
	private String amt2;
	private String amt3;
	private int bishu1Sum;
	private int bishu2Sum;

}
