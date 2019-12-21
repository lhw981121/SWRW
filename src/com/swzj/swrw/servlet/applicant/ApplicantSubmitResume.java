package com.swzj.swrw.servlet.applicant;

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

import com.google.gson.Gson;
import com.swzj.swrw.dao.ResumeDao;
import com.swzj.swrw.util.COMUtil;

/**
 * Servlet implementation class ApplicantSubmitResume
 */
@WebServlet("/ApplicantSubmitResume")
public class ApplicantSubmitResume extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicantSubmitResume() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("basicInfo_id")==null||request.getParameter("realName")==null||request.getParameter("birthday")==null||
			request.getParameter("email")==null||request.getParameter("gender")==null||request.getParameter("job_experience")==null||
			request.getParameter("telephone")==null||request.getParameter("current_loc")==null||request.getParameter("resident_loc")==null||
			request.getParameter("job_intension")==null||request.getParameter("graduate_school")==null||request.getParameter("profession")==null||
			request.getParameter("graduation_date")==null||request.getParameter("education_degree")==null||request.getParameter("project_name")==null||
			request.getParameter("project_job")==null||request.getParameter("project_period")==null||request.getParameter("project_desc")==null||
			request.getParameter("work_title")==null||request.getParameter("department")==null||request.getParameter("work_period")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		//简历基本信息
		String basicInfo_id = request.getParameter("basicInfo_id");
		String realName = request.getParameter("realName");
		Date birthday = COMUtil.strToDate(request.getParameter("birthday"));
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String job_experience = request.getParameter("job_experience");
		String telephone = request.getParameter("telephone");
		String current_loc = request.getParameter("current_loc");
		String resident_loc = request.getParameter("resident_loc");
		String job_intension = request.getParameter("job_intension");
		//教育经历
		String graduate_school = request.getParameter("graduate_school");
		String profession = request.getParameter("profession");
		String graduation_date = request.getParameter("graduation_date");
		String education_degree = request.getParameter("education_degree");
		//项目经验
		String project_name = request.getParameter("project_name");
		String project_job = request.getParameter("project_job");
		String project_period = request.getParameter("project_period");
		String project_desc = request.getParameter("project_desc");
		//工作经验
		String work_title = request.getParameter("work_title");
		String department = request.getParameter("department");
		String work_period = request.getParameter("work_period");
		
		
        boolean isOK = false;
        
        isOK=ResumeDao.updateResume(basicInfo_id, realName, birthday, email, gender, job_experience, telephone, current_loc, resident_loc, job_intension, graduate_school, 
        		profession, graduation_date, education_degree, project_name, project_job, project_period, project_desc, work_title, department, work_period);
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("successMes","简历信息更新成功！\n即将跳转到简历查看页面。。。");
			map.put("errorMes","很抱歉，遇到了未知错误，可能是服务器出错或网络问题。\n请重试或联系管理员！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("successMes","Resume information updated successfully!\nJump to resume view page soon...");
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
