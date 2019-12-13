package com.qst.itoffer.servlet.user;

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

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.qst.itoffer.bean.User;
import com.qst.itoffer.dao.UserDao;

/**
 * Servlet implementation class UserEmailRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("mode")==null||request.getParameter("user_name")==null||request.getParameter("account")==null||
			request.getParameter("user_password")==null||request.getParameter("businessRegister")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		
		String mode = request.getParameter("mode");
		String user_name = request.getParameter("user_name");
		String account = request.getParameter("account");
		String user_password = request.getParameter("user_password");
		String businessRegister = request.getParameter("businessRegister");
				
        UserDao dao = new UserDao();
        User user = new User();
        user.setName(user_name);
        if(mode.equals("email")) {
        	user.setEmail(account);
        }else if(mode.equals("phone")) {
        	user.setPhone(account);
        }
        user.setPwd(user_password);
        user.setType(businessRegister.equals("true")?2:1);
        
        int user_id = dao.register(user);
        
        boolean isOK = false;
        if(user_id!=0) {//添加数据失败
        	isOK = true;
        	user.setID(user_id);
        }else {
        	isOK = false;
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("successMes",user_name+"，恭喜你已成为神葳总局的会员！\n即将跳转到个人中心。。。");
			map.put("errorMes","很抱歉，遇到了未知错误，可能是服务器出错或网络问题。\n请重试或联系管理员！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("successMes",user_name+",Congratulations on becoming a member of SWZJ!\nJump to personal center page soon...");
			map.put("errorMes","Sorry, encountered unknown error, may be server error or network problem, please try again or contact the administrator!");
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
			Logger.getLogger(getClass()).info("用户"+user.getID()+user.getName()+"通过"+(mode.equals("email")?"邮箱":"手机号")+"："+account+"成功注册");
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
