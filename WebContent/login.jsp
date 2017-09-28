<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<title>综合前置查询系统 - 登录</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />

<!--[if lt IE 9]><script src="js/speedup.js" type="text/javascript"></script><script src="js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]-->
<!--[if gte IE 9]><!--><script src="js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]-->

<link rel="SHORTCUT ICON" href="favicon.ico"/>

<script type="text/javascript">
	var DEBUG = true;  // debug
	$(document).ready(function() {
		
		if(DEBUG){
			$('#userid').val("600743");
			$('#password').val("600743");
			//$('#loginform').submit();
		}
		
		$('#userid').select();
		
		$("#userid").on("keydown",function(event){
			if(event.keyCode==13){
				$('#password').select();
				return false;
			}
		});
		
		$("#password").on("keydown",function(event){
			if(event.keyCode==38){
				$('#userid').select();
			}
		});

	});
	/*
	$(function(){
		$('#userid').blur(function(){
			$.ajax({
				type: "POST",
				url:"v.do",
				dataType:"text",
				data:{"userid":$('#userid').val()},
				success:function(data){
					alert(data);
				}
			});
		});
	});
	*/
	
</script>

</head>
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<img src="themes/default/images/login_logo.gif" />
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#"> </a></li>
						<li><a href="#"> </a></li>
						<li><a href="#" target="_blank"> </a></li>
					</ul>
				</div>
				<h2 class="login_title"><img src="themes/default/images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<s:actionerror cssStyle="color:red;"/>
				<s:form action="Login" method="post" name="loginform" id="loginform" namespace="/">
					<p>
						<label>用户名：</label>
						<input type="text" name="userid" size="20" class="login_input" id="userid" value="${param.userid }" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="password" size="20" class="login_input" id="password" />
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</s:form>
			</div>
			<div class="login_banner"><img src="themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#"> </a></li>
					<li><a href="#"> </a></li>
					<li><a href="#"> </a></li>
					<li><a href="#"> </a></li>
				</ul>
				<div class="login_inner">
					<p> </p>
					<p> </p>
					<p> </p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2016 www.jzctb.com Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>