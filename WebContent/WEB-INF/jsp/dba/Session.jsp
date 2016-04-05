<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
<%@ include file="dbList.jsp"%>

<s:form id="pagerForm" method="post" action="SessionSearch.do" namespace="/admin" >
	<input type="hidden" name="pageNum" value="${model.pageNum}" />
	<input type="hidden" name="orderField" value="${orderField }"/>
	<input type="hidden" name="orderDirection" value="${orderDirection }"/>
</s:form>

<div class="pageHeader">
	<s:form action="SessionSearch.do" method="post" theme="simple" rel="pagerForm"  class="pageForm"
	        namespace="/admin" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${pageSize}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td width="80" align="left">选择数据库 :</td>
					<td width="180"><s:select name="dbName" list="#request.dbList" listKey="key" listValue="value"/></td>
					<td></td>
				</tr>
				<tr>
					<td>用户名 :</td>
					<td><input type="text" name="username" maxlength="32" value="${param.username }" size="32"/><span class="info">忽略大小写。如需模糊查询，请使用"%"。</span></td>
					<td></td>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>

	<table class="table" asc="asc" desc="desc" width="1400" layoutH="162">
	<thead>
	<tr>
	    <th width="40">序号</th>
		<th orderField="1" width="120" class="${param.orderDirection }">SID</th>
		<th width="60">SERIAL</th>
		<th width="100">SQL_ID</th>
		<th orderField="4" class="asc" width="100">USER_NAME</th>
		<th width="100">SECONDS_IN_WAIT</th>
	    <th width="100">COMMAND</th>
		<th width="120">MACHINE</th>
		<th width="120">OS_USER</th>
		<th width="80">STATUS</th>
		<th width="100">MODULE</th>
	    <th width="120">ACTION</th>
	    <th width="120">CLIENT_INFO</th>
	</tr>
	<thead>
	<tbody>
		<s:iterator value="list">
		<c:set var="row" scope="page" value="${row+1}" />
		<c:url value="admin/findSql.do" var="SqlText" scope="request">
			<c:param name="dbname" value="${dbName}" />
			<c:param name="sqlid" value="${sqlId }" />
			<c:param name="schema" value="${userName }" />
		</c:url>
		<tr>
            <td align="right"><s:property value="rowNum"/></td>
            <td align="right" ><s:property value="sid"/></td>
            <td align="right" ><s:property value="serial"/></td>
            <td><a href="${SqlText }" target="navTab" title="SQL_TEXT" rel="SQL_TEXT"><s:property value="sqlId"/></a></td>
            <td><s:property value="userName"/></td>
            <td><s:property value="secInWait"/></td>
            <td><s:property value="command"/></td>
            <td><s:property value="machine"/></td>
            <td><s:property value="osUser"/></td>
            <td><s:property value="status"/></td>
            <td><s:property value="module"/></td>
            <td><s:property value="action1"/></td>
            <td><s:property value="clientInfo"/></td>
		</tr>
		</s:iterator>
	</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>共${row}条</span>
		</div>
				
		<div class="pagination" targetType="navTab" totalCount="${totalRows}" numPerPage="${pageSize}" currentPage="${pageNum}" pageNumShown="10"></div>
	</div>
</div>