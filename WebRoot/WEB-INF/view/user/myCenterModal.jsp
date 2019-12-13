<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 用户用户名验证JS -->
<script src="/SWRW/public/js/user/user_name.js"></script>
<!-- 用户邮箱验证JS -->
<script src="/SWRW/public/js/user/user_email.js"></script>
<!-- 用户手机号验证JS -->
<script src="/SWRW/public/js/user/user_phone.js"></script>
<!-- 用户密码验证JS -->
<script src="/SWRW/public/js/user/user_password.js"></script>
<script src="/SWRW/public/js/jQuery.md5.js"></script>
<!-- 用户密码验证JS -->
<script src="/SWRW/public/js/company/company_info.js"></script>

<!-- 企业认证模态框 -->
<div class="modal fade" id="companyCertificationModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:3%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-trademark"></i>&nbsp;<fmt:message key="CompanyCertification" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h5><fmt:message key="CompanyQualificationCertification" /></h5><hr>
							<!-- 企业名称输入框 -->
							<p>
								<input class="form-control" type="text"
								id="company_name" name="company_name"
								placeholder="<fmt:message key="CompanyNameLimit"/>"/>
							</p>
							<!-- 企业法人输入框 -->
							<p>
								<input class="form-control" type="text"
								id="company_legal" name="company_legal"
								placeholder="<fmt:message key="CompanyLegalLimit"/>"/>
							</p>
							<!-- 企业所在地输入框 -->
							<p>
								<input class="form-control" type="text"
								id="company_area" name="company_area"
								placeholder="<fmt:message key="CompanyAreaLimit"/>"/>
							</p>
							<!-- 企业规模输入框 -->
							<select class="custom-select" id="company_size" name="company_size">
								<option value="" selected><fmt:message key="CompanySizeLimit"/></option>
								<option value="500人以下"><fmt:message key="CompanySize1"/></option>
								<option value="500-1000人"><fmt:message key="CompanySize2"/></option>
								<option value="1000-5000人"><fmt:message key="CompanySize3"/></option>
								<option value="5000-10000人"><fmt:message key="CompanySize4"/></option>
								<option value="10000人以上"><fmt:message key="CompanySize5"/></option>
							</select>
							<!-- 企业性质选择框 -->
							<select class="custom-select" id="company_type" name="company_type">
								<option value="" selected><fmt:message key="CompanyTypeLimit"/></option>
								<option value="私营企业"><fmt:message key="CompanyType1"/></option>
								<option value="股份制企业"><fmt:message key="CompanyType2"/></option>
								<option value="外资企业"><fmt:message key="CompanyType3"/></option>
								<option value="合资企业"><fmt:message key="CompanyType4"/></option>
								<option value="三资企业"><fmt:message key="CompanyType5"/></option>
								<option value="国有企业"><fmt:message key="CompanyType6"/></option>
								<option value="其他企业"><fmt:message key="CompanyType7"/></option>
								<option value="中初教育单位"><fmt:message key="CompanyType8"/></option>
								<option value="科研设计单位"><fmt:message key="CompanyType9"/></option>
								<option value="其他事业单位"><fmt:message key="CompanyType10"/></option>
							</select>
							<!-- 企业简介输入框 -->
							<p>
								<textarea class="form-control" name="company_brief" id="company_brief" rows="5"
								placeholder="<fmt:message key="CompanyBriefLimit" />"></textarea>
							</p>
							<!-- 企业营业执照 -->
							<div class="custom-file">
								<input type="file" class="custom-file-input"
								id="company_license" name="company_license" 
								onchange="showImage(this.files[0])" accept="image/*"/>
								<label class="custom-file-label" style="text-align:left" id="license_name_label" for="company_license">
								<fmt:message key="CompanyLicenseLimit"/></label>
							</div>
							<br><br>
							<h6 id="CompanyLicensePreview"></h6>
							<img src="" alt="" id="company_license_photo" height="auto" width="100%" style="display:none">
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="companyCertificateBtn" onclick="companyCertificate();"><fmt:message key="SubmitCertificate" /></button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</div>
</div>
<!-- 显示营业执照文件名 -->
<script>
function showImage(file) {
	$("#license_name_label").html(file.name);
	//检验是否为图像文件
	if (!/image\/\w+/.test(file.type)) {
		alert('<fmt:message key="SelectedPictureError" />');
		return false;
	}
	var reader = new FileReader();
	//将文件以Data URL形式读入页面
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		$("#company_license_photo").attr("src", this.result).show();
		$("#CompanyLicensePreview").html("<fmt:message key='CompanyLicensePreview'/>");
	}
}
</script>

<!-- 企业认证信息模态框 -->
<div class="modal fade" id="companyCredentialModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:3%">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-building-o"></i>&nbsp;<fmt:message key="CompanyCredential" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<!-- 企业名称 -->
							<h5><fmt:message key="CompanyName" />: </h5>
							<p style="font-family:'Microsoft YaHei';color:black; font-size:18px">${company.name}</p>
							<!-- 企业法人 -->
							<h5><fmt:message key="CompanyLegal" />: </h5>
							<p style="font-family:'Microsoft YaHei';color:black;font-size:18px">${company.legal}</p>
							<!-- 企业营业执照 -->
							<h5><fmt:message key="CompanyLicense" />: </h5>
							<p><img src="/SWRW/public/images/company/license/${company.license}?t=${Math.random()}"
							alt="<fmt:message key="CompanyLicense" />" height="auto" width="100%"></p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Close" /></button>
			</div>
		</div>
	</div>
