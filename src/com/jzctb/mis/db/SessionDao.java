package com.jzctb.mis.db;
import com.jzctb.mis.bean.SessionBean;

import java.util.*;
import java.sql.*;

public class SessionDao extends MisDao{
	public SessionDao(String dbName){
		
		this.sqlId="session";
		this.dbName = dbName;
	}

    // 支付宝交易统计
	public List<SessionBean> Search(int pageNum, int pageSize, String username, String orderField, String orderDirection){
        Connection conn = null;
        PreparedStatement psm = null;
		ResultSet rs = null;
		
        List<SessionBean> list = new ArrayList<SessionBean>();
        
        StringBuffer sb = new StringBuffer(getSql(sqlId));
		sb.append(" where username like upper(?) ");//order by 'sid' desc ");
		if(!"".equals(orderField)){
			sb.append("order by " + orderField);
			sb.append("desc".equals(orderDirection) ? " desc" : " asc");
		}
		logger.debug("SQL = ["+sb.toString()+"]");
		
        try{
        	if(null!=(conn = getConnection(dbName))){
	        	psm = conn.prepareStatement(sb.toString());
	        	psm.setString(1, "".equals(username)?"%":username);
	        	//psm.setString(2, "".equals(orderField) ? "username" : orderField);
	        	rs = psm.executeQuery();
	        	while(rs.next()){
	        		//logger.debug("sid = ["+rs.getString(1)+"]");
	        		SessionBean obj = new SessionBean();
	        		obj.setSid(rs.getString(1));
	                obj.setSerial(rs.getString(2));
	                obj.setSqlId(rs.getString(3));
	                obj.setUserName(rs.getString(4));
	                obj.setSecInWait(rs.getString(5));
	                obj.setCommand(rs.getString(6));
	                obj.setMachine(rs.getString(7));
	                obj.setOsUser(rs.getString(8));
	                obj.setStatus(rs.getString(9));
	                obj.setModule(rs.getString(10));
	                obj.setAction1(rs.getString(11));
	                obj.setClientInfo(rs.getString(13));
	        		list.add(obj);
	        	}
        	}
        }catch(SQLException e){
        	logger.error(e.toString());
        }finally{
			closeResultSet(rs);
			closePreparedStatement(psm);
			closeConnect(conn);
        }
        return list;
    }
	
	public String kill(String sid, String serial){
		String sql = "alter system kill session '"+sid+","+serial+"'";
		logger.debug("kill session sql = ["+sql+"]");
		Connection conn = null;
		PreparedStatement psm = null;
		String msg = "";
		try{
			if(null!=(conn = getConnection(dbName))){
				psm = conn.prepareStatement(sql);
				psm.execute();
				msg = "0000结束会话成功";
				logger.debug(msg);
			}
        	
        }catch(SQLException e){
        	msg = "0001结束会话失败："+e.toString();
        	logger.error(e.toString());
        }finally{
			closePreparedStatement(psm);
			closeConnect(conn);
        }
		logger.debug(msg);
		return msg;
	}
	private String dbName = "";
	private String sqlId = "";

}
