//求职者项目经验信息验证JS

//验证项目经验表单
function checkProjectExperience1(){
	if(checkProjectName($('#project_name'))&&checkProjectJob($('#project_job'))&&
		updateProjectPeriodStart($('#project_period_start_data').val(),$('#project_period_start'))&&
		updateProjectPeriodEnd($('#project_period_end_data').val(),$('#project_period_end'))&&
		checkProjectDescription($('#project_desc'))){
		if($('#project_name').val().length!=0||$('#project_job').val().length!=0||$('#project_desc').val().length!=0||$('#project_period_start').val().length!=0||$('#project_period_end').val().length!=0){
			if($('#project_name').val().length!=0&&$('#project_job').val().length!=0&&$('#project_desc').val().length!=0&&$('#project_period_start').val().length!=0&&$('#project_period_end').val().length!=0){
				return true;
			}else{
				swal({
					title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
					text: language=='zh_CN'?'项目经验①必须填写完整。':'Project experience 1 must be completed',
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
function checkProjectExperience2(){
	if(checkProjectName($('#project_name2'))&&checkProjectJob($('#project_job2'))&&
		updateProjectPeriodStart($('#project_period_start_data2').val(),$('#project_period_start2'))&&
		updateProjectPeriodEnd($('#project_period_end_data2').val(),$('#project_period_end2'))&&
		checkProjectDescription($('#project_desc2'))){
		if($('#project_name2').val().length!=0||$('#project_job2').val().length!=0||$('#project_desc2').val().length!=0||$('#project_period_start2').val().length!=0||$('#project_period_end2').val().length!=0){
			if($('#project_name2').val().length!=0&&$('#project_job2').val().length!=0&&$('#project_desc2').val().length!=0&&$('#project_period_start2').val().length!=0&&$('#project_period_end2').val().length!=0){
				return true;
			}else{
				swal({
					title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
					text: language=='zh_CN'?'项目经验②必须填写完整。':'Project experience 2 must be completed',
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
function checkProjectExperience3(){
	if(checkProjectName($('#project_name3'))&&checkProjectJob($('#project_job3'))&&
		updateProjectPeriodStart($('#project_period_start_data3').val(),$('#project_period_start3'))&&
		updateProjectPeriodEnd($('#project_period_end_data3').val(),$('#project_period_end3'))&&
		checkProjectDescription($('#project_desc3'))){
		if($('#project_name3').val().length!=0||$('#project_job3').val().length!=0||$('#project_desc3').val().length!=0||$('#project_period_start3').val().length!=0||$('#project_period_end3').val().length!=0){
			if($('#project_name3').val().length!=0&&$('#project_job3').val().length!=0&&$('#project_desc3').val().length!=0&&$('#project_period_start3').val().length!=0&&$('#project_period_end3').val().length!=0){
				return true;
			}else{
				swal({
					title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
					text: language=='zh_CN'?'项目经验③必须填写完整。':'Project experience 3 must be completed',
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
function checkProjectExperience(){
	if(checkProjectExperience1()&&checkProjectExperience2()&&checkProjectExperience3()){
		if(($('#project_name').val().length==0&&$('#project_job').val().length==0&&$('#project_desc').val().length==0&&$('#project_period_start').val().length==0&&$('#project_period_end').val().length==0)&&
		($('#project_name2').val().length!=0||$('#project_job2').val().length!=0||$('#project_desc2').val().length!=0||$('#project_period_start2').val().length!=0||$('#project_period_end2').val().length!=0)){
			swal({
				title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
				text: language=='zh_CN'?'项目经验①必须填写才能填写项目经验②。':'Project experience 1 must be filled in in order to fill in project experience 2',
				type: 'error',
			});
			return false;
		}else if((($('#project_name').val().length==0&&$('#project_job').val().length==0&&$('#project_desc').val().length==0&&$('#project_period_start').val().length==0&&$('#project_period_end').val().length==0)||
		($('#project_name2').val().length==0&&$('#project_job2').val().length==0&&$('#project_desc2').val().length==0&&$('#project_period_start2').val().length==0&&$('#project_period_end2').val().length==0))&&
		($('#project_name3').val().length!=0||$('#project_job3').val().length!=0||$('#project_desc3').val().length!=0||$('#project_period_start3').val().length!=0||$('#project_period_end3').val().length!=0)){
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




//项目名称验证
function checkProjectName(value) {
	//项目名称的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"项目名称长度超限！":"Wrong project name format!");
		return false;
	} else if(value.val().indexOf("_")!=-1){
		ErrorTipBottomLeft(language=='zh_CN'?"项目名称不能包含下划线'_'！":"Cannot contain underscores!");
		return false;
	} else {
		return true
	}
}

//担任职务验证
function checkProjectJob(value) {
	//担任职务的规则：不超过50个字符
	if (value.length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"担任职务长度超限！":"Wrong project job format!");
		return false;
	} else if(value.val().indexOf("_")!=-1){
		ErrorTipBottomLeft(language=='zh_CN'?"担任职务不能包含下划线'_'！":"Cannot contain underscores!");
		return false;
	} else {
		return true
	}
}

//项目简述验证
function checkProjectDescription(value) {
	//项目简述的规则：不超过255个字符
	if (value.length > 255) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"项目简述长度超限！":"Wrong project description format!");
		return false;
	} else if(value.val().indexOf("_")!=-1){
		ErrorTipBottomLeft(language=='zh_CN'?"项目简述不能包含下划线'_'！":"Cannot contain underscores!");
		return false;
	} else {
		return true
	}
}