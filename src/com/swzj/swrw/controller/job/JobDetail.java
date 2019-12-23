package com.swzj.swrw.controller.job;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swzj.swrw.bean.Company;
import com.swzj.swrw.bean.Job;
import com.swzj.swrw.bean.JobApply;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.CompanyDao;
import com.swzj.swrw.dao.JobApplyDao;
import com.swzj.swrw.dao.JobDao;
import com.swzj.swrw.dao.UserDao;

/**
 * Servlet implementation class JobDetail
 */
@WebServlet("/job/job_detail")
public class JobDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int job_id = request.getParameter("job_id")==null?0:Integer.valueOf(request.getParameter("job_id"));
		Job job = new JobDao().queryJobByID(job_id);
		if(job!=null&&new CompanyDao().queryCompanyByID(job.getCompanyID()).getState()==1&&job.getState()>0) {
			User company_user = new UserDao().queryUserByCompanyID(job.getCompanyID());
			Company company = new CompanyDao().queryCompanyByID(job.getCompanyID());
			HttpSession session = request.getSession();
			User user = session.getAttribute("user")==null?new User():(User)session.getAttribute("user");
			if(user.getID()!=0) {
				JobApply jobApply = new JobApplyDao().queryJobApply(job_id,user.getApplicantID());
				request.setAttribute("jobApply", jobApply);
			}
			request.setAttribute("job", job);
			request.setAttribute("company", company);
			request.setAttribute("company_user", company_user);
			request.getRequestDispatcher("/WEB-INF/view/job/jobDetail.jsp").forward(request, response);
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
