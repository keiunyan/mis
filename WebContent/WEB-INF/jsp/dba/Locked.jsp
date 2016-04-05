<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
<%@ include file="dbList.jsp"%>

<form id="pagerForm" method="post" action="LockedSearch.do">
	<input type="hidden" name="pageNum" value="${model.pageNum}" />
	
</form>

<div class="pageHeader">
	<s:form action="LockedSearch.do" method="post" theme="simple" rel="pagerForm" 
	        namespace="/admin" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${pageSize}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td width="80" align="left">选择数据库</td>
					<td width="180"><s:select name="dbName" list="#request.dbList" listKey="key" listValue="value"/></td>
					<td></td>
				</tr>
				<tr>
					<td>数据库用户名 </td>
					<td><s:textfield name="owner" maxlength="32" size="32"/><span class="info">忽略大小写。如需模糊查询，请使用"%"。</span></td>
					<td></td>
				</tr>
			</table>
		
			<div class="subBar">
				<ul>
					<li><s:actionerror cssStyle="color:red;"/></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
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

	<div layoutH="140">
		<table class="list" width="98%">
		<thead>
		<tr>
			<th width="40">序号</th>
			<th width="80">进程ID</th>
			<th width="60">会话ID</th>
			<th width="80">SERIAL</th>
			<th width="180">对象名称</th>
			<th width="80">所有者</th>
			<th width="80">对象类型</th>
			<th width="80">Oracle用户</th>
			<th width="100">操作系统用户</th>
			<th width="100">锁模式</th>
		</tr>
		<thead>
		<tbody>
			<s:if test="list.size<=0">
	        	<tr style="color:red"><td colspan="10" align="center">没有锁表</td></tr>
	    	</s:if>
			<s:else>
				<s:iterator value="list">
				<c:set var="row" scope="page" value="${row+1}" />
					<tr>
						<td align="right"><c:out value="${row}"/></td>
			            <td><s:property value="process"/></td>
			            <td><s:property value="sid"/></td>
			            <td><s:property value="serial"/></td>
			            <td><s:property value="objectName"/></td>
			            <td><s:property value="owner"/></td>
			            <td><s:property value="objectType"/></td>
			            <td><s:property value="oraUName"/></td>
			            <td><s:property value="osUName"/></td>
			            <td><s:property value="lockedMode"/></td>
					</tr>
				</s:iterator>
			</s:else>
		</tbody>
	</table>
	</div>
	
	<div class="panelBar">
		<div class="pages">
			<span>共${row}条</span>
		</div>
				
		<!-- 
		<div class="pages"><span>总行数：<font color="red">${totalRows}</font> 总页数：<font color="red">${totalPages}</font></span></div>
		 -->
		<div class="pagination" targetType="navTab" totalCount="${totalRows}" numPerPage="${pageSize}" currentPage="${pageNum}" pageNumShown="10"></div>
	</div>
</div>