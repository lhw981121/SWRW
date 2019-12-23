<%@ page language="java" import="java.util.*,com.swzj.swrw.util.COMUtil" pageEncoding="utf-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/klorofil/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 头部 -->
<%@include file="/WEB-INF/view/common/klorofil/head.jsp"%>

</head>

<body>
<div id="wrapper">
<!-- WRAPPER -->
<!-- 导航栏 -->
<%@include file="/WEB-INF/view/common/klorofil/navbar.jsp"%>
<!-- 左侧边栏 -->
<%@include file="/WEB-INF/view/common/klorofil/adminLeftSidebar.jsp"%>

<!-- 内容区域 -->
<div class="main">
<!-- MAIN CONTENT -->
<div class="main-content">
	<!-- 审核通过企业 -->
	<div class="panel" id="approvedCompanyPanel">
		<div class="panel-heading">
			<h3 class="panel-title">审核通过企业资质认证</h3>
		</div>
		<div class="panel-heading">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-8 col-sm-8 col-lg-8">
						<form class="form-inline" id="searchForm" role="form"
							method="get" action="">
							<div class="form-group">
								<span class="panel-title">信息查询</span>
							</div>
						</form>
					</div>
					<div class="col-md-4 col-sm-4 col-lg-4">
						<form role="form" class="form-horizontal" method="get"
							id="searchCDTopic" action="">
							<div class="input-group">
								<input class="form-control" name="queryStr" type="text"
									id="queryStr" value="" placeholder="企业名称、所在地"> <input
									type="hidden" name="selectPages" value="">
								<span class="input-group-btn"><a onclick="return searchCDTopic()" class="btn btn-primary">搜索</a></span>
							</div>
							<script type="text/javascript">
								function searchCDTopic() {
									if (document.getElementById("queryStr").value.length != 0) {
										document.getElementById("searchCDTopic").submit();
										return true;
									} else {
										return false;
									}
								}
							</script>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>操作管理</th>
						<th>企业名称</th>
						<th>企业法人</th>
						<th>企业所在地</th>
						<th>企业性质</th>
						<th>企业规模</th>
						<th>企业状态</th>
					</tr>
				</thead>
				<tbody id="approvedCompanyData">
				</tbody>
			</table>
		</div>

		<!-- 分页 -->
		<%@include file="/WEB-INF/view/common/klorofil/pagination.jsp"%>
	</div>

	<!-- 详情 -->
	<div class="panel" id="viewCompanyPanel" style="display:none">
		<div class="panel-heading">
			<h3 class="panel-title">企业详情</h3>
		</div>
		<div class="panel-body">
			<table class="table table-bordered table-striped table-hover ">
				<colgroup>
					<col style="width:20%">
					<col style="width:80%">
				</colgroup>
				<tbody>
					<tr>
						<td style="text-align:center;">企业名称</td>
						<td id="company_name"></td>
					</tr>
					<tr>
						<td style="text-align:center;">企业法人</td>
						<td id="company_legal"></td>
					</tr>
					<tr>
						<td style="text-align:center;">企业所在地</td>
						<td id="company_area"></td>
					</tr>
					<tr>
						<td style="text-align:center;">企业规模</td>
						<td id="company_size"></td>
					</tr>
					<tr>
						<td style="text-align:center;">企业性质</td>
						<td id="company_type"></td>
					</tr>
					<tr>
						<td style="text-align:center;vertical-align:middle;">企业简介</td>
						<td id="company_brief"></td>
					</tr>
					<tr>
						<td style="text-align:center;">企业状态</td>
						<td id="company_state"></td>
					</tr>
					<tr>
						<td style="text-align:center;">审核状态</td>
						<td id="company_audit_state"></td>
					</tr>
					<tr>
						<td style="text-align:center;">营业执照</td>
						<td><a href="javascript:;" onclick="$('#companyLicenseModal').modal('show')">查看企业营业执照</a></td>
					</tr>
					<tr>
						<td style="text-align:center;">企业注册时间</td>
						<td id="company_created"></td>
					</tr>
					<tr>
						<td style="text-align:center;">最近修改时间</td>
						<td id="company_updated"></td>
					</tr>
				</tbody>
			</table>
			<div class="form-group">
				<div class="text-center">
					<button type="button" class="btn btn-primary" id="viewBackBtn"><i class="fa fa-undo"></i> 返回</button>
				</div>
			</div>
		</div>
	</div>
		
</div>
<!-- END MAIN CONTENT -->
</div>
<!-- END 内容区域 -->

<!-- 企业营业执照模态框 -->
<div class="modal fade" id="companyLicenseModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>     
				<h4 class="modal-title text-primary" style="margin-top:-22px"><i class="fa fa-picture-o"></i>&nbsp;营业执照</h4> 
			</div>
			<div class="modal-body">
				<div class="container" style="width:100%">
					<img id="company_license" src="" alt="未上传企业营业执照" height="auto" width="100%">
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Close" /></button>
			</div>
		</div>
	</div>
</div>

<!-- 页尾 -->
<%@include file="/WEB-INF/view/common/klorofil/foot.jsp"%>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<%@include file="/WEB-INF/view/common/klorofil/javaScript.jsp"%>
<!-- 自定义脚本 -->
<script src="/SWRW/public/js/admin/company/approvedCompany.js"></script>

</body>
</html>

