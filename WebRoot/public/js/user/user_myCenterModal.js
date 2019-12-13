//用户个人中心模态框脚本

//企业认证模态框事件监听
$('#companyCertificationModal').on('show.bs.modal', function() {//show 方法调用之后立即触发该事件
}).on('shown.bs.modal', function() {//此事件在模态框已经显示出来（并且同时在 CSS 过渡效果完成）之后被触发
}).on('hidden.bs.modal', function() {//此事件在模态框被隐藏（并且同时在 CSS 过渡效果完成）之后被触发。
	$('#company_name').val('');
	$('#company_legal').val('');
	$('#company_area').val('');
	$('#company_size').val('');
	$('#company_type').val('');
	$('#company_brief').val('');
	$('#company_license').val('');
	$("#license_name_label").html(language=='zh_CN'?"选择营业执照":"Select Company License");
	$("#company_license_photo").attr("src", "").hide();
	$("#CompanyLicensePreview").html("");
});
//更改用户名模态框事件监听
$('#changeUserNameModal').on('show.bs.modal', function() {//show 方法调用之后立即触发该事件
}).on('shown.bs.modal', function() {//此事件在模态框已经显示出来（并且同时在 CSS 过渡效果完成）之后被触发
}).on('hidden.bs.modal', function() {//此事件在模态框被隐藏（并且同时在 CSS 过渡效果完成）之后被触发。
	$('#user_name').val('');
});
//更改密码模态框事件监听
$('#changePasswordModal').on('show.bs.modal', function() {//show 方法调用之后立即触发该事件
}).on('shown.bs.modal', function() {//此事件在模态框已经显示出来（并且同时在 CSS 过渡效果完成）之后被触发
}).on('hidden.bs.modal', function() {//此事件在模态框被隐藏（并且同时在 CSS 过渡效果完成）之后被触发。
	$('#verifyOldPasswordBtn').show();
	$('#oldPassword_input').show();
	$('#changePasswordBtn').hide();
	$('#newPassword_input').hide();
	$('#user_old_password').val('');
	$('#user_password').val('').attr("type","password");
	$('#user_passwords').val('').attr("type","password");
    $('#eye').attr("class","fa fa-eye-slash fa-2x");
});
//绑定手机号模态框事件监听
$('#bindPhoneModal').on('show.bs.modal', function() {//show 方法调用之后立即触发该事件
}).on('shown.bs.modal', function() {//此事件在模态框已经显示出来（并且同时在 CSS 过渡效果完成）之后被触发
}).on('hidden.bs.modal', function() {//此事件在模态框被隐藏（并且同时在 CSS 过渡效果完成）之后被触发。
	$('#user_phone').val('');
	$('#phonecode').val('');
	$('#phone_code').val('').attr('disabled', true);
	countdown=0;
});
//解绑手机号模态框事件监听
$('#unBindPhoneModal').on('show.bs.modal', function() {//show 方法调用之后立即触发该事件
}).on('shown.bs.modal', function() {//此事件在模态框已经显示出来（并且同时在 CSS 过渡效果完成）之后被触发
}).on('hidden.bs.modal', function() {//此事件在模态框被隐藏（并且同时在 CSS 过渡效果完成）之后被触发。
	$('#unphonecode').val('');
	$('#unphone_code').val('').attr('disabled', true);
	countdown=0;
});
//绑定邮箱模态框事件监听
$('#bindEmailModal').on('show.bs.modal', function() {//show 方法调用之后立即触发该事件
}).on('shown.bs.modal', function() {//此事件在模态框已经显示出来（并且同时在 CSS 过渡效果完成）之后被触发
}).on('hidden.bs.modal', function() {//此事件在模态框被隐藏（并且同时在 CSS 过渡效果完成）之后被触发。
	$('#user_email').val('');
	$('#emailcode').val('');
	$('#email_code').val('').attr('disabled', true);
	countdown=0;
});
//解绑邮箱模态框事件监听
$('#unBindEmailModal').on('show.bs.modal', function() {//show 方法调用之后立即触发该事件
}).on('shown.bs.modal', function() {//此事件在模态框已经显示出来（并且同时在 CSS 过渡效果完成）之后被触发
}).on('hidden.bs.modal', function() {//此事件在模态框被隐藏（并且同时在 CSS 过渡效果完成）之后被触发。
	$('#unemailcode').val('');
	$('#unemail_code').val('').attr('disabled', true);
	countdown=0;
});

