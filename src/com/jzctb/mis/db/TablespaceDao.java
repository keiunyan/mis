package com.jzctb.mis.db;

import com.jzctb.mis.bean.TablespaceBean;

import java.text.DecimalFormat;
import java.util.*;
import java.sql.*;

public class TablespaceDao extends MisDao{
	
	public TablespaceDao() {
		sqlId = "tablespaces";
	}
	
	public TablespaceDao(String dbname){
		this();
		this.dbName = dbname;
	}

    /***
     * 
     * @param pageNum         -  当前页
     * @param pageSize        -  每页返回记录数 
     * @param tablespaceName  -  表空间名称，支持模糊查询
     * @return           "List:TablespaceBean"
     * @throws SQLException
     */
	public List<TablespaceBean> Search(int pageNum, int pageSize, String tablespaceName) {
        Connection conn = null;
        PreparedStatement psm = null;
		ResultSet rs = null;
		
        List<TablespaceBean> list = new ArrayList<TablespaceBean>();
        
		String sql = getSql(sqlId);
		sql = sql + " where t.tablespace_name like upper(?) order by tablespace_name ";
        logger.debug(sql);
        DecimalFormat df = new DecimalFormat("0.00");
        try{
        	if(null!=(conn = getConnection(dbName))){
	        	psm = conn.prepareStatement(sql);
	        	psm.setString(1, "".equals(tablespaceName.trim())?"%":tablespaceName.trim());
	        	rs = psm.executeQuery();
	        	while(rs.next()){
	        		TablespaceBean obj = new TablespaceBean();
	        		obj.setTablespaceName(rs.getString(1));
	        		obj.setDatafiles(rs.getString(2));
	        		obj.setBytes(df.format(rs.getDouble(3)));// .getString(3));
	        		obj.setUsedBytes(df.format(rs.getDouble(4)));
	        		obj.setFreeBytes(df.format(rs.getDouble(5)));
	        		obj.setMaxBytes(df.format(rs.getDouble(6)));
	        		obj.setBlockSize(rs.getString(7));
	        		obj.setStatus(rs.getString(8));
	        		obj.setLogging(rs.getString(9));
	        		list.add(obj);
	        	}
        	}
        } catch (SQLException e) {
        	//logger.error("e.getErrorCode()=["+e.getErrorCode()+"]");
        	//logger.error("e.getMessage()=["+e.getMessage()+"]");
        	//logger.error("e.getSQLState()=["+e.getSQLState()+"]");
        	logger.error(e.toString());
			//e.printStackTrace();
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
