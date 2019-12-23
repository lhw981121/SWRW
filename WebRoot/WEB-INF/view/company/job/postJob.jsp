<%@ page language="java" import="java.util.*,com.swzj.swrw.util.COMUtil" pageEncoding="UTF-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/swrw/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 公共头部 -->
<%@include file="/WEB-INF/view/common/swrw/head.jsp"%>
<!-- 职位信息验证JS -->
<script src="/SWRW/public/js/company/company_jobInfo.js"></script>

</head>
<body>
	<!-- start-公共页眉 -->
	<%@include file="/WEB-INF/view/common/swrw/header.jsp"%>
	<!-- end-公共页眉 -->

	<!---start header-banner -->
	<div class="header-banner clearfix"
		style="background-image:url(/SWRW/public/assets/images/header-bg.jpg)">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="banner-text text-center">
						<h1>
							<%if(request.getAttribute("resubmit")==null){%><fmt:message key="PostNewJob"/><%}else{%><fmt:message key="ResubmitJob"/><%}%>
						</h1>
						<ul class="breadcumb list-inline">
							<li class="list-inline-item"><a href="/SWRW/index"><fmt:message key="Home" /></a></li>
							<li class="list-inline-item"><a href="/SWRW/company"><fmt:message key="Company" /></a></li>
							<li class="list-inline-item"><fmt:message key="PostJob" /></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!---end header-banner -->

	<main class="main-content-area clearfix">
		<div class="post-job-area pb-100 pt-100">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="post-form form-bg">
							<div class="info-title mb-3">
								<h3><%if(request.getAttribute("resubmit")==null){%><fmt:message key="PostNewJob"/><%}else{%><fmt:message key="ResubmitJob"/><%}%></h3>
							</div>
							<div class="row">
								<div class="col-md-12">
									<form>
										<!-- 职位名称 -->
										<div class="form-row">
											<div class="form-group col-md-12">
												<label for="job_name"><fmt:message key="JobName" /></label>
												<input type="text" class="form-control" id="job_name" value="${job.getName() }"
													placeholder="<fmt:message key="JobNameTip" />">
											</div>
										</div>
										<!-- 职位描述 -->
										<div class="form-row">
											<div class="form-group col-md-12">
												<label for="job_desc"><fmt:message key="JobDescription" /></label>
												<textarea class="form-control" id="job_desc" rows="10"
												placeholder="<fmt:message key="JobDescriptionTip" />">${job.getDesc() }</textarea>
											</div>
										</div>
										<!-- 职位所在地 -->
										<div class="form-row">
											<div class="col-md-12">
												<label><fmt:message key="JobArea" /></label>
												<input type="hidden" id="job_area" name="area" value="${job.getArea() }">
											</div>
											<div class="col-md-12 row">
												<div class="col-md-4">
													<select id="citySelect1" name="" class="form-control custom-select" onblur="updateJobArea()">
														<option value=""><fmt:message key="AreaTip1" /></option>
													</select>
												</div>
												<div class="col-md-4">
													<select id="citySelect2" name="" class="form-control custom-select" onblur="updateJobArea()">
														<option value=""><fmt:message key="AreaTip2" /></option>
													</select>
												</div>
												<div class="col-md-4 text-right">
													<select id="citySelect3" name="" class="form-control custom-select" onblur="updateJobArea()">
														<option value=""><fmt:message key="AreaTip3" /></option>
													</select>
												</div>
											</div>
										</div>
										<!-- 职位薪资区间 -->
										<div class="form-row">
											<div class="form-group col-md-12">
												<label for="job_salary"><fmt:message key="JobSalary" /></label>
												<input type="text" class="form-control" id="job_salary" value="${job.getSalary() }"
												placeholder="<fmt:message key="JobSalaryTip" />" >
											</div>
										</div>
										
										<div class="form-row">
											<!-- 职位招聘人数 -->
											<div class="form-group col-md-6">
												<label for="job_hiringnum"><fmt:message key="JobHiringnum" /></label> 
												<input type="text" class="form-control" id="job_hiringnum" value="${job.getHiringNum() }"
												placeholder="<fmt:message key="JobHiringnumTip"/>" />
											</div>
											<!-- 职位截止时间 -->
											<div class="form-group col-md-6">
												<label for="job_endtime"><fmt:message key="JobEndTime" /></label> 
												<input type="hidden" id="job_endtime" name="job_endtime" value="" />
												<div class="input-group date job_endtime_date" data-date="" data-date-format="yyyy-mm-dd" 
												data-link-field="job_endtime" data-link-format="yyyy-mm-dd">
													<input class="form-control" type="text" onchange="updateJobEndTime(this.value)" id="job_endtime_date"
													placeholder="<fmt:message key="JobEndTimeTip" />" autocomplete="off"/>
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>
										</div>
										<!-- 发布招聘协议 -->
										<div class="form-group">
											<div class="form-check">
												<input class="form-check-input" type="checkbox" id="postJobProtocol"> 
												<label class="form-check-label" for="postJobProtocol"><fmt:message key="postJobProtocol" /></label>
											</div>
										</div>
										
										<button type="button" id="postJobBtn" class="btn btn-primary job-post-btn btn-lg btn-block"><fmt:message key="PostJob" /></button>
									</form>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</main>

	<!-- start-公共页脚 -->
	<%@include file="/WEB-INF/view/common/swrw/footer.jsp"%>
	<!-- end-公共页脚 -->

	<!-- start-页面预载 -->
	<%@include file="/WEB-INF/view/common/swrw/preloader.jsp"%>
	<!-- end-页面预载 -->

	<!-- start-公共脚本 -->
	<%@include file="/WEB-INF/view/common/swrw/javaScript.jsp"%>
	<!-- end-公共脚本 -->
	<!-- datetimepicker -->
	<script src="/SWRW/public/assets/js/bootstrap-datetimepicker.min.js"></script>
	<script src="/SWRW/public/assets/js/locales/bootstrap-datetimepicker.${language}.js"></script>
	<!-- 自定义脚本 -->
	<script>
		var oldEndTime = '${COMUtil.dataToStr(job.getEndDate()) }';
		var job_id = oldEndTime.length==0?'0':'${param.job_id}';
		var selectTrue = [IfNull('${job.area}'.split(" ")[0]),IfNull('${job.area}'.split(" ")[1]),IfNull('${job.area}'.split(" ")[2])];
	</script>
	<script src="/SWRW/public/js/city/citys.js"></script>
	<script src="/SWRW/public/js/city/selectCity.js"></script>
	<script src="/SWRW/public/js/company/company_postJob.js"></script>
	
</body>
</html>