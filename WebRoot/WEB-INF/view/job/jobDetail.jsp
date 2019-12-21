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
							${job.name }
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/job"><fmt:message key="Job" /></a></li>
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
									<a href="javascript:;"> 
										<img src="/SWRW/public/images/company/job/${job.ID }.jpg?t=${Math.random()}" alt="image" 
										onerror="this.src='/SWRW/public/images/company/job/-'+parseInt(Math.random()*(12-1+1)+1,10)+'.png';this.onerror=null" />
									</a>
								</div>
								<div class="job-meta">
									<div class="title">
										<h4>${job.name }</h4>
									</div>
									<div class="meta-info d-flex">
										<p><i class="fa fa-briefcase" aria-hidden="true"></i><a href="/SWRW/job/company_detail?company_id=${job.companyID }">${job.companyName }</a></p>
										<p><i class="fa fa-map-marker" aria-hidden="true"></i>${job.area }</p>
										<p><i class="fa fa-calendar" aria-hidden="true"></i><span id="job_created"></span></p>
									</div>
								</div>
								<div class="timing ml-auto" style="width:120px">
									<a class="time-btn${job.state } time-btn" id="job_state_str" ></a>
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
							<c:choose>
							<c:when test="${user.ID!=0 }">
								<c:if test="${job.state==1}">
									<c:if test="${jobApply==null }"><!-- 首次申请 -->
										<button class="btn btn-success" onclick="applyJob(${job.ID},${user.applicantID},'first');"><fmt:message key="ApplyNow" /></button>
									</c:if>
									<c:if test="${jobApply!=null&&jobApply.state==3 }"><!-- 重新申请 -->
										<button class="btn btn-success" onclick="applyJob(${job.ID},${user.applicantID},'repeat');"><fmt:message key="RepeatApply" /></button>
									</c:if>
									<c:if test="${jobApply!=null&&jobApply.state==1 }"><!-- 已申请 -->
										<button class="btn btn-warning"><fmt:message key="Applied" /></button>
									</c:if>
									<c:if test="${jobApply!=null&&jobApply.state==2 }"><!-- 申请通过 -->
										<button class="btn btn-success"><fmt:message key="Accepted" /></button>
									</c:if>
								</c:if>
							</c:when>
							<c:otherwise>
								<button class="btn btn-success" onclick="UserNotLogin()"><fmt:message key="ApplyNow" /></button>
							</c:otherwise>
							</c:choose>
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
									<h3><fmt:message key="Contact" /></h3>
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
												<fmt:message key="ServiceTime" />: <span>sat - mon 09:00am - 05:00pm</span>
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
<script>
	$('#job_state_str').html(getJobState(${job.state}));
	$('#job_created').html(FromTodayFormat(IfNull('${job.created}')));
</script>
<script src="/SWRW/public/js/job/job_detail.js"></script>
</body>
</html>