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
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserAccountLogin")
public class UserAccountLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAccountLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_account")==null||request.getParameter("user_password")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		
		String account = request.getParameter("user_account");
		String password = request.getParameter("user_password");
				
        UserDao dao = new UserDao();
        User user = new User();
        user = dao.login(account,password);
        boolean isLogin = false;
        if(user!=null) {
        	isLogin = true;
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isLogin", isLogin);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","账号或密码错误！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","Wrong account or password!");
		}
		map.put("successMes","登录成功");
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
