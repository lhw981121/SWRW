<%@ page language="java" import="java.util.*,com.swzj.swrw.util.COMUtil" pageEncoding="UTF-8"%>
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
							${basicInfo.getRealName() } <fmt:message key="sResume" />
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/company"><fmt:message key="Company" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/company/job/browse_resume"><fmt:message key="BrowseResume" /></a></li>
							<li class="list-inline-item"><fmt:message key="ResumeDetail" /></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!---end header-banner -->

	<main class="main-content-area pb-100 pt-100 clearfix">
		<div class="submit-form-area">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="submit-form form-bg">
							<table class="table table-bordered">
								<colgroup>
									<col style="width:20%">
									<col style="width:15%">
									<col style="width:25%">
									<col style="width:15%">
									<col style="width:25%">
								</colgroup>
								<!-- 简历基本信息区域 -->
								<thead>
								<tr>
									<th colspan="6">
										<div class="info-title mt-4" style="text-align:center">
											<h4><fmt:message key="BasicInformation" /></h4>
										</div>
									</th>
								</tr>
								</thead>
								<tbody>
								<!-- 简历照片 -->
								<tr>
									<td rowspan="4">
									<div>
										<img src="/SWRW/public/images/applicant/${basicInfo.getHeadShot()}?t=${Math.random()}"
										onerror="this.src='/SWRW/public/images/applicant/anonymous.png';this.onerror=null"
										width="100%" height="100%" alt="<fmt:message key="ResumePhoto" />" id="resume_photo"/>
									</div>
									</td>
								</tr>
								<tr>
									<!-- 真实姓名 -->
									<th style="vertical-align:middle;text-align:center"><fmt:message key="RealName" /></th>
									<td style="vertical-align:middle;text-align:center">${basicInfo.getRealName() }</td>
									<!-- 性别 -->
									<th style="vertical-align:middle;text-align:center"><fmt:message key="Gender" /></th>
									<td style="vertical-align:middle;text-align:center">${basicInfo.getGender() }</td>
								</tr>
								<tr>
									<!-- 年龄 -->
									<th style="vertical-align:middle;text-align:center"><fmt:message key="Age" /></th>
									<td style="vertical-align:middle;text-align:center">${basicInfo.getBirthday()==null?"":COMUtil.dataToAge(basicInfo.getBirthday()) }</td>
									<!-- 工作经验 -->
									<th style="vertical-align:middle;text-align:center"><fmt:message key="JobExperience" /></th>
									<td colspan="2" style="vertical-align:middle;text-align:center">${basicInfo.getJobExp() }</td>
								</tr>
								<tr>
									<!-- 邮箱 -->
									<th style="vertical-align:middle;text-align:center"><fmt:message key="Email" /></th>
									<td style="vertical-align:middle;text-align:center">${basicInfo.getEmail() }</td>
									<!-- 手机号 -->
									<th style="vertical-align:middle;text-align:center"><fmt:message key="Phone" /></th>
									<td style="vertical-align:middle;text-align:center">${basicInfo.getTelephone() }</td>
								</tr>
								<!-- 当前所在地 -->
								<tr>
									<th style="vertical-align:middle;text-align:center"><fmt:message key="CurrentLocation" /></th>
									<td colspan="4">${basicInfo.getCurrentLoc() }</td>
								</tr>
								<!-- 户口所在地 -->
								<tr>
									<th style="vertical-align:middle;text-align:center"><fmt:message key="ResidentLocation" /></th>
									<td colspan="4">${basicInfo.getResidentLoc() }</td>
								</tr>
								<!-- 求职意向 -->
								<tr>
									<th style="vertical-align:middle;text-align:center"><fmt:message key="JobIntension" /></th>
									<td colspan="4">${basicInfo.getJobIntension() }</td>
								</tr>
								</tbody>
								
								<!-- 教育经历信息区域 -->
								<thead>
								<tr>
									<th colspan="5">
										<div class="info-title mt-4" style="text-align:center">
											<h4><fmt:message key="EducationExperienceDetail" /></h4>
										</div>
									</th>
								</tr>
								</thead>
								<tbody>
								<!-- 教育经历 -->
								<c:forEach var="str" items="${educationStr}" varStatus="index">
									<tr>
										<th style="vertical-align:middle;text-align:center"><fmt:message key="EducationExperience" />${index.count }</th>
										<td colspan="4">${str }</td>
									</tr>
								</c:forEach>
								<!-- 未填写教育经历 -->
								<c:if test="${educationStr.size()==0 }">
									<tr><th colspan="5" style="vertical-align:middle;text-align:center"><fmt:message key="NotFilled" /></th></tr>
								</c:if>
								</tbody>
								<!-- 项目经验信息区域 -->
								<thead>
								<tr>
									<th colspan="5">
										<div class="info-title mt-4" style="text-align:center">
											<h4><fmt:message key="ProjectExperienceDetail" /></h4>
										</div>
									</th>
								</tr>
								</thead>
								<tbody>
								<!--项目经验 -->
								<c:forEach var="str" items="${projectExpStr}" varStatus="index">
									<tr>
										<th style="vertical-align:middle;text-align:center"><fmt:message key="ProjectExperience" />${index.count }</th>
										<td colspan="4">${str }</td>
									</tr>
								</c:forEach>
								<!-- 未填写项目经验 -->
								<c:if test="${projectExpStr.size()==0 }">
									<tr><th colspan="5" style="vertical-align:middle;text-align:center"><fmt:message key="NotFilled" /></th></tr>
								</c:if>
								</tbody>
								<!-- 工作经验信息区域 -->
								<thead>
								<tr>
									<th colspan="5">
										<div class="info-title mt-4" style="text-align:center">
											<h4><fmt:message key="WorkExperienceDetail" /></h4>
										</div>
									</th>
								</tr>
								</thead>
								<tbody>
								<!--工作经验 -->
								<c:forEach var="str" items="${workExpStr}" varStatus="index">
									<tr>
										<th style="vertical-align:middle;text-align:center"><fmt:message key="WorkExperience" />${index.count }</th>
										<td colspan="4">${str }</td>
									</tr>
								</c:forEach>
								<!-- 未填写工作经验 -->
								<c:if test="${workExpStr.size()==0 }">
									<tr><th colspan="5" style="vertical-align:middle;text-align:center"><fmt:message key="NotFilled" /></th></tr>
								</c:if>
								</tbody>
							</table>
							<c:if test="${jobApply.state==1 }">
								<div style="text-align:center">
									<button class="btn btn-success" onclick="HireApplicant(${jobApply.ID})"><fmt:message key="HireApplicant" /></button>
									<button class="btn btn-warning" onclick="ShortlistApplicant(${jobApply.ID})"><fmt:message key="ShortlistApplicant" /></button>
									<button class="btn btn-danger" onclick="RejectedApplicant(${jobApply.ID})"><fmt:message key="RejectedApplicant" /></button>
								</div>
							</c:if>
							<c:if test="${jobApply.state==2 }">
								<div style="text-align:center">
									<button class="btn btn-success" ><fmt:message key="Accepted" /></button>
								</div>
							</c:if>
						</div>
					</div>
				</div>
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
<script src="/SWRW/public/js/company/company_resumeDetail.js"></script>
</body>
</html>