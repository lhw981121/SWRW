package com.swzj.swrw.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;

/**
 * Servlet implementation class UserTestLogout
 */
@WebServlet("/UserTestLogout")
public class UserTestLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserTestLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null) {
			ServletContext application = session.getServletContext();
	        Map<String,Object> userMap = application.getAttribute("userMap")==null?new HashMap<String,Object>():(Map<String,Object>)application.getAttribute("userMap");
	        
	        boolean isLogout = false;
	        
	        if(!userMap.containsKey(session.getId())) {//用户已被挤下线
	        	//删除客户端的用户Cookie和session
	        	Cookie[] cookies = request.getCookies();
	    		if (cookies != null) {
	    			for (int i = 0; i < cookies.length; i++) {
	    				if ("COOKIE_USER".equals(cookies[i].getName())) {
	    					cookies[i].setMaxAge(0);
	    					response.addCookie(cookies[i]);
	    				}
	    			}
	    		}
	        	session.removeAttribute("user");
	        	isLogout = true;
	        }
			map.put("isLogout", isLogout);
			
			if(session.getAttribute("language").equals("zh_CN")) {
				map.put("errorMes","您的账号在另一客户端登录，您已被迫下线！\n如果不是本人操作请尽快修改密码！");
				map.put("successMes","");
			}else if(session.getAttribute("language").equals("en_US")){
				map.put("errorMes","Your account is logged in another client,You have been forced to go offline!\nif not your operation please change the password as soon as possible!");
				map.put("successMes","");
			}
		}

		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
