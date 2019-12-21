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
 * Servlet implementation class UserBindPhone
 */
@WebServlet("/UserUpdatePhone")
public class UserUpdatePhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdatePhone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("mode")==null||request.getParameter("user_phone")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		HttpSession session = request.getSession();
		String mode = request.getParameter("mode");
		String user_phone = request.getParameter("user_phone");
				
        UserDao dao = new UserDao();
        User user = (User)session.getAttribute("user");
        
        boolean isOK = false;
        boolean isOnly = false;
        //判断手机号是否为唯一登录方式
        if(mode.equals("unbind")&&(user.getEmail()==null||user.getEmail().length()==0)) {
        	isOK = false;
        	isOnly = true;
        }else {
        	isOK = dao.updateUserPhone(user, mode.equals("bind")?user_phone:"");
        }

        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		map.put("isOnly", isOnly);
		
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("successMes","你已成功"+(mode.equals("bind")?"绑定":"解绑")+"手机号："+user_phone+"\n窗口即将关闭。。。");
			map.put("errorMes","很抱歉，遇到了未知错误，可能是服务器出错或网络问题。\n请重试或联系管理员！");
			if(isOnly)map.put("ErrorMes","手机号已是你登录的唯一方式，无法解绑！\n若有必要请联系管理员！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("successMes","You have successfully "+(mode.equals("bind")?"bind":"unbind")+" your phone number:"+user_phone+"\nThe window is closing...");
			map.put("errorMes","Sorry, encountered unknown error, may be server error or network problem, please try again or contact the administrator!");
			if(isOnly)map.put("ErrorMes","Phone is the only way you can log in,Can't unbind!\nIf necessary, please contact the administrator!");
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
