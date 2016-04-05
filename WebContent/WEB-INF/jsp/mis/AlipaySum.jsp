<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form id="pagerForm" method="post" action="AlipaySumSearch.do" namespace="/view">
	<input type="hidden" name="pageNum" value="${model.pageNum}" />
	
</s:form>

<div class="pageHeader">
	
	<s:form action="AlipaySumSearch.do" method="post" rel="pagerForm" namespace="/view"
	      class="pageForm required-validate" onsubmit="return navTabSearch(this);" >
	      
	 	<input type="hidden" name="pageSize" value="${pageSize}" />
	 	
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td width="600">
						<p>
							<label>起始日期：</label>
							<input type="text" class="date required" dateFmt="yyyyMMdd" name="beginDate" value="${beginDate}" 
							    minDate="2000-01-01" maxDate="2099-12-31" />
							<span class="info">yyyymmdd</span>
						</p>
					</td>
				</tr>
				<tr>
					<td>
						<label>结束日期：</label>
						<input type="text" class="date required" dateFmt="yyyyMMdd" name="endDate" value="${endDate}" 
						    minDate="2000-01-01" maxDate="{%y}-%M-{%d}" />
						<span class="info">yyyymmdd</span>
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
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	
	<table class="table" width="600" layoutH="162">
		<thead>
			<tr>
				<th width="80">日期</th>
				<th width="100">支付(元)</th>
				<th width="100">退货/提现(元)</th>
				<th width="100">差额(元)</th>
				<th width="100">支付笔数</th>
				<th width="100">退货笔数</th>
			</tr>
		</thead>
		<tbody>
			<s:if test="list.size<=0">
	        	<tr style="color:red"><td colspan="6" align="center">没有记录</td></tr>
	    	</s:if>
			<s:else>
				<s:iterator value="list">
				<tr>
					<td><s:property value="trandate"/></td>
					<td align="right"><s:property value="pay"/></td>
					<td align="right"><s:property value="refund"/></td>
					<td align="right"><s:property value="balance"/></td>
					<td align="right"><s:property value="bishu1"/></td>
					<td align="right"><s:property value="bishu2"/></td>
				</tr>
				</s:iterator>
				
				<s:if test="pageNum==totalPages">
				<tr style="color:red">
					<td>合计：</td>
					<td align="right"><s:property value="paySum"/></td>
					<td align="right"><s:property value="refundSum"/></td>
					<td align="right"><s:property value="balanceSum"/></td>
					<td align="right"><s:property value="bishu1Sum"/></td>
					<td align="right"><s:property value="bishu2Sum"/></td>
				</tr>
				</s:if>
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
