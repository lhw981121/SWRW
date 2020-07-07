package com.swzj.swrw.servlet.common;

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
import com.swzj.swrw.bean.Job;
import com.swzj.swrw.bean.Pagination;
import com.swzj.swrw.dao.JobDao;

/**
 * Servlet implementation class JobListingData
 */
@WebServlet("/JobListingData")
public class JobListingData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobListingData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("pageNo")==null||request.getParameter("job_state")==null||request.getParameter("job_area")==null||
				request.getParameter("company_state")==null||request.getParameter("company_size")==null||request.getParameter("company_type")==null||
				request.getParameter("queryStr")==null||request.getParameter("sortField")==null||request.getParameter("mode")==null){
				response.sendRedirect("/SWRW/index");
			return;
		}
		//获取搜索模式
		String mode = request.getParameter("mode");
		//获取当前页码
		int pageNo = Integer.valueOf(request.getParameter("pageNo"));
		//职位招聘状态
		int job_state = Integer.valueOf(request.getParameter("job_state"));
		//职位所在地
		String job_area = request.getParameter("job_area");
		//企业招聘状态
		int company_state = Integer.valueOf(request.getParameter("company_state"));
		//企业规模
		String company_size = request.getParameter("company_size");
		//企业性质
		String company_type = request.getParameter("company_type");
		//查询字段
		String queryStr = request.getParameter("queryStr");
		//排序字段
		String sortField = request.getParameter("sortField");
		//获取单页记录数
		int pageSize = request.getSession().getAttribute("pageSize")==null?10:Integer.valueOf(request.getSession().getAttribute("pageSize").toString());
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Job> jobs = null;
		Pagination pagination = null;
		if(mode.equals("1")) {//按职位搜索
			//获取分页数据总量
			int recordCount = new JobDao().getPageDataJobCount(job_state, job_area, company_state, queryStr);
			//实例化分页对象
			pagination = new Pagination(recordCount,pageNo,pageSize);
			//获取分页数据
			jobs = new JobDao().getPageDataJob(pagination.getPageNo(), pageSize, job_state, job_area, company_state, queryStr, sortField);
		}else {//按企业搜索
			//获取分页数据总量
			int recordCount = new JobDao().getPageDataJobCount(company_state, job_area, company_size, company_type, queryStr);
			//实例化分页对象
			pagination = new Pagination(recordCount,pageNo,pageSize);
			//获取分页数据
			jobs = new JobDao().getPageDataJob(pagination.getPageNo(), pageSize, company_state, job_area, company_size, company_type, queryStr, sortField);
		}
		map.put("jobs", jobs);
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
