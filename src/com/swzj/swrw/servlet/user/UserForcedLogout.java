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

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.swzj.swrw.bean.User;

/**
 * Servlet implementation class UserForcedLogout
 */
@WebServlet("/UserForcedLogout")
public class UserForcedLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserForcedLogout() {
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
		
        boolean isOK = false;
        Map<String,Object> map = new HashMap<String,Object>();

		HttpSession session = request.getSession();
		//强制下线同一账号的其他客户端的用户
		ServletContext application = session.getServletContext();
		Map<String,Object> userMap = application.getAttribute("userMap")==null?new HashMap<String,Object>():(Map<String,Object>)application.getAttribute("userMap");
		for (String key : userMap.keySet()) {
			User user = (User)userMap.get(key);
			if(((user.getEmail()!=null&&user.getEmail().equals(user_account.toLowerCase()))||
				(user.getPhone()!=null&&user.getPhone().equals(user_account)))&&!key.equals(session.getId())) {
				userMap.remove(key);
				isOK = true;
				Logger.getLogger(getClass()).info("用户"+user.getID()+user.getName()+"被强制下线。");
				break;
			}
		}

        map.put("isOK", isOK);
		
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
