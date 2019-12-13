package com.qst.itoffer.servlet.company;

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
import com.qst.itoffer.bean.Company;
import com.qst.itoffer.bean.Message;
import com.qst.itoffer.bean.User;
import com.qst.itoffer.dao.CompanyDao;
import com.qst.itoffer.util.COMUtil;

/**
 * Servlet implementation class CompanyCertificate
 */
@WebServlet("/CompanyCertificate")
public class CompanyCertificate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyCertificate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("company_name")==null||request.getParameter("company_legal")==null||request.getParameter("company_area")==null||
			request.getParameter("company_size")==null||request.getParameter("company_type")==null||request.getParameter("company_brief")==null||
			request.getParameter("company_license")==null){
			response.sendRedirect("/SWRW/index");
			return;
		}
		HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
		String company_name = request.getParameter("company_name");
		String company_legal = request.getParameter("company_legal");
		String company_area = request.getParameter("company_area");
		String company_size = request.getParameter("company_size");
		String company_type = request.getParameter("company_type");
		String company_brief = request.getParameter("company_brief");
		String dataURL = request.getParameter("company_license");
		Company company = new Company();
		company.setID(user.getCompanyID());
		company.setName(company_name);
		company.setLegal(company_legal);
		company.setArea(company_area);
		company.setSize(company_size);
		company.setType(company_type);
		company.setBrief(company_brief);
		
		boolean isOK = false;
		
		String path=request.getSession().getServletContext().getRealPath("/");
		path += "/public/images/company/license";
		String imgName=company.getID()+".jpg";
		company.setLicense(imgName);
		
		COMUtil.decodeBase64DataURLToImageAndUpload(dataURL, path, imgName);
		
		isOK = new CompanyDao().companyCertificate(company);

        if(isOK==true) {
        	/*发送消息给管理员*/
        	Message mes = new Message();
        	//消息概述
        	String message_summary = "企业 "+company.getName()+" 审核通知";
        	//消息内容
        	String message_content = "企业 "+company.getName()+" 提交了认证信息，正等待管理员审核。";
        	//消息发送者用户ID
        	int sender_id = 1;//系统消息
        	//消息接收者用户ID
        	int receiver_id = 2;//管理员
        	//发送消息
        	mes.sendSingleMessage(0, message_summary, message_content, sender_id, receiver_id);
        	/*发送消息给企业用户*/
        	//消息概述
        	message_summary = "企业认证已提交";
        	//消息内容
        	message_content = "你已提交了企业认证信息，正等待管理员审核。";
        	//消息发送者用户ID
        	sender_id = 1;//系统消息
        	//消息接收者用户ID
        	receiver_id = user.getID();//提交企业认证用户
        	//发送消息
        	mes.sendSingleMessage(4, message_summary, message_content, sender_id, receiver_id);
        	Logger.getLogger(getClass()).info("用户"+user.getID()+"成功提交了企业认证。");
        }else {
        	Logger.getLogger(getClass()).error("用户"+user.getID()+"提交企业认证失败。");
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);

		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","遇到未知问题，请重试或联系管理员！");
			map.put("successMes","企业认证提交成功！请等待审核。\n窗口即将关闭。。。");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","Encounter unknown problem, please try again or contact administrator!");
			map.put("successMes","Company certification submitted successfully! Please wait for the review.\nThe window is about to close...");
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
