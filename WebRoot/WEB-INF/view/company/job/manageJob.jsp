<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/swrw/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 公共头部 -->
<%@include file="/WEB-INF/view/common/swrw/head.jsp"%>

</head>
<body>
<!-- start-公共页眉 -->
<%@include file="/WEB-INF/view/common/swrw/header.jsp"%>
<!-- end-公共页眉 -->

	<!---start header-banner -->
	<div class="header-banner clearfix"
		style="background-image:url(/SWRW/public/assets/images/header-bg.jpg)">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="banner-text text-center">
						<h1>
							<fmt:message key="ManageJob" />
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/company"><fmt:message key="Company" /></a></li>
							<li class="list-inline-item"><fmt:message key="ManageJob" /></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!---end header-banner -->

	<main class="main-content-area pt-100 pb-100 job-bar-static">
		<div class="job-list-area ">

			<div class="container">
				<div class="row">
					<div class="col-md-12" id="manageJobHead">
						<div class="job-search-bar">
							<div class="search-bar text-center">
								<div class="form-row">
									<div class="col-md-9">
										<input type="search" placeholder="<fmt:message key="EnterKeywords"/>" />
									</div>
									<div class="col-md-3">
										<button type="button" class="buttonfx curtainup"><fmt:message key="SearchJobs"/></button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="job-list-wrapper">
							<div class="job-post-list mt-4" id="manageJobData">
										
							</div>
						</div>
					</div>
				</div>
				
				<!-- 分页 -->
				<%@include file="/WEB-INF/view/common/swrw/pagination.jsp"%>
				
			</div>
		</div>
	</main>

<!-- start-公共页脚 -->
<%@include file="/WEB-INF/view/common/swrw/footer.jsp"%>
<!-- end-公共页脚 -->

<!-- start-页面预载 -->
<%@include file="/WEB-INF/view/common/swrw/preloader.jsp"%>
<!-- end-页面预载 -->

<!-- start-公共脚本 -->
<%@include file="/WEB-INF/view/common/swrw/javaScript.jsp"%>
<!-- end-公共脚本 -->
<!-- 修改职位头像模态框 -->
<%@include file="/WEB-INF/view/company/job/changeJobHeadModal.jsp" %>
<!-- 自定义脚本 -->
<script>
var company_id = '${company.ID}';
</script>
<script src="/SWRW/public/js/company/company_manageJob.js"></script>
</body>
</html>