package com.qst.itoffer.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class UserIsCookieFilter
 */
@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { @WebInitParam(name = "loginPage", value = "login") }, 
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class UserIsCookieFilter implements Filter {
	
	private String loginPage = "login";

    public UserIsCookieFilter() {
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

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
        boolean flag = false;
        //如果是.jsp文件需要过滤
        if (req.getServletPath().indexOf(".jsp") != -1) {
            flag =true;
        }
        //不过滤登录页面
        if(req.getServletPath().indexOf("login") != -1) {
        	flag = false;
        }
        if (flag) {
        	// 判断被拦截的请求用户是否处于登录状态
    		if (session.getAttribute("user") == null) {
    			//判断客户端是否存有用户Cookie信息
            	boolean existCookie = false;
            	Cookie[] cookies = req.getCookies();
            	if (cookies != null) {
            		for (int i = 0; i < cookies.length; i++) {
            			if ("COOKIE_USER".equals(cookies[i].getName())) {
            				existCookie = true;
            			}
            		}
            	}
            	if(existCookie == true) {//客户端存有用户Cookie信息
            		// 获取被拦截的请求地址及参数
        			String requestPath = req.getRequestURI();
        			if (req.getQueryString() != null) {
        				requestPath += "?" + req.getQueryString();
        			}
        			// 将请求地址保存到request对象中转发到登录页面
        			req.setAttribute("requestPath", requestPath);
        			request.getRequestDispatcher( "/" + loginPage).forward(request, response);
        			return;
            	}else {//客户端不存在用户Cookie信息
            		chain.doFilter(request, response);
            	}
    		} else {
    			//检测用户在线状态
    	        ServletContext application = session.getServletContext();
    	        Map<String,Object> userMap = application.getAttribute("userMap")==null?new HashMap<String,Object>():(Map<String,Object>)application.getAttribute("userMap");
    	        if(userMap.containsKey(session.getId())) {//用户在线
    	        	//更新Cookie中的sessionid
        	        Cookie JSESSIONID = new Cookie("JSESSIONID",session.getId());
        	        JSESSIONID.setMaxAge(60*30);//保存时间为session存活时间
        	        JSESSIONID.setPath("/SWRW");
        	        resp.addCookie(JSESSIONID);
    	        }
    	        chain.doFilter(request, response);
    		}
        }else{
            chain.doFilter(request, response);  
        }
	}

}
