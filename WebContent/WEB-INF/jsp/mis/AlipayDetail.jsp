<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String transtat = request.getParameter("transtat");
	String trancode = request.getParameter("trancode");
%>

<s:form id="pagerForm" method="post" action="AlipayDetailSearch2.do"  namespace="/view" >
	<input type="hidden" name="pageNum" value="${model.pageNum}" />
	
</s:form>

<div class="pageHeader">
	
	<s:form action="AlipayDetailSearch2.do" method="post" rel="pagerForm" namespace="/view"
	      class="pageForm required-validate" onsubmit="return navTabSearch(this);" >
	      
	 	<input type="hidden" name="pageSize" value="${pageSize}" />

		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<label>起始日期：</label>
						<input type="text" class="date required" dateFmt="yyyyMMdd" name="beginDate" value="${beginDate}" 
						    minDate="2000-01-01" maxDate="2099-12-31" />
						<span class="info">yyyymmdd</span>
					</td>
					<td>
						<label>交易状态：</label>
						<select name="transtat" class="required combox">
							<option value="" <%if("".equals(transtat)) out.print("selected");%> > 全部</option>
							<option value="00" <%if("00".equals(transtat)) out.print("selected");%> >成功</option>
							<option value="01" <%if("01".equals(transtat)) out.print("selected");%> >失败</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label>结束日期：</label>
						<input type="text" class="date required" dateFmt="yyyyMMdd" name="endDate" value="${endDate}" 
						    minDate="2000-01-01" maxDate="{%y}-%M-{%d}" />
						<span class="info">yyyymmdd</span>
					</td>
					<td>
						<label>交易类型：</label>
						<select name="trancode" class="required combox">
							<option value="" <%if("".equals(trancode)) out.print("selected");%> > 全部</option>
							<option value="2802" <%if("2802".equals(trancode)) out.print("selected");%> >支付</option>
							<option value="2803" <%if("2803".equals(trancode)) out.print("selected");%> >退款</option>
						</select>
					</td>
					
				</tr>
				<tr>
					<td>
						<label>交易卡号：</label>
						<input type="text" name="cardno" value="${param.cardno}" size="23" />
					</td>
					<td>
						<label>前置流水号：</label>
						<input type="text" name="srvstan" value="${param.srvstan}" size="23" />
					</td>
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
	
	<table class="table" width="1300" layoutH="187">
		<thead>
			<tr>
				<th width="80">交易日期</th>
				<th width="80">交易代码</th>
				<th width="150">卡号</th>
				<th width="80">交易金额</th>
				<th width="60">户名</th>
				<th width="150">支付宝流水号</th>
				<th width="80">前置流水号</th>
				<th width="150">退货/提现卡号</th>
				<th width="150">原交易流水号</th>
				<th width="80">交易状态</th>
				<th width="100">核心应答码</th>
				<th width="160">应答码说明</th>
			</tr>
		</thead>
		<tbody>
			<s:if test="list.size<=0">
	        	<tr style="color:red"><td colspan="12" align="center">没有记录</td></tr>
	    	</s:if>
			<s:else>
				<s:iterator value="list">
				<tr>
					<td><s:property value="trandate"/></td>
					<td>
						<s:if test='trancode=="2802"'>支付</s:if>
						<s:elseif test='trancode=="2803"'>退货/提现</s:elseif>
					</td>
					<td><s:property value="cardno"/></td>
					<td align="right"><s:property value="tranamt"/></td>
					<td><s:property value="extention"/></td>
					<td><s:property value="othstan"/></td>
					<td><s:property value="srvstan"/></td>
					<td><s:property value="signno"/></td>
					<td><s:property value="orgothstan"/></td>
					<td align="center"><s:property value="transtat"/></td>
					<td align="center"><s:property value="hostrspcode"/></td>
					<td><s:property value="rspinf"/></td>
				</tr>
				</s:iterator>
			</s:else>
		</tbody>
	</table>
	
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<s:select class="combox" name="pageSize" 
			    list="#{'10':'10','20':'20','30':'30','50':'50','100':'100','200':'200'}"
			    onchange="navTabPageBreak({numPerPage:this.value})" />

			<span>条，共${totalRows}条</span>
		</div>
				
		<!-- 
		<div class="pages"><span>总行数：<font color="red">${totalRows}</font> 总页数：<font color="red">${totalPages}</font></span></div>
		 -->
		<div class="pagination" targetType="navTab" totalCount="${totalRows}" numPerPage="${pageSize}" currentPage="${pageNum}" pageNumShown="10"></div>
	</div>
	
</div>
