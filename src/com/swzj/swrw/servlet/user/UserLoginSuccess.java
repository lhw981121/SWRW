package com.swzj.swrw.servlet.user;

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

import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.UserDao;
import com.swzj.swrw.util.BASE64;

/**
 * Servlet implementation class UserPhoneLogin
 */
@WebServlet("/UserLoginSuccess")
public class UserLoginSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginSuccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_account")==null||request.getParameter("rememberPsw")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		String user_account = request.getParameter("user_account");
		String rememberPsw = request.getParameter("rememberPsw");
				
        UserDao dao = new UserDao();
        User user = new User();
        user = dao.queryUserByAccount(user_account);
        Integer user_id = user.getID();
        
        //选中记住密码，设置Cookie
        if(rememberPsw.equals("true")) {
        	Cookie COOKIE_USER = new Cookie("COOKIE_USER", BASE64.encodeBase64(user_id.toString()));
        	COOKIE_USER.setMaxAge(60*60*12);//记住12小时
			response.addCookie(COOKIE_USER);
        }
        //设置session并保存sessionid到Cookie
        HttpSession session = request.getSession();
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
        
        Logger.getLogger(getClass()).info("用户"+user.getID()+user.getName()+"成功登录");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
