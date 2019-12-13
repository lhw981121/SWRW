//用户登录页面脚本

var userType;//记录用户类型

/* 选择账号密码登录方式改变表单布局 */
$('#mode_account').click(function(){
	if($('#mode_account').css("color")=="rgb(37, 165, 92)")	return;
	$('#mode_account').css("color","#25A55C");
	$('#mode_phone').css("color","#333333");
	$('#account_input,#phone_input,#accountLoginBtn,#phoneLoginBtn').slideToggle();
})
/* 选择手机号登录方式改变表单布局 */
$('#mode_phone').click(function(){
	if($('#mode_phone').css("color")=="rgb(37, 165, 92)")	return;
	$('#mode_phone').css("color","#25A55C");
	$('#mode_account').css("color","#333333");
	$('#account_input,#phone_input,#accountLoginBtn,#phoneLoginBtn').slideToggle();
})

/* 账号密码登录方式验证 */
function AccountLogin(){
	var user_account = $('#user_account');
	var user_password = $('#user_password');
	if(user_account.val().length==0 || user_password.val().length==0)	return false;
	//判断密码是否为自动填充的md5密文
	if(user_password.val().length!=32){
		user_password.val($.md5(user_password.val()));
	}
	var isLogin = false;
	$.ajax({
		type:"post",
		url:"/SWRW/UserAccountLogin",
		datatype: "json", 
		async:false,
		data:{
			"user_account":$("#user_account").val().toLowerCase(),
			"user_password":$("#user_password").val(),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isLogin==true){//账号密码正确
				userType = r.user.user_type;
				/* 判断账号是否已在线 */
				$.ajax({
					type:"post",
					url:"/SWRW/UserIsOnline",
					datatype: "json",
					async:false,
					data:{
						"user_account":$('#user_account').val(),
					},
					success:function(result) {
						var r = JSON.parse(result);
						if(r.isOnline==true){//用户已在线且不是当前客户端
							swal({
							  title: language=='zh_CN'?"用户已在线":"User Online",
							  text: r.errorMes,
							  type: "warning",
							  showCancelButton: true,
							  confirmButtonColor: "#DD6B55",
							  confirmButtonText: language=='zh_CN'?"强制登录":"Forced Login",
							  cancelButtonText: language=='zh_CN'?"取消登录":"Cancel Login",
							  closeOnConfirm: true
							},
							function(isConfirm){
								if (isConfirm) {
									/* 强制账号密码登录 */
									$.ajax({
										type:"post",
										url:"/SWRW/UserLoginSuccess",
										datatype: "json", 
										async:false,
										data:{
											"user_account":user_account.val(),
											"rememberPsw":$("#rememberPsw").is(':checked'),
										},
										success:function(result) {
											/* 强制其他客户端用户下线 */
											$.ajax({
												type:"post",
												url:"/SWRW/UserForcedLogout",
												datatype: "json", 
												async:true,
												data:{
													"user_account":user_account.val(),
												},
											});
											/* 判断是否是从过滤器而来 */
											if(requestPath.length==0){
												if(userType==1)window.location.href="/SWRW/user/mycenter";
												if(userType==2)window.location.href="/SWRW/user/mycenter";
												if(userType==8)window.location.href="/SWRW/admin";
											}else{
												window.location.href=requestPath;
											}
											isLogin = true; 
										},
										error:function(){
											AjaxError();
											isLogin = false; 
										}
									});
								}
							});
						}else{//用户未在线或当前客户端已登录
							/* 账号密码登录 */
							$.ajax({
								type:"post",
								url:"/SWRW/UserLoginSuccess",
								datatype: "json", 
								async:false,
								data:{
									"user_account":user_account.val(),
									"rememberPsw":$("#rememberPsw").is(':checked'),
								},
								success:function(result) {
									isLogin = true; 
								},
								error:function(){
									AjaxError();
									isLogin = false; 
								}
							});
						}
					},
					error:function(){
						AjaxError();
					}
				});
			}else{//账号密码错误
				ErrorTipBottomCenter(r.errorMes);
				user_password.val('');
				return false;
			}
		},
		error:function(){
			AjaxError();
			isLogin = false; 
		}
	});
	return isLogin;
}

