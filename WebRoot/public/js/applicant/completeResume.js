//求职者完善简历页面脚本


/*<!-- 表单验证 -->*/
function checkAll() {
	if(checkBasicInformation()&&checkEducationExperience()&&checkProjectExperience()&&checkWorkExperience()&&checkCompleteResumeProtocol()){
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
				//教育经历
				"graduate_school":$('#graduate_school').val()+"_"+$('#graduate_school2').val()+"_"+$('#graduate_school3').val(),
				"profession":$('#profession').val()+"_"+$('#profession2').val()+"_"+$('#profession3').val(),
				"graduation_date":$('#graduation_date').val()+"_"+$('#graduation_date2').val()+"_"+$('#graduation_date3').val(),
				"education_degree":$('#education_degree').val()+"_"+$('#education_degree2').val()+"_"+$('#education_degree3').val(),
				//项目经历
				"project_name":$('#project_name').val()+"_"+$('#project_name2').val()+"_"+$('#project_name3').val(),
				"project_job":$('#project_job').val()+"_"+$('#project_job2').val()+"_"+$('#project_job3').val(),
				"project_period":$('#project_period_start').val()+"_"+$('#project_period_end').val()+"_"+
									$('#project_period_start2').val()+"_"+$('#project_period_end2').val()+"_"+
									$('#project_period_start3').val()+"_"+$('#project_period_end3').val(),
				"project_desc":$('#project_desc').val()+"_"+$('#project_desc2').val()+"_"+$('#project_desc3').val(),
				//工作经历
				"work_title":$('#work_title').val()+"_"+$('#work_title2').val()+"_"+$('#work_title3').val(),
				"department":$('#department').val()+"_"+$('#department2').val()+"_"+$('#department3').val(),
				"work_period":$('#work_period_start').val()+"_"+$('#work_period_end').val()+"_"+
									$('#work_period_start2').val()+"_"+$('#work_period_end2').val()+"_"+
									$('#work_period_start3').val()+"_"+$('#work_period_end3').val(),
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

//添加教育经历按钮
$('#addEducationExperienceBtn').click(function(){
	if($("#EducationExperience2").is(":hidden")){
		$("#EducationExperience2").slideToggle();
	}else if($("#EducationExperience3").is(":hidden")){
		$("#EducationExperience3").slideToggle();
	}else{
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'最多只能添加三项教育经历。':'No more than three educational experiences can be added.',
			type: 'error',
		});
	}
})
//删除教育经历按钮
$('#removeEducationExperience2Btn').click(function(){
	if($("#EducationExperience3").is(":visible")){
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'请先删除教育经历③。':'Please delete your education three first.',
			type: 'error',
		});
	}else if($('#graduate_school2').val().length==0&&$('#profession2').val().length==0&&$('#graduation_date2').val().length==0&&$('#education_degree2').val().length==0){
		$("#EducationExperience2").slideToggle();
	}else{
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'教育经历②中含有已填写信息。':'Educational history contains information that has been filled in.',
			type: 'error',
		});
	}
})
$('#removeEducationExperience3Btn').click(function(){
	if($('#graduate_school3').val().length==0&&$('#profession3').val().length==0&&$('#graduation_date3').val().length==0&&$('#education_degree3').val().length==0){
		$("#EducationExperience3").slideToggle();
	}else{
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'教育经历③中含有已填写信息。':'Educational history contains information that has been filled in.',
			type: 'error',
		});
	}
})

//添加项目经验按钮
$('#addProjectExperienceBtn').click(function(){
	if($("#ProjectExperience2").is(":hidden")){
		$("#ProjectExperience2").slideToggle();
	}else if($("#ProjectExperience3").is(":hidden")){
		$("#ProjectExperience3").slideToggle();
	}else{
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'最多只能添加三项项目经验。':'No more than three project experiences can be added.',
			type: 'error',
		});
	}
})
//删除项目经验按钮
$('#removeProjectExperience2Btn').click(function(){
	if($("#ProjectExperience3").is(":visible")){
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'请先删除项目经验③。':'Please delete your project three first.',
			type: 'error',
		});
	}else if($('#project_name2').val().length==0&&$('#project_job2').val().length==0&&$('#project_desc2').val().length==0&&$('#project_period_start2').val().length==0&&$('#project_period_end2').val().length==0){
		$("#ProjectExperience2").slideToggle();
	}else{
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'项目经验②中含有已填写信息。':'Project history contains information that has been filled in.',
			type: 'error',
		});
	}
})
$('#removeProjectExperience3Btn').click(function(){
	if($('#project_name3').val().length==0&&$('#project_job3').val().length==0&&$('#project_desc3').val().length==0&&$('#project_period_start3').val().length==0&&$('#project_period_end3').val().length==0){
		$("#ProjectExperience3").slideToggle();
	}else{
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'项目经验③中含有已填写信息。':'Project history contains information that has been filled in.',
			type: 'error',
		});
	}
})

