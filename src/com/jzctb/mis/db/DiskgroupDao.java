package com.jzctb.mis.db;

import java.util.*;
import java.sql.*;

import com.jzctb.mis.bean.DiskgroupBean;

public class DiskgroupDao extends MisDao{
	public DiskgroupDao(){
		super();
		sqlId = "diskgroup";
	}
	
	public DiskgroupDao(String dbname){
		this();
		this.dbName = dbname;
	}

    // 支付宝交易统计
	public List<DiskgroupBean> Search(int pageNum, int pageSize) throws SQLException{
        Connection conn = null;
        PreparedStatement psm = null;
		ResultSet rs = null;
		
        List<DiskgroupBean> list = new ArrayList<DiskgroupBean>();
        	
        String sql = getSql(sqlId);
        logger.debug("SQL = ["+sql+"]");
        try{
        	if(null!=(conn = getConnection(dbName))){
        		psm = conn.prepareStatement(sql);
        		rs = psm.executeQuery();
	            while(rs.next()){
	            	DiskgroupBean obj = new DiskgroupBean();
					obj.setName(rs.getString(1));
					obj.setState(rs.getString(2));
					obj.setType(rs.getString(3));
					obj.setSectorSize(rs.getString(4));
					obj.setBlockSize(rs.getString(5));
					obj.setAllocUnitSize(rs.getString(6));
					obj.setTotal(rs.getString(7));
					obj.setFree(rs.getString(8));
					obj.setReqFree(rs.getString(9));
					obj.setUsableFile(rs.getString(10));
					obj.setOfflineDisks(rs.getString(11));
					obj.setVotingFiles(rs.getString(12));
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
