<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%
    Map<String, String> dbList = new LinkedHashMap<String, String>();
	
	dbList.put("mis",   "[ mis ]   - MIS");
    dbList.put("test1", "[ test1 ] - 测试");
    dbList.put("devel", "[ devel ] - 开发");
    
    /*
    dbList.put("kernel", "[ kernel ] - 核心");
    dbList.put("agent",  "[ agent ]  - 前置");
    dbList.put("teller", "[ teller ] - 前端");
    dbList.put("ebank",  "[ ebank ]  - 网银");
    dbList.put("mgr",    "[ mgr ]    - 管理系统");
    dbList.put("dw",     "[ dw ]     - 数据仓库");
    dbList.put("reg",    "[ reg ]    - 上报");
    dbList.put("mid",    "[ mid ]    - 中间业务");
    dbList.put("lc",     "[ lc ]     - 理财");
    */
    request.setAttribute("dbList", dbList);
%>
