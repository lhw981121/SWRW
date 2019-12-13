<%@ page language="java" import="java.util.*,com.qst.itoffer.util.COMUtil" pageEncoding="UTF-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/swrw/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 公共头部 -->
<%@include file="/WEB-INF/view/common/swrw/head.jsp"%>
<!-- datetimepicker -->
<link rel="stylesheet" href="/SWRW/public/assets/css/bootstrap-datetimepicker.min.css">
<!-- 简历基本信息验证JS -->
<script src="/SWRW/public/js/applicant/applicant_basicInfo.js"></script>
<!-- 教育经历信息验证JS -->
<script src="/SWRW/public/js/applicant/applicant_education.js"></script>
<!-- 项目经验信息验证JS -->
<script src="/SWRW/public/js/applicant/applicant_projectExp.js"></script>
<!-- 工作经验信息验证JS -->
<script src="/SWRW/public/js/applicant/applicant_workExp.js"></script>

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
							<fmt:message key="CompleteResume" />
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/applicant"><fmt:message key="Applicant" /></a></li>
							<li class="list-inline-item"><fmt:message key="CompleteResume" /></li>
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
									
							<!-- 简历基本信息区域 -->
							<div class="row">
								<div class="col-md-12">
									<div class="info-title mt-4">
										<h4><fmt:message key="BasicInformation" /></h4>
									</div>
								</div>
								<div class="col-md-3">
									<!-- 简历照片 -->
									<div>
										<a href="javascript:void(0);" onclick="$('#changeResumePhotoModal').modal('show')" title="<fmt:message key="ChangeResumePhoto" />"> 
											<img src="/SWRW/public/images/applicant/${basicInfo.getHeadShot()}?t=${Math.random()}"
											onerror="this.src='/SWRW/public/images/applicant/anonymous.png';this.onerror=null" style="margin-left:15px"
											width="103%" height="103%" alt="<fmt:message key="ResumePhoto" />" id="resume_photo"/>
										</a>
									</div>
								</div>
								<div class="col-md-9 row" style="margin:auto">
									<div class="col-md-6">
										<div class="fom-info">
											<div class="form-filed">
												<!-- 真实姓名 -->
												<p>
													<input class="form-control" type="text" name="realName" id="realName"
													value="${basicInfo.getRealName() }" onchange="checkRealName()"
													placeholder="<fmt:message key="RealNameTip" />" title="<fmt:message key="RealName" />"/>
												</p>
												<!-- 生日 -->
												<input type="hidden" id="birthday" name="birthday" value="" />
												<div class="input-group date birthday_date" data-date="" data-date-format="yyyy-mm-dd" 
												data-link-field="birthday" data-link-format="yyyy-mm-dd">
													<input class="form-control" type="text" onchange="updateBirthday(this.value)" id="birthday_data"
													placeholder="<fmt:message key="BirthdayTip" />" title="<fmt:message key="Birthday" />"/>
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
												</div>
												<!-- 邮箱 -->
												<p>
													<input class="form-control" type="text" name="email" id="email"
													value="${basicInfo.getEmail() }" onchange="checkEmail()"
													placeholder="<fmt:message key="EmailTip" />" title="<fmt:message key="Email" />"/>
												</p>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="fom-info">
											<div class="form-filed">
												<!-- 性别 -->
												<p>
													<input class="form-control" type="text" name="gender" id="gender"
													value="${basicInfo.getGender() }" onchange="checkGender()"
													placeholder="<fmt:message key="GenderTip" />" title="<fmt:message key="Gender" />"/>
												</p>
												<!-- 工作经验 -->
												<p>
													<input class="form-control" type="text" name="job_experience" id="job_experience"
													value="${basicInfo.getJobExp() }" onchange="checkJobExperience()"
													placeholder="<fmt:message key="JobExperienceTip" />" title="<fmt:message key="JobExperience" />"/>
												</p>
												<!-- 手机号 -->
												<p>
													<input class="form-control" type="text" name="telephone" id="telephone"
													value="${basicInfo.getTelephone() }" onchange="checkPhone()"
													placeholder="<fmt:message key="PhoneTip" />" title="<fmt:message key="Phone" />"/>
												</p>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="fom-info">
											<div class="form-filed">
												<!-- 当前所在地 -->
												<p>
													<input class="form-control" type="text" name="current_loc" id="current_loc"
													value="${basicInfo.getCurrentLoc() }" onchange="checkCurrentLocation()"
													placeholder="<fmt:message key="CurrentLocationTip" />" title="<fmt:message key="CurrentLocation" />"/>
												</p>
												<!-- 户口所在地 -->
												<p>
													<input class="form-control" type="text" name="resident_loc" id="resident_loc"
													value="${basicInfo.getResidentLoc() }" onchange="checkResidentLocation()"
													placeholder="<fmt:message key="ResidentLocationTip" />" title="<fmt:message key="ResidentLocation" />"/>
												</p>
											</div>
										</div>
									</div>
								</div>
								<!-- 求职意向 -->
								<div class="col-md-12">
									<div class="fom-info">
										<div class="form-filed">
											<p>
												<input class="form-control" type="text" name="job_intension" id="job_intension"
												value="${basicInfo.getJobIntension() }" onchange="checkJobIntension()"
												placeholder="<fmt:message key="JobIntensionTip" />" title="<fmt:message key="JobIntension" />"/>
											</p>
										</div>
									</div>
								</div>
							</div>
							
							<!-- 教育经历信息区域 -->
							<div class="row">
								<div class="col-md-12">
									<div class="info-title mt-4">
										<h4><fmt:message key="EducationExperience" /></h4>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-info">
										<div class="info-field">
											<!-- 毕业学校 -->
											<p>
												<input class="form-control" type="text" name="graduate_school" id="graduate_school"
												value="${education.getSchool() }" onchange="checkGraduationSchool()"
												placeholder="<fmt:message key="GraduationSchoolTip" />" title="<fmt:message key="GraduationSchool" />"/>
											</p>
											<!-- 专业 -->
											<p>
												<input class="form-control" type="text" name="profession" id="profession"
												value="${education.getProfession() }" onchange="checkProfession()"
												placeholder="<fmt:message key="ProfessionTip" />" title="<fmt:message key="Profession" />"/>
											</p>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="fom-info">
										<div class="form-filed">
											<!-- 毕业时间 -->
											<input type="hidden" id="graduation_date" name="graduation_date" value="" />
											<div class="input-group date graduation_date1" data-date="" data-date-format="yyyy-mm-dd" 
												data-link-field="graduation_date" data-link-format="yyyy-mm-dd">
												<input class="form-control" type="text" onchange="updateGraduationDate(this.value)" id="graduation_date1"
												placeholder="<fmt:message key="GraduationDateTip" />" title="<fmt:message key="GraduationDate" />"/>
												<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
											</div>
											<!-- 教育程度 -->
											<p>
												<input class="form-control" type="text" name="education_degree" id="education_degree"
												value="${education.getDegree() }" onchange="checkEducationDegree()"
												placeholder="<fmt:message key="EducationDegreeTip" />" title="<fmt:message key="EducationDegree" />"/>
											</p>
										</div>
									</div>
								</div>
							</div>
							
							<!-- 项目经验信息区域 -->
							<div class="row">
								<div class="col-md-12">
									<div class="info-title mt-4">
										<h4><fmt:message key="ProjectExperience" /></h4>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-info">
										<div class="info-field">
											<!-- 项目名称 -->
											<p>
												<input class="form-control" type="text" name="project_name" id="project_name"
												value="${projectExp.getName() }" onchange="checkProjectName()"
												placeholder="<fmt:message key="ProjectNameTip" />" title="<fmt:message key="ProjectName" />"/>
											</p>
											<!-- 担任职务 -->
											<p>
												<input class="form-control" type="text" name="project_job" id="project_job"
												value="${projectExp.getProjectJob() }" onchange="checkProjectJob()"
												placeholder="<fmt:message key="ProjectJobTip" />" title="<fmt:message key="ProjectJob" />"/>
											</p>
										</div>
									</div>
								</div>
								<!-- 项目参与时间段 -->		
								<div class="col-md-6">
									<!-- 项目开始时间 -->
									<input type="hidden" id="project_period_start" name="project_period_start" value="" />
									<div class="input-group date project_period_start_data" data-date="" data-date-format="yyyy-mm-dd" 
										data-link-field="project_period_start" data-link-format="yyyy-mm-dd">
										<input class="form-control" type="text" onchange="updateProjectPeriodStart(this.value)" id="project_period_start_data"
										placeholder="<fmt:message key="ProjectPeriodStartTip" />" title="<fmt:message key="ProjectPeriodStart" />"/>
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
								<div class="col-md-6">
									<!-- 项目结束时间 -->
									<input type="hidden" id="project_period_end" name="project_period_end" value="" />
									<div class="input-group date project_period_end_data" data-date="" data-date-format="yyyy-mm-dd" 
										data-link-field="project_period_end" data-link-format="yyyy-mm-dd">
										<input class="form-control" type="text" onchange="updateProjectPeriodEnd(this.value)" id="project_period_end_data"
										placeholder="<fmt:message key="ProjectPeriodEndTip" />" title="<fmt:message key="ProjectPeriodEnd" />"/>
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
								<!-- 项目简述 -->
								<div class="col-md-12">
									<textarea class="form-control" name="project_desc" id="project_desc" rows="5" onchange="checkProjectDescription()"
									placeholder="<fmt:message key="ProjectDescriptionTip" />" title="<fmt:message key="ProjectDescription" />">${projectExp.getProjectDesc() }</textarea>
								</div>
							</div>
							
							<!-- 工作经验信息区域 -->
							<div class="row">
								<div class="col-md-12">
									<div class="info-title mt-4">
										<h4><fmt:message key="WorkExperience" /></h4>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-info">
										<div class="info-field">
											<!-- 工作职称 -->
											<p>
												<input class="form-control" type="text" name="work_title" id="work_title"
												value="${workExp.getWorkTitle() }" onchange="checkWorkTitle()"
												placeholder="<fmt:message key="WorkTitleTip" />" title="<fmt:message key="WorkTitle" />"/>
											</p>
											<!-- 工作部门 -->
											<p>
												<input class="form-control" type="text" name="department" id="department"
												value="${workExp.getDepartment() }" onchange="checkWorkDepartment()"
												placeholder="<fmt:message key="WorkDepartmentTip" />" title="<fmt:message key="WorkDepartment" />"/>
											</p>
										</div>
									</div>
								</div>
								<!-- 工作参与时间段 -->		
								<div class="col-md-6">
									<!-- 工作开始时间 -->
									<input type="hidden" id="work_period_start" name="work_period_start" value="" />
									<div class="input-group date work_period_start_data" data-date="" data-date-format="yyyy-mm-dd" 
										data-link-field="work_period_start" data-link-format="yyyy-mm-dd">
										<input class="form-control" type="text" onchange="updateWorkPeriodStart(this.value)" id="work_period_start_data"
										placeholder="<fmt:message key="WorkPeriodStartTip" />" title="<fmt:message key="WorkPeriodStart" />"/>
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
								<div class="col-md-6">
									<!-- 工作结束时间 -->
									<input type="hidden" id="work_period_end" name="work_period_end" value="" />
									<div class="input-group date work_period_end_data" data-date="" data-date-format="yyyy-mm-dd" 
										data-link-field="work_period_end" data-link-format="yyyy-mm-dd">
										<input class="form-control" type="text" onchange="updateWorkPeriodEnd(this.value)" id="work_period_end_data"
										placeholder="<fmt:message key="WorkPeriodEndTip" />" title="<fmt:message key="WorkPeriodEnd" />"/>
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
							</div>
							
							<!-- 其他信息区域 -->
							<div class="row">
								<div class="col-md-12">
									<div class="info-title mt-4">
										<h4>Others Info</h4>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" id="gridCheck"> 
											<label class="form-check-label" for="gridCheck"> Yes, I agreed all the terms of conditions to apply the job </label>
										</div>    
									</div>
									<button type="button" id="submitResumeBtn" class="btn btn-primary job-post-btn btn-lg btn-block mt-2"><fmt:message key="SubmitResume" /></button>
								</div>
							</div>
	
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
	<!-- datetimepicker -->
	<script src="/SWRW/public/assets/js/bootstrap-datetimepicker.min.js"></script>
	<script src="/SWRW/public/assets/js/locales/bootstrap-datetimepicker.${language}.js"></script>
	<!-- end-公共脚本 -->
	<!-- 自定义脚本 -->
	<script>
		var basicInfo_id = '${basicInfo.getID() }'
		var oldBirthday = '${COMUtil.dataToStr(basicInfo.getBirthday()) }';
		var oldGraduationDate = '${COMUtil.dataToStr(education.getGraduationDate()) }';
		var oldProjectPeriodStart = '${projectExp.getPeriod()}'.split("_")[0];
		var oldProjectPeriodEnd = '${projectExp.getPeriod()}'.split("_")[1];
		var oldWorkPeriodStart = '${workExp.getPeriod()}'.split("_")[0];
		var oldWorkPeriodEnd = '${workExp.getPeriod()}'.split("_")[1];
	</script>
	<script src="/SWRW/public/js/applicant/completeResume.js"></script>
	<!-- 修改简历照片模态框 -->
	<%@include file="/WEB-INF/view/applicant/resume/changeResumePhotoModal.jsp" %>
</body>
</html>