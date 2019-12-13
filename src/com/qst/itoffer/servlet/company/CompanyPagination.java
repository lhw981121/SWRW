package com.qst.itoffer.servlet.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.itoffer.bean.Company;
import com.qst.itoffer.bean.Pagination;
import com.qst.itoffer.dao.CompanyDao;

/**
 * Servlet implementation class CompanyPagination
 */
@WebServlet("/CompanyPagination")
public class CompanyPagination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyPagination() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("pageNo")==null||request.getParameter("company_state")==null||request.getParameter("company_area")==null||
			request.getParameter("company_size")==null||request.getParameter("company_type")==null||request.getParameter("queryStr")==null||
			request.getParameter("deleted")==null||request.getParameter("sortField")==null){
			response.sendRedirect("/SWRW/index");
			return;
		}
		//获取当前页码
		int pageNo = Integer.valueOf(request.getParameter("pageNo"));
		//企业招聘状态
		int company_state = Integer.valueOf(request.getParameter("company_state"));
		//企业所在地
		String company_area = request.getParameter("company_area");
		//企业规模
		String company_size = request.getParameter("company_size");
		//企业性质
		String company_type = request.getParameter("company_type");
		//查询字段
		String queryStr = request.getParameter("queryStr");
		//是否已删除
		int deleted = Integer.valueOf(request.getParameter("deleted"));
		//排序字段
		String sortField = request.getParameter("sortField");
		//获取单页记录数
		int pageSize = request.getSession().getAttribute("pageSize")==null?10:Integer.valueOf(request.getSession().getAttribute("pageSize").toString());
		
		Map<String,Object> map = new HashMap<String,Object>();
		//获取分页数据总量
		int recordCount = new CompanyDao().getPageDataCompanyCount(company_state, company_area, company_size, company_type, queryStr, deleted);
		//实例化分页对象
		Pagination pagination = new Pagination(recordCount,pageNo,pageSize);
		//获取分页数据
		List<Company> company = new CompanyDao().getPageDataCompany(pageNo, pageSize, company_state, company_area, company_size, company_type, queryStr, deleted, sortField);
		map.put("company", company);
		map.put("pagination", pagination);
		
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
