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
							${company.name }
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/job"><fmt:message key="Job" /></a></li>
							<li class="list-inline-item"><fmt:message key="AffiliatedCompany" /></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!---end header-banner -->

	<main class="main-content-area clearfix">
		<div class="blog-details-area pb-100 pt-100">
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-md-7">
						<div class="blog-wrapper">
							<div class="blog-post-details">
								<div class="blog-img mb-4">
									<img src="/SWRW/public/assets/images/06_Blog.png" alt="image" />
								</div>
								<div class="blog-content">
									<p>Morem ipsum dolor sit amet, eleifend nunc tellus turpis.
										Eu lorem urna libero vestib ufermentum interdum dui commodo
										natoque libero pretium, sapien commodo, urna nunc, adipiscing
										laoreet pellentesque. Molestie erat sem, viverra senectus
										quibusdam lacinia consectetuer. Est sit ac quam nonummy purus
										nulla, dis vel senectus purus accumsan nec, maecenas felis
										eget. Posuere semper ornare eget at nisl commodo, vivamus
										rutrum auctor non, nulla phasellus sem a, donec metus vitae,
										vivamus non rutrum. Ligula eget ipsum consectetuer, nisi arcu
										adipiscing vitae fermentum ut sollicitudin, malesuada cum
										molestie, wisi pede in leo elit proin id. Aenean fusce urna at
										et, turpis dolor dictum, blandit nullam, vitae nibh
										pellentesque sed in vehicula.</p>
									<p>Varius massa. Urna egestas arcu vitae, fusce erat, ac
										magna justo pellentesque lorem etiam. Vestibulum nulla id
										ante, urna vehicula semper etiam dolor sagittis ipsum, ante et
										porttitor consequat, vel quis ut vestibulum maecenas augue
										aliquam, vivamus sapien accumsan convallis. Adipiscing mauris
										donec, mauris facilisis consectetuer libero, metus nunc odio,
										turpis ut ligula pellentesque. Lectu</p>
									<blockquote>
										<p>Est sit ac quam nonummy purus nulla, dis vel senectus
											purus accumsan nec, maecenas felis eget. Posuere semper
											ornare eget at nisl commodosit ac quam nonummy purus nulla</p>
									</blockquote>
									<p>Morem ipsum dolor sit amet, eleifend nunc tellus turpis.
										Eu lorem urna libero vestibulum, fermentum interdum dui
										commodo natoque libero pretium, sapien commodo, urna nunc,
										adipiscing laoreet pellentesque. Molestie erat sem, viverra
										senectus quibusdam lacinia consectetuer. Est sit ac quam
										nonummy purus nulla, dis vel senectus purus accumsan nec,
										maecenas felis eget. Posuere semper ornare eget at nisl
										commodo, vivamus rutrum auctor non, nulla phasellus sem a,
										donec metus vitae</p>
								</div>

								<div
									class="author-data post-meta d-flex justify-content-between pb-5 ">
									<div class="tags-meta ">
										<ul class="list-inline">
											<li class="list-inline-item">Tags:</li>
											<li class="list-inline-item"><a href="#">Accounting</a></li>
											<li class="list-inline-item"><a href="#">Graphic
													Design</a></li>
											<li class="list-inline-item"><a href="#">Web
													Designer</a></li>
										</ul>
									</div>
									<div class="social-link">
										<ul>
											<li><a href="#"><i class="fa fa-facebook-f"></i></a></li>
											<li><a href="#"><i class="fa fa-twitter"></i></a></li>
											<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
											<li><a href="#"><i class="fa fa-flag"
													aria-hidden="true"></i></a></li>
										</ul>
									</div>
								</div>
								<div class="blog-author-area pb-5 clearfix">
									<div class="author-bio-details d-flex">
										<div class="author-pic mr-4">
											<a href="#"><img
												src="/SWRW/public/assets/images/author-pic.png" alt="image" /></a>
										</div>
										<div class="meta-info">
											<div class="author-name">
												<h4>
													<a href="#">Genoveva</a>
												</h4>
											</div>
											<div class="a-meta  mb-2 d-flex">
												<p>
													<i class="fa fa-briefcase" aria-hidden="true"></i>UX/UI
													Designer
												</p>
												<p>
													<i class="fa fa-map-marker" aria-hidden="true"></i><a
														href="#">Canada</a>
												</p>
											</div>
											<p>Porttitor consequat, vel quis ut vestibulum maecenas
												augue aliquam, vivamus sapien accumsan convallis. Adipiscing
											</p>
										</div>
									</div>
								</div>
								<div class="comments-area pb-5 clearfix">
									<div class="container">
										<div class="row">
											<div class="col-lg-12">
												<div class="section-title inner-section">
													<h3>02 Comments</h3>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12">
												<div class="comments-details">
													<div class="author-image">
														<a href="https://html-trabajo.netlify.com/author.html"><img
															src="/SWRW/public/assets/images/author-c.png" alt="image" /></a>
													</div>
													<div class="comment-text">
														<div class="c-title">
															<h4>
																<a href="https://html-trabajo.netlify.com/author.html">Monal
																	Max</a>
															</h4>
														</div>
														<div class="c-content">
															<p>Circumstances and owing to the claims of duty or
																the obligations dislike men who are so beguiled and
																demoralized by the charms of pleasure they cannot</p>
														</div>
														<div class="reply">
															<span><a href="#">Reply</a></span><i
																class="fa fa-angle-double-right" aria-hidden="true"></i>
														</div>
													</div>
												</div>
												<div class="comments-details">
													<div class="reply-comment">
														<div class="author-image">
															<a href="https://html-trabajo.netlify.com/author.html"><img
																src="/SWRW/public/assets/images/author-c.png"
																alt="image" /></a>
														</div>
														<div class="comment-text">
															<div class="c-title">
																<h4>
																	<a href="https://html-trabajo.netlify.com/author.html">Martin
																		King</a>
																</h4>
															</div>
															<div class="c-content">
																<p>Duty or the obligations dislike men who are so
																	beguiled and demoralized by the charms of pleasure they
																	cannot</p>
															</div>
															<div class="reply">
																<span><a href="#">Reply</a></span><i
																	class="fa fa-angle-double-right" aria-hidden="true"></i>
															</div>
														</div>
													</div>
												</div>
												<div class="comments-details">
													<div class="author-image">
														<a href="https://html-trabajo.netlify.com/author.html"><img
															src="/SWRW/public/assets/images/author-c.png" alt="image" /></a>
													</div>
													<div class="comment-text">
														<div class="c-title">
															<h4>
																<a href="https://html-trabajo.netlify.com/author.html">Monal
																	Max</a>
															</h4>
														</div>
														<div class="c-content">
															<p>Circumstances and owing to the claims of duty or
																the obligations dislike men who are so beguiled and
																demoralized by the charms of pleasure they cannot</p>
														</div>
														<div class="reply">
															<span><a href="#">Reply</a></span><i
																class="fa fa-angle-double-right" aria-hidden="true"></i>
														</div>
													</div>
												</div>
												<div class="comments-details">
													<div class="reply-comment">
														<div class="author-image">
															<a href="https://html-trabajo.netlify.com/author.html"><img
																src="/SWRW/public/assets/images/author-c.png"
																alt="image" /></a>
														</div>
														<div class="comment-text">
															<div class="c-title">
																<h4>
																	<a href="https://html-trabajo.netlify.com/author.html">Martin
																		King</a>
																</h4>
															</div>
															<div class="c-content">
																<p>Duty or the obligations dislike men who are so
																	beguiled and demoralized by the charms of pleasure they
																	cannot</p>
															</div>
															<div class="reply">
																<span><a href="#">Reply</a></span><i
																	class="fa fa-angle-double-right" aria-hidden="true"></i>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="comment-form-area clearfix">
									<div class="container">
										<div class="row">
											<div class="col-lg-12">
												<div class="section-title inner-section">
													<h3>Post a Comment</h3>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12">
												<div class="comment-form">
													<form action="#">
														<input type="text" placeholder="Your Name" /> <input
															type="text" placeholder="Your Email" class="float-right" />
														<textarea placeholder="Type Comment"></textarea>
														<button type="submit" class="buttonfx curtainup">Post
															Comment</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
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
							<div class="sidebar-widget mb-4">
								<div class="sidebar-title">
									<h3>Jobs Category</h3>
								</div>
								<div class="sidebar-details">

									<ul class="category-list list-inline">
										<li><a href="#">Government (02)</a></li>
										<li><a href="#">Media or News (08)</a></li>
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
												<a href="blog-details.html">Increase More Traffic or More
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
<!-- 修改职位头像模态框 -->
<%@include file="/WEB-INF/view/company/job/changeJobHeadModal.jsp" %>
<!-- 自定义脚本 -->
<script>
	$('#job_state_str').html(getJobState(${job.state}));
	var job_id = '${job.ID}';
</script>
<script src="/SWRW/public/js/jobcompany_jobDetail.js"></script>
</body>
</html>