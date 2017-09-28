<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%
    Map<String, String> dbList = new LinkedHashMap<String, String>();
	
	dbList.put("mis",   "[ mis ]   - MIS");
    dbList.put("test1", "[ test1 ] - 测试26");
    dbList.put("devel", "[ devel ] - 开发25");
    dbList.put("test", "[ test ] - 测试21");
    dbList.put("jzzldb", "[ jzzldb ] - 测试21");
    /*
    dbList.put("kernel", "[ kernel ] - 核心");
    dbList.put("agent",  "[ agent ]  - 前置");
    dbList.put("mgr",    "[ mgr ]    - 管理系统");
    dbList.put("dw",     "[ dw ]     - 数据仓库");
    dbList.put("reg",    "[ reg ]    - 上报");
    dbList.put("mid",    "[ mid ]    - 中间业务");
    dbList.put("ebank",  "[ ebank ]  - 网银");
    */
    request.setAttribute("dbList", dbList);
%>
