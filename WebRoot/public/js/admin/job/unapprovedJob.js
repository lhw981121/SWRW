//审核未通过职位页面脚本

/*职位详情框切换*/
function viewJob(id){
	$("#unapprovedJobPanel").hide();
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
	$("#unapprovedJobPanel").show();
});


/*选择页码获取分页数据*/
var page = 1;
function SelectPage(pageNo){
	$('#unapprovedJobData').append('<td colspan="5"><h3 style="text-align:center">'+(language=='zh_CN'?'数据加载中。。。':'Data loading...')+'</h3></td>');
	page = pageNo;
	$.ajax({
		type:"post",
		url:"/SWRW/JobPagination",
		datatype: "json", 
		async:true,
		data:{
			"pageNo":pageNo,
			"company_id":0,
			"job_state":-2,
			"job_area":'',
			"queryStr":'',
			"deleted":0,
			"sortField":'updated_at DESC',
		},
		success:function(result) {
			var r = JSON.parse(result);
			/* 渲染数据 */
			var jobs = r.jobs;
			$('#unapprovedJobData').html('');
			if(jobs.length==0){$('#unapprovedJobData').html('<td colspan="5"><h3 style="text-align:center">'+(language=='zh_CN'?'没有数据':'No Data')+'</h3></td>');}
			$.each(jobs, function (index, job) {
				$('#unapprovedJobData').append(
				'<tr>'
				+'<td style="vertical-align:middle;">'
				+'<a href="javascript:;" onclick="viewJob('+job.job_id+');"><span class="label label-primary"><i class="fa fa-sticky-note-o"></i> 详情</span></a>'
				+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(job.job_name)+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(job.company_name)+'</td>'
				+'<td style="width:20%">'+IfNull(job.job_area)+'</td>'
				+'<td style="vertical-align:middle;">'+DateFormat(IfNull(job.created_at),"yyyy-MM-dd HH:mm:ss")+'</td>'
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