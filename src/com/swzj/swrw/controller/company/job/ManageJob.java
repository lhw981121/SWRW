package com.swzj.swrw.controller.company.job;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swzj.swrw.bean.Company;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.CompanyDao;

/**
 * Servlet implementation class ManageJob
 */
@WebServlet("/company/job/manage_job")
public class ManageJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageJob() {
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
		request.setAttribute("company", company);
		request.getRequestDispatcher("/WEB-INF/view/company/job/manageJob.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
