<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
<%@ include file="dbList.jsp"%>

<form id="pagerForm" method="post" action="DiskgroupSearch.do">
	<input type="hidden" name="pageNum" value="${model.pageNum}" />
	
</form>

<div class="pageHeader">
	<s:form action="DiskgroupSearch.do" method="post" theme="simple" rel="pagerForm" 
	        namespace="/admin" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${pageSize}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td width="80" align="left">选择数据库</td>
					<td width="180"><s:select name="dbName" list="#request.dbList" listKey="key" listValue="value"/></td>
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

	<table class="table" width="1100" layoutH="137">
	<thead>
	<tr>
		<th width="80">NAME</th>
		<th width="60">STATE</th>
		<th width="60">TYPE</th>
		<th width="80">SECTOR_SIZE</th>
		<th width="80">BLOCK_SIZE</th>
	    <th width="80">ALLOC_UNIT_SIZE</th>
		<th width="80">TOTAL_MB</th>
		<th width="80">FREE_MB</th>
		<th width="80">REQ_MIRROR_FREE_MB</th>
		<th width="80">USABLE_FILE_MB</th>
		<th width="60">OFFLINE_DISKS</th>
	    <th width="60">VOTING_FILES</th>
	</tr>
	<thead>
	<tbody>
		<s:iterator value="list">
		<c:set var="row" scope="page" value="${row+1}" />
		<tr>
            <td><s:property value="name"/></td>
            <td><s:property value="state"/></td>
            <td><s:property value="type"/></td>
            <td align="right"><s:property value="sectorSize"/></td>
            <td align="right"><s:property value="blockSize"/></td>
            <td align="right"><s:property value="allocUnitSize"/></td>
            <td align="right"><s:property value="total"/></td>
            <td align="right"><s:property value="free"/></td>
            <td align="right"><s:property value="reqFree"/></td>
            <td align="right"><s:property value="usableFile"/></td>
            <td align="right"><s:property value="offlineDisks"/></td>
            <td align="center"><s:property value="votingFiles"/></td>
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