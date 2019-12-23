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
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.UserDao;

/**
 * Servlet implementation class UserUpdatePassword
 */
@WebServlet("/UserUpdatePassword")
public class UserUpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdatePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_account")==null||request.getParameter("user_password")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		
		String user_account = request.getParameter("user_account");
		String user_password = request.getParameter("user_password");
				
        UserDao dao = new UserDao();
        User user = new User();
        user = dao.queryUserByAccount(user_account);
        if(user == null) {
        	user = dao.queryUserByName(user_account);
        }
        
        boolean isOK = false;
        isOK = dao.updateUserPassword(user, user_password);
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","很抱歉，遇到了未知错误，可能是服务器出错或网络问题。\n请重试或联系管理员！");
			map.put("successMes","你的密码已修改，请牢记！\n即将跳转到个人中心。。。");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","Sorry, encountered unknown error, may be server error or network problem, please try again or contact the administrator!");
			map.put("successMes","Your password has been modified, please remember!\nJump to personal center page soon...");
		}
		
		if(isOK) {
			//设置session并保存sessionid到Cookie
			session.setAttribute("user", user);
			//更新Cookie中的sessionid
	        Cookie JSESSIONID = new Cookie("JSESSIONID",session.getId());
	        JSESSIONID.setMaxAge(60*30);//保存时间为session存活时间
	        response.addCookie(JSESSIONID);
			//更新用户在线状态
	        ServletContext application = request.getServletContext();
	        Map<String,Object> userMap = application.getAttribute("userMap")==null?new HashMap<String,Object>():(Map<String,Object>)application.getAttribute("userMap");
			userMap.put(session.getId(), user);
			application.setAttribute("userMap", userMap);
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
