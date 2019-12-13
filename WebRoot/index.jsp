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

	<main class="main-content-area">
		<!--slider-start -->
		<div class="slider-area clearfix">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="slider-wrapper">
							<div class="row">
								<div class="col-md-68 mx-auto">
									<div class="slider-text text-center">
										<div class="slide-title">
											<h1>
												<span class="mobile-show"><fmt:message key="5,000+JobsAvailable" /></span>
												<span class="mobile-hide"><fmt:message key="5,000+Jobs" /><span class="typed"></span> </span>
											</h1>
											<div class="typed-strings">
												<p>
													<fmt:message key="Available" />
												</p>
											</div>
											<p>
												<fmt:message key="IndexDisplay" />
											</p>
										</div>
										<div class="slider-btn">
											<div class="buttonfx btn-green">
												<a class="shutter-in-horizontal green-border-2" href="javascript:;"><fmt:message key="ApplyNow" /></a>
											</div>
											<div class="btn-trasparent-green buttonfx curtainup">
												<a class="shutter-in-horizontal green-border-2" href="javascript:;"><fmt:message key="JoinwithUs" /></a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="job-search-bar">
								<div class="search-bar text-center">
									<form action="#" class="">
										<div class="form-row">
											<div class="col-md-3">
												<input type="search" placeholder="<fmt:message key="EnterKeywords"/>" />
											</div>
											<div class="col-md-3">
												<select class="custom-multi-select" name="state">
													<option value="" disabled selected><fmt:message key="CompanySize" /></option>
													<option value="AL"><fmt:message key="Lessthan100people" /></option>
													<option value="AL"><fmt:message key="100to200people" /></option>
													<option value="AL"><fmt:message key="200to500people" /></option>
													<option value="AL"><fmt:message key="500to1000people" /></option>
													<option value="AL"><fmt:message key="Morethan1000people" /></option>

												</select>
											</div>
											<div class="col-md-3">
												<select class="custom-multi-select" name="state">
													<option value="" disabled selected><fmt:message key="CompanyType" /></option>
													<option value="part"><fmt:message key="Jointstockenterprises" /></option>
													<option value="full"><fmt:message key="Foreignenterprise" /></option>
													<option value="remote"><fmt:message key="Jointventure" /></option>

												</select>
											</div>
											<div class="col-md-3">
												<button type="submit" class="buttonfx curtainup">
													<fmt:message key="SearchJobs" />
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--slider-end -->

		<!--work-area-start -->
		<div class="how-work-area pt-100 pb-100 clearfix">
			<div class="container">
				<div class="how-work-wrapper text-center">
					<div class="row">
						<div class="col-md-4">
							<div class="work-block" data-aos="fade-up">
								<div class="icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/lock.png"
										alt="image" /></a>
								</div>
								<div class="title">
									<h3>Create Account</h3>
								</div>
								<p>
									Lorem ipsum dolor sit amet, a arcu justo eget, placerat
									suspendisse ornare accumsan et fringilla consectetuer <br /> <a
										class="read-more" href="javascript:;">Read More</a>
								</p>
							</div>
						</div>
						<div class="col-md-4">
							<div class="work-block">
								<div class="icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/search.png"
										alt="image" /></a>
								</div>
								<div class="title">
									<h3>Serach Job</h3>
								</div>
								<p>
									Lorem ipsum dolor sit amet, a arcu justo eget, placerat
									suspendisse ornare accumsan et fringilla consectetuer <br /> <a
										class="read-more" href="javascript:;">Read More</a>
								</p>
							</div>
						</div>
						<div class="col-md-4">
							<div class="work-block m-0">
								<div class="icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/resume.png"
										alt="image" /></a>
								</div>
								<div class="title">
									<h3>Submit Resume</h3>
								</div>
								<p>
									Lorem ipsum dolor sit amet, a arcu justo eget, placerat
									suspendisse ornare accumsan et fringilla consectetuer <br /> <a
										class="read-more" href="javascript:;">Read More</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--work-area-end -->
		<!--job-post-area-start -->
		<div class="job-post-area pt-100 bg-color2 pb-100 minus-15 clearfix">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="section-title text-left">
							<span>This week's top jobs</span>
							<h2>Featured Jobs</h2>
							<div class="line"></div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6 col-md-12">
						<div class="job-post-list">
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
												href="javascript:;">Canada</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>Today
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn1 time-btn" href="javascript:;">Full Time</a>
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
												href="javascript:;">City Town</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>4 Days ago
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn2 time-btn" href="javascript:;">Part Time</a>
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
												href="javascript:;">London</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>3 Days ago
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn3 time-btn" href="javascript:;">Full Time</a>
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
												href="javascript:;">London</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>Today
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn4 time-btn" href="javascript:;">Part Time</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-12">
						<div class="job-post-list">
							<div class="single-job  d-md-flex" data-aos="fade-left">
								<div class="logo">
									<a href="job-post.html"><img
										src="/SWRW/public/assets/images/logo-6.png" alt="image" /></a>
								</div>
								<div class="job-meta">
									<div class="title">
										<h4>
											<a href="job-post.html"> Sales Engineer</a>
										</h4>
									</div>
									<div class="meta-info d-flex">
										<p>
											<i class="fa fa-briefcase" aria-hidden="true"></i>Kibo
										</p>
										<p>
											<i class="fa fa-map-marker" aria-hidden="true"></i><a
												href="javascript:;">London</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>01 Days ago
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn1 time-btn" href="javascript:;">Full Time</a>
								</div>
							</div>
							<div class="single-job d-md-flex" data-aos="fade-right">
								<div class="logo">
									<a href="job-post.html"><img
										src="/SWRW/public/assets/images/logo-7.png" alt="image" /></a>
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
												href="javascript:;">London</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>2 Days ago
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn2 time-btn" href="javascript:;">Part Time</a>
								</div>
							</div>
							<div class="single-job  d-md-flex" data-aos="fade-left">
								<div class="logo">
									<a href="job-post.html"><img
										src="/SWRW/public/assets/images/logo-8.png" alt="image" /></a>
								</div>
								<div class="job-meta">
									<div class="title">
										<h4>
											<a href="job-post.html"> Software Developer -IT Co</a>
										</h4>
									</div>
									<div class="meta-info d-flex">
										<p>
											<i class="fa fa-briefcase" aria-hidden="true"></i>Kibo
										</p>
										<p>
											<i class="fa fa-map-marker" aria-hidden="true"></i><a
												href="javascript:;">London</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>03 Days ago
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn3 time-btn" href="javascript:;">Full Time</a>
								</div>
							</div>
							<div class="single-job d-md-flex" data-aos="fade-left">
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
												href="javascript:;">London</a>
										</p>
										<p>
											<i class="fa fa-calendar" aria-hidden="true"></i>Today
										</p>
									</div>
								</div>
								<div class="timing ml-auto">
									<a class="time-btn4 time-btn" href="javascript:;">Part Time</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--job-post-area-end -->
		<!--apps-download-area-start -->
		<div class="apps-download-area pt-100 pb-100 clearfix">
			<div class="container">
				<div class="row">
					<div class="col-md-7">
						<div class="apps-details-content">
							<div class="section-title text-left">
								<span>Search the job from app</span>
								<h2>
									Download our app <br /> and find your dream job
								</h2>
								<div class="line"></div>
							</div>
							<p>
								<b>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
									sed do eiusmod tempor incididunt ut labore et dolore magna
									aliqua. </b>
							</p>
							<p>non tellus orci ac auctor augue. Aliquam sem fringilla ut
								morbi tincidunt augue. Suspendisse interdum consectetur libero
								id faucibus nisl. Semper auctor neque vitae tempus quam
								pellentesque nec nam aliquam.</p>
							<p>Eu scelerisque felis imperdiet proin fermentum. Odio
								pellentesque diam volutpat commodo sed. At elementum eu
								facilisis sed odio morbi quis commodo odio.</p>
							<div class="apps-btn d-md-flex">
								<a href="javascript:;"> <img
									src="/SWRW/public/assets/images/Google-Play.png" alt="image" />
								</a> <a href="javascript:;"> <img
									src="/SWRW/public/assets/images/App-Store.png" alt="image" />
								</a>
							</div>
						</div>
					</div>
					<div
						class="col-md-4 d-flex align-items-center themeix-h offset-md-1">
						<div class="mobile themeix-h" data-aos="fade-up-left">
							<img src="/SWRW/public/assets/images/mobile-img.png" alt="image" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--apps-download-area-end -->
		<!--job-browse-area-start -->
		<div class="job-browse-area pt-100 pb-100 clearfix"
			style="background-image:url(/SWRW/public/assets/images/bg-2.jpg)">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="details-text text-center" data-aos="flip-up">
							<div class="title pb-3">
								<div class="heading-two mb-2">
									<h2>
										Browse Our <span>6,000+ </span>Latest Jobs
									</h2>
								</div>
								<p>Get your best job in here</p>
							</div>
							<div class="btn-trasparent-white buttonfx curtainup">
								<a href="javascript:;">Get started now</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--job-browse-area-end -->
		<!--job-categories-area-start -->
		<div class="job-categories-area bg-color2 pt-100 pb-100 clearfix">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="section-title text-left">
							<span>Most popular categories</span>
							<h2>Browse Category</h2>
							<div class="line"></div>
						</div>
					</div>
				</div>
				<div class="cat-list-items">
					<div class="row">
						<div class="col-md-4">
							<div class="single-category text-center" data-aos="flip-up">
								<div class="cat-icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/cat-1.jpg"
										alt="image" /></a>
								</div>
								<div class="cat-details">
									<h4>
										<a href="javascript:;">Accounting / finance</a><span>(10 open
											vacancies)</span>
									</h4>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="single-category text-center">
								<div class="cat-icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/cat-2.jpg"
										alt="image" /></a>
								</div>
								<div class="cat-details">
									<h4>
										<a href="javascript:;">Education training</a><span>(15 open
											vacancies)</span>
									</h4>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="single-category text-center" data-aos="flip-up">
								<div class="cat-icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/cat-3.jpg"
										alt="image" /></a>
								</div>
								<div class="cat-details">
									<h4>
										<a href="javascript:;">Book services </a><span>(17 open vacancies)</span>
									</h4>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="single-category text-center">
								<div class="cat-icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/cat-4.jpg"
										alt="image" /></a>
								</div>
								<div class="cat-details">
									<h4>
										<a href="javascript:;">Health care</a><span>(9 open vacancies)</span>
									</h4>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="single-category text-center">
								<div class="cat-icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/cat-5.jpg"
										alt="image" /></a>
								</div>
								<div class="cat-details">
									<h4>
										<a href="javascript:;">Automotive job</a><span>(6 open vacancies)</span>
									</h4>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="single-category text-center">
								<div class="cat-icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/cat-6.jpg"
										alt="image" /></a>
								</div>
								<div class="cat-details">
									<h4>
										<a href="javascript:;">Sales marketing</a><span>(12 open
											vacancies)</span>
									</h4>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="single-category text-center" data-aos="flip-up">
								<div class="cat-icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/cat-7.jpg"
										alt="image" /></a>
								</div>
								<div class="cat-details">
									<h4>
										<a href="javascript:;">Sales marketing</a><span>(12 open
											vacancies)</span>
									</h4>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="single-category text-center">
								<div class="cat-icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/cat-8.jpg"
										alt="image" /></a>
								</div>
								<div class="cat-details">
									<h4>
										<a href="javascript:;">Sales marketing</a><span>(12 open
											vacancies)</span>
									</h4>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="single-category text-center" data-aos="flip-up">
								<div class="cat-icon">
									<a href="javascript:;"><img src="/SWRW/public/assets/images/cat-9.jpg"
										alt="image" /></a>
								</div>
								<div class="cat-details">
									<h4>
										<a href="javascript:;">Sales marketing</a><span>(12 open
											vacancies)</span>
									</h4>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--job-categories-area-end -->
		<!--testimonial-area-start -->
		<div class="testimonial-area pt-100 pb-100 clearfix" id="tastimonial">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="section-title text-left">
							<span>Cover story</span>
							<h2>Success Stories</h2>
							<div class="line"></div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="testimonial-slider owl-carousel">
							<div class="testi-stories  text-left">
								<blockquote class="testimonial">
									<p>Porttitor lacus luctus accumsan tortor posuere ac ut
										consequat semper viverra nam libero justo laoreet sit amet
										cursus sit amet dictum sit amet justo donec enim diam
										vulputate ut pharetra</p>
								</blockquote>
								<div class="arrow-down"></div>
								<p class="testimonial-author">
									<img src="/SWRW/public/assets/images/testimonial-2.png"
										alt="image" /> Neil Roberts | <span>Designer</span>
								</p>
							</div>
							<div class="testi-stories  text-left">
								<blockquote class="testimonial">
									<p>Mollis nunc sed id semper risus in hendrerit gravida
										rutrum quisque non tellus orci ac auctor augue mauris augue
										neque gravida in fermentum et sollicitudin ac orci phasellus
										egestas tellu</p>
								</blockquote>
								<div class="arrow-down"></div>
								<p class="testimonial-author">
									<img src="/SWRW/public/assets/images/testimonial-1.png"
										alt="image" /> Jhon Doe | <span>Photgrapher</span>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--testimonial-area-end -->
		<!--team-area-start -->
		<div class="team-area pt-100 pb-100 clearfix bg-color2">
			<div class="container">
				<div class="team-wrapper text-center">
					<div class="row">
						<div class="col-md-12">
							<div class="section-title text-left">
								<span>Our top three freelancer</span>
								<h2>Top Freelancer</h2>
								<div class="line"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="team-reviewer" data-aos="fade-up">
								<div class="image">
									<a href="javascript:;"><img
										src="/SWRW/public/assets/images/team-img.jpg" alt="image" /></a>
									<ul class="team-social list-inline">
										<li class="facebook"><a href="javascript:;"><i
												class="fa fa-facebook-f"></i></a></li>
										<li class="twitter"><a href="javascript:;"><i
												class="fa fa-twitter"></i></a></li>
										<li class="linkedin"><a href="javascript:;"><i
												class="fa fa-linkedin"></i></a></li>
									</ul>

								</div>
								<div class="content">
									<h4>
										<a href="javascript:;">Paul Molive</a>
									</h4>
									<p>Web Developer</p>
									<ul class="team-star list-inline">
										<li class="list-inline-item"><i class="fa fa-star"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star-o"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star-o"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star-o"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star-o"
											aria-hidden="true"></i></li>
									</ul>
								</div>

							</div>
						</div>
						<div class="col-md-4">
							<div class="team-reviewer" data-aos="fade-up">
								<div class="image">
									<a href="javascript:;"><img
										src="/SWRW/public/assets/images/team-img2.jpg" alt="image" /></a>
									<ul class="team-social list-inline">
										<li class="facebook"><a href="javascript:;"><i
												class="fa fa-facebook-f"></i></a></li>
										<li class="twitter"><a href="javascript:;"><i
												class="fa fa-twitter"></i></a></li>
										<li class="linkedin"><a href="javascript:;"><i
												class="fa fa-linkedin"></i></a></li>
									</ul>

								</div>
								<div class="content">
									<h4>
										<a href="javascript:;">Paige Turner</a>
									</h4>
									<p>Php Developer</p>
									<ul class="team-star list-inline">
										<li class="list-inline-item"><i class="fa fa-star"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star-o"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star-o"
											aria-hidden="true"></i></li>
									</ul>
								</div>

							</div>
						</div>
						<div class="col-md-4">
							<div class="team-reviewer" data-aos="fade-up">
								<div class="image">
									<a href="javascript:;"><img
										src="/SWRW/public/assets/images/team-img3.jpg" alt="image" /></a>
									<ul class="team-social list-inline">
										<li class="facebook"><a href="javascript:;"><i
												class="fa fa-facebook-f"></i></a></li>
										<li class="twitter"><a href="javascript:;"><i
												class="fa fa-twitter"></i></a></li>
										<li class="linkedin"><a href="javascript:;"><i
												class="fa fa-linkedin"></i></a></li>
									</ul>

								</div>
								<div class="content">
									<h4>
										<a href="javascript:;">Bob Frapples</a>
									</h4>
									<p>Java Developer</p>
									<ul class="team-star list-inline">
										<li class="list-inline-item"><i class="fa fa-star"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star"
											aria-hidden="true"></i></li>
										<li class="list-inline-item"><i class="fa fa-star-o"
											aria-hidden="true"></i></li>
									</ul>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--team-area-end -->
		<!--video-area-start -->
		<div class="video-area clearfix">
			<div class="container-fluid p-0">
				<div class="row d-flex ">
					<div
						class="col-lg-7 video-left align-self-stretch justify-content-end  d-flex">
						<div
							class="video-support-wrapper  d-flex align-items-center pt-100 pb-100">
							<div class="row">
								<div class="col-md-6">
									<div class="video-support mb-4">
										<div class="video-icon">
											<img src="/SWRW/public/assets/images/home.png"
												alt="video support icon" />
										</div>
										<div class="video-content">
											<h5>Putting your first</h5>
											<p>The point of using Lorem Ipsum is that it has a
												more-or-less normal distribution of letters, as opposed to
												using.</p>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="video-support mb-4">
										<div class="video-icon">
											<img src="/SWRW/public/assets/images/settings.png"
												alt="video support icon" />
										</div>
										<div class="video-content">
											<h5>Customer Satisfaction</h5>
											<p>It is a long established fact that a reader will be
												distracted by the readable content of a page when layout.</p>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="video-support">
										<div class="video-icon">
											<img src="/SWRW/public/assets/images/smartphone.png"
												alt="video support icon" />
										</div>
										<div class="video-content">
											<h5>Everyone Access</h5>
											<p>The point of using Lorem Ipsum is that it has a
												more-or-less normal distribution of letters, as opposed to
												using.</p>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="video-support">
										<div class="video-icon">
											<img src="/SWRW/public/assets/images/clock.png"
												alt="video support icon" />
										</div>
										<div class="video-content">
											<h5>Real Time Tracking</h5>
											<p>It is a long established fact that a reader will be
												distracted by the readable content of a page when layout.</p>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="col-lg-5 video-right align-self-stretch ">
						<img src="/SWRW/public/assets/images/video-bg.jpg" alt="video image" />
						<div class="video-content text-center">
							<a
								href="http://huya-w21.huya.com/1943/226134752/1300/192a70c9c833422d46e9f98bf0d99409.mp4"
								class="video-play vid-zone buttonfx curtainup"
								data-aos="zoom-in"> <i class="fa fa-play"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--video-area-end -->
		<!--blog-area-start -->
		<div class="blog-area bg-color2 pt-100 pb-100 clearfix">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="section-title text-left">
							<span>Get the latest news</span>
							<h2>Latest Blog</h2>
							<div class="line"></div>
						</div>
					</div>
				</div>
				<div class="blog-wrapper">
					<div class="row">
						<div class="col-md-4">
							<div class="blog-block" data-aos="fade-in">
								<div class="blog-img">
									<div class="image-div">
										<img src="/SWRW/public/assets/images/blog-img-1.png" alt="image" />
										<div class="blog-overlay">
											<p>
												<a href="blog-details.html"><i class="fa fa-link"
													aria-hidden="true"></i></a>
											</p>
										</div>
									</div>
								</div>
								<div class="blog-content">
									<div class="blog-title">
										<h3>
											<a href="blog-details.html">Get your dream job in Envato
											</a>
										</h3>
									</div>
									<ul class="blog-meta mb-3 list-inline">
										<li class="list-inline-item">By <a href="javascript:;">Malina</a></li>
										<li class="list-inline-item">Feb 21, 2018</li>
									</ul>
									<div class="content">
										<p>Vitae tempus quam pellentesque nec nam aliquam sem et
											tortor consequat id porta nibh venenatis cras sed felis eget
										</p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="blog-block" data-aos="fade-in">
								<div class="blog-img">
									<div class="image-div">
										<img src="/SWRW/public/assets/images/blog-img-2.png" alt="image" />
										<div class="blog-overlay">
											<p>
												<a href="blog-details.html"><i class="fa fa-link"
													aria-hidden="true"></i></a>
											</p>
										</div>
									</div>
								</div>
								<div class="blog-content">
									<div class="blog-title">
										<h3>
											<a href="blog-details.html">CEO needed for the reputated
												company </a>
										</h3>
									</div>
									<ul class="blog-meta mb-3 list-inline">
										<li class="list-inline-item">By <a href="javascript:;">Malina</a></li>
										<li class="list-inline-item">Feb 21, 2018</li>
									</ul>
									<div class="content">
										<p>Faucibus pulvinar elementum integer enim neque volutpat
											ac tincidunt vitae semper quis lectus nulla at volutpat diam
										</p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="blog-block" data-aos="fade-in">
								<div class="blog-img">
									<div class="image-div">
										<img src="/SWRW/public/assets/images/blog-img-3.png" alt="image" />
										<div class="blog-overlay">
											<p>
												<a href="blog-details.html"><i class="fa fa-link"
													aria-hidden="true"></i></a>
											</p>
										</div>
									</div>
								</div>
								<div class="blog-content">
									<div class="blog-title">
										<h3>
											<a href="blog-details.html">Secret tips to be success in
												your business </a>
										</h3>
									</div>
									<ul class="blog-meta mb-3 list-inline">
										<li class="list-inline-item">By <a href="javascript:;">Malina</a></li>
										<li class="list-inline-item">Feb 21, 2018</li>
									</ul>
									<div class="content">
										<p>Lorem ipsum dolor sit amet, imperdiet elit nisl etiam
											ducimus, molestie etiam tempus, tincidunt egetid enim
											hendrerit interdum</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--blog-area-end -->
		<!--client-area-start -->
		<div class="client-area pt-100 pb-100 clearfix">
			<div class="container">
				<div class="row">
					<div class="col-lg-8">
						<div class="client-logo">
							<a href="javascript:;"><img
								src="/SWRW/public/assets/images/company-logo.png" alt="image" /></a>
						</div>
					</div>
					<div
						class="col-lg-4 d-flex align-items-center justify-content-center">
						<div class="client-content">
							<h5>See Why Over</h5>
							<h3 class="client-big">10,00258+</h3>
							<p>Companies have already used Trabajo</p>
							<a href="#tastimonial" class="client-btn">Trabajo Reviews </a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--client-area-end -->
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
	<script>
		/*  Type js  */
		if ((".typed").length > 0) {
			var options = {
				stringsElement : '.typed-strings',
				typeSpeed : language=='zh_CN'?200:100,
				backDelay : 700,
				loop : !0,
				startDelay : 500,
				cursorChar : '|',
			}
			var typed = new Typed(".typed", options);
		}
	</script>
</body>
</html>

