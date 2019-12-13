//用户账号验证JS

function checkUser_account(){
	var value = $('#user_account').val();
    //账号的规则：必填，6~18位字符，只能包含英文字母、数字、下划线
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"账号不能为空！":"The account cannot be empty!");
        return false;
	}else{
		var reg = /^([_]|[0-9a-zA-Z])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"账号只能包含英文字母、数字、下划线！":"Wrong account format!");
	        return false;  
	    }else if(value.length<6||value.length>18){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"账号必须为6~18位字符！":"Wrong account format!");
	        return false;  
	    }else{
			return true;
		}
	}
}

/*function checkUser_account(language){
	var value = $('#user_account').val();
	var tip = $('#user_account_tip');
    //账号的规则：必填，6~18位字符，只能包含英文字母、数字、下划线
	if(value.length == 0){
		//不符合规则
        tip.html(language=='zh_CN'?"账号不能为空！":"The account cannot be empty!");
		tip.attr("class","text-danger");
		tip.css("display","block");  
        return false;
	}else{
		var reg = /^([_]|[0-9a-zA-Z])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			tip.html(language=='zh_CN'?"账号只能包含英文字母、数字、下划线！":"Wrong account format!");
			tip.attr("class","text-danger");
			tip.css("display","block"); 
	        return false;  
	    }else if(value.length<6||value.length>18){
	        //不符合规则
			tip.html(language=='zh_CN'?"账号必须为6~18位字符！":"Wrong account format!");
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