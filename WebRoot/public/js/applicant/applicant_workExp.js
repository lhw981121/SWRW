//求职者工作经验信息验证JS

//工作职称验证
function checkWorkTitle() {
	var value = $('#work_title').val();
	//工作职称的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"工作职称长度超限！":"Wrong work title format!");
		return false;
	} else {
		return true;
	}
}

//工作部门验证
function checkWorkDepartment() {
	var value = $('#department').val();
	//工作部门的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"工作部门长度超限！":"Wrong work department format!");
		return false;
	} else {
		return true;
	}
}