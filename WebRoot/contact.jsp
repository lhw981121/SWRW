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
						<h1><fmt:message key="ContactUs" /></h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><fmt:message key="ContactUs" /></li>
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
					<div class="col-lg-8 col-md-7">
						<div class="contact-wrapper">
							<div class="google-maps mb-4">
								<iframe src="http://www.google.cn/maps/embed?pb=!1m18!1m12!1m3!1d2918.7245402921867!2d-77.51051068460839!3d42.98407590345725!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89d137044fbb1913%3A0xd8d3f38d6f1f4f70!2sMendon+Golf+Club!5e0!3m2!1sen!2sbd!4v1540117988439"
									style="border:0;width:100%;height:450px;"></iframe>
							</div>
							<p>Nrem ipsum dolor sit amet, eleifend nunc tellus turpis. Eu
								lorem urna liberve bulumfermentum interdum dui commodo natoque
								libero pretium, sapien commodo, urna nunc, adipiscing laoreet
								pellentesque. Molestie erat sem</p>
							<div class="contact-form mt-4">
								<div class="section-title inner-section">
									<h3>Contact Us</h3>
								</div>
								<form action="#">
									<input type="text" placeholder="Your Name" /> <input
										type="text" placeholder="Your Email" class="float-right" />
									<textarea placeholder="Type Comment"></textarea>
									<button type="submit" class="btn-c buttonfx curtainup">Send
										Message</button>
								</form>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-5">
						<div class="right-sidebar">
							<div class="sidebar-widget mb-4">
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
							</div>
							<div class="sidebar-widget">
								<div class="sidebar-title">
									<h3>Contact info</h3>
								</div>
								<div class="sidebar-details">
									<div class="contact-details  d-flex">
										<div class="icon">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="contact-info">
											<p>
												Email: <span>company@gmail.com</span>
											</p>
										</div>
									</div>
									<div class="contact-details  d-flex">
										<div class="icon">
											<i class="fa fa-phone"></i>
										</div>
										<div class="contact-info">
											<p>
												Phone: <span>05-682-0355</span>
											</p>
										</div>
									</div>
									<div class="contact-details  d-flex">
										<div class="icon">
											<i class="fa fa-map-marker"></i>
										</div>
										<div class="contact-info">
											<p>
												Location: <span>South Angelina,Canada</span>
											</p>
										</div>
									</div>
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
<!-- end-公共脚本 -->
</body>
</html>