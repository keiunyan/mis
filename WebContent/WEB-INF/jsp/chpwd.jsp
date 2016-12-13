<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	
	<s:form method="post" action="chPwd.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">

			<div class="unit">
				<label>旧密码：</label>
				<input type="password" name="oldPwd" id="oldPwd" size="30" minlength="6" maxlength="20" class="required" />
			</div>
			<div class="unit">
				<label>新密码：</label>
				<input type="password" name="newPwd" id="cp_newPwd" size="30" minlength="6" maxlength="20" class="required alphanumeric"/>
			</div>
			<div class="unit">
				<label>确认新密码：</label>
				<input type="password" name="rNewPwd" size="30" equalTo="#cp_newPwd" class="required alphanumeric"/>
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</s:form>
	
</div>

<script type="text/javascript">
  $(document).ready(function(){
	  $('#oldPwd').focus();
	  
  });
  
</script>