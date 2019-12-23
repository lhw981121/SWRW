//求职者教育经历信息验证JS

//验证教育经历表单
function checkEducationExperience1(){
	if(checkGraduationSchool($('#graduate_school'))&&updateGraduationDate($('#graduation_date1').val(),$('#graduation_date'))&&
			checkProfession($('#profession'))&&checkEducationDegree($('#education_degree'))){
		if($('#graduate_school').val().length!=0||$('#profession').val().length!=0||$('#graduation_date').val().length!=0||$('#education_degree').val().length!=0){
			if($('#graduate_school').val().length!=0&&$('#profession').val().length!=0&&$('#graduation_date').val().length!=0&&$('#education_degree').val().length!=0){
				return true;
			}else{
				swal({
					title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
					text: language=='zh_CN'?'教育经历①必须填写完整。':'Education 1 must be completed',
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
function checkEducationExperience2(){
	if(checkGraduationSchool($('#graduate_school2'))&&updateGraduationDate($('#graduation_date12').val(),$('#graduation_date2'))&&
			checkProfession($('#profession2'))&&checkEducationDegree($('#education_degree2'))){
		if($('#graduate_school2').val().length!=0||$('#profession2').val().length!=0||$('#graduation_date2').val().length!=0||$('#education_degree2').val().length!=0){
			if($('#graduate_school2').val().length!=0&&$('#profession2').val().length!=0&&$('#graduation_date2').val().length!=0&&$('#education_degree2').val().length!=0){
				return true;
			}else{
				swal({
					title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
					text: language=='zh_CN'?'教育经历②必须填写完整。':'Education 1 must be completed',
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
function checkEducationExperience3(){
	if(checkGraduationSchool($('#graduate_school3'))&&updateGraduationDate($('#graduation_date13').val(),$('#graduation_date3'))&&
			checkProfession($('#profession3'))&&checkEducationDegree($('#education_degree3'))){
		if($('#graduate_school3').val().length!=0||$('#profession3').val().length!=0||$('#graduation_date3').val().length!=0||$('#education_degree3').val().length!=0){
			if($('#graduate_school3').val().length!=0&&$('#profession3').val().length!=0&&$('#graduation_date3').val().length!=0&&$('#education_degree3').val().length!=0){
				return true;
			}else{
				swal({
					title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
					text: language=='zh_CN'?'教育经历③必须填写完整。':'Education 3 must be completed',
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
function checkEducationExperience(){
	if(checkEducationExperience1()&&checkEducationExperience2()&&checkEducationExperience3()){
		if(($('#graduate_school').val().length==0&&$('#profession').val().length==0&&$('#graduation_date').val().length==0&&$('#education_degree').val().length==0)&&
		($('#graduate_school2').val().length!=0||$('#profession2').val().length!=0||$('#graduation_date2').val().length!=0||$('#education_degree2').val().length!=0)){
			swal({
				title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
				text: language=='zh_CN'?'教育经历①必须填写才能填写教育经历②。':'Education experience 1 must be filled in in order to fill in education experience 2',
				type: 'error',
			});
			return false;
		}else if((($('#graduate_school').val().length==0&&$('#profession').val().length==0&&$('#graduation_date1').val().length==0&&$('#education_degree').val().length==0)||
		($('#graduate_school2').val().length==0&&$('#profession2').val().length==0&&$('#graduation_date2').val().length==0&&$('#education_degree2').val().length==0))&&
		($('#graduate_school3').val().length!=0||$('#profession3').val().length!=0||$('#graduation_date3').val().length!=0||$('#education_degree3').val().length!=0)){
			swal({
				title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
				text: language=='zh_CN'?'教育经历①②必须填写才能填写教育经历③。':'Education experience 1 and 2 must be filled in in order to fill in education experience 3',
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

//毕业学校验证
function checkGraduationSchool(value) {
	//毕业学校的规则：不超过50个字符
	if (value.val().length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"毕业学校长度超限！":"Wrong graduate school format!");
		return false;
	} else if(value.val().indexOf("_")!=-1){
		ErrorTipBottomLeft(language=='zh_CN'?"毕业学校不能包含下划线'_'！":"Cannot contain underscores!");
		return false;
	} else {
		return true
	}
}

//所学专业验证
function checkProfession(value) {
	//所学专业的规则：不超过50个字符
	if (value.val().length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"所学专业长度超限！":"Wrong profession format!");
		return false;
	} else if(value.val().indexOf("_")!=-1){
		ErrorTipBottomLeft(language=='zh_CN'?"所学专业不能包含下划线'_'！":"Cannot contain underscores!");
		return false;
	} else {
		return true
	}
}

//教育程度验证
function checkEducationDegree(value) {
	//教育程度的规则：不超过50个字符
	if (value.val().length > 50) {
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"教育程度长度超限！":"Wrong education degree format!");
		return false;
	} else if(value.val().indexOf("_")!=-1){
		ErrorTipBottomLeft(language=='zh_CN'?"教育程度不能包含下划线'_'！":"Cannot contain underscores!");
		return false;
	} else {
		return true
	}
}

