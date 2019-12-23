package com.swzj.swrw.controller.applicant.resume;

import java.io.IOException;
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


/**
 * Servlet implementation class CompleteResume
 */
@WebServlet("/applicant/resume/complete_resume")
public class CompleteResume extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteResume() {
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
		request.setAttribute("education", education);
		request.setAttribute("projectExp", projectExp);
		request.setAttribute("workExp", workExp);
		
		request.getRequestDispatcher("/WEB-INF/view/applicant/resume/completeResume.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
