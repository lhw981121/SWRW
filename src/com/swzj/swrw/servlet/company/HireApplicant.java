package com.swzj.swrw.servlet.company;

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
import com.swzj.swrw.bean.BasicInfo;
import com.swzj.swrw.bean.Job;
import com.swzj.swrw.bean.JobApply;
import com.swzj.swrw.bean.Message;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.BasicInfoDao;
import com.swzj.swrw.dao.JobApplyDao;
import com.swzj.swrw.dao.JobDao;
import com.swzj.swrw.dao.UserDao;

/**
 * Servlet implementation class HireApplicant
 */
@WebServlet("/HireApplicant")
public class HireApplicant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HireApplicant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("apply_id")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		HttpSession session = request.getSession();
		int apply_id = Integer.valueOf(request.getParameter("apply_id"));
		
		JobApplyDao dao = new JobApplyDao();
		JobApply jobApply = dao.queryJobApplyByID(apply_id);
		jobApply.setState(2);
        
        boolean isOK = false;
        isOK = dao.updateJobApply(jobApply);
        
        User applicant = new UserDao().queryUserByApplicantID(jobApply.getApplicantID());
        BasicInfo basicInfo = new BasicInfoDao().queryBasicInfoByID(jobApply.getApplicantID());
        if(isOK==true) {
        	Job job = new JobDao().queryJobByID(jobApply.getJobID());
			User company = new UserDao().queryUserByCompanyID(job.getCompanyID());
			/*发送消息给职位所属企业*/
        	Message mes = new Message();
        	//消息概述
        	String message_summary = "已通知求职者 "+applicant.getName()+" 面试";
        	//消息内容
        	String message_content = ""
        			+ "已向求职者 <a href='/SWRW/company/job/resume_detail?applicant_id="+applicant.getApplicantID()+"&job_id="+job.getID()+"'>"+applicant.getName()+"</a> "
        			+ "发送了面试职位 <a href='/SWRW/company/job/job_detail?job_id="+job.getID()+"'>"+job.getName()+"</a> 的通知，有需要可直接联系："
        			+ (applicant.getEmail()==null?"":("<br>邮箱："+applicant.getEmail()))
					+ (applicant.getPhone()==null?"":("<br>手机："+applicant.getPhone()));
        	//消息发送者用户ID
        	int sender_id = 1;//系统消息
        	//消息接收者用户ID
        	int receiver_id = company.getID();//所属企业用户
        	//发送消息
        	mes.sendSingleMessage(0, message_summary, message_content, sender_id, receiver_id);
        	/*发送消息给求职者用户*/
        	//消息概述
        	message_summary = job.getName()+" 面试通知";
        	//消息内容
        	message_content = ""
        			+ "你已成功被企业 <a href='/SWRW//job/company_detail?company_id="+company.getCompanyID()+"'>"+company.getName()+"</a> 相中！🎉"
        			+ "安排你面试职位 <a href='/SWRW/job/job_detail?job_id="+job.getID()+"'>"+job.getName()+"</a>"
        			+ "<br>请保持电话畅通，留意短信或电话获取详细面试时间及地点等信息！";
        	//消息发送者用户ID
        	sender_id = 1;//系统消息
        	//消息接收者用户ID
        	receiver_id = applicant.getID();//提交简历用户
        	//发送消息
        	mes.sendSingleMessage(1, message_summary, message_content, sender_id, receiver_id);
        	Logger.getLogger(getClass()).info("通知求职者"+basicInfo.getRealName()+"面试成功。");
        }else {
        	Logger.getLogger(getClass()).error("通知求职者"+basicInfo.getRealName()+"面试出错失败。");
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("successMes","已成功通知 "+basicInfo.getRealName()+" 面试！");
			map.put("errorMes","很抱歉，遇到了未知错误，可能是服务器出错或网络问题。\n请重试或联系管理员！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("successMes","Successful notification for interview!");
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
