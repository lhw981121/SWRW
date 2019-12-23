//企业浏览简历页面脚本

//通知求职者面试
function HireApplicant(apply_id){
	swal({
		title : language=='zh_CN'?"操作提示":"Operation Tips",
		text : language=='zh_CN'?"确定通知该求职者面试？":"Make sure to notify the candidate for an interview?",
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
			url:"/SWRW/HireApplicant",
			datatype: "json", 
			async:false,
			data:{
				"apply_id":apply_id,
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//操作成功
					swal({
						title: language=='zh_CN'?"操作成功":"Operate Successfully",
						text: r.successMes,
						type: "success",
					},
					function(){
						SelectPage(page);
					});
					setTimeout(function () {SelectPage(page);}, 2000);
				}else{//操作失败
					swal({
						title: language=='zh_CN'?"操作失败":"Operate Failed",
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

//候选求职者简历
function ShortlistApplicant(apply_id){
	$.ajax({
		type:"post",
		url:"/SWRW/ShortlistApplicant",
		datatype: "json", 
		async:false,
		data:{
			"apply_id":apply_id,
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//操作成功
				SelectPage(page);
			}else{//操作失败
				swal({
					title: language=='zh_CN'?"操作失败":"Operate Failed",
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

//驳回求职者简历
function RejectedApplicant(apply_id){
	swal({
		title : language=='zh_CN'?"操作提示":"Operation Tips",
		text : language=='zh_CN'?"确定驳回该求职者的简历？":"Make sure to reject the candidate's resume?",
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
			url:"/SWRW/RejectedApplicant",
			datatype: "json", 
			async:false,
			data:{
				"apply_id":apply_id,
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//操作成功
					swal({
						title: language=='zh_CN'?"操作成功":"Operate Successfully",
						text: r.successMes,
						type: "success",
					},
					function(){
						SelectPage(page);
					});
					setTimeout(function () {SelectPage(page);}, 2000);
				}else{//操作失败
					swal({
						title: language=='zh_CN'?"操作失败":"Operate Failed",
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

/*选择页码获取分页数据*/
var page = 1;
function SelectPage(pageNo){
	$('#browseResumeData').append('<h2 class="pt-100 pb-100" style="text-align:center">'+(language=="zh_CN"?"数据加载中。。。":"Data loading...")+'</h2>')
	page = pageNo;
	$.ajax({
		type:"post",
		url:"/SWRW/JobApplyPagination",
		datatype: "json",
		async:true,
		data:{
			"pageNo":pageNo,
			"job_id":job_id,
			"company_id":company_id,
			"applicant_id":0,
			"apply_state":1,
			"deleted":0,
			"sortField":'updated_at',
		},
		success:function(result) {
			var r = JSON.parse(result);
			/* 渲染数据 */
			var basicInfos = r.basicInfos;
			$('#browseResumeData').html('');
			if(basicInfos.length==0){$('#browseResumeData').html('<h2 class="pt-100 pb-100" style="text-align:center">'
			+(language=="zh_CN"?"未收到简历":"No resume received")+'</h2>');}
			$.each(basicInfos, function (index, basicInfo) {
				$('#browseResumeData').append(
				'<div class="single-candidate d-md-flex" data-aos="fade-'+(index%2==0?"left":"right")+'">'
					+'<div class="logo">'
						+'<a href="/SWRW/company/job/resume_detail?applicant_id='+basicInfo.basicinfo_id+'&job_id='+basicInfo.current_loc+'" title='
						+(language=="zh_CN"?"查看简历":"View Resume")+'>'
							+'<img src="/SWRW/public/images/applicant/'+basicInfo.head_shot+'?t='+Math.random()+'"'
							+'alt="image" onerror="this.src=\'/SWRW/public/images/applicant/anonymous.png\';this.onerror=null"/>'
						+'</a>'
					+'</div>'
					+'<div class="candidate-meta">'
						+'<div class="title"><h4><a href="/SWRW/company/job/resume_detail?applicant_id='+basicInfo.basicinfo_id+'&job_id='+basicInfo.current_loc+'"> '
						+IfNull(basicInfo.realname)+'</a></h4></div>'
						+'<div class="meta-info d-flex">'
							+'<p><i class="fa fa-male" aria-hidden="true"></i>'+IfNull(basicInfo.gender)+'</a></p>'
							+'<p><i class="fa fa-genderless" aria-hidden="true"></i>'+DateToAge(IfNull(basicInfo.birthday))+'</p>'
							+'<p><i class="fa fa-share" aria-hidden="true"></i>'+IfNull(basicInfo.job_intension)+'</p>'
							+'<p><i class="fa fa-clock-o" aria-hidden="true"></i>'+FromTodayFormat(IfNull(basicInfo.created_at))+'</p>'
						+'</div>'
					+'</div>'
					+'<div class="timing ml-auto">'
						+'<a class="time-btn1 time-btn" href="javascript:;" onclick="HireApplicant('+basicInfo.resident_loc+')">'+(language=="zh_CN"?"安排面试":"Hire Me")+'</a> '
						+'<a class="time-btn2 time-btn" href="avascript:;" onclick="ShortlistApplicant('+basicInfo.resident_loc+')">'+(language=="zh_CN"?"候选":"Shortlist")+'</a> '
						+'<a class="time-btn3 time-btn" href="avascript:;" onclick="RejectedApplicant('+basicInfo.resident_loc+')">'+(language=="zh_CN"?"驳回":"Rejected")+'</a>'
					+'</div>'
				+'</div>');
			});
			/* 渲染页码 */
			RenderPagination(r.pagination);
		},
		error:function(){
			AjaxError();
		}
	});
}
SelectPage(page);