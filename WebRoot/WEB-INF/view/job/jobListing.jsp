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
							<fmt:message key="JobListingSWZJ" />
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/job"><fmt:message key="Job" /></a></li>
							<li class="list-inline-item"><fmt:message key="JobListing" /></li>
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
					<div class="col-md-12">
						<div class="job-search-bar">
							<div class="search-bar text-center">
								<div class="form-row">
									<div class="col-md-2">
										<select id="job_area" class="custom-multi-select" onchange="SelectPage(1)">
											<option value=""><fmt:message key="AreaTip11" /></option>
										</select>
									</div>
									<div class="col-md-2">
										<select id="job_state" class="custom-multi-select" onchange="SelectPage(1)">
											<option value="4"><fmt:message key="JobState"/></option>
											<option value="1"><fmt:message key="JobState1"/></option>
											<option value="2"><fmt:message key="JobState2"/></option>
											<option value="3"><fmt:message key="JobState3"/></option>
										</select>
									</div>
									<div class="col-md-2">
										<select class="custom-multi-select" id="company_size" onchange="SelectPage(1)">
											<option value=""><fmt:message key="CompanySize"/></option>
											<option value="500人以下"><fmt:message key="CompanySize1"/></option>
											<option value="500-1000人"><fmt:message key="CompanySize2"/></option>
											<option value="1000-5000人"><fmt:message key="CompanySize3"/></option>
											<option value="5000-10000人"><fmt:message key="CompanySize4"/></option>
											<option value="10000人以上"><fmt:message key="CompanySize5"/></option>
										</select>
									</div>
									<div class="col-md-2">
										<select class="custom-multi-select" id="company_type" onchange="SelectPage(1)">
											<option value=""><fmt:message key="CompanyType"/></option>
											<option value="私营企业"><fmt:message key="CompanyType1"/></option>
											<option value="股份制企业"><fmt:message key="CompanyType2"/></option>
											<option value="外资企业"><fmt:message key="CompanyType3"/></option>
											<option value="合资企业"><fmt:message key="CompanyType4"/></option>
											<option value="三资企业"><fmt:message key="CompanyType5"/></option>
											<option value="国有企业"><fmt:message key="CompanyType6"/></option>
											<option value="其他企业"><fmt:message key="CompanyType7"/></option>
											<option value="中初教育单位"><fmt:message key="CompanyType8"/></option>
											<option value="科研设计单位"><fmt:message key="CompanyType9"/></option>
											<option value="其他事业单位"><fmt:message key="CompanyType10"/></option>
										</select>
									</div>
									<div class="col-md-3">
										<input type="search" placeholder="<fmt:message key="EnterKeywords"/>" id="queryStr" autocomplete="off"
										onkeypress="if(event.keyCode==13) {searchBtn.click();return false;}"/>
									</div>
									<div class="col-md-1">
										<button id="searchBtn" onclick="SelectPage(1)" class="buttonfx curtainup"><fmt:message key="SearchJobs" /></button>
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
							<div class="job-post-list mt-4" id="JobListingData">
									
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
<!-- 自定义脚本 -->
<script src="/SWRW/public/js/city/citys.js"></script>
<script>
//渲染省会
for (var i = 0; i<citys.length; i++) {
	$('#job_area').append('<option value="'+citys[i].name+'">'+citys[i].name+'</option>');
}
//渲染主页传来的搜索数据
$('#company_size').val('${param.company_size}');
$('#company_type').val('${param.company_type}');
$('#queryStr').val('${param.queryStr}');
</script>
<script src="/SWRW/public/js/job/job_listing.js"></script>
</body>
</html>