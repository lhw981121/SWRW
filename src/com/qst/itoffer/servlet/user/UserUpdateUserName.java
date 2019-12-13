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
 * Servlet implementation class UserUpdateUserName
 */
@WebServlet("/UserUpdateUserName")
public class UserUpdateUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateUserName() {
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
		HttpSession session = request.getSession();
		String user_name = request.getParameter("user_name");
				
        UserDao dao = new UserDao();
        User user = (User)session.getAttribute("user");
        
        boolean isOK = false;
        isOK = dao.updateUserName(user, user_name);
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("successMes","你已成功修改用户名\n窗口即将关闭。。。");
			map.put("errorMes","很抱歉，遇到了未知错误，可能是服务器出错或网络问题。\n请重试或联系管理员！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("successMes","You have successfully modified the user name\nThe window is closing...");
			map.put("errorMes","Sorry, encountered unknown error, may be server error or network problem, please try again or contact the administrator!");
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
