<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="favicon.ico"/>
<title>焦作中旅银行 - 综合前置查询系统</title>

<link href="themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="external/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>

<!-- JQuery UI -->
<link href="css/jquery-ui.css" type="text/css"  media="screen"/>
<link href="css/jquery-ui.structure.css" type="text/css"  media="screen"/>
<link href="css/jquery-ui.theme.css" type="text/css"  media="screen"/>

<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lt IE 9]><script src="js/speedup.js" type="text/javascript"></script><script src="js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]-->
<!--[if gte IE 9]><!--><script src="js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]-->

<!-- JQuery UI -->
<script type="text/javascript" src="js/jquery-ui.js"></script>

<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="external/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
<script src="external/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="external/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="external/chart/raphael.js"></script>
<script type="text/javascript" src="external/chart/g.raphael.js"></script>
<script type="text/javascript" src="external/chart/g.bar.js"></script>
<script type="text/javascript" src="external/chart/g.line.js"></script>
<script type="text/javascript" src="external/chart/g.pie.js"></script>
<script type="text/javascript" src="external/chart/g.dot.js"></script>

<script src="js/dwz.min.js" type="text/javascript"></script>
<script src="js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
//		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"Login.do",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"pageSize", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>

</head>
<body>
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://www.jzctb.com">标志</a>
				<ul class="nav">
					<li><a>2016年3月14日</a></li>
					<!-- <li><a>当前用户：${sessionScope.user.userid}</a></li>  -->
					<!-- <li><a>当前用户：<s:property value="%{#session.user.userid}" /></a></li>  -->
					<li><a>当前用户：<s:property value="user.userid" /></a></li>
					<li><a href="chPwdDlg.do" target="dialog" width="600">修改密码</a></li>
					<li><a href="Logout.do">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>业务信息查询</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a>电子支付类交易</a>
								<ul>
									<li><a href="view/AlipaySum.do" target="navTab" rel="AlipaySum">支付宝交易统计</a></li>
									<li><a href="view/AlipayDetail.do" target="navTab" rel="AlipayDetail">支付宝交易明细</a></li>
									<li><a href="view/UnipaySum.do" target="navTab" rel="UnipaySum">银联在线交易统计</a></li>
									<li><a href="view/UnipaySum.do" target="navTab" rel="UnipaySum">UnionPay</a></li>
								</ul>
							</li>
							
							<li><a>代扣信息查询</a>
								<ul>
									<li><a href="view/undefined" >燃气费签约信息</a></li>
									<li><a href="view/undefined" >燃气费统计</a></li>
									<li><a href="view/undefined" >燃气费明细</a></li>
									<li><a href="view/undefined" >水费签约信息</a></li>
									<li><a href="view/undefined" >水费统计</a></li>
									<li><a href="view/undefined" >水费明细</a></li>
									<li><a href="view/undefined" >医疗交易</a></li>
								</ul>
							</li>
							<li><a>代发工资查询</a>
								<ul>
									<li><a href="view/DaifaSum.do" target="navTab" rel="DaifaSum">代发工资统计</a></li>
									<li><a href="view/DaifaDetail.do" target="navTab" rel="DaifaDetail">代发工资明细</a></li>
								</ul>
							</li>								
						</ul>
					</div>
					
					<!-- ↓↓↓↓↓↓↓↓角色为0管理员时加载管理员菜单↓↓↓↓↓↓↓↓ -->
					<c:if test="${sessionScope.user.rule==0}"> 
						<div class="accordionHeader">
							<h2><span>Folder</span>DBA工具</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li><a href="admin/Tablespace.do" target="navTab" rel="Tablespace">表空间</a></li>
								<li><a href="admin/Diskgroup.do" target="navTab" rel="Diskgroup">磁盘组</a></li>
								<li><a href="admin/Datafile.do" target="navTab" rel="Datafile">数据文件</a></li>
								<li><a href="admin/DBUser.do" target="navTab" rel="DBUser">用户</a></li>
								<li><a href="admin/Session.do" target="navTab" rel="Session">会话</a></li>
								<li><a href="admin/Locked.do" target="navTab" rel="Locked">锁</a></li>
								
							<li><a>高级查询</a>
								<ul>
									<li><a href="admin/CustomQuery.do" target="navTab" rel="CustomQuery">自定义查询</a></li>
								</ul>
							</li>
								
							</ul>
							
						</div>
					</c:if>
					<!-- ↑↑↑↑↑↑↑↑角色为0管理员时加载管理员菜单↑↑↑↑↑↑↑↑ -->
					
					<!-- 以下为测试用的页面 -->
					<c:if test="${sessionScope.user.rule==0}">
						<div class="accordionHeader">
							<h2><span>Folder</span>测试工具</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li><a href="test/Test.do" target="navTab" id="test" rel="Test">测试页面</a></li>
								<li><a href="Encrypt.do" target="navTab" id="encrypt" rel="Encrypt">字符串加解密</a></li>
							</ul>
						</div>
					</c:if>
					<!-- 以上为测试用的页面  -->
					
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
							<!-- 主页说明内容 -->
						</div>						
					</div>					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 焦作中旅银行 www.jzctb.com</div>

</body>
</html>