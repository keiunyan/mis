package com.jzctb.mis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jzctb.mis.bean.UnipaySumBean;

public class UnipaySumDao extends MisDao {
	
	public UnipaySumDao(){
		super();
	}
	
	public List<UnipaySumBean> Search(int pageNum, int pageSize, String beginDate, String endDate, String statMode){
		
		logger.debug(" pageNum  = ["+pageNum+"]");
		logger.debug("pageSize  = ["+pageSize+"]");
		logger.debug("beginDate = ["+beginDate+"]");
		logger.debug("  endDate = ["+endDate+"]");
		logger.debug("statMode  = ["+statMode+"]");
		String sql = "";
		if(statMode.equals("02")){
			/* 按月统计 */
			beginDate = beginDate.substring(0, 6);
			endDate   = endDate.substring(0, 6);
			sql = "select substr(stldate,1,6) d, count(1) c, sum(tranamt)/100 s from V_UNIONPAYDETAIL t " +
			      "where substr(stldate,1,6) between ? and ?  group by substr(stldate,1,6) order by 1";

		}else{
			/* 按日统计 */
			sql = "select stldate d, count(1) c, sum(tranamt)/100 s from V_UNIONPAYDETAIL t " +
			      "where stldate between ? and ?  group by stldate order by 1";
		}
		
		logger.debug("SQL = ["+sql+"]");
				
		// 获取记录总行数、总金额、总笔数
		String sql1 = "select count(1), sum(c), sum(s) from ("+sql+") t";
		logger.debug("SQL1 = ["+sql1+"]");
		
		Connection conn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		List<UnipaySumBean> list = new ArrayList<UnipaySumBean>();
        try{  
        	conn = getConnection();
			psm = conn.prepareStatement(sql1);
			psm.setString(1, beginDate);
			psm.setString(2, endDate);

			rs = psm.executeQuery();
			if(rs.next()){
				totalRows = rs.getInt(1);				
				sum1 = rs.getString(2);
				sum2 = rs.getString(3);
			}
			closeResultSet(rs);
			closePreparedStatement(psm);
			
			// 分页查询
			String sql2 = "select * from(select t.*, rownum rn from ("+sql+")t "+
								"where rownum < ?) where rn > ? ";
			logger.debug("SQL2 = ["+sql2+"]");
			psm = conn.prepareStatement(sql2);
			psm.setString(1, beginDate);
			psm.setString(2, endDate);
			psm.setInt(3, pageNum*pageSize+1);
			psm.setInt(4, (pageNum-1)*pageSize);
			
			rs = psm.executeQuery();
            while(rs.next()){
            	UnipaySumBean obj = new UnipaySumBean();
				obj.setField1(rs.getString(1));
				obj.setField2(rs.getString(2));
				obj.setField3(rs.getString(3));
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
	
	public String getSum1(){return sum1;}
	public String getSum2(){return sum2;}
	
	private String sum1;
	private String sum2;

}
