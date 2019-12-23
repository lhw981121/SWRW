package com.swzj.swrw.servlet.admin;

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
import com.swzj.swrw.bean.Company;
import com.swzj.swrw.bean.Message;
import com.swzj.swrw.dao.CompanyDao;
import com.swzj.swrw.dao.UserDao;

/**
 * Servlet implementation class PassCompany
 */
@WebServlet("/AdminPassCompany")
public class AdminPassCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPassCompany() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("company_id")==null||request.getParameter("passOpinion")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		HttpSession session = request.getSession();
		int company_id = Integer.valueOf(request.getParameter("company_id"));
		String passOpinion = request.getParameter("passOpinion");
		
		CompanyDao dao = new CompanyDao();
		Company company = dao.queryCompanyByID(company_id);
		company.setState(1);
        
        boolean isOK = false;
        
        isOK = dao.updateCompany(company);
        
        if(isOK==true) {
        	/*发送消息给企业用户*/
        	Message mes = new Message();
        	//消息概述
        	String message_summary = "企业资质认证审核通过";
        	//消息内容
        	String message_content = "恭喜你，你提交的企业资质认证已通过！<br>审核意见："+passOpinion+"<br>快去<a href='/SWRW/company/job/post_job'>发布职位</a>吧！";
        	//消息发送者用户ID
        	int sender_id = 1;//系统消息
        	//消息接收者用户ID
        	int receiver_id = new UserDao().queryUserByCompanyID(company_id).getID();//企业对应的用户
        	//发送消息
        	mes.sendSingleMessage(1, message_summary, message_content, sender_id, receiver_id);
        	Logger.getLogger(getClass()).info("企业"+company.getName()+"通过了企业资质认证。");
        }else {
        	Logger.getLogger(getClass()).error("通过企业"+company.getName()+"企业资质认证出错失败。");
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("successMes","已成功通过企业 "+company.getName()+" 的资质认证！");
			map.put("errorMes","很抱歉，遇到了未知错误，可能是服务器出错或网络问题。\n请重试或联系管理员！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("successMes","Has successfully passed the enterprise's qualification certification!");
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
