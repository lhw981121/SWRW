package com.swzj.swrw.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.swzj.swrw.util.SendEmail;

/**
 * Servlet implementation class UserSendEmailCode
 */
@WebServlet("/UserSendEmailCode")
public class UserSendEmailCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSendEmailCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_email")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		SendEmail email = new SendEmail();
		String user_email = request.getParameter("user_email");
        String code = getEmailCode();
        String to = user_email;
        String title = "神葳总局邮箱注册验证";	
        String content = "<div style='width:640px; background:#fff; border:solid 1px #efefef; margin:0 auto; padding:0 0 0 0'>" +
        		" <table width='560' border='0' align='center' cellpadding='0' cellspacing='0' style=' margin:0 auto; margin-left:30px; margin-right:30px;'>" +
        		"    <tbody>" +
        		"      <td>" +
        		"      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>神葳总局邮箱注册验证通知:</p>" +
        		"      <p style='color:#666; font-size:14px'>您正在进行神葳总局用户邮箱注册验证，您收到的邮箱验证码为： " +
        		"      <p style='color:red; font-size:28px'>" + code +
        			   "<p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>如果验证码已经失效，请回到神葳总局重新发送验证码，谢谢您的合作！</p>" +
        		"      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>如非本人操作，请忽略此邮件，由此给您带来的不便请谅解！</p>" +
        		"      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>感谢您对神葳总局的支持。</p>" +
        		"      </td>" +
        		"    </tbody>" +
        		" </table>" +
        		"</div>";
        boolean isOK = false;
        try {
			isOK = email.send(to, title, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(isOK==true) {
        	Logger.getLogger(getClass()).info("神葳总局成功给 "+to+" 发送邮件！ 验证码："+code);
        }else {
        	Logger.getLogger(getClass()).error("出现未知错误，神葳总局给 "+to+" 发送邮件失败！");
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);

		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","邮箱不存在或遇到未知问题，请重试或联系管理员！");
			map.put("successMes","邮件发送成功！请注意查收。");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","Mailbox does not exist or encounter unknown problem, please try again or contact administrator!");
			map.put("successMes","Email sent successfully! Please note that check.");
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
	
	//创建邮箱验证码
    public static String getEmailCode() {
		String string = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer code = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			Random random = new Random();
			int index = random.nextInt(string.length());
			char ch = string.charAt(index);
			code.append(ch);
		}
		return code.toString();
	}

}
