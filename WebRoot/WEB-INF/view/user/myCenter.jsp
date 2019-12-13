<%@ page language="java" import="java.util.*,com.qst.itoffer.util.COMUtil" pageEncoding="UTF-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/swrw/init.jsp"%>

<!DOCTYPE html>
<html lang="${language}">
<head>
<!-- 公共头部 -->
<%@include file="/WEB-INF/view/common/swrw/head.jsp"%>
<style>
table td:nth-child(1) {text-align: left;}
table td:nth-child(2) {text-align: left;}
table td:nth-child(3) {text-align: left;}
table td:nth-child(4) {text-align: center;}
table td p:nth-child(1) {margin: 5px 0;}
table td p:nth-child(1) {margin: 10px 0;}
.title {font-size: 18px}
.text {color: grey;font-size: 14px}
</style>
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
							<fmt:message key="UserMyCenter" />
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/user"><fmt:message key="User" /></a></li>
							<li class="list-inline-item"><fmt:message key="UserMyCenter" /></li>
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
					<div class="col-md-12" style="text-align:center">
						<!-- 头像开始 -->
						<div class="avatar">
							<a href="javascript:void(0);" onclick="$('#changeAvatarModal').modal('show')" title="<fmt:message key="ChangeAvatar" />"> 
								<img src="/SWRW/public/images/user/avatar/${user.getID()}.jpg?t=${Math.random()}"
								onerror="this.src='/SWRW/public/images/user/avatar/0.jpg';this.onerror=null"
								width="140" height="140" style="border-radius:50%" alt="Avatar" id="user_avatar_mycenter"/>
							</a>
						</div>
						<!-- 头像结束 -->
						<!-- 用户名 -->
						<p style="margin:5px 0;color:rgb(0, 170, 255); font-size:22px" title="<fmt:message key="ChangeUserName" />">
							<a href="javascript:void(0);" onclick="$('#changeUserNameModal').modal('show')" id="userName">${user.getName()}</a>
						</p>
						<!-- 用户类型 -->
						<p class="title">
							<span><fmt:message key="UserType" />: </span><span>${user.getTypeStr(language)}</span>
						</p>
						<br>
						<table class="table table-hover" style="text-align:center;">
							<colgroup>
								<col style="width:8%">
								<col style="width:55%">
								<col style="width:20%">
								<col style="width:17%">
							</colgroup>
							<tbody>
								<!-- 用户信息区域 -->
								<c:choose>
								<c:when test="${user.getType() == 1 }">
								<!-- 求职者 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/SWRW/public/images/user/icon_information.png" height="45"></td>
									<td>
										<p class="title"><fmt:message key="ResumeInfo" /></p>
										<p class="text"><fmt:message key="ResumeInfoTip" /></p>
									</td>
									<td style="vertical-align:middle;">
										<i	class="lnr lnr-pie-chart"></i>
										<span><fmt:message key="ResumeIntegrity" />${user.getResumeIntegrity(user.getApplicantID()) }</span>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${!user.getResumeIntegrity(user.getApplicantID()).equals('100%')}">
											<a href="/SWRW/applicant/resume/complete_resume" class="btn btn-primary"><fmt:message key="Complete" /></a>
										</c:when>
										<c:otherwise>
											<a href="/SWRW/applicant/resume/view_resume" class="btn btn-primary"><fmt:message key="View" /></a>
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
								</c:when>
								<c:when test="${user.getType() == 2 }">
								<!-- 企业 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/SWRW/public/images/user/icon_information.png" height="45"></td>
									<td>
										<p class="title"><fmt:message key="CompanyInfo" /></p>
										<p class="text"><fmt:message key="CompanyInfoTip" /></p>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${company.getState() <= 0}">
											<i	class="lnr lnr-cross-circle"></i><span><fmt:message key="NotVerified" /></span>
										</c:when>
										<c:otherwise>
											<i	class="lnr lnr-license"></i><span><fmt:message key="HasVerified" /></span>
										</c:otherwise>
										</c:choose>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${company.getState() <= 0}">
											<a href="javascript:void(0);" class="btn btn-primary" 
											onclick="if(${company.getState()!=-1}){$('#companyCertificationModal').modal('show')}
														else{swal({title: language=='zh_CN'?'认证审核中':'Certification audit',
															text: language=='zh_CN'?'你的企业认证信息正处于审核状态！':'Your company certification is under review!',
															type: 'error',});}">
											<fmt:message key="Certificate" /></a>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);" class="btn btn-primary" onclick="$('#companyCredentialModal').modal('show')"><fmt:message key="View" /></a>
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
								</c:when>
								</c:choose>
								<!-- 用户信息区域 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/SWRW/public/images/user/icon_userdata.png" height="40" style="margin-left: 6px;"></td>
									<td>
										<p class="title"><fmt:message key="UserInfo" /></p>
										<p class="text"><fmt:message key="UserInfoTip" /></p>
									</td>
									<td style="vertical-align:middle;">
										<i	class="lnr lnr-checkmark-circle"></i><span><fmt:message key="Using" /></span>
									</td>
									<td style="vertical-align:middle;">
										<a href="javascript:void(0);" class="btn btn-primary" onclick="$('#userInfoModal').modal('show')"><fmt:message key="View" /></a>
									</td>
								</tr>
								<!-- 账号密码区域 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/SWRW/public/images/user/icon_password.png" height="45"></td>
									<td>
										<p class="title"><fmt:message key="UserPassowrd" /></p>
										<p class="text"><fmt:message key="UserPasswordTip" /></p>
									</td>
									<td style="vertical-align:middle;">
										<i class="lnr lnr-checkmark-circle"></i><span><fmt:message key="HasSet" /></span>
									</td>
									<td style="vertical-align:middle;">
										<a href="javascript:void(0);" class="btn btn-primary" onclick="$('#changePasswordModal').modal('show')"><fmt:message key="Modify" /></a>
									</td>
								</tr>
								<!-- 手机区域 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/SWRW/public/images/user/icon_bindmobile.png" height="45"></td>
									<td>
										<p class="title"><fmt:message key="UserPhone" /><span style="color: grey;">&emsp;${COMUtil.HidePhone(user.getPhone())}</span></p>
										<p class="text"><fmt:message key="UserPhoneTip" /></p>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${empty user.getPhone()}">
											<i class="lnr lnr-cross-circle"></i><span><fmt:message key="NotBind" /></span>
										</c:when>
										<c:otherwise>
											<i class="lnr lnr-checkmark-circle"></i><span><fmt:message key="HasBind" /></span>
										</c:otherwise>
										</c:choose>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${empty user.getPhone()}">
											<a href="javascript:void(0);" class="btn btn-primary" onclick="$('#bindPhoneModal').modal('show')"><fmt:message key="Bind" /></a>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);" class="btn btn-primary" onclick="$('#unBindPhoneModal').modal('show')"><fmt:message key="UnBind" /></a>
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<!-- 邮箱区域 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/SWRW/public/images/user/icon_bindemial.png" height="45"></td>
									<td>
										<p class="title"><fmt:message key="UserEmail" /><span style="color: grey;">&emsp;${COMUtil.HideEmail(user.getEmail())}</span></p>
										<p class="text"><fmt:message key="UserEmailTip" /></p>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${empty user.getEmail()}">
											<i class="lnr lnr-cross-circle"></i><span><fmt:message key="NotBind" /></span>
										</c:when>
										<c:otherwise>
											<i class="lnr lnr-checkmark-circle"></i><span><fmt:message key="HasBind" /></span>
										</c:otherwise>
										</c:choose>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${empty user.getEmail()}">
											<a href="javascript:void(0);" class="btn btn-primary" onclick="$('#bindEmailModal').modal('show')"><fmt:message key="Bind" /></a>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);" class="btn btn-primary" onclick="$('#unBindEmailModal').modal('show')"><fmt:message key="UnBind" /></a>
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</tbody>
						</table>
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
	
	<!-- 修改头像模态框 -->
	<%@include file="/WEB-INF/view/user/changeAvatarModal.jsp" %>
	<!-- 个人中心页面模态框 -->
	<%@include file="/WEB-INF/view/user/myCenterModal.jsp" %>
</body>
</html>