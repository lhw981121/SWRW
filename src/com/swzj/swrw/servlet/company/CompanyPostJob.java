package com.swzj.swrw.servlet.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import com.swzj.swrw.bean.Job;
import com.swzj.swrw.bean.Message;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.CompanyDao;
import com.swzj.swrw.dao.JobDao;
import com.swzj.swrw.util.COMUtil;

/**
 * Servlet implementation class CompanyPostJob
 */
@WebServlet("/CompanyPostJob")
public class CompanyPostJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyPostJob() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("job_id")==null||request.getParameter("job_name")==null||request.getParameter("job_desc")==null||
				request.getParameter("job_area")==null||request.getParameter("job_salary")==null||request.getParameter("job_hiringnum")==null||
				request.getParameter("job_endtime")==null){
				response.sendRedirect("/SWRW/index");
				return;
			}
			int job_id = Integer.valueOf(request.getParameter("job_id"));
			HttpSession session = request.getSession();
	        User user = (User)session.getAttribute("user");
	        int company_id = user.getCompanyID();
			String job_name = request.getParameter("job_name");
			String job_desc = request.getParameter("job_desc");
			String job_area = request.getParameter("job_area");
			String job_salary = request.getParameter("job_salary");
			int job_hiringnum = Integer.valueOf(request.getParameter("job_hiringnum"));
			Date job_endtime = COMUtil.strToDate(request.getParameter("job_endtime"));
			Job job = new JobDao().queryJobByID(job_id);
			if(job==null) {
				job = new Job();
			}
			job.setCompanyID(company_id);
			job.setName(job_name);
			job.setDesc(job_desc);
			job.setArea(job_area);
			job.setSalary(job_salary);
			job.setHiringNum(job_hiringnum);
			job.setEndDate(job_endtime);
			job.setState(-1);
			Company company = new CompanyDao().queryCompanyByID(company_id);
			
			boolean isOK = false;
			
			if(job_id==0) {//首次提交
				isOK = new JobDao().createJob(job)==0?false:true;
			}else{//重新提交
				isOK = new JobDao().updateJob(job);
			}

	        if(isOK==true) {
	        	/*发送消息给管理员*/
	        	Message mes = new Message();
	        	//消息概述
	        	String message_summary = "职位 "+job.getName()+" 审核通知";
	        	//消息内容
	        	String message_content = "企业 "+company.getName()+" 发布了一个职位： "+job.getName()+"，正等待管理员审核。<a href='/SWRW/admin/audit/job/wait_audit_job'>去审核</a>";
	        	//消息发送者用户ID
	        	int sender_id = 1;//系统消息
	        	//消息接收者用户ID
	        	int receiver_id = 2;//管理员
	        	//发送消息
	        	mes.sendSingleMessage(0, message_summary, message_content, sender_id, receiver_id);
	        	/*发送消息给企业用户*/
	        	//消息概述
	        	message_summary = "职位招聘提交审核";
	        	//消息内容
	        	message_content = "你已提交了职位招聘信息，正等待管理员审核。";
	        	//消息发送者用户ID
	        	sender_id = 1;//系统消息
	        	//消息接收者用户ID
	        	receiver_id = user.getID();//提交企业认证用户
	        	//发送消息
	        	mes.sendSingleMessage(4, message_summary, message_content, sender_id, receiver_id);
	        	Logger.getLogger(getClass()).info("用户"+user.getID()+"成功提交了职位招聘。");
	        }else {
	        	Logger.getLogger(getClass()).error("用户"+user.getID()+"提交职位招聘失败。");
	        }
	        
	        Map<String,Object> map = new HashMap<String,Object>();
			map.put("isOK", isOK);

			if(session.getAttribute("language").equals("zh_CN")) {
				map.put("errorMes","遇到未知问题，请重试或联系管理员！");
				map.put("successMes","职位招聘提交成功！请等待审核。\n窗口即将关闭。。。");
			}else if(session.getAttribute("language").equals("en_US")){
				map.put("errorMes","Encounter unknown problem, please try again or contact administrator!");
				map.put("successMes","Post job submitted successfully! Please wait for the review.\nThe window is about to close...");
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
