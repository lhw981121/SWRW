<%@ page language="java" import="java.util.*,com.qst.itoffer.util.COMUtil" pageEncoding="UTF-8"%>
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
							<fmt:message key="MyResume" />${user.getResumeIntegrity(user.getApplicantID()).equals("100%")?"":user.getResumeIntegrity(user.getApplicantID())}
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/applicant"><fmt:message key="Applicant" /></a></li>
							<li class="list-inline-item"><fmt:message key="ViewResume" /></li>
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
							<table class="table table-bordered" style="text-align:/* center */">
								<colgroup>
									<col style="width:18%">
									<col style="width:15%">
									<col style="width:26%">
									<col style="width:15%">
									<col style="width:26%">
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
										<img src="/SWRW/public/images/applicant/${basicInfo.getHeadShot()}?t=${Math.random()}" title="<fmt:message key="ResumePhoto" />"
										onerror="this.src='/SWRW/public/images/applicant/anonymous.png';this.onerror=null"
										width="100%" height="100%" alt="<fmt:message key="ResumePhoto" />" id="resume_photo"/>
									</div>
									</td>
								</tr>
								<tr>
									<!-- 真实姓名 -->
									<th style="vertical-align:middle"><fmt:message key="RealName" /></th>
									<td style="vertical-align:middle">${basicInfo.getRealName() }</td>
									<!-- 性别 -->
									<th style="vertical-align:middle"><fmt:message key="Gender" /></th>
									<td style="vertical-align:middle">${basicInfo.getGender() }</td>
								</tr>
								<tr>
									<!-- 年龄 -->
									<th style="vertical-align:middle"><fmt:message key="Age" /></th>
									<td style="vertical-align:middle">${basicInfo.getBirthday()==null?"":COMUtil.dataToAge(basicInfo.getBirthday()) }</td>
									<!-- 工作经验 -->
									<th style="vertical-align:middle"><fmt:message key="JobExperience" /></th>
									<td colspan="2" style="vertical-align:middle">${basicInfo.getJobExp() }</td>
								</tr>
								<tr>
									<!-- 邮箱 -->
									<th style="vertical-align:middle"><fmt:message key="Email" /></th>
									<td style="vertical-align:middle">${basicInfo.getEmail() }</td>
									<!-- 手机号 -->
									<th style="vertical-align:middle"><fmt:message key="Phone" /></th>
									<td style="vertical-align:middle">${basicInfo.getTelephone() }</td>
								</tr>
								<!-- 当前所在地 -->
								<tr>
									<th><fmt:message key="CurrentLocation" /></th>
									<td colspan="4">${basicInfo.getCurrentLoc() }</td>
								</tr>
								<!-- 户口所在地 -->
								<tr>
									<th><fmt:message key="ResidentLocation" /></th>
									<td colspan="4">${basicInfo.getResidentLoc() }</td>
								</tr>
								<!-- 求职意向 -->
								<tr>
									<th style="vertical-align:middle"><fmt:message key="JobIntension" /></th>
									<td colspan="4">${basicInfo.getJobIntension() }</td>
								</tr>
								</tbody>
								
								<!-- 教育经历信息区域 -->
								<thead>
								<tr>
									<th colspan="5">
										<div class="info-title mt-4" style="text-align:center">
											<h4><fmt:message key="EducationExperience" /></h4>
										</div>
									</th>
								</tr>
								</thead>
								<tbody>
								<!-- 毕业学校 -->
								<tr>
									<th><fmt:message key="GraduationSchool" /></th>
									<td colspan="4">${education.getSchool() }</td>
								</tr>
								<!-- 专业 -->
								<tr>
									<th><fmt:message key="Profession" /></th>
									<td colspan="4">${education.getProfession() }</td>
								</tr>
								<!-- 毕业时间 -->
								<tr>
									<th><fmt:message key="GraduationDate" /></th>
									<td colspan="4">${COMUtil.dataToData(education.getGraduationDate()) }</td>
								</tr>
								<!-- 教育程度 -->
								<tr>
									<th><fmt:message key="EducationDegree" /></th>
									<td colspan="4">${education.getDegree() }</td>
								</tr>
								
								<!-- 项目经验信息区域 -->
								<thead>
								<tr>
									<th colspan="5">
										<div class="info-title mt-4" style="text-align:center">
											<h4><fmt:message key="ProjectExperience" /></h4>
										</div>
									</th>
								</tr>
								</thead>
								<tbody>
								<!-- 项目名称 -->
								<tr>
									<th><fmt:message key="ProjectName" /></th>
									<td colspan="4">${projectExp.getName() }</td>
								</tr>
								<!-- 担任职务 -->
								<tr>
									<th><fmt:message key="ProjectJob" /></th>
									<td colspan="4">${projectExp.getProjectJob() }</td>
								</tr>
								<!-- 项目参与时间段 -->
								<tr>
									<th><fmt:message key="ProjectPeriod" /></th>
									<td colspan="4">${COMUtil.periodStrToPeriod(projectExp.getPeriod())}</td>
								</tr>
								<!-- 项目简述 -->
								<tr>
									<th style="vertical-align:middle"><fmt:message key="ProjectDescription" /></th>
									<td colspan="4">${projectExp.getProjectDesc() }</td>
								</tr>
								
								<!-- 工作经验信息区域 -->
								<thead>
								<tr>
									<th colspan="5">
										<div class="info-title mt-4" style="text-align:center">
											<h4><fmt:message key="WorkExperience" /></h4>
										</div>
									</th>
								</tr>
								</thead>
								<tbody>
								<!-- 工作职称 -->
								<tr>
									<th><fmt:message key="WorkTitle" /></th>
									<td colspan="4">${workExp.getWorkTitle() }</td>
								</tr>
								<!-- 工作部门 -->
								<tr>
									<th><fmt:message key="WorkDepartment" /></th>
									<td colspan="4">${workExp.getDepartment() }</td>
								</tr>
								<!-- 工作时间段 -->
								<tr>
									<th><fmt:message key="WorkPeriod" /></th>
									<td colspan="4">${COMUtil.periodStrToPeriod(workExp.getPeriod())}</td>
								</tr>

							</table>
	
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
</body>
</html>