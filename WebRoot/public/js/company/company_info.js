//企业信息验证JS

//企业名称验证
function checkCompanyName(){
    var value = $('#company_name').val();
    //企业名称的规则： 必填，只能为汉字、字母或数字，长度不超过50
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"企业名称不能为空！":"The company name cannot be empty!");
	    return false;
	}else{
		var reg = /^([\u0391-\uFFE5]|[0-9a-zA-Z])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"企业名称输入不符合规范！":"Wrong company name format!");
	        return false;  
	    }else if(value.length>50){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"企业名称长度超限！":"Wrong company name format!");
	        return false;  
	    }else{
			return true;
		}
	}
}
//企业法人验证
function checkCompanyLegal(){
    var value = $('#company_legal').val();
    //企业法人的规则： 必填，只能为汉字、字母或数字，长度不超过20
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"企业法人不能为空！":"The company legal person cannot be empty!");
	    return false;
	}else{
		var reg = /^([\u0391-\uFFE5]|[0-9a-zA-Z])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"企业法人输入不符合规范！":"Wrong company legal person format!");
	        return false;  
	    }else if(value.length>50){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"企业法人长度超限！":"Wrong company legal person format!");
	        return false;  
	    }else{
			return true;
		}
	}
}
//企业所在地验证
function checkCompanyArea(){
    var value = $('#company_area').val();
    //企业所在地的规则： 必填，只能为汉字、字母，长度不超过50
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"企业所在地不能为空！":"The company area cannot be empty!");
	    return false;
	}else{
		var reg = /^([\u0391-\uFFE5]|[a-zA-Z])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"企业所在地输入不符合规范！":"Wrong company area format!");
	        return false;  
	    }else if(value.length>50){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"企业所在地长度超限！":"Wrong company area format!");
	        return false;  
	    }else{
			return true;
		}
	}
}
//企业规模验证
function checkCompanySize(){
    var value = $('#company_size').val();
    //企业规模的规则： 必选
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"企业规模必选！":"The company size must choose!");
	    return false;
	}else{
		return true;
	}
}
//企业性质验证
function checkCompanyType(){
	var value = $('#company_type').val();
    //企业性质的规则： 必选
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"企业性质必选！":"Company nature must choose!");
	    return false;
	}else{
		return true;
	}
}
//企业简介验证
function checkCompanyBrief(){
    var value = $('#company_brief').val();
    //企业简介的规则： 必填，只能为汉字、字母或数字，长度不超过500
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"企业简介不能为空！":"The company brief cannot be empty!");
	    return false;
	}else{
		var reg = /^([\u0391-\uFFE5]|[0-9a-zA-Z])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"企业简介输入不符合规范！":"Wrong company brief format!");
	        return false;  
	    }else if(value.length>500){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"企业简介长度超限！":"Wrong company brief format!");
	        return false;  
	    }else{
			return true;
		}
	}
}
//企业营业执照验证
function checkCompanyLicense(){
	var value = $('#company_license').val();
    //企业营业执照的规则： 必填
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"必须上传企业营业执照！":"Must upload the company business license!");
	    return false;
	}else{
		return true;
	}
}


