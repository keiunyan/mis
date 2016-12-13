<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
<%@ include file="dbList.jsp"%>

<form id="pagerForm" method="post" action="DatafileSearch.do">
	<input type="hidden" name="pageNum" value="${model.pageNum}" />
	
</form>

<div class="pageHeader">
	<s:form action="DatafileSearch.do" method="post" theme="simple" rel="pagerForm" 
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
					<td>表空间名称 :</td>
					<td><s:textfield name="tablespaceName" maxlength="32" size="32"/><span class="info">忽略大小写。如需模糊查询，请使用"%"。</span></td>
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
			<li><a class="icon" href="javascript:void()" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>

	<table class="table" width="900" layoutH="162">
	<thead>
	<tr>
		<th width="40">序号</th>
		<th width="260">文件名</th>
		<th width="80">表空间</th>
		<th width="100">大小[MB]</th>
		<th width="100">状态</th>
		<th width="100">已使用[MB]</th>
		<th width="100">已使用%</th>
		<th width="100">自动扩展</th>
	</tr>
	<thead>
	<tbody>
		
		<s:iterator value="list">
		<c:set var="row" scope="page" value="${row+1}" />
		<tr>
			<td align="right"><c:out value="${row}"/></td>
			<td><s:property value="fileName"/></td>
			<td><s:property value="tablespace"/></td>
			<td align="right"><s:property value="size"/></td>
			<td align="right"><s:property value="status"/></td>
			<td align="right"><s:property value="used"/></td>
			<td align="right"><s:property value="usedPre"/></td>
			<td align="right"><s:property value="autoExtend"/></td>
		</tr>
		</s:iterator>
	</tbody>
	</table>

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