</div>

<!-- 更改用户名模态框 -->
<div class="modal fade" id="changeUserNameModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-user-o"></i>&nbsp;<fmt:message key="ChangeUserName" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h5><fmt:message key="ChangeUserName" /></h5><hr>
							<!-- 用户名输入框 -->
							<input class="form-control" type="text"
							id="user_name" name="user_name"
							placeholder="<fmt:message key="UserNameLimit"/>"
							onkeypress="if(event.keyCode==13) {changeUserNameBtn.click();return false;}"/>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="changeUserNameBtn" onclick="changeUserName();"><fmt:message key="Modify" /></button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</div>
</div>

<!-- 用户信息模态框 -->
<div class="modal fade" id="userInfoModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%;">
	<div class="modal-dialog" style="width:320px;">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-id-card-o"></i>&nbsp;<fmt:message key="UserInfo" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="sidebar-widget mb-4">
								<div class="right-sidebar">
									<div class="sidebar-details">
										<div class="single-overview  d-flex">
											<div class="icon">
												<i class="fa fa-user"></i>
											</div>
											<div class="meta-overview">
												<p>
													<fmt:message key="UserName" />
													: <span title="<fmt:message key="ChangeUserName" />">
														<a href="javascript:void(0);"
														onclick="$('#userInfoModal').modal('hide');$('#changeUserNameModal').modal('show')"
														id="userName">${user.getName()}</a>
													</span>
												</p>
											</div>
										</div>
										<div class="single-overview  d-flex">
											<div class="icon">
												<i class="fa fa-street-view"></i>
											</div>
											<div class="meta-overview">
												<p>
													<fmt:message key="UserType" />
													: <span>${user.getTypeStr(language)}</span>
												</p>
											</div>
										</div>
										<div class="single-overview  d-flex">
											<div class="icon">
												<i class="fa fa-phone"></i>
											</div>
											<div class="meta-overview">
												<p>
													<fmt:message key="Phone" />
													: <span>${(user.getPhone()==null||user.getPhone().length()==0)?"未绑定":user.getPhone()}</span>
												</p>
											</div>
										</div>
										<div class="single-overview  d-flex">
											<div class="icon">
												<i class="fa fa-envelope-o"></i>
											</div>
											<div class="meta-overview">
												<p>
													<fmt:message key="Email" />
													: <span>${(user.getEmail()==null||user.getEmail().length()==0)?"未绑定":user.getEmail()}</span>
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
			<div class="modal-footer">
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Close" /></button>
			</div>
		</div>
	</div>
</div>

<!-- 更改密码模态框 -->
<div class="modal fade" id="changePasswordModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-key"></i>&nbsp;<fmt:message key="ChangePassword" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<!-- 旧密码输入框 -->
							<div id="oldPassword_input">
								<input id="user_password_old" name="user_password_old" type="hidden" value="${user.getPwd()}"/>
								<h5><fmt:message key="VerifyOldPassword" /></h5><hr>
								<input class="form-control" type="password"
								id="user_old_password" name="user_old_password"
								placeholder="<fmt:message key="OldPasswordLimit"/>"
								onkeypress="if(event.keyCode==13) {verifyOldPasswordBtn.click();return false;}"/>
							</div>
							<!-- 新密码输入框 -->
							<div style="display:none" id="newPassword_input">
								<h5><fmt:message key="EnterNewPassword" /></h5><hr>
								<!-- 新密码输入框1 -->
								<div class="custom-control custom-control-inline" style="margin-left:-28px;width:93%">
									<input class="form-control" type="password"
										id="user_password" name="user_password"
										placeholder="<fmt:message key="PasswordLimit"/>" 
										onkeypress="if(event.keyCode==13) {changePasswordBtn.click();return false;}"/>
								</div><i class="fa fa-eye-slash fa-2x" id="eye" onclick="hideShowNewPsw()"></i>
								<!-- 新密码输入框2 -->
								<p style="margin-top:16px">
									<input class="form-control" type="password"
										id="user_passwords" name="user_passwords"
										placeholder="<fmt:message key="PasswordAgain"/>" 
										onkeypress="if(event.keyCode==13) {changePasswordBtn.click();return false;}"/>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="verifyOldPasswordBtn" onclick="verifyOldPassword();"><fmt:message key="Verify" /></button>
				<button style="display:none" class="btn btn-primary" id="changePasswordBtn" onchange="checkUser_password()" onclick="changePassword();"><fmt:message key="Modify" /></button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</div>
</div>

