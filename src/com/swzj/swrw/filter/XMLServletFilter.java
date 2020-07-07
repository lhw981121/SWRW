package com.swzj.swrw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class AsyncServletFilter
 */
@WebFilter(
		servletNames = {
				"com.swzj.swrw.servlet.admin.AdminPassCompany",
				"com.swzj.swrw.servlet.admin.AdminPassJob",
				"com.swzj.swrw.servlet.admin.AdminVetoCompany",
				"com.swzj.swrw.servlet.admin.AdminVetoJob",
				"com.swzj.swrw.servlet.applicant.ApplicantApplyJob",
				"com.swzj.swrw.servlet.applicant.ApplicantSubmitResume",
				"com.swzj.swrw.servlet.applicant.ApplicantUploadResumePhone",
				"com.swzj.swrw.servlet.common.ChangeLanguage",
				"com.swzj.swrw.servlet.common.ChangePageSize",
				"com.swzj.swrw.servlet.common.CompanyPagination",
				"com.swzj.swrw.servlet.common.JobApplyPagination",
				"com.swzj.swrw.servlet.common.JobListingData",
				"com.swzj.swrw.servlet.common.JobPagination",
				"com.swzj.swrw.servlet.common.RefreshNewMessage",
				"com.swzj.swrw.servlet.common.TextEmailRealExist",
				"com.swzj.swrw.servlet.company.CompanyCertificate",
				"com.swzj.swrw.servlet.company.CompanyChangeJobDetail",
				"com.swzj.swrw.servlet.company.CompanyPostJob",
				"com.swzj.swrw.servlet.company.CompanyQueryByID",
				"com.swzj.swrw.servlet.company.CompanyUploadJobHead",
				"com.swzj.swrw.servlet.company.HireApplicant",
				"com.swzj.swrw.servlet.company.JobQueryByID",
				"com.swzj.swrw.servlet.company.RejectedApplicant",
				"com.swzj.swrw.servlet.company.ShortlistApplicant",
				"com.swzj.swrw.servlet.message.MessageDelete",
				"com.swzj.swrw.servlet.message.MessageDeleteAll",
				"com.swzj.swrw.servlet.message.MessageDeleteRead",
				"com.swzj.swrw.servlet.message.MessageDeleteUnRead",
				"com.swzj.swrw.servlet.message.MessageMarkAllRead",
				"com.swzj.swrw.servlet.message.MessageMarkRead",
				"com.swzj.swrw.servlet.message.MessageMarkUnRead",
				"com.swzj.swrw.servlet.message.MessagePagination",
				"com.swzj.swrw.servlet.user.UserAccountLogin",
				"com.swzj.swrw.servlet.user.UserForcedLogout",
				"com.swzj.swrw.servlet.user.UserIsAccountExist",
				"com.swzj.swrw.servlet.user.UserIsEmailExist",
				"com.swzj.swrw.servlet.user.UserIsOnline",
				"com.swzj.swrw.servlet.user.UserIsPhoneExist",
				"com.swzj.swrw.servlet.user.UserIsUserNameExist",
				"com.swzj.swrw.servlet.user.UserLoginSuccess",
				"com.swzj.swrw.servlet.user.UserRegister",
				"com.swzj.swrw.servlet.user.UserSendEmailCode",
				"com.swzj.swrw.servlet.user.UserSendPhoneCode",
				"com.swzj.swrw.servlet.user.UserTestLogout",
				"com.swzj.swrw.servlet.user.UserUpdateEmail",
				"com.swzj.swrw.servlet.user.UserUpdatePassword",
				"com.swzj.swrw.servlet.user.UserUpdatePhone",
				"com.swzj.swrw.servlet.user.UserUpdateUserName",
				"com.swzj.swrw.servlet.user.UserUploadAvatar",
				})
public class XMLServletFilter implements Filter {

    /**
     * Default constructor. 
     */
    public XMLServletFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String requestType =req.getHeader("X-Requested-With");
		if(requestType!=null&&requestType.equalsIgnoreCase("XMLHttpRequest")) {//异步请求
			chain.doFilter(request, response);
		}else {//同步请求
			request.getRequestDispatcher("/WEB-INF/view/404.jsp").forward(request, response);
			return;
		}	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
