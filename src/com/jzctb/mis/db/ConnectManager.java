package com.jzctb.mis.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.jzctb.mis.action.MisAction;

public class ConnectManager{
    private ConnectManager(){
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			String errmsg = String.format("加载oracle数据库驱动[%s]失败", "oracle.jdbc.driver.OracleDriver");
			logger.error(errmsg);
		}
    }
    
    /**
     * 从配置文件context.xml中获取数据库连接
     * @return Connection
     */
    public static synchronized Connection getConnection() {
    	Connection conn = null;
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle/mis");
            conn = ds.getConnection();
        }catch(NamingException e){
            e.printStackTrace();
            conn = null;
        }catch(SQLException e){
        	e.printStackTrace();
        	conn = null;
        }
        return conn;
    }
    
    /**
     * 根据dbname从数据库表T_DB查询数据库URL、UserName、PassWord后，生成数据库连接
     * @param dbname 数据库名称：system@orcl
     * @return 数据库连接Connection
     */
    public static synchronized Connection getConnection(String dbname){
    	logger.debug("dbname = ["+dbname+"]");
    	String dburl="",username="",passwd="";
		String sql = "select db_url, db_user, db_passwd,db_sid from t_db where db_name=? ";

    	Connection conn = null;
    	PreparedStatement psm = null;
    	ResultSet rs = null;
    	try{
    		conn = getConnection();
    		psm = conn.prepareStatement(sql);
    		psm.setString(1, dbname);
    		rs = psm.executeQuery();
    		if(rs.next()){
    			dburl = rs.getString(1);
    			username = rs.getString(2);
    			passwd = new MisAction().passwd(rs.getString(3), rs.getString(4), 1);
    			
    			String info = String.format("dburl=[%s] username=[%s] passwd=[%s]",dburl,username,"********");
    			logger.debug(info);
    					    	
		    	return DriverManager.getConnection(dburl, username, passwd);
    		}else{
    			return null;
    		}
		}catch(SQLException e){
			String errmsg = String.format("获取数据库连接失败: url=[%s] username=[%s] passwd=[%s]",dburl,username,"********");
			logger.error(errmsg);
		}finally{
			closeResultSet(rs);
			closePreparedStatement(psm);
			closeConn(conn);
		}
		return null;
    }
    
    /**
     * 根据用户名、密码、数据库地址获取连接
     * @param username - 用户名
     * @param password - 密码
     * @param ip       - 数据库IP地址 
     * @param port     - 数据库端口
     * @param dbname   - 数据库名称SID
     * @return <code>Connection</code>
     * @throws SQLException 
     */
    public static Connection getConnection(String username, String password,String ip, int port,String dbname) throws SQLException{
    	Connection conn = null;
    	String dburl = String.format("jdbc:oracle:thin:@//%s:%d/%s", ip,port,dbname );
    	conn =  DriverManager.getConnection(dburl, username, password);
		return conn;
	}
    
    /***
     * 关闭数据库连接
     * @param conn - 已打开的数据库连接
     */
    public static void closeConn(Connection conn){
    	try{
    		if(conn!=null){
    			conn.close();
    		}
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
		conn = null;    	
    }
    
    /***
     * 关闭结果集
     * @param rs - 待关闭的ResultSet对象
     */
	public static void closeResultSet(ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		rs = null;
	}
	
	public static void closeStatement(Statement sm){
		if(sm!=null){
			try{
				sm.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		sm = null;
	}
	
	public static void closePreparedStatement(PreparedStatement psm){
		if(psm!=null){
			try{
				psm.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		psm = null;
	}

    
    private static Logger logger = Logger.getLogger(ConnectManager.class); 
}