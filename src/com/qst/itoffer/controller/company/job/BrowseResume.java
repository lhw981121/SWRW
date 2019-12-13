package com.qst.itoffer.controller.company.job;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.itoffer.bean.BasicInfo;
import com.qst.itoffer.bean.Education;
import com.qst.itoffer.bean.ProjectExp;
import com.qst.itoffer.bean.WorkExp;
import com.qst.itoffer.dao.BasicInfoDao;
import com.qst.itoffer.dao.EducationDao;
import com.qst.itoffer.dao.ProjectExpDao;
import com.qst.itoffer.dao.WorkExpDao;

/**
 * Servlet implementation class BrowseResume
 */
@WebServlet("/company/job/browse_resume")
public class BrowseResume extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseResume() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BasicInfo basicInfo = new BasicInfoDao().queryBasicInfoByID(1);
		Education education = new EducationDao().queryEducationByBasicinfoID(basicInfo.getID());
		ProjectExp projectExp = new ProjectExpDao().queryProjectExpByBasicinfoID(basicInfo.getID());
		WorkExp workExp = new WorkExpDao().queryWorkExpByBasicinfoID(basicInfo.getID());
		request.setAttribute("basicInfo", basicInfo);
		request.setAttribute("education", education);
		request.setAttribute("projectExp", projectExp);
		request.setAttribute("workExp", workExp);
		
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