//企业认证
function companyCertificate(){
	if(checkCompanyName()&&checkCompanyLegal()&&checkCompanyArea()&&checkCompanySize()&&checkCompanyType()&&checkCompanyBrief()&&checkCompanyLicense()){
		$.ajax({
			type:"post",
			url:"/SWRW/CompanyCertificate",
			datatype: "json", 
			async:false,
			data:{
				"company_name":$('#company_name').val(),
				"company_legal":$('#company_legal').val(),
				"company_area":$('#company_area').val(),
				"company_size":$('#company_size').val(),
				"company_type":$('#company_type').val(),
				"company_brief":$('#company_brief').val(),
				"company_license":$("#company_license_photo").attr("src"),
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//提交认证成功
					$('#companyCertificationModal').modal('hide');//隐藏企业认证模态框
					swal({
						title: language=='zh_CN'?"提交认证成功":"Submit Certificate successfully",
						text: r.successMes,
						type: "success",
					},
					function(){
						window.location.reload();
					});
					setTimeout(function () {window.location.reload();}, 2000);
				}else{//提交认证失败
					swal({
						title: language=='zh_CN'?"提交认证失败":"Submit Certificate Failed",
						text: r.errorMes,
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});
	}
}

/*<!-- 判断用户名是否存在 -->*/
function IsUserNameExist(tip){
	if(checkUser_name()==false)	return false;
	if($('#user_name').val()==$('#userName').html()){//输入为当前用户名
		if(tip==1){
			iziToast.success({
				title: language=='zh_CN'?"可用":"Available",
				message: language=='zh_CN'?"可用是可用但是没有变化呀。":"Usable is usable but there is no change.",
				position: 'bottomRight',
				transitionIn: 'bounceInLeft',
			});
		}
		return true;
	}
	var ok = false;
	$.ajax({
		type:"post",
		url:"/SWRW/UserIsUserNameExist",
		datatype: "json", 
		async:false,
		data:{
			"user_name":$('#user_name').val(),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isExist==true){//用户名已存在。
				ok = false;
				ErrorTipBottomCenter(r.errorMes);
			}else{//用户名不存在。
				ok = true;
				if(tip==1){
					iziToast.success({
						title: language=='zh_CN'?"可用":"Available",
						message: r.successMes,
						position: 'bottomRight',
						transitionIn: 'bounceInLeft',
					});
				}
			}
		},
		error:function(){
			AjaxError();
		}
	});
	return ok;
}
/*更改用户名*/
function changeUserName(){
	if(!IsUserNameExist(1))	return;
	$.ajax({
		type:"post",
		url:"/SWRW/UserUpdateUserName",
		datatype: "json", 
		async:false,
		data:{
			"user_name":$('#user_name').val(),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//用户名修改成功
				$('#changeUserNameModal').modal('hide');//隐藏修改用户名模态框
				swal({
					title: language=='zh_CN'?"修改用户名成功":"Modify Password successfully",
					text: r.successMes,
					type: "success",
				},
				function(){
					window.location.reload();
				});
				setTimeout(function () {window.location.reload();}, 2000);
			}else{//用户名修改失败
				swal({
					title: language=='zh_CN'?"修改用户名失败":"Modify Password Failed",
					text: r.errorMes,
					type: "error",
				});
			}
		},
		error:function(){
			AjaxError();
		}
	});
}

/*验证旧密码*/
function verifyOldPassword(){
	if(!InputNotNull($('#user_old_password'),language=='zh_CN'?"旧密码不能为空！":"The old password cannot be empty!"))	return;
	if($.md5($("#user_old_password").val())==$('#user_password_old').val().toLowerCase()){//验证旧密码正确
		$('#changePasswordBtn,#verifyOldPasswordBtn,#oldPassword_input,#newPassword_input').slideToggle();
	}else{
		ErrorTipBottomCenter(language=='zh_CN'?"旧密码错误！":"Old password error!");
	}
}
/*<!-- 验证两次密码是否相同 -->*/
function checkUser_passwords(){
	var password = $('#user_password').val();
	var passwords = $('#user_passwords').val();
	if(password!=passwords){
		ErrorTipBottomCenter(language=='zh_CN'?"两次密码不相同！":"The two passwords are different!");
		return false;
	}else{
		return true;
	}
}
/*<!-- 密码表单验证 -->*/
function checkPassword(){ 
	if(checkUser_password()&&checkUser_passwords()){
		return true;
	}else{  
		return false;  
	}
}
//判断是否成功更改密码
function changePassword(){
	if(!checkPassword()) return;
	$.ajax({
		type:"post",
		url:"/SWRW/UserUpdatePassword",
		datatype: "json",
		async:false,
		data:{
			"user_account":$("#userName").html(),
			"user_password":$.md5($("#user_password").val()),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//重置密码成功
				$('#changePasswordModal').modal('hide');//隐藏修改密码模态框
				swal({
					title: language=='zh_CN'?"修改密码成功":"Modify Password successfully",
					text: r.successMes,
					type: "success",
				},
				function(){
					window.location.reload();
				});
				setTimeout(function () {window.location.reload();}, 3000);
			}else{//重置密码失败
				swal({
					title: language=='zh_CN'?"修改密码失败":"Modify Password Failed",
					text: r.errorMes,
					type: "error",
				});
			}
		},
		error:function(){
			AjaxError();
		}
	});
}

