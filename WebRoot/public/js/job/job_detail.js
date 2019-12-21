//职位详情页面脚本

//申请职位
function applyJob(job_id,applicant_id,mode){
	swal({
		title : language=='zh_CN'?"确认提交简历？":"Are You Sure To Submit Resume?",
		text : language=='zh_CN'?"简历提交后不可撤销；\n提交简历后可继续完善简历。":"Resume is irrevocable after submission; \n Resume can be further improved after submission.",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : language=='zh_CN'?"确定":"Sure",
		cancelButtonText: language=='zh_CN'?"取消":"Cancel",
		closeOnConfirm : false
	},
	function() {
		$.ajax({
			type:"post",
			url:"/SWRW/ApplicantApplyJob",
			datatype: "json",
			async:true,
			data:{
				"mode":mode,
				"job_id":job_id,
				"applicant_id":applicant_id,
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//简历提交成功
					swal({
						title: language=='zh_CN'?"简历提交成功":"Submit Resume Successfully",
						text: r.successMes,
						type: "success",
					},
					function(){
						window.location.reload();
					});
					setTimeout(function () {window.location.reload();}, 2000);
				}else{//简历提交失败
					swal({
						title: language=='zh_CN'?"简历提交失败":"Submit Resume Failed",
						text: r.errorMes,
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