<!-- 绑定手机号模态框 -->
<div class="modal fade" id="bindPhoneModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-mobile"></i>&nbsp;<fmt:message key="BindPhone" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<p>
								<input id="sendPhone" name="sendPhone" type="hidden"/>
								<input class="form-control" type="tel"
									id="user_phone" name="user_phone" onchange="IsPhoneExist(1)"
									placeholder="<fmt:message key="PhoneTip"/>" 
									onkeypress="if(event.keyCode==13) {bindPhoneCodeBtn.click();return false;}"/>
							</p>
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
								<input id="phonecode" name="phonecode" type="hidden"/>
								<input class="form-control" type="text" autocomplete="off"
								id="phone_code" name="phone_code" disabled="disabled"
								placeholder="<fmt:message key="PhoneCodeTip"/>"
								onkeypress="if(event.keyCode==13) {bindPhoneBtn.click();return false;}"/>
							</div>
							<button class="btn btn-info" type="button" style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="bindPhoneCodeBtn" 
							onclick="if(checkUser_phone()&&IsPhoneExist()){countdown=60;sendPhoneCode($('#bindPhoneCodeBtn'),$('#phone_code'),$('#phonecode'),$('#user_phone'));}">
							<fmt:message key="GetCode" /></button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="bindPhoneBtn" onclick="bindPhone();"><fmt:message key="Bind" /></button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</div>
</div>
<!-- 解绑手机号模态框 -->
<div class="modal fade" id="unBindPhoneModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-mobile"></i>&nbsp;<fmt:message key="UnBindPhone" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<input id="user_phone_old" name="user_phone_old" type="hidden" value="${user.getPhone()}"/>
							<h5><fmt:message key="UnBindPhone" />&ensp;${COMUtil.HidePhone(user.getPhone())}</h5><hr>
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
								<input id="unphonecode" name="unphonecode" type="hidden"/>
								<input class="form-control" type="text" autocomplete="off"
								id="unphone_code" name="unphone_code" disabled="disabled"
								placeholder="<fmt:message key="PhoneCodeTip"/>"
								onkeypress="if(event.keyCode==13) {unBindPhoneBtn.click();return false;}"/>
							</div>
							<button class="btn btn-info" type="button" style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="unBindPhoneCodeBtn" 
							onclick="countdown=60;sendPhoneCode($('#unBindPhoneCodeBtn'),$('#unphone_code'),$('#unphonecode'),$('#user_phone_old'));">
							<fmt:message key="GetCode" /></button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="unBindPhoneBtn" onclick="unBindPhone();"><fmt:message key="UnBind" /></button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</div>
</div>

<!-- 绑定邮箱模态框 -->
<div class="modal fade" id="bindEmailModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-envelope-o"></i>&nbsp;<fmt:message key="BindEmail" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<p>
								<input id="sendEmail" name="sendEmail" type="hidden"/>
								<input class="form-control" type="tel"
									id="user_email" name="user_email" onchange="IsEmailExist(1)"
									placeholder="<fmt:message key="EmailTip"/>" 
									onkeypress="if(event.keyCode==13) {bindEmailCodeBtn.click();return false;}"/>
							</p>
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
								<input id="emailcode" name="emailcode" type="hidden"/>
								<input class="form-control" type="text" autocomplete="off"
								id="email_code" name="email_code" disabled="disabled"
								placeholder="<fmt:message key="EmailCodeTip"/>"
								onkeypress="if(event.keyCode==13) {bindEmailBtn.click();return false;}"/>
							</div>
							<button class="btn btn-info" type="button" style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="bindEmailCodeBtn" 
							onclick="if(checkUser_email()&&IsEmailExist()){countdown=60;sendEmailCode($('#bindEmailCodeBtn'),$('#email_code'),$('#emailcode'),$('#user_email'));}">
							<fmt:message key="GetCode" /></button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="bindEmailBtn" onclick="bindEmail();"><fmt:message key="Bind" /></button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</div>
</div>
<!-- 解绑邮箱模态框 -->
<div class="modal fade" id="unBindEmailModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-envelope-o"></i>&nbsp;<fmt:message key="UnBindEmail" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<input id="user_email_old" name="user_email_old" type="hidden" value="${user.getEmail()}"/>
							<h5><fmt:message key="UnBindEmail" />&ensp;${COMUtil.HideEmail(user.getEmail())}</h5><hr>
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
								<input id="unemailcode" name="unemailcode" type="hidden"/>
								<input class="form-control" type="text" autocomplete="off"
								id="unemail_code" name="unemail_code" disabled="disabled"
								placeholder="<fmt:message key="EmailCodeTip"/>"
								onkeypress="if(event.keyCode==13) {unBindEmailBtn.click();return false;}"/>
							</div>
							<button class="btn btn-info" type="button" style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="unBindEmailCodeBtn" 
							onclick="countdown=60;sendEmailCode($('#unBindEmailCodeBtn'),$('#unemail_code'),$('#unemailcode'),$('#user_email_old'));">
							<fmt:message key="GetCode" /></button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="unBindEmailBtn" onclick="unBindEmail();"><fmt:message key="UnBind" /></button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</div>
</div>

<!-- 自定义脚本 -->
<script src="/SWRW/public/js/user/user_myCenterModal.js"></script>

