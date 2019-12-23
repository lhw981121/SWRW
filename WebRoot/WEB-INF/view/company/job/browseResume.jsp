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
							<c:if test="${job==null }">浏览收到的简历</c:if>
							<c:if test="${job!=null }">浏览职位&ensp;${job.name }&ensp;收到的简历</c:if>
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/company"><fmt:message key="Company" /></a></li>
							<li class="list-inline-item">简历浏览</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!---end header-banner -->

	<main class="main-content-area">
		<div class="candidate-list-area pt-100 pb-100">
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-md-7">
						<div class="candidate-list-wrapper">
							<div class="candidate-list-list" id="browseResumeData">

							</div>
						</div>
						
						<!-- 分页 -->
						<%@include file="/WEB-INF/view/common/swrw/pagination.jsp"%>
						
					</div>
					<div class="col-lg-4 col-md-5">
						<div class="right-sidebar">
							<!-- <div class="sidebar-widget mb-4">
								<div class="sidebar-title">
									<h3>Search Jobs</h3>
								</div>
								<div class="sidebar-details">
									<form action="#">
										<div class="form-group">
											<input type="search" placeholder="keyword" />
										</div>
										<button type="submit" class="buttonfx curtainup">Search</button>
									</form>
								</div>
							</div> -->
							<div class="sidebar-widget mb-4">
								<div class="sidebar-title">
									<h3>Jobs Category</h3>
								</div>
								<div class="sidebar-details">

									<ul class="category-list list-inline">
										<li><a href="#">Government (02)</a></li>
										<li><a href="#">Media  News (08)</a></li>
										<li><a href="#">Accounting (03)</a></li>
										<li><a href="#">Design (06)</a></li>
										<li><a href="#">Educations (45)</a></li>
										<li><a href="#">Medical (16)</a></li>
									</ul>

								</div>
							</div>
							<div class="sidebar-widget mb-4">
								<div class="sidebar-title">
									<h3>Recent Post</h3>
								</div>
								<div class="sidebar-details">
									<div class="recent-post-wrapper">
										<div class="recent-post d-flex">
											<div class="post-thumb">
												<a href="blog-details.html"><img
													src="/SWRW/public/assets/images/post-thumb-1.png"
													alt="image" /></a>
											</div>
											<div class="post-title">
												<p>
													<a href="blog-details.html">5 Content Management System</a>
												</p>
											</div>
										</div>
									</div>
									<div class="recent-post d-flex">
										<div class="post-thumb">
											<a href="blog-details.html"><img
												src="/SWRW/public/assets/images/post-thumb-2.png"
												alt="image" /></a>
										</div>
										<div class="post-title">
											<p>
												<a href="blog-details.html">wellentesq epre tium quis
													comment</a>
											</p>
										</div>
									</div>
									<div class="recent-post d-flex">
										<div class="post-thumb">
											<a href="blog-details.html"><img
												src="/SWRW/public/assets/images/post-thumb-3.png"
												alt="image" /></a>
										</div>
										<div class="post-title">
											<p>
												<a href="blog-details.html">Increase More Traffic  More
													Sales</a>
											</p>
										</div>
									</div>
									<div class="recent-post d-flex">
										<div class="post-thumb">
											<a href="blog-details.html"><img
												src="/SWRW/public/assets/images/post-thumb-4.png"
												alt="image" /></a>
										</div>
										<div class="post-title">
											<p>
												<a href="blog-details.html">Ttesq epre tium quis comment</a>
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="sidebar-widget mb-4 p-0">
								<a href="#"><img src="/SWRW/public/assets/images/ads.png"
									alt="image" /></a>
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
var job_id = '${job==null?0:job.ID}';
var company_id = job_id==0?'${company.ID}':0;
</script>
<script src="/SWRW/public/js/company/company_browseResume.js"></script>
</body>
</html>