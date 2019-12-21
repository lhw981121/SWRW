package com.swzj.swrw.servlet.applicant;

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
import com.swzj.swrw.bean.Job;
import com.swzj.swrw.bean.JobApply;
import com.swzj.swrw.bean.Message;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.JobApplyDao;
import com.swzj.swrw.dao.JobDao;
import com.swzj.swrw.dao.UserDao;

/**
 * Servlet implementation class ApplicantApplyJob
 */
@WebServlet("/ApplicantApplyJob")
public class ApplicantApplyJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicantApplyJob() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("mode")==null||request.getParameter("job_id")==null||request.getParameter("applicant_id")==null){
			response.sendRedirect("/SWRW/index");
			return;
		}
		String mode = request.getParameter("mode");
		int job_id = Integer.valueOf(request.getParameter("job_id"));
		int applicant_id = Integer.valueOf(request.getParameter("applicant_id"));
		JobApply jobApply = new JobApply();
		JobApplyDao jobApplyDao = new JobApplyDao();
		if(mode.equals("repeat")) {
			jobApply = jobApplyDao.queryJobApply(job_id, applicant_id);
		}
		jobApply.setJobID(job_id);
		jobApply.setApplicantID(applicant_id);
		jobApply.setState(1);
		
		boolean isOK = false;
		if(mode.equals("repeat")) {//重新申请
			isOK = jobApplyDao.updateJobApply(jobApply);
		}else {//首次申请
			isOK = new JobApplyDao().createJobApply(jobApply)!=0;
		}
		
		if(isOK) {
			UserDao userDao = new UserDao();
			User applicant = userDao.queryUserByApplicantID(applicant_id);
			Job job = new JobDao().queryJobByID(job_id);
			User company = userDao.queryUserByCompanyID(job.getCompanyID());
			/*发送消息给职位所属企业*/
        	Message mes = new Message();
        	//消息概述
        	String message_summary = "收到来自 "+applicant.getName()+" 的简历";
        	//消息内容
        	String message_content = applicant.getName()+" 向你的职位 "+job.getName()+" 投递了简历，等待阅览。"
        			+ "<a href='/SWRW/company/job/resume_detail?applicant_id="+applicant.getApplicantID()+"'>查看简历</a>";
        	//消息发送者用户ID
        	int sender_id = 1;//系统消息
        	//消息接收者用户ID
        	int receiver_id = company.getID();//所属企业用户
        	//发送消息
        	mes.sendSingleMessage(0, message_summary, message_content, sender_id, receiver_id);
        	/*发送消息给求职者用户*/
        	//消息概述
        	message_summary = "成功向职位 "+job.getName()+" 投递简历";
        	//消息内容
        	message_content = "你已成功向职位 "+job.getName()+" 投递了简历，祝你顺利入职！去<a href='/SWRW/applicant/resume/complete_resume'>完善简历</a>";
        	//消息发送者用户ID
        	sender_id = 1;//系统消息
        	//消息接收者用户ID
        	receiver_id = applicant.getID();//提交简历用户
        	//发送消息
        	mes.sendSingleMessage(4, message_summary, message_content, sender_id, receiver_id);
			Logger.getLogger(getClass()).info("求职者 "+applicant_id+" 成功申请职位 "+job_id);
		}else {
			Logger.getLogger(getClass()).error("求职者 "+applicant_id+" 申请职位 "+job_id+" 失败");
		}
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);

		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","遇到未知问题，请重试或联系管理员！");
			map.put("successMes","简历已成功提交！\n祝你成功入职！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","Encounter unknown problem, please try again or contact administrator!");
			map.put("successMes","Resume submitted successfully! \n wish you a successful entry!");
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
