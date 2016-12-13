<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<script type="text/javascript">
	$(document).ready(function(){
		$('#string1').focus();
		$('#enc_submit').button();
		
		
	});
	
</script>


<div class="pageHeader">
	<s:form action="Encrypt.do" namespace="/" method="post" theme="simple"
		    onsubmit="return navTabSearch(this);" >
		<!-- <s:fielderror /> -->
		<div class="searchBar">
			<table class="searchContent">
			<tr>
				<td>源字符串</td>
				<td width="300">
					<s:textfield type="text" name="string1" id="string1" size="60" class="required" />
					<span id="hexstr1"></span>
				</td>
			</tr>
			<tr>
				<td>目标字符串</td>
				<td>
					<s:textfield type="text" name="string2" id="string2" size="60" readonly="true"/>
					<span id="hexstr2">hex = [<font color='red'>${hexString}</font>]</span>
				</td>
			</tr>
			<tr>
				<td>KEY</td>
				<td><s:textfield type="text" name="key" id="key" size="60" /></td>
			</tr>
			<tr>
				<td>模式</td>
				<td><s:select name="mode" id="mode" class="combox" list="#{'0':'加密','1':'解密'}" listKey="key" listValue="value" /></td>
			</tr>
			<tr>
			<td></td>
			<td><button type="submit" id="enc_submit">加密/解密</button></td>
			</tr>
		</table>
		</div>
	</s:form>
</div>

