<%@ page language="java" import="java.util.*,com.qst.itoffer.bean.User,com.qst.itoffer.dao.UserDao" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 页面URL路径 -->
<%
//项目根目录
String path = request.getContextPath();
//项目根目录全路径
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//当前完整url
String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();

pageContext.setAttribute("currentURL", request.getRequestURL());
%>

<!-- 更新session中的用户信息 -->
<%
User user = new User();
if(session.getAttribute("user") != null){
	user = new UserDao().queryUserByID(((User)session.getAttribute("user")).getID()); 
	session.setAttribute("user", user);
}else{
	pageContext.setAttribute("user", user);
}
 %>

<!-- 设置页面语言 -->
<%
if(session.getAttribute("language")==null){
	session.setAttribute("language", "zh_CN");
}
 %>
 
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="language"/>