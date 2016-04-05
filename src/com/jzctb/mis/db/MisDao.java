package com.jzctb.mis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jzctb.mis.db.ConnectManager;

import org.apache.log4j.Logger;

public class MisDao {

	public MisDao() {
		totalRows  = 0;
		
	}
 		
	public String getSql(String sqlId){
		String sqlText = "";
		Connection conn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			String sql = "select sql_text from t_sql t where t.sql_id = ?";
			psm = conn.prepareStatement(sql);
			psm.setString(1, sqlId);
			rs = psm.executeQuery();
			
			if(rs.next()){
				sqlText= rs.getString(1);
				logger.debug("sqlId = ["+sqlId+"]");
//				logger.debug("sqlText = ["+sqlText+"]");				
			}
		}catch(SQLException e){
            //e.printStackTrace();
            logger.error(e.toString());
        }finally{
        	closeResultSet(rs);
        	closePreparedStatement(psm);
        	closeConnect(conn);
        }
		return sqlText;
	}
	
	public Connection getConnection(){
		return ConnectManager.getConnection();
	}
	
	public Connection getConnection(String dbname){
		return ConnectManager.getConnection(dbname);
	}
	
	public void closeConnect(Connection conn){
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		conn = null;
	}
	
	public void closeResultSet(ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		rs = null;
	}
	
	public void closeStatement(Statement sm){
		if(sm!=null){
			try{
				sm.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		sm = null;
	}
	
	public void closePreparedStatement(PreparedStatement psm){
		if(psm!=null){
			try{
				psm.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		psm = null;
	}

	
	public int totalRows;   // 总记录数
    public static Logger logger = Logger.getLogger(MisDao.class); 
    
}
