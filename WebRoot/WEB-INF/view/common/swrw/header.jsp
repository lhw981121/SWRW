<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header class="header-area">
   <div class="container">
      <div class="row">
         <div class="col-lg-2">
            <div class="logo">
               <a href="/SWRW/index"><img src="/SWRW/public/assets/images/logo.png" alt="Logo" /></a>
            </div>
         </div>
         <div class="col-lg-10">
            <div class="header-container d-flex justify-content-between highlight">
               <nav class="highlight">
                  <ul id="responsive-menu" class="slimmenu">
                  	<!-- 首页 -->
                     <li><a class="nav-link" href="/SWRW/index"><fmt:message key="Home"/></a>
                     </li>
                     <!-- 关于 -->
                     <li class="nav-item">
                        <a class="nav-link" href="javascript:;"><fmt:message key="About"/></a>
                        <ul>
                           <li><a href="/SWRW/about.jsp"><fmt:message key="About"/></a></li>
                           <li><a href="/SWRW/contact.jsp"><fmt:message key="ContactUs"/></a></li>
                           <li><a href="/SWRW/blog.jsp"><fmt:message key="Blog"/></a></li>
                        </ul>
                     </li>

							<!-- 动态导航 -->
		             	<c:choose>
							<c:when test="${currentURL.indexOf('/SWRW/WEB-INF/view/user')!=-1 }">
								<!-- 用户页面 -->
								<c:if test="${user.getType()==1 }">
								<!-- 求职者导航 -->
								<li class="nav-item">
	                        <a class="nav-link" href="/SWRW/job"><fmt:message key="Job"/></a>
	                     </li>
								<li class="nav-item">
	                        <a class="nav-link" href="javascript:;"><fmt:message key="Resume"/></a>
	                        <ul>
	                        	<li><a href="/SWRW/applicant/resume/view_resume"><fmt:message key="ViewResume"/></a></li>
	                        	<li><a href="/SWRW/applicant/resume/complete_resume"><fmt:message key="CompleteResume_header"/></a></li>
	                        	<li><a href="/SWRW/applicant/resume/submit_history"><fmt:message key="ResumeSubmitRecord"/></a></li>
	                           <li><a href="/SWRW/applicant/resume/viewed_history"><fmt:message key="ViewedRecord"/></a></li>
	                        </ul>
	                     </li>
								</c:if>
								<c:if test="${user.getType()==2 }">
								<!-- 企业导航 -->
								<li class="nav-item">
	                        <a class="nav-link" href="javascript:;"><fmt:message key="Company"/></a>
	                        <ul>
	                        	<li><a href="/SWRW/company"><fmt:message key="Company"/></a></li>
	                           <li><a href="/SWRW/user/mycenter?mode=CompanyCertification"><fmt:message key="CompanyCertification"/></a></li>
	                           <li><a href="/SWRW/user/mycenter?mode=Credential"><fmt:message key="Credential"/></a></li>
	                        </ul>
	                     </li>
	                     <li class="nav-item">
	                        <a class="nav-link" href="javascript:;"><fmt:message key="Job"/></a>
	                        <ul>
	                        	<li><a href="/SWRW/company/job/browse_resume"><fmt:message key="BrowseResume"/></a></li>
	                        	<li><a href="/SWRW/company/job/manage_job"><fmt:message key="ManageJob"/></a></li>
	                        	<li><a href="/SWRW/company/job/post_job"><fmt:message key="PostJob"/></a></li>	
	                        </ul>
	                     </li>
								</c:if>
							</c:when>
							<c:when test="${currentURL.indexOf('/SWRW/WEB-INF/view/message')!=-1 }">
								<!-- 消息页面导航 -->
								<li class="nav-item">
	                        <a class="nav-link" href="javascript:;"><fmt:message key="Message"/></a>
	                        <ul>
	                           <li><a href="/SWRW/message/all_message"><fmt:message key="AllMessage"/></a></li>
	                           <li><a href="/SWRW/message/unread_message"><fmt:message key="UnReadMessage"/></a></li>
	                           <li><a href="/SWRW/message/hasread_message"><fmt:message key="HasReadMessage"/></a></li>							 
	                        </ul>
	                     </li>
							</c:when>
							<c:when test="${currentURL.indexOf('/SWRW/WEB-INF/view/applicant')!=-1 }">
								<!-- 求职者页面导航 -->
								<li class="nav-item">
	                        <a class="nav-link" href="/SWRW/job"><fmt:message key="Job"/></a>
	                     </li>
								<li class="nav-item">
	                        <a class="nav-link" href="javascript:;"><fmt:message key="Resume"/></a>
	                        <ul>
	                        	<li><a href="/SWRW/applicant/resume/view_resume"><fmt:message key="ViewResume"/></a></li>
	                        	<li><a href="/SWRW/applicant/resume/complete_resume"><fmt:message key="CompleteResume_header"/></a></li>
	                        	<li><a href="/SWRW/applicant/resume/submit_history"><fmt:message key="ResumeSubmitRecord"/></a></li>
	                           <li><a href="/SWRW/applicant/resume/viewed_history"><fmt:message key="ViewedRecord"/></a></li>
	                        </ul>
	                     </li>
							</c:when>
							<c:when test="${currentURL.indexOf('/SWRW/WEB-INF/view/company')!=-1 }">
								<!-- 企业页面导航 -->
								<li class="nav-item">
	                        <a class="nav-link" href="javascript:;"><fmt:message key="Company"/></a>
	                        <ul>
	                        	<li><a href="/SWRW/company"><fmt:message key="Company"/></a></li>
	                           <li><a href="/SWRW/user/mycenter?mode=CompanyCertification"><fmt:message key="CompanyCertification"/></a></li>
	                           <li><a href="/SWRW/user/mycenter?mode=Credential"><fmt:message key="Credential"/></a></li>
	                        </ul>
	                     </li>
	                     <li class="nav-item">
	                        <a class="nav-link" href="javascript:;"><fmt:message key="Job"/></a>
	                        <ul>
	                        	<li><a href="/SWRW/company/job/browse_resume"><fmt:message key="BrowseResume"/></a></li>
	                        	<li><a href="/SWRW/company/job/manage_job"><fmt:message key="ManageJob"/></a></li>
	                        	<li><a href="/SWRW/company/job/post_job"><fmt:message key="PostJob"/></a></li>	
	                        </ul>
	                     </li>
							</c:when>
							<c:otherwise>
								<!-- 默认导航 -->
								<li class="nav-item">
	                        <a class="nav-link" href="javascript:;"><fmt:message key="Applicant"/></a>
	                        <ul>
	                           <li><a href="/SWRW/applicant"><fmt:message key="Applicant"/></a></li>
	                           <li><a href="/SWRW/job"><fmt:message key="JobListing"/></a></li>
	                           <li><a href="/SWRW/applicant/resume/complete_resume"><fmt:message key="CompleteResume_header"/></a></li>		 
	                           <li><a href="/SWRW/register"><fmt:message key="Register"/></a></li>
	                        </ul>
	                     </li>
	                     <li class="nav-item">
	                        <a class="nav-link" href="javascript:;"><fmt:message key="Company"/></a>
	                        <ul>
	                           <li><a href="/SWRW/company"><fmt:message key="Company"/></a></li>
	                           <li><a href="/SWRW/user/mycenter?mode=CompanyCertification"><fmt:message key="CompanyCertification"/></a></li>
	                           <li><a href="/SWRW/company/job/post_job"><fmt:message key="PostJob"/></a></li>
	                           <li><a href="/SWRW/register?mode=company"><fmt:message key="Register"/></a></li>
	                        </ul>
	                     </li>
							</c:otherwise>
							</c:choose>

                     <!-- 语言选择 -->
                     <li class="nav-item">
			               <a class="nav-link" href="javascript:;"><fmt:message key="Language"/></a>
			               <ul>
			                  <li><a href="javascript:;" onclick="changeLanguage('zh_CN');"><fmt:message key="Chinese"/></a></li>
			                  <li><a href="javascript:;" onclick="changeLanguage('en_US');"><fmt:message key="English"/></a></li>
			               </ul>
		             	</li>
		             	<!-- 用户信息 -->
		             	<li class="nav-item">
			             	<c:choose>
								<c:when test="${user.getID()==0}">
									<li class="nav-item call-to-action "><a href="/SWRW/login"><fmt:message key="Login"/></a></li>
								</c:when>
								<c:otherwise>
									<a href="javascript:;" class="nav-link">
					               <img src="/SWRW/public/images/user/avatar/${user.getID() }.jpg?t=${Math.random() }" 
					               onerror="this.src='/SWRW/public/images/user/avatar/0.jpg';this.onerror=null"
					               height="28" width="28" style="border-radius:50%" alt="Avatar" id="user_avatar_header"/>
					               <span><span class="badge" id="message_count_1"></span>${user.getName() }</span>
				            	</a>
	                        <ul>
	                        	<c:if test="${user.getType()==8 }">
	                        	<li><a href="/SWRW/admin" target="_blank"><span>
	                           	<i class="fa fa-credit-card-alt"></i> <fmt:message key="Admin"/>
	                           </span></a></li>
										</c:if>
	                           <li><a href="/SWRW/user/mycenter" target="_self"><span>
	                           	<i class="fa fa-user-circle-o"></i> <fmt:message key="MyCenter"/>
	                           </span></a></li>
	                           <li><a href="/SWRW/message" target="_blank"><span>
	                           	<i class="fa fa-commenting"></i> <fmt:message key="MessageCenter"/> <span class="badge" id="message_count_2"></span>
	                           </span></a></li>
	                           <li><a href="javascript:;" target="_self"><span>
	                           	<i class="fa fa-cog"></i> <fmt:message key="Setting"/>
	                           </span></a></li>
	                           <li><a href="/SWRW/UserLogout" target="_self"><span>
	                           	<i class="fa fa-sign-out" aria-hidden="true"></i> <fmt:message key="Logout"/>
	                           </span></a></li>
	                        </ul>
								</c:otherwise>
								</c:choose>
                     </li>
		             	
                  </ul>
               </nav>
            </div>
         </div>
      </div>
   </div>
</header>
