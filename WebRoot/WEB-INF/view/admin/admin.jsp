<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/klorofil/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 头部 -->
<%@include file="/WEB-INF/view/common/klorofil/head.jsp" %>

</head>

<body>
<div id="wrapper"><!-- WRAPPER -->
<!-- 导航栏 -->
<%@include file="/WEB-INF/view/common/klorofil/navbar.jsp" %>
<!-- 左侧边栏 -->
<%@include file="/WEB-INF/view/common/klorofil/adminLeftSidebar.jsp" %>

<!-- 内容区域 -->
<div class="main">
<!-- MAIN CONTENT -->
<div class="main-content">

<!-- ERROR TIP -->
<!-- END ERROR TIP -->
        
     <div class="container-fluid">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="panel panel-default">
                    <div class="panel-heading">欢迎  ${user.getName()} 登录</div>
                    <div class="panel-body">
                    	你是 ${user.getTypeStr(language)} ！
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- END MAIN CONTENT -->
</div>
<!-- END 内容区域 -->

<!-- 页尾 -->
<%@include file="/WEB-INF/view/common/klorofil/foot.jsp" %>
</div><!-- END WRAPPER -->
<!-- Javascript -->
<%@include file="/WEB-INF/view/common/klorofil/javaScript.jsp" %>

</body>
</html>