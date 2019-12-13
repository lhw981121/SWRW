//用户密码验证JS

function checkUser_password(){
    var value = $('#user_password').val();
    //密码的规则：必填，6~18位字符，必须包含数字、字母或特殊字符其中两项及以上
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"密码不能为空！":"The password cannot be empty!");
        return false;
	}else{
		var reg = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*./\-\+]+$)[a-zA-Z\d!@#$%^&*.\-\+]+$/;
	    if(!reg.test(value)){
	        //不符合规则
		    ErrorTipBottomLeft(language=='zh_CN'?"密码必须包含数字、字母或特殊字符其中两项及以上！":"Wrong password format!");
	        return false;  
	    }else if(value.length<6||value.length>18){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"密码必须为6~18位字符！":"Wrong password format!");
	        return false;  
	    }else{
			return true;
		}
	}
}

/*function checkUser_password(language){
    var value = $('#user_password').val();
	var tip = $('#user_password_tip');
	
    //密码的规则：必填，6~18位字符，必须包含数字、字母或特殊字符其中两项及以上
	if(value.length == 0){
		//不符合规则
        tip.html(language=='zh_CN'?"密码不能为空！":"The password cannot be empty!");
		tip.attr("class","text-danger");    
		tip.css("display","block");  
        return false;
	}else{
		var reg = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*./\-\+]+$)[a-zA-Z\d!@#$%^&*.\-\+]+$/;
	    if(!reg.test(value)){
	        //不符合规则
			tip.html(language=='zh_CN'?"密码必须包含数字、字母或特殊字符其中两项及以上！":"Wrong password format!");
			tip.attr("class","text-danger");
			tip.css("display","block");  
	        return false;  
	    }else if(value.length<6||value.length>18){
	        //不符合规则
			tip.html(language=='zh_CN'?"密码必须为6~18位字符！":"Wrong password format!");
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