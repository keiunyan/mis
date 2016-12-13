package com.jzctb.mis.db;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.jzctb.mis.bean.GeneralBean;

public class CustomQueryDao extends MisDao {
	
	public CustomQueryDao(){
		super();

	}
	/**
	 * 自定义查询
	 * @param username
	 * @param password
	 * @param ip
	 * @param port
	 * @param dbname
	 * @param sql
	 * @param pageNum
	 * @param pageSize
	 */
	public void query(String username,String password,String ip,int port,String dbname,String sql,int pageNum,int pageSize){
		Connection conn = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			conn = ConnectManager.getConnection(username,password,ip,port,dbname);
			sm = conn.createStatement();
			rs = sm.executeQuery(sql);
			while(rs.next()){
				logger.debug(rs.getString(1));
			}
			
			
		}catch(SQLException e){
			logger.error(e.toString());
		}finally{
			closeResultSet(rs);
			closeStatement(sm);
			closeConnect(conn);
		}
		
		
	}
}
