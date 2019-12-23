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
 * Servlet implementation class UserIsUserNameExist
 */
@WebServlet("/UserIsUserNameExist")
public class UserIsUserNameExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserIsUserNameExist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_name")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		
		String user_name = request.getParameter("user_name");
				
        UserDao dao = new UserDao();
        User user = new User();
        user = dao.queryUserByName(user_name);
        
        boolean isExist = false;
        if(user!=null) {
        	isExist = true;
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isExist", isExist);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","该用户名已存在！");
			map.put("successMes","该用户名可以使用！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","The user name already exists!");
			map.put("successMes","The user name can be used!");
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
