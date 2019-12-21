<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/klorofil/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 头部 -->
<%@include file="/WEB-INF/view/common/klorofil/head.jsp"%>

</head>

<body>
<div id="wrapper">
<!-- WRAPPER -->
<!-- 导航栏 -->
<%@include file="/WEB-INF/view/common/klorofil/navbar.jsp"%>
<!-- 左侧边栏 -->
<%@include file="/WEB-INF/view/common/klorofil/messageLeftSidebar.jsp"%>

<!-- 内容区域 -->
<div class="main">
<!-- MAIN CONTENT -->
<div class="main-content">

	<div class="container-fluid">
		<div class="row">

			<div class="col-md-12">
				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title">${language.equals("en_US")?"Unread Message":"未读消息"}</h3>
						<c:if test="${message.size()!=0 }">
							<div class="text-center" style="margin-top:-21px;">
								<a href="javascript:;" onclick="markAllMessageRead(${user.getID() })">
									<span class="label label-success"><i class="fa fa-check-square-o"></i>&nbsp;${language.equals("en_US")?"Mark Read All":"标记全部已读"}</span>
								</a>
							</div>
						</c:if>
						<div class="right">
							<a href="javascript:;" onclick="deleteUnReadMessage(${user.getID() })">
								<span class="label label-danger"><i class="fa fa-trash"></i>&nbsp;${language.equals("en_US")?"Empty Unread Messages":"清空未读消息"}</span>
							</a>
						</div>
					</div>
					<div class="panel-body" style="text-align:center;font-size:24px">
						${language.equals("en_US")?"Here are all the messages you haven't read":"这里是你所有的未读消息"}
					</div>
				</div>
			</div>

			<c:forEach var="mes" items="${message}" varStatus="floor">
				<div class="col-md-12" id="message_block_${mes.getID() }">
					<span id="${mes.getID() }"></span>
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">${language.equals("en_US")?"Summary":"概述"}:${mes.getSummary() }</h3>
							<span style="margin-top:-2cm;" class="badge bg-danger">${mes.getReaded()==false?language.equals("en_US")?"new message":"新消息":"" }</span>
							<div class="text-center">
								<h3 style="margin-top:-41px;" class="panel-title">${language.equals("en_US")?"Sender":"发送者"}:${mes.getSender() }</h3>
							</div>
							<div class="right">
								<a href="javascript:;" onclick="deleteMessage(${mes.getID() })">
									<span class="label label-danger"><i class="fa fa-window-close"></i>&nbsp;${language.equals("en_US")?"Delete Message":"删除消息"}</span>
								</a>
								<button type="button" class="btn-toggle-collapse">
									<i class="lnr lnr-chevron-up"></i>
								</button>
								<button type="button" class="btn-remove">
									<i class="lnr lnr-cross"></i>
								</button>
							</div>
						</div>
						<div class="panel-body">
							<p>${mes.getContent() }</p>
						</div>
						<div class="panel-footer">
							<span>#${floor.count+startFloor}</span> <span style="margin-left:50px;">${language.equals("en_US")?"Identifier":"编号"}:${mes.getIden() }</span>
							<div class="text-center" style="margin-top:-27px;">
								<a type="button" onclick="markMessageRead(${mes.getID() })" class="btn btn-success">${language.equals("en_US")?"Mark as Read":"标为已读"}</a>
							</div>
							<div class="text-right" style="margin-top:-27px;">
								<span>${language.equals("en_US")?"Sending time":"发送时间"}:${mes.getCreated() }</span>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<c:if test="${message.size()==0 }">
				<div class="col-md-12">
					<div class="panel">
						<div class="panel-heading">
							<div class="right">
								<button type="button" class="btn-toggle-collapse">
									<i class="lnr lnr-chevron-up"></i>
								</button>
								<button type="button" class="btn-remove">
									<i class="lnr lnr-cross"></i>
								</button>
							</div>
						</div>
						<div class="panel-body">
							<p style="text-align:center;font-size:20px">${language.equals("en_US")?"You have no unread messages!!!":"你没有未读消息！！！"}</p>
						</div>
					</div>
				</div>
			</c:if>

		</div>
		<!-- 分页 -->
		<%@include file="/WEB-INF/view/common/klorofil/pagination.jsp"%>
	</div>

</div>
<!-- END MAIN CONTENT -->
</div>
<!-- END 内容区域 -->

<!-- 页尾 -->
<%@include file="/WEB-INF/view/common/klorofil/foot.jsp"%>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<%@include file="/WEB-INF/view/common/klorofil/javaScript.jsp"%>
<!-- 自定义脚本 -->
<script src="/SWRW/public/js/message/message_manageinfo.js"></script>
<script>
//改变页码
$(document).ready(function(){
	//页面行数改变，刷新页面
	$("#pageSize").change(function(){
		window.location.reload();
	});
});
$("#pageSize option").each(function(){
	if($(this).val()=='${pageSize}'){
		$(this).prop('selected',true);
	}
});
</script>

</body>
</html>