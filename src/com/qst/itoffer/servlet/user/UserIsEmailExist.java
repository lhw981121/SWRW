package com.qst.itoffer.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.qst.itoffer.bean.User;
import com.qst.itoffer.dao.UserDao;

/**
 * Servlet implementation class UserIsAccountExist
 */
@WebServlet("/UserIsEmailExist")
public class UserIsEmailExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserIsEmailExist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_email")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		
		String user_email = request.getParameter("user_email");
				
        UserDao dao = new UserDao();
        User user = new User();
        user = dao.queryUserByEmail(user_email);
        
        boolean isExist = false;
        if(user!=null) {
        	isExist = true;
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isExist", isExist);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","该邮箱已被注册！");
			map.put("successMes","该邮箱可以注册！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","The email address has been registered!");
			map.put("successMes","The email address can be registered!");
		}
		map.put("user", user);
		
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
