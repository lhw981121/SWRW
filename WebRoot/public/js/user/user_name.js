//用户名验证JS

function checkUser_name(){
    var value = $('#user_name').val();
    //用户名的规则： 必填，只能为汉字、字母或数字，长度为2-20
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"用户名不能为空！":"The user name cannot be empty!");
	    return false;
	}else{
		var reg = /^([\u0391-\uFFE5]|[0-9a-zA-Z])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"用户名输入不符合规范！":"Wrong user name format!");
	        return false;  
	    }else if(value.length<2||value.length>20){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"用户名长度非法！":"Wrong user name format!");
	        return false;  
	    }else{
			return true;
		}
	}
}