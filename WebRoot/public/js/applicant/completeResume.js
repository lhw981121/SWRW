//求职者完善简历页面脚本


/*<!-- 表单验证 -->*/
function checkAll() {
	if(checkRealName()&&checkGender()&&updateBirthday($('#birthday_data').val())&&checkJobExperience()&&checkEmail()&&checkPhone()&&
		checkCurrentLocation()&&checkResidentLocation()&&checkJobIntension()&&checkGraduationSchool()&&updateGraduationDate($('#graduation_date1').val())&&
		checkProfession()&&checkEducationDegree()&&checkProjectName()&&checkProjectJob()&&updateProjectPeriodStart($('#project_period_start_data').val())&&
		updateProjectPeriodEnd($('#project_period_end_data').val())&&checkProjectDescription()&&checkWorkTitle()&&checkWorkDepartment()&&
		updateWorkPeriodStart($('#work_period_start_data').val())&&updateWorkPeriodEnd($('#work_period_end_data').val())){
		return true;
	}else{
		return false;
	}
}

//表单提交按钮
$('#submitResumeBtn').click(function(){
	if(checkAll()){
		$.ajax({
			type:"post",
			url:"/SWRW/ApplicantSubmitResume",
			datatype: "json",
			async:true,
			data:{
				"basicInfo_id":basicInfo_id,
				"realName":$('#realName').val(),
				"birthday":$('#birthday').val(),
				"email":$('#email').val(),
				"gender":$('#gender').val(),
				"job_experience":$('#job_experience').val(),
				"telephone":$('#telephone').val(),
				"current_loc":$('#current_loc').val(),
				"resident_loc":$('#resident_loc').val(),
				"job_intension":$('#job_intension').val(),
				"graduate_school":$('#graduate_school').val(),
				"profession":$('#profession').val(),
				"graduation_date":$('#graduation_date').val(),
				"education_degree":$('#education_degree').val(),
				"project_name":$('#project_name').val(),
				"project_job":$('#project_job').val(),
				"project_period_start":$('#project_period_start').val(),
				"project_period_end":$('#project_period_end').val(),
				"project_desc":$('#project_desc').val(),
				"work_title":$('#work_title').val(),
				"department":$('#department').val(),
				"work_period_start":$('#work_period_start').val(),
				"work_period_end":$('#work_period_end').val(),
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//简历信息提交成功
					swal({
						title: language=='zh_CN'?"提交成功":"Submit Successful",
						text: r.successMes,
						type: "success",
					},
					function(){
						window.location.href="/SWRW/applicant/resume/view_resume";
					});
					setTimeout(function () {window.location.href="/SWRW/applicant/resume/view_resume";}, 3000);
				}else{//提交失败
					swal({
						title: language=='zh_CN'?"提交失败":"Submit Failure",
						text: r.errorMes,
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});
	}
})

/* 生日输入框 */
$('.birthday_date').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.birthday_date').datetimepicker('setDate', new Date(oldBirthday));
function updateBirthday(val) {
	if(val.length==0)	return true;
	if (/^\d{4}-\d{2}-\d{2}$/.test(val)) {
		$('#birthday').val(val);
		return true;
	}else{
		$('#birthday').val("error");
		ErrorTipBottomLeft(language=='zh_CN'?"生日日期输入不符合规范！":"Wrong birthday format!");
		return false;
	}
}

/* 毕业时间输入框 */
$('.graduation_date1').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.graduation_date1').datetimepicker('setDate', new Date(oldGraduationDate));
function updateGraduationDate(val) {
	if(val.length==0)	return true;
	if (/^\d{4}-\d{2}-\d{2}$/.test(val)) {
		$('#graduation_date').val(val);
		return true;
	}else{
		$('#graduation_date').val("error");
		ErrorTipBottomLeft(language=='zh_CN'?"毕业时间输入不符合规范！":"Wrong graduation date format!");
		return false;
	}
}

/* 项目开始时间输入框 */
$('.project_period_start_data').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.project_period_start_data').datetimepicker('setDate', new Date(oldProjectPeriodStart));
function updateProjectPeriodStart(val) {
	if(val.length==0)	return true;
	if (/^\d{4}-\d{2}-\d{2}$/.test(val)) {
		$('#project_period_start').val(val);
		return true;
	}else{
		$('#project_period_start').val("error");
		ErrorTipBottomLeft(language=='zh_CN'?"项目开始时间输入不符合规范！":"Wrong project start date format!");
		return false;
	}
}

/* 项目结束时间输入框 */
$('.project_period_end_data').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.project_period_end_data').datetimepicker('setDate', new Date(oldProjectPeriodEnd));
function updateProjectPeriodEnd(val){
	if(val.length==0)	return true;
	if(/^\d{4}-\d{2}-\d{2}$/.test(val)){
		$('#project_period_end').val(val);
		return true;
	}else{
		$('#project_period_end').val("error");
		ErrorTipBottomLeft(language=='zh_CN'?"项目结束时间输入不符合规范！":"Wrong project end date format!");
		return false;
	}
}

/* 工作开始时间输入框 */
$('.work_period_start_data').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.work_period_start_data').datetimepicker('setDate', new Date(oldWorkPeriodStart));
function updateWorkPeriodStart(val) {
	if(val.length==0)	return true;
	if (/^\d{4}-\d{2}-\d{2}$/.test(val)) {
		$('#work_period_start').val(val);
		return true;
	}else{
		$('#work_period_start').val("error");
		ErrorTipBottomLeft(language=='zh_CN'?"工作开始时间输入不符合规范！":"Wrong work start date format!");
		return false;
	}
}

/* 工作结束时间输入框 */
$('.work_period_end_data').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.work_period_end_data').datetimepicker('setDate', new Date(oldWorkPeriodEnd));
function updateWorkPeriodEnd(val){
	if(val.length==0)	return true;
	if(/^\d{4}-\d{2}-\d{2}$/.test(val)){
		$('#work_period_end').val(val);
		return true;
	}else{
		$('#work_period_end').val("error");
		ErrorTipBottomLeft(language=='zh_CN'?"工作结束时间输入不符合规范！":"Wrong work end date format!");
		return false;
	}
}