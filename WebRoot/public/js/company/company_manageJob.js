//企业职位管理页面脚本



















/*选择页码获取分页数据*/
var page = 1;
function SelectPage(pageNo){
	page = pageNo;
	$.ajax({
		type:"post",
		url:"/SWRW/JobPagination",
		datatype: "json", 
		async:true,
		data:{
			"pageNo":pageNo,
			"company_id":company_id,
			"job_state":5,
			"job_area":'',
			"queryStr":'',
			"deleted":0,
			"sortField":'created_at DESC',
		},
		success:function(result) {
			var r = JSON.parse(result);
			/* 渲染数据 */
			var jobs = r.jobs;
			$('#manageJobData').html('');
			if(jobs.length==0){$('#manageJobHead').html('<h2 style="text-align:center">还没有职位，快去&emsp;<a href="post_job" class="btn btn-primary">发布招聘</a></h2>');return;}
			$.each(jobs, function (index, job) {
				$('#manageJobData').append(
				'<div class="single-job d-md-flex" data-aos="fade-'+(index%2==0?"right":"left")+'">'
					+'<div class="logo">'
						+'<a href="javascript:;" title="'+(language=="zh_CN"?"更换职位头像":"Change Position Head")+'">'
							+'<img src="/SWRW/public/images/company/job/'+job.job_id+'.jpg?t='+Math.random()+'" id="job_head_'+job.job_id+'"'
							+'onclick="$(\'#changeJobHeadModal\').modal(\'show\');$(\'#manage_job_id\').val('+job.job_id+');" '
							+'alt="image" onerror="this.src=\'/SWRW/public/images/company/job/-'+parseInt(Math.random()*(12-1+1)+1,10)+'.png\';this.onerror=null"/>'
						+'</a>'
					+'</div>'
					+'<div class="job-meta">'
						+'<div class="title"><h4><a href="job_detail?job_id='+job.job_id+'"> '+IfNull(job.job_name)+'</a></h4></div>'
						+'<div class="meta-info d-flex">'
							+'<p><i class="fa fa-briefcase" aria-hidden="true"></i><a href="/SWRW/company">'+IfNull(job.company_name)+'</a></p>'
							+'<p><i class="fa fa-map-marker" aria-hidden="true"></i>'+IfNull(job.job_area)+'</p>'
							+'<p><i class="fa fa-calendar" aria-hidden="true"></i>'+DateFormat(IfNull(job.created_at),"yyyy-MM-dd HH:mm:ss")+'</p>'
						+'</div>'
					+'</div>'
					+'<div class="timing ml-auto" style="margin-right:10px;width:150px">'
						+'<a class="time-btn">'+getJobState(job.job_state)+'</a>'
					+'</div>'
					+'<div class="timing ml-auto" style="width:100px">'
						+(job.job_state==-2?'<a class="time-btn3 time-btn" href="post_job?job_id='+job.job_id+'">'+(language=="zh_CN"?"重新提交":"Resubmit")+'</a>':
						(job.job_state==-1?'<a class="time-btn2 time-btn" href="javascript:;">'+(language=="zh_CN"?"等待审核":"Auditing")+'</a>':
							'<a class="time-btn" href="job_detail?job_id='+job.job_id+'">'+(language=="zh_CN"?"详情":"Detail")+'</a>'))
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