//企业浏览简历页面脚本



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
			if(basicInfos.length==0){$('#browseResumeData').html('<h2 class="pt-100 pb-100" style="text-align:center">'+(language=="zh_CN"?"未收到简历":"No resume received")+'</h2>');}
			$.each(basicInfos, function (index, basicInfo) {
				$('#browseResumeData').append(
				'<div class="single-candidate d-md-flex" data-aos="fade-'+(index%2==0?"left":"right")+'">'
					+'<div class="logo">'
						+'<a href="/SWRW/company/job/resume_detail?applicant_id='+basicInfo.basicinfo_id+'" title='+(language=="zh_CN"?"查看简历":"View Resume")+'>'
							+'<img src="/SWRW/public/images/applicant/'+basicInfo.head_shot+'?t='+Math.random()+'"'
							+'alt="image" onerror="this.src=\'/SWRW/public/images/applicant/anonymous.png\';this.onerror=null"/>'
						+'</a>'
					+'</div>'
					+'<div class="candidate-meta">'
						+'<div class="title"><h4><a href="/SWRW/company/job/resume_detail?applicant_id='+basicInfo.basicinfo_id+'"> '+IfNull(basicInfo.realname)+'</a></h4></div>'
						+'<div class="meta-info d-flex">'
							+'<p><i class="fa fa-male" aria-hidden="true"></i>'+IfNull(basicInfo.gender)+'</a></p>'
							+'<p><i class="fa fa-genderless" aria-hidden="true"></i>'+DateToAge(IfNull(basicInfo.birthday))+'</p>'
							+'<p><i class="fa fa-map-marker" aria-hidden="true"></i>'+IfNull(basicInfo.current_loc)+'</p>'
						+'</div>'
					+'</div>'
					+'<div class="timing ml-auto">'
						+'<a class="time-btn1 time-btn" href="#">'+(language=="zh_CN"?"安排面试":"Hire Me")+'</a> '
						+'<a class="time-btn2 time-btn" href="#">'+(language=="zh_CN"?"候选":"Shortlist")+'</a> '
						+'<a class="time-btn3 time-btn" href="#">'+(language=="zh_CN"?"驳回":"Rejected")+'</a>'
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