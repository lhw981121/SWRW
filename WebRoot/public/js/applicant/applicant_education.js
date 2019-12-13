//求职者教育经历信息验证JS

//毕业学校验证
function checkGraduationSchool() {
	var value = $('#graduate_school').val();
	//毕业学校的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"毕业学校长度超限！":"Wrong graduate school format!");
		return false;
	} else {
		return true;
	}
}

//所学专业验证
function checkProfession() {
	var value = $('#profession').val();
	//所学专业的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"所学专业长度超限！":"Wrong profession format!");
		return false;
	} else {
		return true;
	}
}

//教育程度验证
function checkEducationDegree() {
	var value = $('#education_degree').val();
	//教育程度的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"教育程度长度超限！":"Wrong education degree format!");
		return false;
	} else {
		return true;
	}
}

