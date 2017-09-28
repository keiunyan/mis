package com.jzctb.mis.db;

import java.util.*;
import java.sql.*;

import com.jzctb.mis.bean.DBUserBean;

public class DBUserDao extends MisDao{
	public DBUserDao(){
		super();
		sqlId = "users";
	}
	
	public DBUserDao(String dbname){
		this();
		this.dbName = dbname;
	}

    // 支付宝交易统计
	public List<DBUserBean> Search(int pageNum, int pageSize, String username, String accountStatus) throws SQLException{
        Connection conn = null;
        PreparedStatement psm = null;
		ResultSet rs = null;
		
        List<DBUserBean> list = new ArrayList<DBUserBean>();
        	
        String sql = getSql(sqlId) + " where username like upper(?) and account_status like ? order by username";
        logger.debug("SQL = ["+sql+"]");
        try{
        	if(null!=(conn = getConnection(dbName))){
        		psm = conn.prepareStatement(sql);
        		psm.setString(1, "".equals(username)?"%":username);
        		psm.setString(2, "".equals(accountStatus)?"%":accountStatus);
        		
        		rs = psm.executeQuery();
	            while(rs.next()){
	            	DBUserBean obj = new DBUserBean();
					obj.setUsername              (rs.getString(1));
					obj.setAccount_status        (rs.getString(2));
					obj.setExpiry_date           (rs.getString(3));
					obj.setDefault_tablespace    (rs.getString(4));
					obj.setTemporary_tablespace  (rs.getString(5));
					obj.setProfile               (rs.getString(6));
					obj.setCreated               (rs.getString(7));
					obj.setUser_type             (rs.getString(8));
	                list.add(obj);
	            }
        	}
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
	
	private String dbName = "";
	private String sqlId = "";

}
