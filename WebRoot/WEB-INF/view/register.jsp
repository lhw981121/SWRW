<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/swrw/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 公共头部 -->
<%@include file="/WEB-INF/view/common/swrw/head.jsp"%>
</head>
<!-- 用户用户名验证JS -->
<script src="/SWRW/public/js/user/user_name.js"></script>
<!-- 用户邮箱验证JS -->
<script src="/SWRW/public/js/user/user_email.js"></script>
<!-- 用户手机号验证JS -->
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
							<fmt:message key="Register" />
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><fmt:message key="Register" /></li>
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
						<div style="background-image:url(/SWRW/public/assets/images/register-bg.png);background-size:100% 100%;position:absolute;width:100%;height:100%;" >
							<div style="user-select:none;">
								<h1 style="color: ;font-size: 27px;font-weight: 300;padding: 5% 5% 0%;"><fmt:message key="RegisterLeft1" /></h1>
								<p style="color: ;font-size: 20px;font-weight: 300;padding: 2% 8%;"><fmt:message key="RegisterLeft2" /></p>
							</div>
						</div>
					</div>
					
					<div class="col-md-5">
						<div class="login-form form-bg">
							<h3 style="text-align:center">
								<fmt:message key="CreateAnAccount" />
							</h3>
							<!-- 注册方式选择 -->
							<div class="mode-left">
                        <span id="mode_email" style="color:#25A55C"><fmt:message key="EmailRegister" /></span>
                     </div>
                     <div class="mode-right" >
                        <span id="mode_phone"><fmt:message key="PhoneRegister" /></span>
                     </div>
							<!-- 注册表单 -->
							<form id="registerForm">
								<div class="form-info">
									<div class="info-field">
										<!-- 用户名输入框 -->
										<p>
											<input class="form-control" type="text"
												id="user_name" name="user_name"
												placeholder="<fmt:message key="UserNameLimit"/>" 
												onchange="IsUserNameExist(1);"/>
										</p>
										
										<!-- 邮箱注册 -->
										<div id="email_input">
											<p>
												<input id="sendEmail" name="sendEmail" type="hidden"/>
												<input class="form-control" type="email"
													id="user_email" name="user_email"
													placeholder="<fmt:message key="EmailTip"/>" 
													onkeypress="if(event.keyCode==13) {emailCodeBtn.click();return false;}"
													onchange="IsEmailExist(1)"/>
											</p>
										
											<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
												<input id="emailcode" name="emailcode" type="hidden"/>
												<input class="form-control" type="text" autocomplete="off" 
												id="email_code" name="email_code" disabled="disabled"
												placeholder="<fmt:message key="EmailCodeTip"/>" 
												onkeypress="if(event.keyCode==13) {emailRegisterBtn.click();}"/>
											</div>
											<button class="btn btn-info" type="button" 
											style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="emailCodeBtn"><fmt:message key="GetCode" /></button>
										</div>
										
										<!-- 手机号注册 -->
										<div style="display:none" id="phone_input">
											<p>
												<input id="sendPhone" name="sendPhone" type="hidden"/>
												<input class="form-control" type="tel"
													id="user_phone" name="user_phone"
													placeholder="<fmt:message key="PhoneTip"/>" 
													onkeypress="if(event.keyCode==13) {phoneCodeBtn.click();return false;}"
													onchange="IsPhoneExist(1);"/>
											</p>
											
											<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
												<input id="phonecode" name="phonecode" type="hidden"/>
												<input class="form-control" type="text" autocomplete="off" 
												id="phone_code" name="phone_code" disabled="disabled"
												placeholder="<fmt:message key="PhoneCodeTip"/>"
												onkeypress="if(event.keyCode==13) {phoneRegisterBtn.click();}"/>
											</div>
											<button class="btn btn-info" type="button" 
											style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="phoneCodeBtn"><fmt:message key="GetCode" /></button>
										</div>
										
										<!-- 密码输入框 -->
										<div class="custom-control custom-control-inline" style="margin-left:-24px;width:90%">
											<input class="form-control" type="password"
												id="user_password" name="user_password"
												placeholder="<fmt:message key="PasswordLimit"/>" 
												onkeypress="if(event.keyCode==13) {if($('#mode_email').css('color')=='rgb(37, 165, 92)'){emailRegisterBtn.click();}else{phoneRegisterBtn.click();}return false;}"/>
										</div><i class="fa fa-eye-slash fa-2x" id="eye" onclick="hideShowPsw()"></i>
										
										<!-- 注册按钮 -->
										<div>
											<button class="btn btn-primary btn-block" type="button" id="emailRegisterBtn"><fmt:message key="Register" /></button>
											<button class="btn btn-primary btn-block" type="button" id="phoneRegisterBtn" style="display:none"><fmt:message key="Register" /></button>						
										</div>
										
										<!-- 企业注册 -->
										<div class="form-group" style="margin-top:10px">
											<input class="form-check-input" type="checkbox" id="businessRegister" name="businessRegister" ${param.type==null?'':'checked' }/> 
											<label class="form-check-label" for="businessRegister"><fmt:message key="BusinessRegistration" /></label>
										</div>
										
										<span style="text-align:right;margin-top:-48px"><c:if test="${language=='zh_CN'}"><fmt:message key="HaveAccount" /></c:if>
										<a href="/SWRW/login"><fmt:message key="GoToLogin" /></a></span>
									</div>
								</div>

							</form>
							<!-- 用户协议 -->
							<div class="form-bottom" style="text-align:center;margin-top:15px">
								<fmt:message key="RegisterProtocol" />				
							</div>
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
	<script src="/SWRW/public/js/user/user_register.js"></script>
</body>
</html>

