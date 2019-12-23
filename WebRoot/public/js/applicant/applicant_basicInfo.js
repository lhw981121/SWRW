//求职者简历基本信息验证JS

//验证基本信息表单
function checkBasicInformation(){
	if(checkRealName()&&checkGender()&&updateBirthday($('#birthday_data').val())&&checkJobExperience()&&checkEmail()&&checkPhone()&&
		checkCurrentLocation()&&checkResidentLocation()&&checkJobIntension()){
		return true;
	}else{
		return false;
	}
}

//真实姓名验证
function checkRealName(){
    var value = $('#realName').val();
    //真实姓名的规则：只能为汉字、字母或数字，长度为2-20
	var reg = /^([\u0391-\uFFE5]|[0-9a-zA-Z\s])+$/;
    if(value.length!=0&&!reg.test(value)){
        //不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"真实姓名输入不符合规范！":"Wrong realname format!");
        return false;  
    }else if(value.length!=0&&value.length<2||value.length>20){
        //不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"真实姓名长度非法！":"Wrong realname format!");
        return false;  
    }else{
		return true;
	}
}

//邮箱验证
function checkEmail(){
    var value = $('#email').val();
    //邮箱的规则：邮箱正则表达式
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(value.length!=0&&!reg.test(value)){
        //不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"邮箱格式有误！":"Wrong email format!");
	    return false;  
    }else{
		return true;
	}
}

//性别验证
function checkGender(){
    var value = $('#gender').val();
    //性别的规则：男或女 male or female
    if(value.length!=0&&value!='男'&&value!='女'&&value!='male'&&value!='female'){
        //不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"性别输入不符合规范！":"Wrong gender format!");
	    return false;
    }else{
		return true;
	}
}

//工作经验验证
function checkJobExperience() {
	var value = $('#job_experience').val();
	//工作经验的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"工作经验长度超限！":"Wrong job experience format!");
		return false;
	} else {
		return true;
	}
}

//手机验证
function checkPhone(){
    var value = $('#telephone').val();
    //手机号的规则：手机号标准
	var reg = /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;
    if(value.length!=0&&!reg.test(value)){
        //不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"手机号输入不规范！":"Wrong mobile phone format!");
        return false;  
    }else{
        return true;  
	}
}

//当前所在地验证
function checkCurrentLocation() {
	var value = $('#current_loc').val();
	//当前所在地的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"当前所在地长度超限！":"Wrong current location format!");
		return false;
	} else {
		return true;
	}
}

//户口所在地验证
function checkResidentLocation() {
	var value = $('#resident_loc').val();
	//户口所在地的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"户口所在地长度超限！":"Wrong resident location format!");
		return false;
	} else {
		return true;
	}
}

//求职意向验证
function checkJobIntension() {
	var value = $('#job_intension').val();
	//求职意向的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"求职意向长度超限！":"Wrong job intension format!");
		return false;
	} else {
		return true;
	}
}
























