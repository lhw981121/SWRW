<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/swrw/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 公共头部 -->
<%@include file="/WEB-INF/view/common/swrw/head.jsp"%>
</head>
<!-- 邮箱验证码验证JS -->
<script src="/SWRW/public/js/user/user_email.js"></script>
<!-- 手机号验证码验证JS -->
<script src="/SWRW/public/js/user/user_phone.js"></script>
<!-- 用户密码验证JS -->
<script src="/SWRW/public/js/user/user_password.js"></script>

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
							<fmt:message key="ForgotPassword" />
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><fmt:message key="ForgotPassword" /></li>
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
					<div class="col-md-6" style="margin:auto">
						<div class="login-form form-bg">
							
							<!-- 填写账号 -->
							<form id="accountForm">
								<div class="form-info">
									<div class="info-field">
										<h3 style="text-align:center">
											<fmt:message key="LostPassword1" />
										</h3><hr>
										<!-- 用户账号输入框 -->
										<p>
											<input class="form-control" type="text"
												id="user_account" name="user_account" autocomplete="off" 
												placeholder="<fmt:message key="LostAccountTip"/>" 
												onkeypress="if(event.keyCode==13) {accountBtn.click();return false;}"/>
										</p>
										<!-- 查找按钮 -->
										<div>
											<button class="btn btn-primary btn-block" type="button" id="accountBtn"><fmt:message key="Find" /></button>
										</div>
									</div>
								</div>
							</form>
							
							<!-- 选择验证方式 -->
							<form id="modeForm" style="display:none">
								<div class="form-info">
									<div class="info-field">
										<h3 style="text-align:center">
											<fmt:message key="LostPassword2" />
										</h3><hr>
										<!-- 用户账号输入框 -->
										<select class="custom-select" id="selectMode">
											<option selected value="0" id="default_mode"><fmt:message key="SelectMode" /></option>
											<option value="1" id="user_email_mode" style="display:none"></option>
											<option value="2" id="user_phone_mode" style="display:none"></option>
										</select>
									</div>
								</div>
							</form>
							
							<!-- 填写邮箱验证码 -->
							<form id="emailForm" style="display:none">
								<div class="form-info">
									<div class="info-field">
										<h3 style="text-align:center">
											<fmt:message key="LostPassword3" />
										</h3><hr>
										<!-- 验证码输入框 -->
										<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
											<input id="emailcode" name="emailcode" type="hidden"/>
											<input class="form-control" type="text" autocomplete="off" 
											id="email_code" name="email_code" disabled="disabled"
											placeholder="<fmt:message key="EmailCodeTip"/>"
											onkeypress="if(event.keyCode==13) {emailBtn.click();return false;}"/>
										</div>
										<button class="btn btn-info" type="button" 
										style="margin:-3px 0px 0px -20px;width:34%;font-size:15px" id="emailCodeBtn"><fmt:message key="GetCode" /></button>
										<!-- 提交按钮 -->
										<div style="text-align:center">
											<button class="btn btn-primary" type="button" id="emailBtn"><fmt:message key="Submit" /></button>
											<button class="btn btn-primary" type="button" id="modeBack1"><fmt:message key="Back" /></button>
										</div>
									</div>
								</div>
							</form>
							
							<!-- 填写手机短信验证码 -->
							<form id="phoneForm" style="display:none">
								<div class="form-info">
									<div class="info-field">
										<h3 style="text-align:center">
											<fmt:message key="LostPassword3" />
										</h3><hr>
										<!-- 验证码输入框 -->
										<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
											<input id="phonecode" name="phonecode" type="hidden"/>
											<input class="form-control" type="text" autocomplete="off" 
											id="phone_code" name="phone_code" disabled="disabled"
											placeholder="<fmt:message key="PhoneCodeTip"/>"
											onkeypress="if(event.keyCode==13) {phoneBtn.click();return false;}"/>
										</div>
										<button class="btn btn-info" type="button" 
										style="margin:-3px 0px 0px -20px;width:34%;font-size:15px" id="phoneCodeBtn"><fmt:message key="GetCode" /></button>
										<!-- 提交按钮 -->
										<div style="text-align:center">
											<button class="btn btn-primary" type="button" id="phoneBtn"><fmt:message key="Submit" /></button>
											<button class="btn btn-primary" type="button" id="modeBack2"><fmt:message key="Back" /></button>
										</div>
									</div>
								</div>
							</form>
							
							<!-- 填写新密码 -->
							<form id="passwordForm" style="display:none">
								<div class="form-info">
									<div class="info-field">
										<h3 style="text-align:center">
											<fmt:message key="LostPassword4" />
										</h3><hr>
										<!-- 新密码输入框1 -->
										<div class="custom-control custom-control-inline" style="margin-left:-24px;width:93%">
											<input class="form-control" type="password"
												id="user_password" name="user_password"
												placeholder="<fmt:message key="PasswordLimit"/>" 
												onkeypress="if(event.keyCode==13) {passwordBtn.click();return false;}"/>
										</div><i class="fa fa-eye-slash fa-2x" id="eye" onclick="hideShowNewPsw()"></i>
										<!-- 新密码输入框2 -->
										<p>
											<input class="form-control" type="password"
												id="user_passwords" name="user_passwords"
												placeholder="<fmt:message key="PasswordAgain"/>" 
												onkeypress="if(event.keyCode==13) {passwordBtn.click();return false;}"/>
										</p>
										<!-- 提交按钮 -->
										<div>
											<button class="btn btn-primary btn-block" type="button" id="passwordBtn"><fmt:message key="Submit" /></button>
										</div>
									</div>
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
	<script src="/SWRW/public/js/user/user_lostPassword.js"></script>
</body>
</html>