//添加工作经验按钮
$('#addWorkExperienceBtn').click(function(){
	if($("#WorkExperience2").is(":hidden")){
		$("#WorkExperience2").slideToggle();
	}else if($("#WorkExperience3").is(":hidden")){
		$("#WorkExperience3").slideToggle();
	}else{
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'最多只能添加三项工作经验。':'No more than three work experiences can be added.',
			type: 'error',
		});
	}
})
//删除工作经验按钮
$('#removeWorkExperience2Btn').click(function(){
	if($("#WorkExperience3").is(":visible")){
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'请先删除工作经验③。':'Please delete your work three first.',
			type: 'error',
		});
	}else if($('#work_title2').val().length==0&&$('#department2').val().length==0&&$('#work_period_start2').val().length==0&&$('#work_period_end2').val().length==0){
		$("#WorkExperience2").slideToggle();
	}else{
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'工作经验②中含有已填写信息。':'Work history contains information that has been filled in.',
			type: 'error',
		});
	}
})
$('#removeWorkExperience3Btn').click(function(){
	if($('#work_title3').val().length==0&&$('#department3').val().length==0&&$('#work_period_start3').val().length==0&&$('#work_period_end3').val().length==0){
		$("#WorkExperience3").slideToggle();
	}else{
		swal({
			title: language=='zh_CN'?'操作禁止':'Prohibit Operating',
			text: language=='zh_CN'?'工作经验③中含有已填写信息。':'Work history contains information that has been filled in.',
			type: 'error',
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
	if(val.length==0){
		$('#birthday').val('');
		return true;
	}
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
$('.graduation_date12').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.graduation_date13').datetimepicker({
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
$('.graduation_date12').datetimepicker('setDate', new Date(oldGraduationDate2));
$('.graduation_date13').datetimepicker('setDate', new Date(oldGraduationDate3));
function updateGraduationDate(val,date) {
	if(val.length==0){
		date.val('');
		return true;
	}
	if (/^\d{4}-\d{2}-\d{2}$/.test(val)) {
		date.val(val);
		return true;
	}else{
		date.val("error");
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
$('.project_period_start_data2').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.project_period_start_data3').datetimepicker({
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
$('.project_period_start_data2').datetimepicker('setDate', new Date(oldProjectPeriodStart2));
$('.project_period_start_data3').datetimepicker('setDate', new Date(oldProjectPeriodStart3));
function updateProjectPeriodStart(val,date) {
	if(val.length==0){
		date.val('');
		return true;
	}
	if (/^\d{4}-\d{2}-\d{2}$/.test(val)) {
		date.val(val);
		return true;
	}else{
		date.val("error");
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
$('.project_period_end_data2').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.project_period_end_data3').datetimepicker({
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
$('.project_period_end_data2').datetimepicker('setDate', new Date(oldProjectPeriodEnd2));
$('.project_period_end_data3').datetimepicker('setDate', new Date(oldProjectPeriodEnd3));
function updateProjectPeriodEnd(val,date){
	if(val.length==0){
		date.val('');
		return true;
	}
	if(/^\d{4}-\d{2}-\d{2}$/.test(val)){
		date.val(val);
		return true;
	}else{
		date.val("error");
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
$('.work_period_start_data2').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.work_period_start_data3').datetimepicker({
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
$('.work_period_start_data2').datetimepicker('setDate', new Date(oldWorkPeriodStart3));
$('.work_period_start_data3').datetimepicker('setDate', new Date(oldWorkPeriodStart3));
function updateWorkPeriodStart(val,date) {
	if(val.length==0){
		date.val('');
		return true;
	}
	if (/^\d{4}-\d{2}-\d{2}$/.test(val)) {
		date.val(val);
		return true;
	}else{
		date.val("error");
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
$('.work_period_end_data2').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.work_period_end_data3').datetimepicker({
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
$('.work_period_end_data2').datetimepicker('setDate', new Date(oldWorkPeriodEnd2));
$('.work_period_end_data3').datetimepicker('setDate', new Date(oldWorkPeriodEnd3));
function updateWorkPeriodEnd(val,date){
	if(val.length==0){
		date.val('');
		return true;
	}
	if(/^\d{4}-\d{2}-\d{2}$/.test(val)){
		date.val(val);
		return true;
	}else{
		date.val("error");
		ErrorTipBottomLeft(language=='zh_CN'?"工作结束时间输入不符合规范！":"Wrong work end date format!");
		return false;
	}
}

//完善简历协议勾选验证
function checkCompleteResumeProtocol(){
	if($('#completeResumeProtocol').is(':checked')){
		return true;
	}else{
		ErrorTipBottomLeft(language=='zh_CN'?"你未勾选简历提交协议！":"You have not checked the resume submit Protocol!");
		return false;
	}
}