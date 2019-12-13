<%@ page language="java" import="java.util.*,java.net.URLDecoder" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- NAVBAR -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="brand">
  		<a href="/SWRW/index"><img src="/SWRW/public/klorofilAssets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
   </div>
   <div class="container-fluid">
		<div class="navbar-btn">
			<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
		</div>
      <c:if test="${currentURL.indexOf(\"/message/\")!=-1 }">
		<form class="navbar-form navbar-left">
			<div class="button-group" style="margin-left:2cm">
				<a class="btn btn-primary" href="javascript:(void);" onclick="changeLanguage('en_US');"><fmt:message key="English"/></a>&emsp;
				<a class="btn btn-primary" href="javascript:(void);" onclick="changeLanguage('zh_CN');"><fmt:message key="Chinese"/></a>
			</div>
		</form>
		</c:if>
			<div id="navbar-menu">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
					<i class="lnr lnr-alarm"></i><span class="badge bg-danger" id="message_count_3"></span></a>
					<ul class="dropdown-menu notifications" id="message_menu"></ul>
				</li>
				<li class="dropdown">
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img src="/SWRW/public/images/user/avatar/${user.getID() }.jpg?t=<%=Math.random()%>" 
						onerror="this.src='/SWRW/public/images/user/avatar/0.jpg';this.onerror=null" class="img-circle" alt="Avatar"/>
						<span>${user.getName() }</span>
						<i class="icon-submenu lnr lnr-chevron-down"></i>
	            </a>
	            <ul class="dropdown-menu">
	            
					<c:if test="${user.getType()==8 }">
						<li><a href="/SWRW/admin" target="_blank"><span><i class="fa fa-credit-card-alt"></i> <fmt:message key="Admin" /></span></a></li>
					</c:if>

					<c:choose>
					<c:when test="${currentURL.indexOf(\"/user/\")!=-1 }">
						<li><a href="/SWRW/user/mycenter" target="_self"><i class="lnr lnr-user"></i> <span>个人中心</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/SWRW/user/mycenter" target="_blank"><i class="lnr lnr-user"></i> <span>个人中心</span></a></li>
					</c:otherwise>
					</c:choose>
					
					<c:choose>
					<c:when test="${currentURL.indexOf(\"/message/\")!=-1 }">
						<li><a href="/SWRW/message" target="_self"><i class="lnr lnr-bubble"></i> <span>Message </span><span class="badge" id="message_count_4"></span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/SWRW/message" target="_blank"><i class="lnr lnr-bubble"></i> <span>Message </span><span class="badge" id="message_count_4"></span></a></li>
					</c:otherwise>
					</c:choose>
					
					<c:choose>
					<c:when test="${currentURL.indexOf(\"/set/\")!=-1 }">
						<li><a href="/SWRW/setting" target="_self"><i class="lnr lnr-cog"></i> <span>设置</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/SWRW/setting" target="_blank"><i class="lnr lnr-cog"></i> <span>设置</span></a></li>
					</c:otherwise>
					</c:choose>
					
					<li><a href="/SWRW/UserLogout"><i class="lnr lnr-exit"></i> <span><fmt:message key="Logout"/></span></a></li>
					
	      		</ul>
				</li>
			</ul>
    	</div>
	</div>
</nav>
<!-- END NAVBAR -->
