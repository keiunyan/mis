<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
<%@ include file="dbList.jsp"%>

<form id="pagerForm" method="post" action="CustomQuery.do">
	<input type="hidden" name="pageNum" value="${model.pageNum}" />
	
</form>

<div class="pageHeader">
	<s:form action="CustomQuery.do" method="post" theme="simple" rel="pagerForm" 
	        namespace="/admin" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${pageSize}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td width="60" align="right">数据库名称:</td>
					<td><s:textfield name="dbName" maxlength="32" size="20"/></td>
					<td width="40" align="right">IP地址:</td>
					<td><s:textfield name="ip" maxlength="16" size="20"/></td>
					<td width="40" align="right">端口:</td>
					<td><s:textfield name="port" maxlength="6" size="8"/></td>
				</tr>
				<tr>
					<td width="60" align="right">用户名:</td>
					<td><s:textfield name="username" maxlength="32" size="20"/></td>
					<td width="40" align="right">密码:</td>
					<td><s:password name="password" maxlength="32" size="20" /></td>
					<td></td>
					<td><div class="buttonContent"><button>连接</button></div></td>
				</tr>
				<tr>
					<td width="60" align="right">SQL:</td>
					<td colspan="5" align="center">
						<s:textarea rows="5" name="sql" cols="80"></s:textarea>
					</td>
				</tr>
			</table>
			
			<div class="subBar">
				<ul>
					<li><s:actionerror cssStyle="color:red;"/></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">执行</button></div></div></li>
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

		<table class="table" width="1200" layoutH="262">
		<thead>
		<tr>
			<th width="40">序号</th>
			<th width="60">USERNAME</th>
			<th width="60">ACCOUNT_STATUS</th>
			<th width="80">EXPIRY_DATE</th>
			<th width="180">DEFAULT_TABLESPACE</th>
			<th width="180">TEMPORARY_TABLESPACE</th>
			<th width="80">PROFILE</th>
			<th width="100">CREATED</th>
			<th width="80">USER_TYPE</th>
		</tr>
		<thead>
		<tbody>
			<s:iterator value="list">
			<c:set var="row" scope="page" value="${row+1}" />
				<tr>
					<td align="right"><c:out value="${row}"/></td>
					<td>${username             }</td>
					<td>${account_status       }</td>
					<td>${expiry_date          }</td>
					<td>${default_tablespace   }</td>
					<td>${temporary_tablespace }</td>
					<td>${profile              }</td>
					<td>${created              }</td>
					<td>${user_type            }</td>
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