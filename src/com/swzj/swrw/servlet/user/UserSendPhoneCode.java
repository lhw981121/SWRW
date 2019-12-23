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

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.swzj.swrw.util.PhoneCode;

/**
 * Servlet implementation class UserSendPhoneCode
 */
@WebServlet("/UserSendPhoneCode")
public class UserSendPhoneCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSendPhoneCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_phone")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		PhoneCode phone = new PhoneCode();
		String user_phone = request.getParameter("user_phone");
		String code = getPhoneCode();
		
		boolean isOK = true;
        try {
			isOK = phone.sendPhoneCode(user_phone, code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(isOK==true) {
        	Logger.getLogger(getClass()).info("神葳总局成功给 "+user_phone+" 发送手机短信！ 验证码："+code);
        }else {
        	Logger.getLogger(getClass()).error("出现未知错误，神葳总局给 "+user_phone+" 发送手机短信失败！验证码："+code);
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);

		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","遇到未知问题，请重试或联系管理员！");
			map.put("successMes","手机短信发送成功！请注意查收。");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","Encounter unknown problem, please try again or contact administrator!");
			map.put("successMes","SMS sent successfully! Please note that check.");
		}
		map.put("code", code);
		
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
	
	//创建手机短信验证码
    public static String getPhoneCode() {
    	String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
		return random;
	}

}
