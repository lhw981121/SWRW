package com.qst.itoffer.servlet.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.qst.itoffer.bean.User;


/**
 * Servlet implementation class UserLogout
 */
@WebServlet("/UserLogout")
public class UserLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//注销删除所有Cookie和Session
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("COOKIE_USER".equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		//更新用户为离线状态
        ServletContext application = session.getServletContext();
        Map<String,Object> userMap = application.getAttribute("userMap")==null?new HashMap<String,Object>():(Map<String,Object>)application.getAttribute("userMap");
        if(userMap.containsKey(session.getId())) {
        	userMap.remove(session.getId());
        	session.removeAttribute("user");
        	Logger.getLogger(getClass()).info("用户"+user.getID()+user.getName()+"成功注销已离线。");
        }
        application.setAttribute("userMap", userMap);
        
		response.sendRedirect("/SWRW/index");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
