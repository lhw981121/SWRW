//用户注册页面脚本

/*<!-- 密码是否可见 -->*/
function hideShowPsw(){
	var eye = $('#eye');
	var passwordInput = $('#user_password');
    if (passwordInput.attr("type") == "password") {
        passwordInput.attr("type","text");
        eye.attr("class","fa fa-eye fa-2x");
    }else {
        passwordInput.attr("type","password");
        eye.attr("class","fa fa-eye-slash fa-2x");
    }
}

/*<!-- 判断用户名是否存在 -->*/
function IsUserNameExist(tip){
	if(checkUser_name()==false)	return false;
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
			"mode":"register",
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

/* 选择邮箱注册方式改变表单布局 */
$('#mode_email').click(function(){
	if($('#mode_email').css("color")=="rgb(37, 165, 92)")	return;
	$('#mode_email').css("color","#25A55C");
	$('#mode_phone').css("color","#333333");
	$('#email_input,#phone_input,#emailRegisterBtn,#phoneRegisterBtn').slideToggle();
})
/* 选择手机号注册方式改变表单布局 */
$('#mode_phone').click(function(){
	if($('#mode_phone').css("color")=="rgb(37, 165, 92)")	return;
	$('#mode_phone').css("color","#25A55C");
	$('#mode_email').css("color","#333333");
	$('#email_input,#phone_input,#emailRegisterBtn,#phoneRegisterBtn').slideToggle();
})

/* 发送验证码 */
var countdown = 60;
/* 发送邮箱验证码 */
$('#emailCodeBtn').click(function(){
	if(checkUser_email()==false)	return;
	if(IsEmailExist()==false)	return;
	settime($('#emailCodeBtn'),$('#user_email'));
	InfoTipBottomRight(language=='zh_CN'?"邮件正在发送中。。。":"Sending email...");
	var user_email = $("#user_email").val();
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
				$("#emailcode").val(r.code);
				$("#email_code").attr('disabled', false);
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
})
/* 发送手机验证码 */
$('#phoneCodeBtn').click(function(){
	if(checkUser_phone()==false)	return;
	if(IsPhoneExist()==false)	return;
	settime($('#phoneCodeBtn'),$('#user_phone'));
	InfoTipBottomRight(language=='zh_CN'?"短信正在发送中。。。":"Sending mobile phone note...");
	var user_phone = $("#user_phone").val();
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
				$("#phonecode").val(r.code);
				$("#phone_code").attr('disabled', false);
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
});

/* 邮箱注册按钮 */
$('#emailRegisterBtn').on('click',function(){
	var check = checkAll("email");
	if(check==false)	return;
	if($('#user_email').val()!=$('#sendEmail').val()){
		WarningTipBottomCenter(language=='zh_CN'?"邮箱被修改！请输入正确的邮箱！":"Email address modified!Please enter the correct Email address!");
		return;
	}
	$.ajax({
		type:"post",
		url:"/SWRW/UserRegister",
		datatype: "json",
		async:true,
		data:{
			"mode":"email",
			"user_name":$('#user_name').val(),
			"account":$('#user_email').val().toLowerCase(),
			"user_password":$.md5($('#user_password').val()),
			"businessRegister":$("#businessRegister").is(':checked'),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//注册成功
				swal({
					title: language=='zh_CN'?"注册成功":"Registration Successful",
					text: r.successMes,
					type: "success",
				},
				function(){
					window.location.href="/SWRW/user/mycenter";
				});
				setTimeout(function () {window.location.href="/SWRW/user/mycenter";}, 3000);
			}else{//注册失败
				swal({
					title: language=='zh_CN'?"注册失败":"Registration Failed",
					text: r.errorMes,
					type: "error",
				});
			}
		},
		error:function(){
			AjaxError();
		}
	});	
})
/* 手机号注册按钮 */
$('#phoneRegisterBtn').on('click',function(){
	var check = checkAll("phone");
	if(check==false)	return;
	if($('#user_phone').val()!=$('#sendPhone').val()){
		WarningTipBottomCenter(language=='zh_CN'?"手机号被修改！请输入正确的手机号！":"Mobile phone number modified!Please enter the correct phone number!");
		return;
	}
	$.ajax({
		type:"post",
		url:"/SWRW/UserRegister",
		datatype: "json",
		async:true,
		data:{
			"mode":"phone",
			"user_name":$('#user_name').val(),
			"account":$('#user_phone').val(),
			"user_password":$.md5($('#user_password').val()),
			"businessRegister":$("#businessRegister").is(':checked'),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//注册成功
				swal({
					title: language=='zh_CN'?"注册成功":"Registration Successful",
					text: r.successMes,
					type: "success",
				},
				function(){
					window.location.href="/SWRW/user/mycenter";
				});
				setTimeout(function () {window.location.href="/SWRW/user/mycenter";}, 3000);
			}else{//注册失败
				swal({
					title: language=='zh_CN'?"注册失败":"Registration Failed",
					text: r.errorMes,
					type: "error",
				});
			}
		},
		error:function(){
			AjaxError();
		}
	});	
})

/*<!-- 表单验证 -->*/
function checkAll(mode) {
	if(mode=="email"){
		if(IsUserNameExist()&&IsEmailExist()&&checkEmail_code($('#email_code').val(),$('#emailcode').val())&&checkUser_password()){
			return true;
		}
	}else if(mode=="phone"){
		if(IsUserNameExist()&&IsPhoneExist()&&checkPhone_code($('#phone_code').val(),$('#phonecode').val())&&checkUser_password()){
			return true;
		}
	}
	return false;
}