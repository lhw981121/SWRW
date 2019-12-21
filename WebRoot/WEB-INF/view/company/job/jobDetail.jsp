<%@ page language="java" import="java.util.*,com.swzj.swrw.util.COMUtil" pageEncoding="UTF-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/swrw/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 公共头部 -->
<%@include file="/WEB-INF/view/common/swrw/head.jsp"%>
<!-- 职位信息验证JS -->
<script src="/SWRW/public/js/company/company_jobInfo.js"></script>

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
							职位详情
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/company"><fmt:message key="Company" /></a></li>
							<li class="list-inline-item">职位详情</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!---end header-banner -->

	<main class="main-content-area">
		<div class="job-post-details-area pt-100 pb-100">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<div class="job-post-wrapper">
							<div class="single-job mb-4 d-md-flex">
								<div class="logo">
									<a href="javascript:;" title="更换职位头像"> 
										<img src="/SWRW/public/images/company/job/${job.ID }.jpg?t=${Math.random()}" id="job_head_${job.ID }"
										onclick="$('#changeJobHeadModal').modal('show');$('#manage_job_id').val('${job.ID }');"
										alt="image" onerror="this.src='/SWRW/public/images/company/job/1.png';this.onerror=null" />
									</a>
								</div>
								<div class="job-meta">
									<div class="title">
										<h4>${job.name }</h4>
									</div>
									<div class="meta-info d-flex">
										<p><i class="fa fa-briefcase" aria-hidden="true"></i><a href="/SWRW/company">${job.companyName }</a></p>
										<p><i class="fa fa-map-marker" aria-hidden="true"></i>${job.area }</p>
										<p><i class="fa fa-calendar" aria-hidden="true"></i>${COMUtil.dataToStrLong(job.updated)}</p>
									</div>
								</div>
								<div class="timing ml-auto" style="width:120px">
									<a class="time-btn" id="job_state_str" href="javascript:;" onclick="changeJobState(${job.state })" title="变更职位招聘状态"></a>
									<input type="hidden" id="job_state" name="job_state" value="${job.state }"/>
								</div>
							</div>
							<div class="entry-content">
								<h4>职位描述</h4>
								<p>${job.desc }</p>
								<h4>招聘人数</h4>
								<p>${job.hiringNum }</p>
								<h4>职位薪资</h4>
								<p>${job.salary }</p>
								<h4>截止时间</h4>
								<p>${COMUtil.dataToStr(job.endDate) }</p>
							</div>
							<div class="job-apply-wrapper mb-5 mt-4">
							<c:if test="${job.state>=1}">
								<a class="btn btn-danger" href="javascript:;" 
								onclick="if(${job.state>=1}){$('#changeJobDetail').modal('show')}
											else{swal({title: language=='zh_CN'?'职位不可用':'Position Unavailable',
												text: language=='zh_CN'?'该职位还未通过审核，无法进行修改！':'This position has not been approved and cannot be modified!',
												type: 'error',});}">修改</a>
								<a class="btn btn-primary" href="browse_resume?job_id=${job.ID }">浏览简历</a>
							</c:if>
							</div>
						</div>
						<div class="job-post-list">
							<div class="sidebar-title inner-section ">
								<h3>Related Jobs</h3>
							</div>
							<div class="single-job d-md-flex" data-aos="fade-left">
								<div class="logo">
									<a href="job-post.html"><img
										src="/SWRW/public/assets/images/logo-2.png" alt="image" /></a>
								</div>
								<div class="job-meta">
									<div class="title">
										<h4>
											<a href="job-post.html"> Restaurant Team Member</a>
										</h4>
									</div>
									<div class="meta-info d-flex">
										<p>
											<i class="fa fa-briefcase" aria-hidden="true"></i>Kibo
										</p>
										<p>
											<i class="fa fa-map-marker" aria-hidden="true"></i><a
												href="#">Canada</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>Today
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn1 time-btn" href="#">Full Time</a>
								</div>
							</div>
							<div class="single-job  d-md-flex" data-aos="fade-right">
								<div class="logo">
									<a href="job-post.html"><img
										src="/SWRW/public/assets/images/logo-3.png" alt="image" /></a>
								</div>
								<div class="job-meta">
									<div class="title">
										<h4>
											<a href="job-post.html"> Web Designer needed</a>
										</h4>
									</div>
									<div class="meta-info d-flex">
										<p>
											<i class="fa fa-briefcase" aria-hidden="true"></i>Kibo
										</p>
										<p>
											<i class="fa fa-map-marker" aria-hidden="true"></i><a
												href="#">City Town</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>4 Days ago
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn2 time-btn" href="#">Part Time</a>
								</div>
							</div>
							<div class="single-job  d-md-flex" data-aos="fade-left">
								<div class="logo">
									<a href="job-post.html"><img
										src="/SWRW/public/assets/images/logo-4.png" alt="image" /></a>
								</div>
								<div class="job-meta">
									<div class="title">
										<h4>
											<a href="job-post.html"> MySQL Developers</a>
										</h4>
									</div>
									<div class="meta-info d-flex">
										<p>
											<i class="fa fa-briefcase" aria-hidden="true"></i>Kibo
										</p>
										<p>
											<i class="fa fa-map-marker" aria-hidden="true"></i><a
												href="#">London</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>3 Days ago
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn3 time-btn" href="#">Full Time</a>
								</div>
							</div>
							<div class="single-job d-md-flex" data-aos="fade-right">
								<div class="logo">
									<a href="job-post.html"><img
										src="/SWRW/public/assets/images/logo-5.png" alt="image" /></a>
								</div>
								<div class="job-meta">
									<div class="title">
										<h4>
											<a href="job-post.html"> UX/UI Designer</a>
										</h4>
									</div>
									<div class="meta-info d-flex">
										<p>
											<i class="fa fa-briefcase" aria-hidden="true"></i>Kibo
										</p>
										<p>
											<i class="fa fa-map-marker" aria-hidden="true"></i><a
												href="#">London</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>Today
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn4 time-btn" href="#">Part Time</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="right-sidebar">
							<div class="sidebar-widget mb-4">
								<div class="sidebar-title">
									<h3>联系方式</h3>
								</div>
								<div class="sidebar-details">
									<div class="contact-details  d-flex">
										<div class="icon">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="contact-info">
											<p>
												<fmt:message key="Email" />: <span>${company_user.email }</span>
											</p>
										</div>
									</div>
									<div class="contact-details  d-flex">
										<div class="icon">
											<i class="fa fa-phone"></i>
										</div>
										<div class="contact-info">
											<p>
												<fmt:message key="Phone" />: <span>${company_user.phone }</span>
											</p>
										</div>
									</div>
									<div class="contact-details  d-flex">
										<div class="icon">
											<i class="fa fa-map-marker"></i>
										</div>
										<div class="contact-info">
											<p>
												<fmt:message key="CompanyArea" />: <span>${company.area }</span>
											</p>
										</div>
									</div>
									<div class="contact-details  d-flex">
										<div class="icon">
											<i class="fa fa-clock-o"></i>
										</div>
										<div class="contact-info">
											<p>
												服务时间: <span>sat - mon 09:00am - 05:00pm</span>
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="google-maps">
								<iframe src="http://www.google.cn/maps/embed?pb=!1m18!1m12!1m3!1d3648.736304708796!2d90.39302341506834!3d23.863495784533754!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3755c421ebca2981%3A0x2428d2fc4b4ba3f8!2sFreelancing+Care!5e0!3m2!1sen!2sbd!4v1531393261353"
									style="border:0;width:100%;height:210px;"></iframe>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	
	<!-- 修改职位信息模态框 -->
	<div class="modal fade" id="changeJobDetail" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:3%">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title text-primary"><i class="fa fa-edit"></i>&nbsp;修改职位信息</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<!-- 职位名称 -->
								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="job_name"><fmt:message key="JobName" /></label>
										<input type="text" class="form-control" id="job_name" value="${job.getName() }"
											placeholder="<fmt:message key="JobNameTip" />">
									</div>
								</div>
								<!-- 职位所在地 -->
								<div class="form-row">
									<div class="col-md-12">
										<label><fmt:message key="JobArea" /></label>
										<input type="hidden" id="job_area" name="area" value="${job.getArea() }">
									</div>
									<div class="col-md-12 row">
										<div class="col-md-4">
											<select id="citySelect1" name="" class="form-control custom-select" onblur="updateJobArea()">
												<option value=""><fmt:message key="AreaTip1" /></option>
											</select>
										</div>
										<div class="col-md-4">
											<select id="citySelect2" name="" class="form-control custom-select" onblur="updateJobArea()">
												<option value=""><fmt:message key="AreaTip2" /></option>
											</select>
										</div>
										<div class="col-md-4 text-right">
											<select id="citySelect3" name="" class="form-control custom-select" onblur="updateJobArea()">
												<option value=""><fmt:message key="AreaTip3" /></option>
											</select>
										</div>
									</div>
								</div>
								<!-- 职位薪资区间 -->
								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="job_salary"><fmt:message key="JobSalary" /></label>
										<input type="text" class="form-control" id="job_salary" value="${job.getSalary() }"
										placeholder="<fmt:message key="JobSalaryTip" />" >
									</div>
								</div>
								<div class="form-row">
									<!-- 职位招聘人数 -->
									<div class="form-group col-md-6">
										<label for="job_hiringnum"><fmt:message key="JobHiringnum" /></label> 
										<input type="text" class="form-control" id="job_hiringnum" value="${job.getHiringNum() }"
										placeholder="<fmt:message key="JobHiringnumTip"/>" />
									</div>
									<!-- 职位截止时间 -->
									<div class="form-group col-md-6">
										<label for="job_endtime"><fmt:message key="JobEndTime" /></label> 
										<input type="hidden" id="job_endtime" name="job_endtime" value="" />
										<div class="input-group date job_endtime_date" data-date="" data-date-format="yyyy-mm-dd" 
										data-link-field="job_endtime" data-link-format="yyyy-mm-dd">
											<input class="form-control" type="text" onchange="updateJobEndTime(this.value)" id="job_endtime_date"
											placeholder="<fmt:message key="JobEndTimeTip" />" autocomplete="off"/>
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										</div>
									</div>
								</div>
								<!-- 职位描述 -->
								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="job_desc"><fmt:message key="JobDescription" /></label>
										<textarea class="form-control" id="job_desc" rows="10"
										placeholder="<fmt:message key="JobDescriptionTip" />">${job.getDesc() }</textarea>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="changeJobDetail(${job.ID});"><fmt:message key="Submit" /></button>
					<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Cancel" /></button>
				</div>
			</div>
		</div>
	</div>

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
<!-- datetimepicker -->
<script src="/SWRW/public/assets/js/bootstrap-datetimepicker.min.js"></script>
<script src="/SWRW/public/assets/js/locales/bootstrap-datetimepicker.${language}.js"></script>
<!-- 自定义脚本 -->
<script>
	$('#job_state_str').html(getJobState(${job.state}));
	var oldEndTime = '${COMUtil.dataToStr(job.getEndDate()) }';
	var selectTrue = [IfNull('${job.area}'.split(" ")[0]),IfNull('${job.area}'.split(" ")[1]),IfNull('${job.area}'.split(" ")[2])];
</script>
<script src="/SWRW/public/js/city/citys.js"></script>
<script src="/SWRW/public/js/city/selectCity.js"></script>
<script src="/SWRW/public/js/company/company_jobDetail.js"></script>
</body>
</html>