/*<!-- 判断邮箱是否存在 -->*/
function IsEmailExist(tip){
	if(checkUser_email()==false)	return false;
	var ok = false;
	$.ajax({
		type:"post",
		url:"/SWRW/UserIsEmailExist",
		datatype: "json", 
		async:false,
		data:{
			"user_email":$('#user_email').val().toLowerCase(),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isExist==true){//邮箱已存在。
				ok = false;
				ErrorTipBottomCenter(r.errorMes);
			}else{//邮箱不存在。
				ok = true;
				if(tip==1){
					iziToast.success({
						title: language=='zh_CN'?"可用":"Available",
						message: r.successMes,
						position: 'bottomRight',
						transitionIn: 'bounceInLeft',
					});
				}
			}
		},
		error:function(){
			AjaxError();
		}
	});
	return ok;
}
/*<!-- 判断手机号是否存在 -->*/
function IsPhoneExist(tip){
	if(checkUser_phone()==false)	return false;
	var ok = false;
	$.ajax({
		type:"post",
		url:"/SWRW/UserIsPhoneExist",
		datatype: "json", 
		async:false,
		data:{
			"user_phone":$('#user_phone').val(),
			"mode":"bind",
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isExist==true){//手机号已存在。
				ok = false;
				ErrorTipBottomCenter(r.errorMes);
			}else{//手机号不存在。
				ok = true;
				if(tip==1){
					iziToast.success({
						title: language=='zh_CN'?"可用":"Available",
						message: r.successMes,
						position: 'bottomRight',
						transitionIn: 'bounceInLeft',
					});
				}
			}
		},
		error:function(){
			AjaxError();
		}
	});
	return ok;
}

/* 发送验证码 */
var countdown = 60;
/* 发送手机验证码 */
function sendPhoneCode(btn,codeinput,code,sendinput){
	settime(btn,sendinput);
	InfoTipBottomRight(language=='zh_CN'?"短信正在发送中。。。":"Sending mobile phone note...");
	var user_phone = sendinput.val();
	$.ajax({
		type:"post",
		url:"/SWRW/UserSendPhoneCode",
		datatype: "json",
		async:true,
		data:{
			"user_phone":user_phone,
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//短信发送成功
				SuccessTipBottomRight(r.successMes);
				code.val(r.code);
				codeinput.attr('disabled', false);
				$("#sendPhone").val(user_phone);
			}else{//发送失败
				ErrorTipBottomCenter(r.errorMes);
				countdown=0;
			}
		},
		error:function(){
			AjaxError();
		}
	});
}
/* 发送邮箱验证码 */
function sendEmailCode(btn,codeinput,code,sendinput){
	settime(btn,sendinput);
	InfoTipBottomRight(language=='zh_CN'?"邮件正在发送中。。。":"Sending email...");
	var user_email = sendinput.val();
	$.ajax({
		type:"post",
		url:"/SWRW/UserSendEmailCode",
		datatype: "json",
		async:true,
		data:{
			"user_email":user_email.toLowerCase(),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//邮箱验证码发送成功
				SuccessTipBottomRight(r.successMes);
				code.val(r.code);
				codeinput.attr('disabled', false);
				$("#sendEmail").val(user_email);
			}else{//发送失败
				ErrorTipBottomCenter(r.errorMes);
				countdown=0;
			}
		},
		error:function(){
			AjaxError();
		}
	});
}

