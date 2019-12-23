package com.swzj.swrw.servlet.user;

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
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.UserDao;

/**
 * Servlet implementation class UserIsPhoneExist
 */
@WebServlet("/UserIsPhoneExist")
public class UserIsPhoneExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserIsPhoneExist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_phone")==null||request.getParameter("mode")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		
		String user_phone = request.getParameter("user_phone");
		String mode = request.getParameter("mode");
				
        UserDao dao = new UserDao();
        User user = new User();
        user = dao.queryUserByPhone(user_phone);
        
        boolean isExist = false;
        if(user!=null) {
        	isExist = true;
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isExist", isExist);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			if(mode.equals("login")) {
				map.put("errorMes","该手机号未注册！");
				map.put("successMes","");
			}else {
				map.put("errorMes","该手机号已被注册！");
				map.put("successMes","该手机号可以注册！");
			}
		}else if(session.getAttribute("language").equals("en_US")){
			if(mode.equals("login")) {
				map.put("errorMes","The phone number not registered!");
				map.put("successMes","");
			}else {
				map.put("errorMes","The phone number has been registered!");
				map.put("successMes","The phone number can be registered!");
			}
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
