package com.swzj.swrw.controller.job;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swzj.swrw.bean.Company;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.CompanyDao;
import com.swzj.swrw.dao.UserDao;

/**
 * Servlet implementation class CompanyDetail
 */
@WebServlet("/job/company_detail")
public class JobCompanyDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobCompanyDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int company_id = request.getParameter("company_id")==null?0:Integer.valueOf(request.getParameter("company_id"));
		Company company = new CompanyDao().queryCompanyByID(company_id);
		if(company!=null&&company.getState()>0) {
			User company_user = new UserDao().queryUserByCompanyID(company_id);
			request.setAttribute("company", company);
			request.setAttribute("company_user", company_user);
			request.getRequestDispatcher("/WEB-INF/view/job/jobCompanyDetail.jsp").forward(request, response);
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