/* 绑定手机号 */
function bindPhone(){
	if(IsPhoneExist()&&checkPhone_code($('#phone_code').val(),$('#phonecode').val())){
		if($('#user_phone').val()!=$('#sendPhone').val()){
			WarningTipBottomCenter(language=='zh_CN'?"手机号被修改！请输入正确的手机号！":"Mobile phone number modified!Please enter the correct phone number!");
			return;
		}
		$.ajax({
			type:"post",
			url:"/SWRW/UserUpdatePhone",
			datatype: "json",
			async:true,
			data:{
				"mode":'bind',
				"user_phone":$("#user_phone").val(),
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//绑定成功
					$('#bindPhoneModal').modal('hide');//隐藏绑定手机号模态框
					swal({
						title: language=='zh_CN'?"绑定成功":"Binding Successful",
						text: r.successMes,
						type: "success",
					},
					function(){
						window.location.reload();
					});
					setTimeout(function () {window.location.reload();}, 3000);
				}else{//绑定失败
					swal({
						title: language=='zh_CN'?"绑定失败":"Bind Failed",
						text: r.errorMes,
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});	
	}else{
		return false;
	}
}
/* 解绑手机号 */
function unBindPhone(){
	if(checkPhone_code($('#unphone_code').val(),$('#unphonecode').val())){
		$.ajax({
			type:"post",
			url:"/SWRW/UserUpdatePhone",
			datatype: "json",
			async:true,
			data:{
				"mode":'unbind',
				"user_phone":'',
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//解绑成功
					$('#unBindPhoneModal').modal('hide');//隐藏解绑手机号模态框
					swal({
						title: language=='zh_CN'?"解绑成功":"Unbind Successful",
						text: r.successMes,
						type: "success",
					},
					function(){
						window.location.reload();
					});
					setTimeout(function () {window.location.reload();}, 3000);
				}else{//解绑失败
					var text;
					if(r.isOnly==true){//唯一登录方式，无法解绑
						text = r.ErrorMes;
					}else{//未知错误
						text = r.errorMes;
					}
					swal({
						title: language=='zh_CN'?"解绑失败":"Unbind Failed",
						text: text,
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});	
	}else{
		return false;
	}
}
/* 绑定邮箱 */
function bindEmail(){
	if(IsEmailExist()&&checkEmail_code($('#email_code').val(),$('#emailcode').val())){
		if($('#user_email').val()!=$('#sendEmail').val()){
			WarningTipBottomCenter(language=='zh_CN'?"邮箱被修改！请输入正确的邮箱！":"Email address modified!Please enter the correct Email address!");
			return;
		}
		$.ajax({
			type:"post",
			url:"/SWRW/UserUpdateEmail",
			datatype: "json",
			async:true,
			data:{
				"mode":'bind',
				"user_email":$("#user_email").val().toLowerCase(),
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//绑定成功
					$('#bindEmailModal').modal('hide');//隐藏绑定邮箱模态框
					swal({
						title: language=='zh_CN'?"绑定成功":"Bind Successful",
						text: r.successMes,
						type: "success",
					},
					function(){
						window.location.reload();
					});
					setTimeout(function () {window.location.reload();}, 3000);
				}else{//绑定失败
					swal({
						title: language=='zh_CN'?"绑定失败":"Bind Failed",
						text: r.errorMes,
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});	
	}else{
		return false;
	}
}
/* 解绑邮箱 */
function unBindEmail(){
	if(checkEmail_code($('#unemail_code').val(),$('#unemailcode').val())){
		$.ajax({
			type:"post",
			url:"/SWRW/UserUpdateEmail",
			datatype: "json",
			async:true,
			data:{
				"mode":'unbind',
				"user_email":'',
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//解绑成功
					$('#unBindEmailModal').modal('hide');//隐藏解绑邮箱模态框
					swal({
						title: language=='zh_CN'?"解绑成功":"Unbind Successful",
						text: r.successMes,
						type: "success",
					},
					function(){
						window.location.reload();
					});
					setTimeout(function () {window.location.reload();}, 3000);
				}else{//解绑失败
					var text;
					if(r.isOnly==true){//唯一登录方式，无法解绑
						text = r.ErrorMes;
					}else{//未知错误
						text = r.errorMes;
					}
					swal({
						title: language=='zh_CN'?"解绑失败":"Unbind Failed",
						text: text,
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});	
	}else{
		return false;
	}
}

/*<!-- 密码是否可见 -->*/
function hideShowNewPsw(){
	var eye = $('#eye');
	var passwordInput1 = $('#user_password');
	var passwordInput2 = $('#user_passwords');
    if (passwordInput1.attr("type") == "password") {
        passwordInput1.attr("type","text");
        passwordInput2.attr("type","text");
        eye.attr("class","fa fa-eye fa-2x");
    }else {
        passwordInput1.attr("type","password");
        passwordInput2.attr("type","password");
        eye.attr("class","fa fa-eye-slash fa-2x");
    }
}