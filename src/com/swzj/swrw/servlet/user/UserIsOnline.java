package com.swzj.swrw.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.swzj.swrw.bean.User;

/**
 * Servlet implementation class UserIsOnline
 */
@WebServlet("/UserIsOnline")
public class UserIsOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserIsOnline() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_account")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		
		String user_account = request.getParameter("user_account");
		
        boolean isOnline = false;
        Map<String,Object> map = new HashMap<String,Object>();

		HttpSession session = request.getSession();
		//检测用户是否已在线
		ServletContext application = session.getServletContext();
		Map<String,Object> userMap = application.getAttribute("userMap")==null?new HashMap<String,Object>():(Map<String,Object>)application.getAttribute("userMap");
		for (String key : userMap.keySet()) {
			User user = (User)userMap.get(key);
			if(((user.getEmail()!=null&&user.getEmail().equals(user_account.toLowerCase()))||
				(user.getPhone()!=null&&user.getPhone().equals(user_account)))&&!key.equals(session.getId())) {
				isOnline = true;
				break;
			}
		}

        map.put("isOnline", isOnline);
        
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","您的账号已在其他客户端登录！\n如果不是您本人登录请尽快修改密码！\n强制登录即可强制下线其他客户端的账号");
			map.put("successMes","");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","Your account has been logged in other clients! \nIf not you login please change the password as soon as possible!\nForced login can be forced offline other client accounts");
			map.put("successMes","");
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
