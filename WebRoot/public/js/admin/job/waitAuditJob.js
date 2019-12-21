//管理员待审核职位页面脚本

/*<!-- 待审框、审核意见框、职位详情框切换 -->*/
function passJobPanel(id){
	$("#passJob_id").val(id);
	$("#waitAuditJobPanel").hide();
	$("#viewJobPanel").hide();
	$("#passOpinionPanel").show();
}
$("#passBackBtn").click(function(){
	$("#passOpinionPanel").hide();
	$("#waitAuditJobPanel").show();
});
function vetoJobPanel(id){
	$("#vetoJob_id").val(id);
	$("#waitAuditJobPanel").hide();
	$("#viewJobPanel").hide();
	$("#vetoOpinionPanel").show();
}
$("#vetoBackBtn").click(function(){
	$("#vetoOpinionPanel").hide();
	$("#waitAuditJobPanel").show();
});
function viewJob(id){
	$("#waitAuditJobPanel").hide();
	$("#viewJobPanel").show();
	$.ajax({
		type:"post",
		url:"/SWRW/JobQueryByID",
		datatype: "json", 
		async:true,
		data:{
			"job_id":id,
		},
		success:function(result) {
			var r = JSON.parse(result);
			var job = r.job;
			$("#job_name").html(job.job_name);
			$("#company_name").html(job.company_name);
			$("#job_hiringnum").html(job.job_hiringnum);
			$("#job_salary").html(job.job_salary);
			$("#job_area").html(job.job_area);
			$("#job_desc").html(job.job_desc);
			$("#job_state").html(getCompanyState(job.job_state));
			$("#job_audit_state").html(getAuditState(job.job_state));
			$("#job_created").html(DateFormat(IfNull(job.created_at),"yyyy-MM-dd HH:mm:ss"));
			$("#job_updated").html(DateFormat(IfNull(job.updated_at),"yyyy-MM-dd HH:mm:ss"));
			$("#viewPassJobBtn").attr("onclick","passJobPanel("+job.job_id+")");
			$("#viewVetoJobBtn").attr("onclick","vetoJobPanel("+job.job_id+")");
		},
		error:function(){
			AjaxError();
		}
	});
}
$("#viewBackBtn").click(function(){
	$("#viewJobPanel").hide();
	$("#waitAuditJobPanel").show();
});

//通过审核
function passJob(){
	swal({
		title : language=='zh_CN'?"确定通过该职位发布？":"Are you sure to post through the position?",
		text : '',
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
			url:"/SWRW/AdminPassJob",
			datatype: "json", 
			async:false,
			data:{
				"job_id":$('#passJob_id').val(),
				"passOpinion":$('#passOpinion').val()
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
						$("#viewJobPanel").hide();
						$("#passOpinionPanel").hide();
						$("#waitAuditJobPanel").show();
						SelectPage(page);
						$("#passOpinion").val("");
					});
					setTimeout(function () {
						$("#viewJobPanel").hide();
						$("#passOpinionPanel").hide();
						$("#waitAuditJobPanel").show();
						SelectPage(page);
						$("#passOpinion").val("");
					}, 2000);
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
//否决审核
function vetoJob(){
	swal({
		title : language=='zh_CN'?"确定否决该职位发布？":"Are you sure to veto the position?",
		text : '',
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
			url:"/SWRW/AdminVetoJob",
			datatype: "json", 
			async:false,
			data:{
				"job_id":$('#vetoJob_id').val(),
				"vetoOpinion":$('#vetoOpinion').val()
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
						$("#viewJobPanel").hide();
						$("#vetoOpinionPanel").hide();
						$("#waitAuditJobPanel").show();
						SelectPage(page);
						$("#vetoOpinion").val("");
					});
					setTimeout(function () {
						$("#viewJobPanel").hide();
						$("#vetoOpinionPanel").hide();
						$("#waitAuditJobPanel").show();
						SelectPage(page);
						$("#vetoOpinion").val("");
					}, 2000);
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
	$('#waitAuditJobData').append('<td colspan="5"><h3 style="text-align:center">'+(language=='zh_CN'?'数据加载中。。。':'Data loading...')+'</h3></td>');
	page = pageNo;
	$.ajax({
		type:"post",
		url:"/SWRW/JobPagination",
		datatype: "json", 
		async:true,
		data:{
			"pageNo":pageNo,
			"company_id":0,
			"job_state":-1,
			"job_area":'',
			"queryStr":'',
			"deleted":0,
			"sortField":'updated_at DESC',
		},
		success:function(result) {
			var r = JSON.parse(result);
			/* 渲染数据 */
			var jobs = r.jobs;
			$('#waitAuditJobData').html('');
			if(jobs.length==0){$('#waitAuditJobData').html('<td colspan="5"><h3 style="text-align:center">'+(language=='zh_CN'?'没有数据':'No Data')+'</h3></td>');}
			$.each(jobs, function (index, job) {
				$('#waitAuditJobData').append(
				'<tr>'
				+'<td style="vertical-align:middle;">'
				+'<a href="javascript:;" onclick="passJobPanel('+job.job_id+');"><span class="label label-success"><i class="fa fa-check-circle"></i> 通过</span></a> '
				+'<a href="javascript:;" onclick="vetoJobPanel('+job.job_id+');"><span class="label label-danger"><i class="fa fa-times-circle"></i> 否决</span></a> '
				+'<a href="javascript:;" onclick="viewJob('+job.job_id+');"><span class="label label-primary"><i class="fa fa-sticky-note-o"></i> 详情</span></a>'
				+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(job.job_name)+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(job.company_name)+'</td>'
				+'<td style="width:20%">'+IfNull(job.job_area)+'</td>'
				+'<td style="vertical-align:middle;">'+DateFormat(IfNull(job.updated_at),"yyyy-MM-dd HH:mm:ss")+'</td>'
				+'</tr>');
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