//用户邮箱验证

function checkUser_email(){
    var value = $('#user_email').val();
    //邮箱的规则：必填，邮箱正则表达式
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"邮箱不能为空！":"Email address cannot be empty!");
		return false;
	}else{
		var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"邮箱格式有误！":"Wrong email format!");
		    return false;  
	    }else{
			return true;
			/*检测邮箱是否真实存在*/
			/*var isExist = false;
			$.ajax({
				type:"post",
				url:"/SWRW/TextEmailRealExist",
				datatype: "json",
				async:false,
				data:{
					"email":value,
				},
				success:function(result) {
					var r = JSON.parse(result);
					if(r.isExist==true){
						isExist = true;
					}else{
						iziToast.error({
							title: language=='zh_CN'?"不存在":"Absence",
							message: language=='zh_CN'?"检测到该邮箱非真实存在，无法使用！":"The mailbox is not real and cannot be used!",
							position: 'bottomRight',
							transitionIn: 'bounceInLeft',
						});
					}
				},
				error:function(){
					AjaxError();
				}
			});
			return isExist;*/
		}
	}
}


//邮箱验证码验证
function checkEmail_code(value,code){
	if(value.length == 0){
		ErrorTipBottomLeft(language=='zh_CN'?"验证码不能为空！":"The verification code cannot be empty!");
        return false;
	}else{
		var emailcode = code;
		if(emailcode!=null&&value!=emailcode){
		    ErrorTipBottomLeft(language=='zh_CN'?"验证码错误！":"Verification code error!");
	        return false;
		}else{
	        return true;
		}
	}
}

/*function checkUser_email(language){
    var value = $('#user_email').val();
	var tip = $('#user_email_tip');
    //邮箱的规则：必填，邮箱正则表达式
	if(value.length == 0){
		//不符合规则
        tip.html(language=='zh_CN'?"邮箱不能为空！":"Email address cannot be empty!");
		tip.attr("class","text-danger");
		tip.css("display","block");  
		return false;
	}else{
		var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	    if(!reg.test(value)){
	        //不符合规则
			tip.html(language=='zh_CN'?"邮箱格式有误！":"Wrong email format!");
			tip.attr("class","text-danger");
			tip.css("display","block"); 
		    return false;  
	    }else{
			//符合规则
			tip.html("");
			tip.css("display","none"); 
			return true;
		}
	}
}


//邮箱验证码验证
function checkEmail_code(language){
    var value = $('#email_code').val();
	var tip = $('#email_code_tip');
	if(value.length == 0){
		tip.html(language=='zh_CN'?"验证码不能为空！":"The verification code cannot be empty!");
		tip.attr("class","text-danger");
		tip.css("display","block");  
        return false;
	}else{
		var emailcode = $("#emailcode").val();
		if(emailcode!=null&&value!=emailcode){
			tip.html(language=='zh_CN'?"验证码错误！":"Verification code error!");
			tip.attr("class","text-danger");
			tip.css("display","block");    
	        return false;
		}else{
			tip.html("");
			tip.css("display","none"); 
	        return true;
		}
	}
}*/