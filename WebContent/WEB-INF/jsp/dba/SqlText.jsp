<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.jzctb.mis.db.ConnectManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSetMetaData" %>

<div class="pageContent">
	<table class="list" width="1100" layoutH="0" style="word-break:break-all; word-wrap:break-word;" >
	<thead>
		<tr>
			<th width="300" align="right">属性&nbsp;</th>
			<th width="800" align="left">&nbsp;值</th>
		</tr>
	</thead>
	<tbody>	
	<%
	    String dbName = request.getParameter("dbname");
	    String sqlId = request.getParameter("sqlid");
	    String schema = request.getParameter("schema");
	    Connection conn = null;
	    PreparedStatement psm = null;
	    ResultSet rs = null;
	    String sql = "select * from v$sql where sql_id=? and parsing_schema_name=?";
	    try {
	        conn = ConnectManager.getConnection(dbName);
	        psm = conn.prepareStatement(sql); 
	        psm.setString(1, sqlId);
	        psm.setString(2, schema);
	        rs = psm.executeQuery();
	        if(rs.next()) {
	        	ResultSetMetaData rsmd = rs.getMetaData();
	        	for(int i=0; i<rsmd.getColumnCount(); i++) {
	        		out.print("<tr><td align='right' style='color:green'>"+rsmd.getColumnLabel(i+1)+"&nbsp;</td><td>&nbsp;"+rs.getString(i+1)+"</td></tr>");
				}
	        	ConnectManager.closePreparedStatement(psm);
	        }else{
	            sql="select sql_text from v$sqltext where sql_id=? order by piece";
	            psm = conn.prepareStatement(sql); 
	            psm.setString(1, sqlId);
	            rs = psm.executeQuery();
	            StringBuffer sb = new StringBuffer("");
	            
	            while(rs.next()){
	                sb.append(rs.getString("SQL_TEXT"));
	            }
	            String sqltext = sb.toString();
	            if(!"".equals(sqltext)){
	            	out.print("<tr><td align='right' style='color:green'>SQL_TEXT&nbsp;</td><td>"+sqltext+"</td></tr>");
	            }else{
	            	out.print("<tr><td>未找到相关信息</td></tr>");
	            }
	        }
	    }catch(SQLException e) {
	        out.println("SQLException:\n" + e);
	    }catch(Exception e) {
	        out.println("Exception:\n" + e);
	    }
	    finally{
	    	ConnectManager.closeResultSet(rs);
	    	ConnectManager.closePreparedStatement(psm);
	    	ConnectManager.closeConn(conn);
	    }
	%>
	</tbody>
	</table>
</div>