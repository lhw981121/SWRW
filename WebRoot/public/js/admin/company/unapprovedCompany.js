//审核未通过企业资质认证脚本

/*企业详情框切换*/
function viewCompany(id){
	$("#unapprovedCompanyPanel").hide();
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
	$("#unapprovedCompanyPanel").show();
});


/*选择页码获取分页数据*/
var page = 1;
function SelectPage(pageNo){
	$('#unapprovedCompanyData').append('<td colspan="7"><h3 style="text-align:center">'+(language=='zh_CN'?'数据加载中。。。':'Data loading...')+'</h3></td>');
	page = pageNo;
	$.ajax({
		type:"post",
		url:"/SWRW/CompanyPagination",
		datatype: "json", 
		async:true,
		data:{
			"pageNo":pageNo,
			"company_state":-2,
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
			$('#unapprovedCompanyData').html('');
			if(company.length==0){$('#unapprovedCompanyData').html('<td colspan="7"><h3 style="text-align:center">'+(language=='zh_CN'?'没有数据':'No Data')+'</h3></td>');}
			$.each(company, function (index, com) {
				$('#unapprovedCompanyData').append(
				'<tr>'
				+'<td style="vertical-align:middle;">'
				+'<a href="javascript:;" onclick="viewCompany('+com.company_id+');"><span class="label label-primary"><i class="fa fa-sticky-note-o"></i> 详情</span></a>'
				+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(com.company_name)+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(com.company_legal)+'</td>'
				+'<td style="width:15%">'+IfNull(com.company_area)+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(com.company_type)+'</td>'
				+'<td style="vertical-align:middle;">'+IfNull(com.company_size)+'</td>'
				+'<td style="vertical-align:middle;">'+DateFormat(IfNull(com.created_at),"yyyy-MM-dd HH:mm:ss")+'</td>'
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