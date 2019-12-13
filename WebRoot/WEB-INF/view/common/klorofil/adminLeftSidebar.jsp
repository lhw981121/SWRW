<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- LEFT SIDEBAR -->
<div id="sidebar-nav" class="sidebar">
	<div class="sidebar-scroll">
		<nav>
			<ul class="nav">

				<li><a href="/SWRW/admin" id="admin" class=""><i class="lnr lnr-home"></i> <span>主页</span></a></li>

				<li>
					<a href="#company_audit" data-toggle="collapse" id="#company_audit" class="collapsed">
					<i class="fa fa-building-o"></i> <span>企业认证审核</span><i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="company_audit" class="collapse">
						<ul class="nav">
							<li><a href="/SWRW/admin/audit/company/wait_audit_company" id="wait_audit_company" class="">待审核企业</a></li>
							<li><a href="/SWRW/admin/audit/company/approved_company" id="approved_company" class="">审核通过企业</a></li>
							<li><a href="/SWRW/admin/audit/company/unapproved_company" id="unapproved_company" class="">审核未通过企业</a></li>
						</ul>
					</div>
				</li>
				
				<li>
					<a href="#job_audit" data-toggle="collapse" id="#job_audit" class="collapsed">
					<i class="fa fa-clipboard"></i> <span>职位审核</span><i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="job_audit" class="collapse">
						<ul class="nav">
							<li><a href="/SWRW/admin/audit/job/wait_audit_job" id="wait_audit_job" class="">待审核职位</a></li>
							<li><a href="/SWRW/admin/audit/job/approved_job" id="approved_job" class="">审核通过职位</a></li>
							<li><a href="/SWRW/admin/audit/job/unapproved_job" id="unapproved_job" class="">审核未通过职位</a></li>
						</ul>
					</div>
				</li>

				<li><a href="#manageInfo" data-toggle="collapse"
					id="#manageInfo" class="collapsed"> <i class="lnr lnr-database"></i>
						<span>信息管理</span><i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="manageInfo" class="collapse">
						<ul class="nav">
							<li><a
								href="/CSMS/SWZJ/admin/manageInfo/Student/studentInfo.jsp"
								id="Student" class="">学生信息管理</a></li>
							<li><a
								href="/CSMS/SWZJ/admin/manageInfo/CDTopic/cdtopicInfo.jsp"
								id="CDTopic" class="">课题信息管理</a></li>
							<li><a
								href="/CSMS/SWZJ/admin/manageInfo/Teacher/teacherInfo.jsp"
								id="Teacher" class="">教师信息管理</a></li>
							<li><a href="#" id="Class" class="">班级信息管理</a></li>
							<li><a href="#" id="Major" class="">专业信息管理</a></li>
							<li><a href="#" id="College" class="">学院信息管理</a></li>
						</ul>
					</div></li>

			</ul>
		</nav>
	</div>
</div>
<!-- END LEFT SIDEBAR -->

<!-- 侧边栏选中保持 -->
<script>
	var pathname = window.location.pathname;
	
	//主页
	if(pathname.indexOf("SWRW/admin/") == -1){
		document.getElementById("admin").className = "active";
	}
	
	//管理员企业认证审核菜单
	if (pathname.indexOf("audit/company/") != -1) {
		document.getElementById("#company_audit").className = "active";
		document.getElementById("company_audit").className = "collapse in";
		if (pathname.indexOf("wait_audit_company") != -1) {
			document.getElementById("wait_audit_company").className = "active";
		} else if (pathname.indexOf("unapproved_company") != -1) {
			document.getElementById("unapproved_company").className = "active";
		} else if (pathname.indexOf("approved_company") != -1) {
			document.getElementById("approved_company").className = "active";
		}
	}

	//管理员信息管理菜单
	if (pathname.indexOf("manageInfo") != -1) {
		document.getElementById("#manageInfo").className = "active";
		document.getElementById("manageInfo").className = "collapse in";
		if (pathname.indexOf("Student") != -1) {
			document.getElementById("Student").className = "active";
		} else if (pathname.indexOf("CDTopic") != -1) {
			document.getElementById("CDTopic").className = "active";
		} else if (pathname.indexOf("Teacher") != -1) {
			document.getElementById("Teacher").className = "active";
		} else if (pathname.indexOf("Class") != -1) {
			document.getElementById("Class").className = "active";
		} else if (pathname.indexOf("Major") != -1) {
			document.getElementById("Major").className = "active";
		} else if (pathname.indexOf("College") != -1) {
			document.getElementById("College").className = "active";
		}
	}


	//管理员消息菜单
	if (pathname.indexOf("message") != -1) {
		document.getElementById("#message").className = "active";
		document.getElementById("message").className = "collapse in";
		if (pathname.indexOf("myMessage") != -1) {
			document.getElementById("myMessage").className = "active";
		} else if (pathname.indexOf("sendMessage") != -1) {
			document.getElementById("sendMessage").className = "active";
		}
	}
</script>