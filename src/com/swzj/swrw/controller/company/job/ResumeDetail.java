package com.swzj.swrw.controller.company.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swzj.swrw.bean.BasicInfo;
import com.swzj.swrw.bean.Education;
import com.swzj.swrw.bean.Job;
import com.swzj.swrw.bean.JobApply;
import com.swzj.swrw.bean.ProjectExp;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.bean.WorkExp;
import com.swzj.swrw.dao.BasicInfoDao;
import com.swzj.swrw.dao.EducationDao;
import com.swzj.swrw.dao.JobApplyDao;
import com.swzj.swrw.dao.JobDao;
import com.swzj.swrw.dao.ProjectExpDao;
import com.swzj.swrw.dao.WorkExpDao;
import com.swzj.swrw.util.COMUtil;

/**
 * Servlet implementation class ResumeDetail
 */
@WebServlet("/company/job/resume_detail")
public class ResumeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResumeDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("applicant_id")==null||request.getParameter("job_id")==null) {
			request.getRequestDispatcher("/WEB-INF/view/404.jsp").forward(request, response);
			return;
		}
		int applicant_id = Integer.valueOf(request.getParameter("applicant_id"));
		int job_id = Integer.valueOf(request.getParameter("job_id"));
		HttpSession session = request.getSession();
		Job job = new JobDao().queryJobByID(job_id);
		JobApply jobApply = new JobApplyDao().queryJobApply(job_id,applicant_id);
		if(job.getCompanyID()==((User)session.getAttribute("user")).getCompanyID()&&jobApply != null) {//判断是否是当前企业的职位及收到的简历
			BasicInfo basicInfo = new BasicInfoDao().queryBasicInfoByID(applicant_id);
			Education education = new EducationDao().queryEducationByBasicinfoID(basicInfo.getID());
			ProjectExp projectExp = new ProjectExpDao().queryProjectExpByBasicinfoID(basicInfo.getID());
			WorkExp workExp = new WorkExpDao().queryWorkExpByBasicinfoID(basicInfo.getID());
			request.setAttribute("basicInfo", basicInfo);
			List<String> educationStr = new ArrayList<>();
			educationStr = COMUtil.getEducationStr(education);
			List<String> projectExpStr = new ArrayList<>();
			projectExpStr = COMUtil.getProjectExpStr(projectExp);
			List<String> workExpStr = new ArrayList<>();
			workExpStr = COMUtil.getWorkExpStr(workExp);
			request.setAttribute("educationStr", educationStr);
			request.setAttribute("projectExpStr", projectExpStr);
			request.setAttribute("workExpStr", workExpStr);
			
			request.setAttribute("jobApply", jobApply);
			request.getRequestDispatcher("/WEB-INF/view/company/job/resumeDetail.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/view/404.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
