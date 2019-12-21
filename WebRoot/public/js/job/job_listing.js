//职位列表页面脚本



/*选择页码获取分页数据*/
var page = 1;
function SelectPage(pageNo){
	$('#JobListingData').append('<h2 class="pt-100 pb-100" style="text-align:center">'+(language=="zh_CN"?"数据加载中。。。":"Data loading...")+'</h2>')
	page = pageNo;
	$.ajax({
		type:"post",
		url:"/SWRW/JobListingData",
		datatype: "json", 
		async:true,
		data:{
			"pageNo":pageNo,
			"job_state":$('#job_state').val(),
			"job_area":$('#job_area').val(),
			"company_state":1,
			"company_size":$('#company_size').val(),
			"company_type":$('#company_type').val(),
			"queryStr":$('#queryStr').val(),
			"sortField":'job_state,updated_at DESC',
		},
		success:function(result) {
			var r = JSON.parse(result);
			/* 渲染数据 */
			var jobs = r.jobs;
			$('#JobListingData').html('');
			if(jobs.length==0){$('#JobListingData').html('<h2 class="pt-100 pb-100" style="text-align:center">'+(language=="zh_CN"?"未查询到职位":"No position found")+'</h2>');}
			$.each(jobs, function (index, job) {
				$('#JobListingData').append(
				'<div class="single-job d-md-flex" data-aos="fade-'+(index%2==0?"right":"left")+'">'
					+'<div class="logo">'
						+'<a href="/SWRW/job/job_detail?job_id='+job.job_id+'">'
							+'<img src="/SWRW/public/images/company/job/'+job.job_id+'.jpg?t='+Math.random()+'"'
							+'alt="image" onerror="this.src=\'/SWRW/public/images/company/job/-'+parseInt(Math.random()*(12-1+1)+1,10)+'.png\';this.onerror=null"/>'
						+'</a>'
					+'</div>'
					+'<div class="job-meta">'
						+'<div class="title"><h4><a href="/SWRW/job/job_detail?job_id='+job.job_id+'"> '+IfNull(job.job_name)+'</a></h4></div>'
						+'<div class="meta-info d-flex">'
							+'<p><i class="fa fa-briefcase" aria-hidden="true"></i><a href="/SWRW/job/company_detail?company_id='+job.company_id+'">'+IfNull(job.company_name)+'</a></p>'
							+'<p><i class="fa fa-map-marker" aria-hidden="true"></i>'+IfNull(job.job_area)+'</p>'
							+'<p><i class="fa fa-calendar" aria-hidden="true"></i>'+FromTodayFormat(IfNull(job.created_at))+'</p>'
						+'</div>'
					+'</div>'
					+'<div class="timing ml-auto" style="margin-right:10px;width:150px">'
						+'<a class="time-btn">'+getJobState(job.job_state)+'</a>'
					+'</div>'
					+'<div class="timing ml-auto" style="width:100px">'
						+'<a class="time-btn" href="/SWRW/job/job_detail?job_id='+job.job_id+'">'+(language=="zh_CN"?"详情":"Detail")+'</a>'
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