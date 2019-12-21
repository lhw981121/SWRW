package com.swzj.swrw.controller.applicant.resume;

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
import com.swzj.swrw.bean.ProjectExp;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.bean.WorkExp;
import com.swzj.swrw.dao.BasicInfoDao;
import com.swzj.swrw.dao.EducationDao;
import com.swzj.swrw.dao.ProjectExpDao;
import com.swzj.swrw.dao.WorkExpDao;
import com.swzj.swrw.util.COMUtil;

/**
 * Servlet implementation class ViewResume
 */
@WebServlet("/applicant/resume/view_resume")
public class ViewResume extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewResume() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int applicant_id = ((User)session.getAttribute("user")).getApplicantID();
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
		
		request.getRequestDispatcher("/WEB-INF/view/applicant/resume/viewResume.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
