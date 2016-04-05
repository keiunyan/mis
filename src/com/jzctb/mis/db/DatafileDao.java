package com.jzctb.mis.db;

import java.util.*;
import java.sql.*;

import com.jzctb.mis.bean.DatafileBean;

public class DatafileDao extends MisDao{
	public DatafileDao(){
		super();
		sqlId = "datafiles";
	}
	
	public DatafileDao(String dbname){
		this();
		this.dbName = dbname;
	}

    // 支付宝交易统计
	public List<DatafileBean> Search(int pageNum, int pageSize, String tablespaceName) throws SQLException{
        Connection conn = null;
        PreparedStatement psm = null;
		ResultSet rs = null;
		
        List<DatafileBean> list = new ArrayList<DatafileBean>();
        	
        String sql = getSql(sqlId);
        sql = sql + " where \"Tablespace\" like upper(?)";
        logger.debug("SQL = ["+sql+"]");
        try{
        	if(null!=(conn = getConnection(dbName))){
        		psm = conn.prepareStatement(sql);
                psm.setString(1, "".equals(tablespaceName)?"%":tablespaceName);

        		rs = psm.executeQuery();
	            while(rs.next()){
	            	DatafileBean obj = new DatafileBean();
	            	obj.setFileName(rs.getString(1));
	                obj.setTablespace(rs.getString(2));
	                obj.setStatus(rs.getString(3));
	                obj.setSize(rs.getString(4));
	                obj.setUsed(rs.getString(5));
	                obj.setUsedPre(rs.getString(6));
	                obj.setAutoExtend(rs.getString(7));
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
