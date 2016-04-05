package com.jzctb.mis.db;

import java.util.*;
import java.sql.*;

import com.jzctb.mis.bean.LockedBean;

public class LockedDao extends MisDao{
	public LockedDao(){
		super();
		sqlId = "locked";
	}
	
	public LockedDao(String dbname){
		this();
		this.dbName = dbname;
	}

    // 支付宝交易统计
	public List<LockedBean> Search(int pageNum, int pageSize, String owner) throws SQLException{
        Connection conn = null;
        PreparedStatement psm = null;
		ResultSet rs = null;
		
        List<LockedBean> list = new ArrayList<LockedBean>();
        	
        String sql = getSql(sqlId) + " and owner like upper(?)";
        logger.debug("SQL = ["+sql+"]");
        try{
        	if(null!=(conn = getConnection(dbName))){
        		psm = conn.prepareStatement(sql);
        		psm.setString(1, "".equals(owner)?"%":owner);
        		rs = psm.executeQuery();
	            while(rs.next()){
	            	LockedBean obj = new LockedBean();
					obj.setProcess(rs.getString(1));
					obj.setSid(rs.getString(2));
					obj.setSerial(rs.getString(3));
					obj.setObjectName(rs.getString(4));
					obj.setOwner(rs.getString(5));
					obj.setObjectType(rs.getString(6));
					obj.setOraUName(rs.getString(7));
					obj.setOsUName(rs.getString(8));
					obj.setLockedMode(rs.getString(9));
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
