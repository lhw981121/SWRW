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

	<main class="main-content-area clearfix">
		<div class="not-found-area pt-100 pb-100">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="not-found text-center">
							<div class="image">
								<img src="/SWRW/public/assets/images/11_404.png" alt="" />
							</div>
							<p><fmt:message key="404Page"/></p>
							<div class="btn-green buttonfx curtainup mt-4">
								<a href="/SWRW/index"><fmt:message key="ReturntoHome"/></a>
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
