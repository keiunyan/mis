package com.jzctb.mis.db;
import com.jzctb.mis.bean.AlipayDetailBean;

import java.util.*;
import java.sql.*;

public class AlipayDetailDao extends MisDao{
    public AlipayDetailDao(){
		super();
	}
    
    /**
     * 查询支付宝交易明细
     * @param pageNum  - 查询第几页
     * @param pageSize - 每页返回记录数
     * @param params   - Key-Value对的HashMap
     * @return         包含AlipayDetailBean的List对象
     */
	public List<AlipayDetailBean> Search(int pageNum, int pageSize, Map<String,String> params){
		
        List<AlipayDetailBean> list = new ArrayList<AlipayDetailBean>();
        Connection conn = null;
        PreparedStatement psm = null;
        Statement sm = null;
        ResultSet rs = null;
        StringBuffer sb = 
        		new StringBuffer("select * from v_alipaydetail where trandate>='" + params.get("beginDate") +
        		                 "' and trandate<='" + params.get("endDate") + "' ");
        
        
        // 交易卡号 cardno支付卡号  signno退款卡号
        if(null != params.get("cardno")){
        	String cardno = params.get("cardno").replace("'", "").trim();
        	sb.append("and (cardno='"+cardno+"' or signno='"+cardno+"') ");     
        }
        // 交易状态  00成功，01失败
    	String transtat = params.get("transtat");
    	if("00".equals(transtat)){
    		sb.append("and transtat='00' and hostrspcode='0000' ");
    	}else if("01".equals(transtat)){
    		sb.append("and transtat='01' ");
    	}
        // 交易代码  2802支付，2803退货
    	String trancode = params.get("trancode");
        if("2802".equals(trancode)||"2803".equals(trancode)) {
        	sb.append("and trancode='"+trancode+"' "); 
        }
        // 前置流水号
        if(null != params.get("srvstan")){
        	sb.append("and srvstan='"+params.get("srvstan").replace("'", "").trim()+"' ");   
        }
        
        String sql = sb.toString();
        logger.debug("sql = ["+sql+"]");
        
        try {
        	conn = getConnection();
			
        	sm = conn.createStatement();
        	rs = sm.executeQuery("select count(1) from ("+sql+")");
        	if(rs.next()){
        		totalRows = rs.getInt(1);
        	}
        	closeResultSet(rs);
        	closeStatement(sm);
        	      	
        	psm = conn.prepareStatement("select * from(select rownum rn, t.* from ("+sql+")t "+
					                    "where rownum < ?) where rn > ? ");
        	psm.setInt(1, pageNum*pageSize+1);
        	psm.setInt(2, (pageNum-1)*pageSize);
        	
			rs = psm.executeQuery();
			while(rs.next()){
				
				AlipayDetailBean alipayDetail=new AlipayDetailBean();
				alipayDetail.setTrandate(rs.getString("trandate"));
				alipayDetail.setTrancode(rs.getString("trancode"));
				alipayDetail.setCardno(rs.getString("cardno"));
				alipayDetail.setSignno(rs.getString("signno"));
				alipayDetail.setTranamt(rs.getString("tranamt"));
				alipayDetail.setCharge(rs.getString("charge"));
				alipayDetail.setOrgothstan(rs.getString("orgothstan"));
				alipayDetail.setExtention(rs.getString("extention"));
				alipayDetail.setOthstan(rs.getString("othstan"));
				alipayDetail.setSrvstan(rs.getString("srvstan"));
				alipayDetail.setRspinf(rs.getString("rspinf"));
				alipayDetail.setTranstat(rs.getString("transtat"));
				alipayDetail.setHostrspcode(rs.getString("hostrspcode"));
                list.add(alipayDetail);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}finally{
			closeResultSet(rs);
			closeStatement(sm);
			closePreparedStatement(psm);
			closeConnect(conn);
		}
        
        return list;
    }
	
	// 使用绑定变量查询
	public List<AlipayDetailBean> Search2(int pageNum, int pageSize, Map<String,String> params){
		
        List<AlipayDetailBean> list = new ArrayList<AlipayDetailBean>();
        Connection conn = null;
        PreparedStatement psm = null;
        Statement sm = null;
        ResultSet rs = null;
        StringBuffer sb = 
        		new StringBuffer("select * from v_alipaydetail where 1=1 ");
        
        List<String> p = new ArrayList<String>();
        
        if( null != params.get("beginDate")){
        	sb.append("and trandate>=? ");
        	p.add(params.get("beginDate"));
        }
        if( null != params.get("endDate")){
        	sb.append("and trandate<=? ");
        	p.add(params.get("endDate"));
        }
        // 交易卡号 cardno支付卡号  signno退款卡号
        if( null != params.get("cardno")){
        	sb.append("and (cardno=? or signno=?) ");
        	p.add(params.get("cardno"));
        	p.add(params.get("cardno"));
        }
       
        // 交易状态  00成功，01失败
    	String transtat = params.get("transtat");
    	if("00".equals(transtat)){
    		sb.append("and transtat='00' and hostrspcode='0000' ");
    	}else if("01".equals(transtat)){
    		sb.append("and transtat='01' ");
    	}
        // 交易代码  2802支付，2803退货
    	String trancode = params.get("trancode");
        if("2802".equals(trancode)||"2803".equals(trancode)) {
        	sb.append("and trancode=? ");
        	p.add(trancode);
        }
        // 前置流水号
        if(null != params.get("srvstan")){
        	sb.append("and srvstan=? ");
        	p.add(params.get("srvstan"));
        }
        
        String sql = sb.toString();
        logger.debug("sql = ["+sql+"]");
        
        try {
        	conn = getConnection();
        	psm = conn.prepareStatement("select count(1) from ("+sql+")");// conn.createStatement();
        	for(int i=0;i<p.size();i++){
        		psm.setString(i+1, p.get(i));
        	}
        	rs = psm.executeQuery();
        	if(rs.next()){
        		totalRows = rs.getInt(1);
        	}
        	closeResultSet(rs);
        	closePreparedStatement(psm);
        	      	
        	psm = conn.prepareStatement("select * from(select rownum rn, t.* from ("+sql+")t "+
					                    "where rownum < ?) where rn > ? ");
        	int i = 0;
        	for(i=0;i<p.size();i++){
        		psm.setString(i+1, p.get(i));
        	}
        	psm.setInt(++i, pageNum*pageSize+1);
        	psm.setInt(++i, (pageNum-1)*pageSize);
        	
			rs = psm.executeQuery();
			while(rs.next()){
				
				AlipayDetailBean alipayDetail=new AlipayDetailBean();
				alipayDetail.setTrandate(rs.getString("trandate"));
				alipayDetail.setTrancode(rs.getString("trancode"));
				alipayDetail.setCardno(rs.getString("cardno"));
				alipayDetail.setSignno(rs.getString("signno"));
				alipayDetail.setTranamt(rs.getString("tranamt"));
				alipayDetail.setCharge(rs.getString("charge"));
				alipayDetail.setOrgothstan(rs.getString("orgothstan"));
				alipayDetail.setExtention(rs.getString("extention"));
				alipayDetail.setOthstan(rs.getString("othstan"));
				alipayDetail.setSrvstan(rs.getString("srvstan"));
				alipayDetail.setRspinf(rs.getString("rspinf"));
				alipayDetail.setTranstat(rs.getString("transtat"));
				alipayDetail.setHostrspcode(rs.getString("hostrspcode"));
                list.add(alipayDetail);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}finally{
			closeResultSet(rs);
			closeStatement(sm);
			closePreparedStatement(psm);
			closeConnect(conn);
		}
        
        return list;
    }

}