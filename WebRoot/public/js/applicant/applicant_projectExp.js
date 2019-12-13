//求职者项目经验信息验证JS

//项目名称验证
function checkProjectName() {
	var value = $('#project_name').val();
	//项目名称的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"项目名称长度超限！":"Wrong project name format!");
		return false;
	} else {
		return true;
	}
}

//担任职务验证
function checkProjectJob() {
	var value = $('#project_job').val();
	//担任职务的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"担任职务长度超限！":"Wrong project job format!");
		return false;
	} else {
		return true;
	}
}

//项目简述验证
function checkProjectDescription() {
	var value = $('#project_desc').val();
	//项目简述的规则：不超过255个字符
	if (value.length > 255) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"项目简述长度超限！":"Wrong project description format!");
		return false;
	} else {
		return true;
	}
}