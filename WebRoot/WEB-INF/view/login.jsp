<%@ page language="java" import="java.util.*,com.swzj.swrw.util.BASE64,com.swzj.swrw.bean.User,com.swzj.swrw.dao.UserDao" pageEncoding="UTF-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/swrw/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 公共头部 -->
<%@include file="/WEB-INF/view/common/swrw/head.jsp"%>
<!-- 用户手机号验证JS -->
<script src="/SWRW/public/js/user/user_phone.js"></script>

</head>
<body>
	<!-- start-公共页眉 -->
	<%@include file="/WEB-INF/view/common/swrw/header.jsp"%>
	<!-- end-公共页眉 -->

	<!---start header-banner -->
	<div class="header-banner clearfix"
		style="background-image:url(/SWRW/public/assets/images/header-bg.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="banner-text text-center">
						<h1>
							<fmt:message key="Login" />
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><fmt:message key="Login" /></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!---end header-banner -->

	<main class="main-content-area clearfix">
		<!---start login-form-area -->
		<div class="login-form-area pb-100 pt-100">
			<div class="container">
				<div class="row">
					
					<div class="col-md-7">
						<div style="background-image:url(/SWRW/public/assets/images/login-bg.png);background-size:100% 100%;position:absolute;width:100%;height:100%;" >
							<div style="user-select:none;">
								<h1 style="font-size: 27px;font-weight: 300;padding: 5% 5% 0%;"><fmt:message key="LoginLeft1" /></h1>
								<p style="font-size: 20px;font-weight: 300;padding: 2% 8%;"><fmt:message key="LoginLeft2" /></p>
							</div>
						</div>
					</div>

					<div class="col-md-5">
						<div class="login-form form-bg">
							<h3 style="text-align:center">
								<fmt:message key="Login" />
							</h3>
							<!-- 登录方式选择 -->
							<div class="mode-left">
                        <span id="mode_account" style="color:#25A55C"><fmt:message key="AccountLogin" /></span>
                     </div>
                     <div class="mode-right" >
                        <span id="mode_phone"><fmt:message key="PhoneLogin" /></span>
                     </div>
                     <!-- 登录表单 -->
							<form id="loginForm">
								<div class="form-info">
									<div class="info-field">
										<!-- 账号密码登录方式 -->
										<div id="account_input">
											<p>
												<input class="form-control" type="text" value="${account}"
													id="user_account" name="user_account"
													placeholder="<fmt:message key="AccountTip" />"
													onkeypress="if(event.keyCode==13) {accountLoginBtn.click();return false;}"/>
											</p>
											<p>
												<input class="form-control" type="password" value="${password}"
													id="user_password" name="user_password"
													placeholder="<fmt:message key="PasswordTip" />"
													onkeypress="if(event.keyCode==13) {accountLoginBtn.click();return false;}"/>
											</p>
										</div>
										
										<!-- 手机验证登录方式 -->
										<div style="display:none" id="phone_input">
											<p>
												<input id="sendPhone" name="sendPhone" type="hidden"/>
												<input class="form-control" type="tel"
													id="user_phone" name="user_phone"
													placeholder="<fmt:message key="PhoneTip"/>" 
													onkeypress="if(event.keyCode==13) {phoneCodeBtn.click();return false;}"/>
											</p>
											
											<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
												<input id="phonecode" name="phonecode" type="hidden"/>
												<input class="form-control" type="text" autocomplete="off" 
												id="phone_code" name="phone_code" disabled="disabled"
												placeholder="<fmt:message key="PhoneCodeTip"/>" 
												onkeypress="if(event.keyCode==13) {phoneLoginBtn.click();return false;}"/>
											</div>
											<button class="btn btn-info" type="button" 
											style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="phoneCodeBtn"><fmt:message key="GetCode" /></button>
										</div>
										
										<!-- 记住密码 -->
										<div class="form-group">
											<div class="form-check">
												<input class="form-check-input" type="checkbox" id="rememberPsw" name="rememberPsw"/> 
												<label class="form-check-label" for="rememberPsw"><fmt:message key="RememberPassword" /></label>
											</div>
										</div>
										
										<!-- 登录按钮 -->
										<div>
											<button class="btn btn-primary btn-block" type="button" id="accountLoginBtn"><fmt:message key="Login" /></button>
											<button class="btn btn-primary btn-block" type="button" id="phoneLoginBtn" style="display:none"><fmt:message key="Login" /></button>							
										</div>
										
										<span style="text-align:left;margin-top:10px"><a href="/SWRW/lost_password"><fmt:message key="Forgotpassword" /></a></span> 
										<span style="text-align:right;margin-top:-29px"><c:if test="${language=='zh_CN'}"><fmt:message key="NotHaveAccount" /></c:if>
										<a href="/SWRW/register"><fmt:message key="CreateAnAccount" /></a></span>
									</div>
								</div>
								<!-- 用户协议 -->
								<div class="form-bottom" style="text-align:center;margin-top:15px">
									<fmt:message key="LoginProtocol" />				
								</div>
							</form>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!---end login-form-area -->
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
	<script src="/SWRW/public/js/jQuery.md5.js"></script>
	<!-- 自定义脚本 -->
	<script src="/SWRW/public/js/user/user_login.js"></script>
	<!-- Cookie中有用户信息直接验证通过 -->
	<%if(request.getAttribute("password")!=null){%>
		<script>
			$('#rememberPsw').attr("checked",true);
			$('#accountLoginBtn').click();
		</script>
	<%}%>
	<!-- 用户已登录（session中有用户信息）直接验证通过 -->
	<%if(session.getAttribute("user")!=null){%>
		<script>
			$('#user_account').val('${user.getEmail()==null?user.getPhone():user.getEmail()}');
			$('#user_password').val('${user.getPwd()}');
			$('#accountLoginBtn').click();
		</script>
	<%}%> 
</body>
</html>
