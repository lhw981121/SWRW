package com.swzj.swrw.controller.company.job;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swzj.swrw.bean.Company;
import com.swzj.swrw.bean.Job;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.CompanyDao;
import com.swzj.swrw.dao.JobDao;
import com.swzj.swrw.dao.UserDao;

/**
 * Servlet implementation class JobDetail
 */
@WebServlet("/company/job/job_detail")
public class CompanyJobDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyJobDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int company_id = ((User)session.getAttribute("user")).getCompanyID();
		Company company = new CompanyDao().queryCompanyByID(company_id);
		if(company.getState()<=0) {//企业未认证
			request.setAttribute("limitedAccess", 4);
			request.getRequestDispatcher("/user/mycenter").forward(request, response);
			return;
		}
		int job_id = request.getParameter("job_id")==null?0:Integer.valueOf(request.getParameter("job_id"));
		Job job = new JobDao().queryJobByID(job_id);
		User company_user = new UserDao().queryUserByCompanyID(company_id);
		if(job!=null&&job.getCompanyID()==company_id) {
			request.setAttribute("job", job);
			request.setAttribute("company", company);
			request.setAttribute("company_user", company_user);
			request.getRequestDispatcher("/WEB-INF/view/company/job/jobDetail.jsp").forward(request, response);
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
