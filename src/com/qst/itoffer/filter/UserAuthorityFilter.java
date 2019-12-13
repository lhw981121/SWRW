package com.qst.itoffer.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qst.itoffer.bean.User;

/**
 * Servlet Filter implementation class ApplicantAuthorityFilter
 */
@WebFilter(
		urlPatterns = { "/user/*","/applicant/*","/company/*","/message/*","/admin/*" }, 
		servletNames = {"" }, 
		initParams = { @WebInitParam(name = "loginPage", value = "login") }, 
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class UserAuthorityFilter implements Filter {
	
	private String loginPage = "login";

    public UserAuthorityFilter() {
    }

	public void init(FilterConfig fConfig) throws ServletException {
		// 获取当请求被拦截时转向的页面
		loginPage = fConfig.getInitParameter("loginPage");
		if (null == loginPage) {
			loginPage = "login";
		}
	}

	public void destroy() {
		this.loginPage = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
        boolean flag = false;
        //如果是.jsp文件需要过滤
        if (req.getServletPath().indexOf(".jsp") != -1
        	||req.getServletPath().indexOf("/user") != -1
        	||req.getServletPath().indexOf("/applicant") != -1
        	||req.getServletPath().indexOf("/company") != -1
        	||req.getServletPath().indexOf("/message") != -1
        	||req.getServletPath().indexOf("/admin") != -1) {
            flag =true;
        }
        if (flag) {
        	//判断被拦截的请求用户是否处于登录状态
    		if (session.getAttribute("user") == null) {//用户未登录，跳转到登录页面
    			// 获取被拦截的请求地址及参数
    			String requestPath = req.getRequestURI();
    			if (req.getQueryString() != null) {
    				requestPath += "?" + req.getQueryString();
    			}
    			// 将请求地址保存到request对象中转发到登录页面
    			req.setAttribute("requestPath", requestPath);
    			request.getRequestDispatcher( "/" + loginPage).forward(request, response);
    			return;
    		} else {//用户已登录，判断是求职者还是企业
    			User user = (User)session.getAttribute("user");
    			//拒绝求职者访问企业页面
    			if(req.getServletPath().indexOf("/company") != -1 && user.getType() == 1) {
    				req.setAttribute("limitedAccess", 1);
    				request.getRequestDispatcher("/index").forward(request, response);
    				return;
    			}
    			//拒绝企业访问求职者页面
    			if(req.getServletPath().indexOf("/applicant") != -1 && user.getType() == 2) {
    				req.setAttribute("limitedAccess", 2);
    				request.getRequestDispatcher("/index").forward(request, response);
    				return;
    			}
    			//拒绝非管理员访问管理员页面
    			if(req.getServletPath().indexOf("/admin") != -1 && user.getType() != 8) {
    				req.setAttribute("limitedAccess", 3);
    				request.getRequestDispatcher("/index").forward(request, response);
    				return;
    			}
    			//管理员访问求职者或企业页面
    			if((req.getServletPath().indexOf("/applicant") == 0||req.getServletPath().indexOf("/company") == 0)&&user.getType()==8) {
    				resp.sendRedirect("/SWRW/index");
    				return;
    			}
    			chain.doFilter(request, response);
    		}
        }else{
            chain.doFilter(request, response);  
        }
	}

}

