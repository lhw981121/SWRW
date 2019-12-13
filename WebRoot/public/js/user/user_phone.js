//用户手机号验证JS

function checkUser_phone(){
	var value = $('#user_phone').val();
    //手机号的规则： 必填，手机号标准
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"手机号不能为空！":"The mobile phone cannot be empty!");
        return false;
	}else{
		/*var reg = /^[1]([3-9])[0-9]{9}$/;*/
		var reg = /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"手机号输入不规范！":"Wrong mobile phone format!");
	        return false;  
	    }else{
	        return true;  
		}
	}
}

//手机验证码验证
function checkPhone_code(value,code){
	if(value.length == 0){
		ErrorTipBottomLeft(language=='zh_CN'?"验证码不能为空！":"The verification code cannot be empty!");
        return false;
	}else{
		var phonecode = code;
		if(phonecode!=null&&value!=phonecode){
			ErrorTipBottomLeft(language=='zh_CN'?"验证码错误！":"Verification code error!");
	        return false;
		}else{ 
	        return true;
		}
	}
}