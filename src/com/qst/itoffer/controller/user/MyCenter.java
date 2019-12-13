package com.qst.itoffer.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qst.itoffer.bean.Company;
import com.qst.itoffer.bean.User;
import com.qst.itoffer.dao.CompanyDao;

/**
 * Servlet implementation class MyCenter
 */
@WebServlet("/user/mycenter")
public class MyCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCenter() {
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
		request.setAttribute("company", company);
		
		request.getRequestDispatcher("/WEB-INF/view/user/myCenter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
