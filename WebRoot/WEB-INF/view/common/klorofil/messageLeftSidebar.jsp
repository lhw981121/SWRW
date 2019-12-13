<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- LEFT SIDEBAR -->
<div id="sidebar-nav" class="sidebar">
	<div class="sidebar-scroll">
		<nav>
			<ul class="nav">

				<li><a href="/SWRW/message/unread_message" id="unread_message" class=""> 
				<i class="lnr lnr-pie-chart"></i> <span>${language.equals("en_US")?"Unread Message":"未读消息" }</span> 
				<span class="badge bg-danger" id="message_count_5"></span></a></li>

				<li><a href="/SWRW/message/history_message"id="history_message" class=""> 
				<i class="lnr lnr-map"></i> <span>${language.equals("en_US")?"History Message":"已读消息" }</span></a></li>
				
				<li><a href="/SWRW/message/all_message" id="all_message" class=""> 
				<i class="lnr lnr-bubble"></i> <span>${language.equals("en_US")?"My Message":"全部消息"}</span></a></li>

			</ul>
		</nav>
	</div>
</div>
<!-- END LEFT SIDEBAR -->

<!-- 侧边栏选中保持 -->
<script>
var pathname = window.location.pathname;
//未读消息
if(pathname.indexOf("unread_message") != -1){
	document.getElementById("unread_message").className = "active";
}else
//已读消息
if(pathname.indexOf("history_message") != -1){
	document.getElementById("history_message").className = "active";
}else
//未读消息
if(pathname.indexOf("all_message") != -1){
	document.getElementById("all_message").className = "active";
}else{
	document.getElementById("unread_message").className = "active";
}
</script>
