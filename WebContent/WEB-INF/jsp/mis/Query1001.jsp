<!-- 
功能： 三家银行的大额、小额和超级网银的笔数
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %> 

<c:set var="WorkDate" value="${param.WorkDate}" scope="request" />

<sql:setDataSource var="conn" 
	driver="oracle.jdbc.driver.OracleDriver" 
	url="jdbc:oracle:thin:@188.177.155.26:1521/test1" 
	user="payafedb" password="payafedb"/>

<c:set var="sql">
	WITH A AS
	 (SELECT SYSCODE， SENDCHARGEBK
	    FROM T_CNAPS_TRANREC
	   WHERE SYSCODE IN ('HVPS', 'BEPS')
	     AND SENDCHARGEBK IN ('313424076706', '313175000011', '313424076706')
	     AND T_SYSTIME < '170000'
	     AND RSFLAG = '1'
	  AND WORKDATE = ?
	  UNION ALL
	  SELECT SYSCODE, SENDCHARGEBK
	    FROM T_BEPS_DETAIL
	   WHERE SYSCODE = 'IBPS'
	     AND SENDCHARGEBK IN ('313424076706', '313175000011', '313424076706')
	     AND T_SYSTIME < '170000'
	     AND RSFLAG = '1'
	  AND WORKDATE = ?
	  ),
	B AS
	 (SELECT 'HVPS' "SYSCODE" FROM DUAL
	  UNION ALL
	  SELECT 'BEPS' "SYSCODE" FROM DUAL
	  UNION ALL
	  SELECT 'IBPS' "SYSCODE" FROM DUAL)
	SELECT DECODE(B.SYSCODE, 'HVPS', '大额[HVPS]', 'BEPS', '小额[BEPS]', 'IBPS', '超级网银[IBPS]') "交易代码",
	COUNT(CASE A.SENDCHARGEBK WHEN '781393010011' THEN A.SENDCHARGEBK END) "厦门国际[笔数]",
	COUNT(CASE A.SENDCHARGEBK WHEN '313175000011' THEN A.SENDCHARGEBK END) "晋中银行[笔数]",
	COUNT(CASE A.SENDCHARGEBK WHEN '313424076706' THEN A.SENDCHARGEBK END) "九江银行[笔数]"
	FROM B LEFT JOIN A ON A.SYSCODE = B.SYSCODE GROUP BY B.SYSCODE
</c:set>

<sql:query dataSource="${conn}" sql="${sql}" var="rs">
	<sql:param value="${WorkDate}"/> <!-- 第1个参数 -->
	<sql:param value="${WorkDate}"/> <!-- 第2个参数 -->
</sql:query>


<div class="pageHeader">
	<s:form action="Query1001.do" method="post" theme="simple" class="pageForm"
	        namespace="/view" onsubmit="return navTabSearch(this);">	
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>日期：</td>
					<td>
						<input type="text" class="date required" 
							dateFmt="yyyyMMdd" name="WorkDate" value="${WorkDate}" 
							minDate="2000-01-01" maxDate="2099-12-31" />
						<span class="info">格式：yyyymmdd</span>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><s:actionerror cssStyle="color:red;"/></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				</ul>
			</div>
		</div>	        
	</s:form>
</div>

<div class="pageContent">
	<table class="table" width="420" layoutH="110" style="word-break:break-all; word-wrap:break-word;" >
		<thead>
			<tr>
				<th width="120" align="right">交易码&nbsp;</th>
				<th width="100" align="right">厦门国际[笔数]&nbsp;</th>
				<th width="100" align="right">晋中银行[笔数]&nbsp;</th>
				<th width="100" align="right">九江银行[笔数]&nbsp;</th>
			</tr>
		</thead>
		<tbody>	
			<c:forEach var="row" items="${rs.rowsByIndex}">
	    		<tr>
	    			<td><c:out value="${row[0]}"/></td>
	    			<td><c:out value="${row[1]}"/></td>
	    			<td><c:out value="${row[2]}"/></td>
	    			<td><c:out value="${row[3]}"/></td>
	    		</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>完成</span>
		</div>
	</div>
</div>
