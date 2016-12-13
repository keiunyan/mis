<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	$(document).ready(function(){
		
		
	});
	
	$(function(){
		$('#save').click(function(){
			
			$.ajax({
				type: "POST",
				url:"test/TestSearch.do",
				dataType:"text",
				data:{"info":"hello","参数10":"值10"},
				success:function(data){
					alert(data);
				}
			});
						
		});
	});
	
	$(function(){
		$('#button1').click(function(){
			 $.post("test/Testmq.do",{"a":"1"},function(data){
				  alert(data);
		      },"json");
						
		});
	});
	$(function(){
		$('#button2').click(function(){
			 $.post("view/idbrowser.do",{"a":"1"},function(data){
				  alert(data);
		      },"json");
						
		});
	});

</script>

<div class="pageHeader">
	<p>
		<label>客 户 号：</label>
		<input name="sn" type="text" size="30" value="100001" readonly="readonly"/>
	</p>
	<p>
		<label>消息：</label>
		<input name="info" type="text" size="30" class="required" />
		<span id="links"></span>
	</p>
	<p>
		<label>默认格式：</label>
		<input type="text" name="date1" class="date" readonly="true"/>
		<a class="inputDateButton" href="javascript:;">选择</a>
		<span class="info">yyyy-MM-dd</span>
	</p>
</div>

<div class="pageContent">
	<form class="pageForm" >
		<div class="pageFormContent" layoutH="156">
			<p>
				<label>客 户 号：</label>
				<input name="sn" type="text" size="30" value="100001" readonly="readonly"/>
			</p>
			<p>
				<label>消息：</label>
				<input name="info" type="text" size="30" class="required" />
				<span id="links"></span>
			</p>
			<button type="button" id="button1">MQ测试</button>
			<button type="button" id="button2">身份证核查</button>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="save">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
	
</div>

<script type="text/javascript">
</script>