/* 手机验证码登录方式验证 */
function PhoneLogin(){
	var user_phone = $('#user_phone');
	var phone_code = $('#phone_code');
	if(user_phone.val().length==0 || phone_code.val().length==0)	return false;
	var isLogin = false;
	if(user_phone.val()==$("#sendPhone").val()){//手机号验证正确
		/* 判断账号是否已在线 */
		$.ajax({
			type:"post",
			url:"/SWRW/UserIsOnline",
			datatype: "json",
			async:false,
			data:{
				"user_account":user_phone.val(),
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOnline==true){//用户已在线且不是当前客户端
					swal({
					  title: language=='zh_CN'?"用户已在线":"User Online",
					  text: r.errorMes,
					  type: "warning",
					  showCancelButton: true,
					  confirmButtonColor: "#DD6B55",
					  confirmButtonText: language=='zh_CN'?"强制登录":"Forced Login",
					  cancelButtonText: language=='zh_CN'?"取消登录":"Cancel Login",
					  closeOnConfirm: false
					},
					function(isConfirm){
						if(isConfirm){
							/* 强制手机登录 */
							$.ajax({
								type:"post",
								url:"/SWRW/UserLoginSuccess",
								datatype: "json", 
								async:false,
								data:{
									"user_account":user_phone.val(),
									"rememberPsw":$("#rememberPsw").is(':checked'),
								},
								success:function(result) {
									/* 强制其他客户端用户下线 */
									$.ajax({
										type:"post",
										url:"/SWRW/UserForcedLogout",
										datatype: "json", 
										async:true,
										data:{
											"user_account":user_phone.val(),
										},
									});
									/* 判断是否是从过滤器而来 */
									if(requestPath.length==0){
										if(userType==1)window.location.href="/SWRW/user/mycenter";
										if(userType==2)window.location.href="/SWRW/user/mycenter";
										if(userType==8)window.location.href="/SWRW/admin";
									}else{
										window.location.href=requestPath;
									}
									isLogin = true;
								},
								error:function(){
									AjaxError();
									isLogin = false;
								}
							});
						}
					});
				}else{//用户未在线
					/* 手机登录 */
					$.ajax({
						type:"post",
						url:"/SWRW/UserLoginSuccess",
						datatype: "json", 
						async:false,
						data:{
							"user_account":user_phone.val(),
							"rememberPsw":$("#rememberPsw").is(':checked'),
						},
						success:function(result) {
							isLogin = true;
						},
						error:function(){
							AjaxError();
							isLogin = false;
						}
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});
	}else{
		WarningTipBottomCenter(language=='zh_CN'?"手机号被修改！请输入正确的手机号！":"Mobile phone number modified!Please enter the correct phone number!");
		isLogin = false;
	}
	return isLogin;
}

/*<!-- 判断手机号是否存在 -->*/
function IsPhoneExist(){
	if(checkUser_phone()==false)	return false;
	var ok = false;
	$.ajax({
		type:"post",
		url:"/SWRW/UserIsPhoneExist",
		datatype: "json", 
		async:false,
		data:{
			"user_phone":$('#user_phone').val(),
			"mode":"login",
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isExist==true){//手机号已存在。
				userType = r.user.user_type;
				ok = true;
			}else{//手机号不存在。
				ErrorTipBottomCenter(r.errorMes);
				ok = false;
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
$('#phoneCodeBtn').click(function(){
	if(InputNotNull($('#user_phone'),language=='zh_CN'?"手机号不能为空！":"The mobile phone cannot be empty!")==false)	return;
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

/* 账号密码登录按钮 */
$('#accountLoginBtn').on('click',function(){
	var check = checkAll("account");
	if(check==true){
		/* 判断是否是从过滤器而来 */
		if(requestPath.length==0){
			if(userType==1)window.location.href="/SWRW/user/mycenter";
			if(userType==2)window.location.href="/SWRW/user/mycenter";
			if(userType==8)window.location.href="/SWRW/admin";
		}else{
			window.location.href=requestPath;
		}
	}
})
/* 手机号登录按钮 */
$('#phoneLoginBtn').on('click',function(){
	var check = checkAll("phone");
	if(check==true){
		/* 判断是否是从过滤器而来 */
		if(requestPath.length==0){
			if(userType==1)window.location.href="/SWRW/user/mycenter";
			if(userType==2)window.location.href="/SWRW/user/mycenter";
			if(userType==8)window.location.href="/SWRW/admin";
		}else{
			window.location.href=requestPath;
		}
	}
})

/*<!-- 表单验证 -->*/
function checkAll(mode) {
	if(mode=="account"){
		var user_account = InputNotNull($('#user_account'),language=='zh_CN'?"账号不能为空！":"The account cannot be empty!");
		var user_password = InputNotNull($('#user_password'),language=='zh_CN'?"密码不能为空！":"The password cannot be empty!");
		if (user_account && user_password && AccountLogin()) {
			return true;
		}
	}else if(mode=="phone"){
		var user_account = InputNotNull($('#user_phone'),language=='zh_CN'?"手机号不能为空！":"The mobile phone cannot be empty!");
		var user_password = checkPhone_code($('#phone_code').val(),$('#phonecode').val());
		if (user_account && user_password && PhoneLogin()) {
			return true;
		}
	}
	return false;
}