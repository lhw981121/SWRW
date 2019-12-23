//管理员待审核企业页面脚本

/*<!-- 待审框、审核意见框、企业详情框切换 -->*/
function passCompanyPanel(id){
	$("#passCompany_id").val(id);
	$("#waitAuditCompanyPanel").hide();
	$("#viewCompanyPanel").hide();
	$("#passOpinionPanel").show();
}
$("#passBackBtn").click(function(){
	$("#passOpinionPanel").hide();
	$("#waitAuditCompanyPanel").show();
});
function vetoCompanyPanel(id){
	$("#vetoCompany_id").val(id);
	$("#waitAuditCompanyPanel").hide();
	$("#viewCompanyPanel").hide();
	$("#vetoOpinionPanel").show();
}
$("#vetoBackBtn").click(function(){
	$("#vetoOpinionPanel").hide();
	$("#waitAuditCompanyPanel").show();
});
function viewCompany(id){
	$("#waitAuditCompanyPanel").hide();
	$("#viewCompanyPanel").show();
	$.ajax({
		type:"post",
		url:"/SWRW/CompanyQueryByID",
		datatype: "json", 
		async:true,
		data:{
			"company_id":id,
		},
		success:function(result) {
			var r = JSON.parse(result);
			var company = r.company;
			$("#company_name").html(company.company_name);
			$("#company_legal").html(company.company_legal);
			$("#company_area").html(company.company_area);
			$("#company_size").html(company.company_size);
			$("#company_type").html(company.company_type);
			$("#company_brief").html(company.company_brief);
			$("#company_state").html(getCompanyState(company.company_state));
			$("#company_audit_state").html(getAuditState(company.company_state));
			$("#company_license").attr('src','/SWRW/public/images/company/license/'+company.company_license+'?t='+Math.random());
			$("#company_created").html(DateFormat(IfNull(company.created_at),"yyyy-MM-dd HH:mm:ss"));
			$("#company_updated").html(DateFormat(IfNull(company.updated_at),"yyyy-MM-dd HH:mm:ss"));
			$("#viewPassCompanyBtn").attr("onclick","passCompanyPanel("+company.company_id+")");
			$("#viewVetoCompanyBtn").attr("onclick","vetoCompanyPanel("+company.company_id+")");
		},
		error:function(){
			AjaxError();
		}
	});
}
$("#viewBackBtn").click(function(){
	$("#viewCompanyPanel").hide();
	$("#waitAuditCompanyPanel").show();
});

//通过审核
function passCompany(){
	swal({
		title : language=='zh_CN'?"确定通过该企业认证？":"Make sure to pass the enterprise certification?",
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
			url:"/SWRW/AdminPassCompany",
			datatype: "json", 
			async:false,
			data:{
				"company_id":$('#passCompany_id').val(),
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
						$("#viewCompanyPanel").hide();
						$("#passOpinionPanel").hide();
						$("#waitAuditCompanyPanel").show();
						SelectPage(page);
					});
					setTimeout(function () {
						$("#viewCompanyPanel").hide();
						$("#passOpinionPanel").hide();
						$("#waitAuditCompanyPanel").show();
						SelectPage(page);
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
function vetoCompany(){
	swal({
		title : language=='zh_CN'?"确定否决该企业认证？":"Make sure to veto the enterprise certification?",
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
			url:"/SWRW/AdminVetoCompany",
			datatype: "json", 
			async:false,
			data:{
				"company_id":$('#vetoCompany_id').val(),
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
						$("#viewCompanyPanel").hide();
						$("#vetoOpinionPanel").hide();
						$("#waitAuditCompanyPanel").show();
						SelectPage(page);
					});
					setTimeout(function () {
						$("#viewCompanyPanel").hide();
						$("#vetoOpinionPanel").hide();
						$("#waitAuditCompanyPanel").show();
						SelectPage(page);
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
	$('#waitAuditCompanyData').append('<td colspan="7"><h3 style="text-align:center">'+(language=='zh_CN'?'数据加载中。。。':'Data loading...')+'</h3></td>');
	page = pageNo;
	$.ajax({
		type:"post",
		url:"/SWRW/CompanyPagination",
		datatype: "json", 
		async:true,
		data:{
			"pageNo":pageNo,
			"company_state":-1,
			"company_area":'',
			"company_size":'',
			"company_type":'',
			"queryStr":'',
			"deleted":0,
			"sortField":'updated_at DESC',
		},
		success:function(result) {
			var r = JSON.parse(result);
			/* 渲染数据 */
			var company = r.company;
			$('#waitAuditCompanyData').html('');
			if(company.length==0){$('#waitAuditCompanyData').html('<td colspan="7"><h3 style="text-align:center">'+(language=='zh_CN'?'没有数据':'No Data')+'</h3></td>');}
			$.each(company, function (index, com) {
				$('#waitAuditCompanyData').append(
				'<tr>'
				+'<td style="vertical-align:middle;">'
				+'<a href="javascript:;" onclick="passCompanyPanel('+com.company_id+');"><span class="label label-success"><i class="fa fa-check-circle"></i> 通过</span></a> '
				+'<a href="javascript:;" onclick="vetoCompanyPanel('+com.company_id+');"><span class="label label-danger"><i class="fa fa-times-circle"></i> 否决</span></a> '
				+'<a href="javascript:;" onclick="viewCompany('+com.company_id+');"><span class="label label-primary"><i class="fa fa-sticky-note-o"></i> 详情</span></a>'
				+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(com.company_name)+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(com.company_legal)+'</td>'
				+'<td style="width:15%">'+IfNull(com.company_area)+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(com.company_type)+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(com.company_size)+'</td>'
				+'<td style="vertical-align:middle;">'+DateFormat(IfNull(com.updated_at),"yyyy-MM-dd HH:mm:ss")+'</td>'
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