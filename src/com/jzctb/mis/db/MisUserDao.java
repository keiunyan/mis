package com.jzctb.mis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jzctb.mis.bean.MisUserBean;

public class MisUserDao extends MisDao {
	
	/**
	 * 用户登录验证
	 * @param username - 用户名
	 * @param passwd   - 密码
	 * @return 0000登录成功   1001:用户名不存在;1002:密码不正确;1003:用户已被锁定
	 */
	public String LogonVerifi(MisUserBean user){
        Connection conn = null;
        PreparedStatement psm = null;
		ResultSet rs = null;

		String sql = "select user_id, user_passwd, user_name, user_rule, user_locked from t_users where user_id = ? ";
		try{
			if(null!=(conn = getConnection())){
				psm = conn.prepareStatement(sql);
				psm.setString(1, user.getUserid());
				rs = psm.executeQuery();
				if(rs.next()){
					if("1".equals(rs.getString(5))){
						return "1003"; //用户已被锁定
					}else if(!user.getPassword().equals(rs.getString(2))){
						return "1002"; //密码不正确
					}else{
						user.setUsername(rs.getString(3));
						user.setRule(rs.getString(4));
						return "0000";
					}
				}else{
					return "1001";  //用户名不存在
				}
			}
		}catch (SQLException e) {
			//e.printStackTrace();
			logger.error(e.toString());
		}finally{
			closeResultSet(rs);
			closePreparedStatement(psm);
			closeConnect(conn);
		}
		return "";
	}
	
	public boolean userIsExist(String userid){
        Connection conn = null;
        PreparedStatement psm = null;
		ResultSet rs = null;

		String sql = "select user_id from t_users where user_id = ? ";
		try{
			if(null!=(conn = getConnection())){
				psm = conn.prepareStatement(sql);
				psm.setString(1, userid);
				rs = psm.executeQuery();
				if(rs.next()){
					return true;
				}
			}
		}catch (SQLException e) {
			//e.printStackTrace();
			logger.error(e.toString());
		}finally{
			closeResultSet(rs);
			closePreparedStatement(psm);
			closeConnect(conn);
		}
		return false;
	}
	public String test(){
		getSql("session");
		return "OK";
	}
}
