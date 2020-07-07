//企业职位详情页面脚本

/*<!-- 表单验证 -->*/
function checkAll() {
	if(checkJobName()&&checkJobDescription()&&checkJobArea()&&checkJobSalary()&&checkJobHiringnum()&&
		updateJobEndTime($('#job_endtime_date').val())){
		return true;
	}else{
		return false;
	}
}

//修改职位信息
function changeJobDetail(job_id){
	if(checkAll()){
		$.ajax({
			type:"post",
			url:"/SWRW/CompanyChangeJobDetail",
			datatype: "json",
			async:true,
			data:{
				"job_id":job_id,
				"job_name":$('#job_name').val(),
				"job_desc":$('#job_desc').val(),
				"job_area":$('#job_area').val(),
				"job_salary":$('#job_salary').val(),
				"job_hiringnum":$('#job_hiringnum').val(),
				"job_endtime":$('#job_endtime').val(),
				"job_state":$('#job_state').val(),
				"mode":'state'
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//发布招聘成功
					swal({
						title: language=='zh_CN'?"修改职位信息成功":"Modify Job Detail Successful",
						text: r.successMes,
						type: "success",
					},
					function(){
						window.location.reload();
					});
					setTimeout(function () {window.location.reload();}, 3000);
				}else{//发布招聘失败
					swal({
						title: language=='zh_CN'?"修改职位信息失败":"Modify Job Detail Failure",
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
}

//修改职位状态
function changeJobState(job_id,state){
	if(state<=0){//职位未通过审核
		swal({
			title: language=='zh_CN'?'职位不可用':'Position Unavailable',
			text: language=='zh_CN'?'该职位还未通过审核，无法修改职位状态！':'The position has not been approved and cannot be modified!',
			type: 'error',
		});
	}else if(state==3){//职位招聘已结束
		swal({
			title: language=='zh_CN'?'招聘已结束':'Recruitment Closed',
			text: language=='zh_CN'?'该职位招聘已结束，无法修改职位状态！\n如需继续招聘请修改职位截止时间。':
				'The job recruitment has been completed, the position status cannot be modified!\nPlease revise the deadline for further recruitment.',
			type: 'error',
		});
	}else{
		state=state==1?2:1;
		swal({
			title : language=='zh_CN'?"确定"+(state==1?"开始":"暂停")+"该职位招聘？":"Are you sure you want to "+(state==1?"start":"pause")+" the job?",
			text : language=='zh_CN'?"职位招聘期间的特殊处理。":"Special treatment during recruitment period.",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : language=='zh_CN'?"确定":"Sure",
			cancelButtonText: language=='zh_CN'?"取消":"Cancel",
			closeOnConfirm : true
		},
		function() {
			$.ajax({
				type:"post",
				url:"/SWRW/CompanyChangeJobDetail",
				datatype: "json",
				async:true,
				data:{
					"job_id":job_id,
					"job_name":$('#job_name').val(),
					"job_desc":$('#job_desc').val(),
					"job_area":$('#job_area').val(),
					"job_salary":$('#job_salary').val(),
					"job_hiringnum":$('#job_hiringnum').val(),
					"job_endtime":$('#job_endtime').val(),
					"job_state":state,
				},
				success:function(result) {
					var r = JSON.parse(result);
					if(r.isOK==true){//修改职位状态成功
						$('#job_state_str').html(getJobState(state));
						$('#job_state').val(state);
					}else{//修改职位状态失败
						swal({
							title: language=='zh_CN'?"修改职位招聘状态失败":"Modify Job State Failure",
							text: language=='zh_CN'?"遇到未知问题，请重试或联系管理员！":"Encounter unknown problem, please try again or contact administrator!",
							type: "error",
						});
					}
				},
				error:function(){
					AjaxError();
				}
			});
		});
	}
}

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