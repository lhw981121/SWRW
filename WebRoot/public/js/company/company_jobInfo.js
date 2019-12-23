//职位信息验证JS


//职位名称验证
function checkJobName() {
	var value = $('#job_name').val();
    //职位名称的规则： 必填，只能为汉字、字母或数字，长度不超过50
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"职位名称不能为空！":"The job name cannot be empty!");
	    return false;
	}else{
		var reg = /^([\u0391-\uFFE5]|[0-9a-zA-Z\s])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"职位名称输入不符合规范！":"Wrong job name format!");
	        return false;  
	    }else if(value.length>50){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"职位名称长度超限！":"Wrong job name format!");
	        return false;  
	    }else{
			return true;
		}
	}
}

//职位描述验证
function checkJobDescription() {
	var value = $('#job_desc').val();
    //职位描述的规则： 必填，长度不超过500
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"职位描述不能为空！":"The job description cannot be empty!");
	    return false;
	}else{
		if(value.length>500){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"职位描述长度超限！":"Wrong job description format!");
	        return false;  
	    }else{
			return true;
		}
	}
}

//职位所在地验证
function checkJobArea(){
    var value = $('#job_area').val();
    //职位所在地的规则： 必选
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"职位所在地不能为空！":"The job area cannot be empty!");
	    return false;
	}else{
		return true;
	}
}

//职位薪资区间验证
function checkJobSalary() {
	var value = $('#job_salary').val();
    //职位薪资区间的规则： 必填，只能为汉字、字母或数字及字符'/'，长度不超过50
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"职位薪资区间不能为空！":"The job salary cannot be empty!");
	    return false;
	}else{
		var reg = /^([\u0391-\uFFE5]|[0-9a-zA-Z/\-])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"职位薪资区间输入不符合规范！":"Wrong job salary format!");
	        return false;  
	    }else if(value.length>50){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"职位薪资区间长度超限！":"Wrong job salary format!");
	        return false;  
	    }else{
			return true;
		}
	}
}

//职位招聘人数验证
function checkJobHiringnum() {
	var value = $('#job_hiringnum').val();
    //职位招聘人数的规则： 必填，只能数字，长度不超过11
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"职位招聘人数不能为空！":"The job hiringnum cannot be empty!");
	    return false;
	}else{
		var reg = /^([0-9])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"职位招聘人数输入不符合规范！":"Wrong job hiringnum format!");
	        return false;  
	    }else if(value.length>11){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"职位招聘人数长度超限！":"Wrong job hiringnum format!");
	        return false;  
	    }else{
			return true;
		}
	}
}

//发布招聘协议勾选验证
function checkPostJobProtocol(){
	if($('#postJobProtocol').is(':checked')){
		return true;
	}else{
		ErrorTipBottomLeft(language=='zh_CN'?"你未勾选发布招聘协议！":"You have not checked the Post Job Protocol!");
		return false;
	}
}























