//求职者工作经验信息验证JS

//验证工作经验表单
function checkWorkExperience1(){
	if(checkWorkTitle($('#work_title'))&&checkWorkDepartment($('#department'))&&
		updateWorkPeriodStart($('#work_period_start_data').val(),$('#work_period_start'))&&
		updateWorkPeriodEnd($('#work_period_end_data').val(),$('#work_period_end'))){
		if($('#work_title').val().length!=0||$('#department').val().length!=0||$('#work_period_start').val().length!=0||$('#work_period_end').val().length!=0){
			if($('#work_title').val().length!=0&&$('#department').val().length!=0&&$('#work_period_start').val().length!=0&&$('#work_period_end').val().length!=0){
				return true;
			}else{
				swal({
					title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
					text: language=='zh_CN'?'工作经验①必须填写完整。':'Work experience 1 must be completed',
					type: 'error',
				});
				return false;
			}
		}else{
			return true;
		}
	}else{
		return false;
	}
}
function checkWorkExperience2(){
	if(checkWorkTitle($('#work_title2'))&&checkWorkDepartment($('#department2'))&&
		updateWorkPeriodStart($('#work_period_start_data2').val(),$('#work_period_start2'))&&
		updateWorkPeriodEnd($('#work_period_end_data2').val(),$('#work_period_end2'))){
		if($('#work_title2').val().length!=0||$('#department2').val().length!=0||$('#work_period_start2').val().length!=0||$('#work_period_end2').val().length!=0){
			if($('#work_title2').val().length!=0&&$('#department2').val().length!=0&&$('#work_period_start2').val().length!=0&&$('#work_period_end2').val().length!=0){
				return true;
			}else{
				swal({
					title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
					text: language=='zh_CN'?'工作经验②必须填写完整。':'Work experience 2 must be completed',
					type: 'error',
				});
				return false;
			}
		}else{
			return true;
		}
	}else{
		return false;
	}
}
function checkWorkExperience3(){
	if(checkWorkTitle($('#work_title3'))&&checkWorkDepartment($('#department3'))&&
		updateWorkPeriodStart($('#work_period_start_data3').val(),$('#work_period_start3'))&&
		updateWorkPeriodEnd($('#work_period_end_data3').val(),$('#work_period_end3'))){
		if($('#work_title3').val().length!=0||$('#department3').val().length!=0||$('#work_period_start3').val().length!=0||$('#work_period_end3').val().length!=0){
			if($('#work_title3').val().length!=0&&$('#department3').val().length!=0&&$('#work_period_start3').val().length!=0&&$('#work_period_end3').val().length!=0){
				return true;
			}else{
				swal({
					title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
					text: language=='zh_CN'?'工作经验③必须填写完整。':'Work experience 3 must be completed',
					type: 'error',
				});
				return false;
			}
		}else{
			return true;
		}
	}else{
		return false;
	}
}
function checkWorkExperience(){
	if(checkWorkExperience1()&&checkWorkExperience2()&&checkWorkExperience3()){
		if(($('#work_title').val().length==0&&$('#department').val().length==0&&$('#work_period_start').val().length==0&&$('#work_period_end').val().length==0)&&
		($('#work_title').val().length!=0||$('#department').val().length!=0||$('#work_period_start2').val().length!=0||$('#work_period_end2').val().length!=0)){
			swal({
				title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
				text: language=='zh_CN'?'项目经验①必须填写才能填写项目经验②。':'Project experience 1 must be filled in in order to fill in project experience 2',
				type: 'error',
			});
			return false;
		}else if((($('#work_title').val().length==0&&$('#department').val().length==0&&$('#work_period_start').val().length==0&&$('#work_period_end').val().length==0)||
		($('#work_title2').val().length==0&&$('#department2').val().length==0&&$('#work_period_start2').val().length==0&&$('#work_period_end2').val().length==0))&&
		($('#work_title3').val().length!=0||$('#department3').val().length!=0||$('#work_period_start3').val().length!=0||$('#work_period_end3').val().length!=0)){
			swal({
				title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
				text: language=='zh_CN'?'项目经验①②必须填写才能填写项目经验③。':'Project experience 1 and 2 must be filled in in order to fill in project experience 3',
				type: 'error',
			});
			return false;
		}else{
			return true;
		}
	}else{
		return false;
	}
}

//工作职称验证
function checkWorkTitle(value) {
	//工作职称的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"工作职称长度超限！":"Wrong work title format!");
		return false;
	} else if(value.val().indexOf("_")!=-1){
		ErrorTipBottomLeft(language=='zh_CN'?"工作职称不能包含下划线'_'！":"Cannot contain underscores!");
		return false;
	} else {
		return true
	}
}

//工作部门验证
function checkWorkDepartment(value) {
	//工作部门的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"工作部门长度超限！":"Wrong work department format!");
		return false;
	} else if(value.val().indexOf("_")!=-1){
		ErrorTipBottomLeft(language=='zh_CN'?"工作部门不能包含下划线'_'！":"Cannot contain underscores!");
		return false;
	} else {
		return true
	}
}