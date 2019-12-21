//企业发布招聘脚本

/*<!-- 表单验证 -->*/
function checkAll() {
	if(checkJobName()&&checkJobDescription()&&checkJobArea()&&checkJobSalary()&&checkJobHiringnum()&&
		updateJobEndTime($('#job_endtime_date').val())&&checkPostJobProtocol()){
		return true;
	}else{
		return false;
	}
}

//发布招聘按钮
$('#postJobBtn').click(function(){
	if(checkAll()){
		$.ajax({
			type:"post",
			url:"/SWRW/CompanyPostJob",
			datatype: "json",
			async:true,
			data:{
				"job_id":job_id,
				"job_name":$('#job_name').val(),
				"job_desc":$('#job_desc').val(),
				"job_area":$('#job_area').val(),
				"job_salary":$('#job_salary').val(),
				"job_hiringnum":$('#job_hiringnum').val(),
				"job_endtime":$('#job_endtime').val()
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//发布招聘成功
					swal({
						title: language=='zh_CN'?"发布招聘成功":"Post Job Successful",
						text: r.successMes,
						type: "success",
					},
					function(){
						window.location.href="/SWRW/company/job/manage_job";
					});
					setTimeout(function () {window.location.href="/SWRW/company/job/manage_job";}, 3000);
				}else{//发布招聘失败
					swal({
						title: language=='zh_CN'?"发布招聘失败":"Post Job Failure",
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

//职位所在地选择改变
function updateJobArea(){
	var citySelect1 = $('#citySelect1').val();
	var citySelect2 = $('#citySelect2').val();
	var citySelect3 = $('#citySelect3').val();
	var city = citySelect1+" "+citySelect2+" "+citySelect3;
	$('#job_area').val(city);
}


/* 职位结束日期输入框 */
$('.job_endtime_date').datetimepicker({
	language: language,
	weekStart: 1,//一周从哪一天开始。0（星期日）到6（星期六）
	todayBtn:  1,//是否显示今天按钮
	autoclose: 0,//选择日期后是否关闭
	todayHighlight: 0,//是否高亮当前日期
	startView: 2,//0 or 'hour' 为小时视图；1 or 'day' 为天视图；2 or 'month' 为月视图（为默认值）；3 or 'year'  为年视图；4 or 'decade' 为十年视图
	minView: 2,
	forceParse: 0,//当选择器关闭的时候，是否强制解析输入框中的值
});
$('.job_endtime_date').datetimepicker('setDate', new Date(oldEndTime));
function updateJobEndTime(val) {
	if(val.length==0){
		ErrorTipBottomLeft(language=='zh_CN'?"职位结束日期不能为空！":"The job end time be empty!");
		return false;
	}
	if (/^\d{4}-\d{2}-\d{2}$/.test(val)) {
		$('#job_endtime').val(val);
		return true;
	}else{
		$('#job_endtime').val("error");
		ErrorTipBottomLeft(language=='zh_CN'?"职位结束日期输入不符合规范！":"Wrong job end time format!");
		return false;
	}
}