package com.swzj.swrw.filter;

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

/**
 * Servlet Filter implementation class SetCharacterEncodingFilter
 */
@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { @WebInitParam(name = "encoding", value = "UTF-8") }, 
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD ,DispatcherType.ASYNC })
public class SetCharacterEncodingFilter implements Filter {

	private String encoding = "UTF-8";

    public SetCharacterEncodingFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
		// 获取当请求被拦截时转向的页面
    	encoding = fConfig.getInitParameter("encoding");
		if (encoding == null) {
			encoding = "UTF-8";
		}
	}
    
	public void destroy() {
		this.encoding = null;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
}
