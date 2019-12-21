package com.swzj.swrw.servlet.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.swzj.swrw.bean.BasicInfo;
import com.swzj.swrw.bean.Job;
import com.swzj.swrw.bean.JobApply;
import com.swzj.swrw.bean.Pagination;
import com.swzj.swrw.dao.BasicInfoDao;
import com.swzj.swrw.dao.JobApplyDao;
import com.swzj.swrw.dao.JobDao;

/**
 * Servlet implementation class JobApplyPagination
 */
@WebServlet("/JobApplyPagination")
public class JobApplyPagination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobApplyPagination() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("pageNo")==null||request.getParameter("job_id")==null||request.getParameter("applicant_id")==null||
				request.getParameter("apply_state")==null||request.getParameter("deleted")==null||request.getParameter("sortField")==null){
				response.sendRedirect("/SWRW/index");
			return;
		}
		//获取当前页码
		int pageNo = Integer.valueOf(request.getParameter("pageNo"));
		//职位ID
		int job_id = Integer.valueOf(request.getParameter("job_id"));
		//求职者ID
		int applicant_id = Integer.valueOf(request.getParameter("applicant_id"));
		//职位申请状态
		int apply_state = Integer.valueOf(request.getParameter("apply_state"));
		//是否已删除
		int deleted = Integer.valueOf(request.getParameter("deleted"));
		//排序字段
		String sortField = request.getParameter("sortField");
		//获取单页记录数
		int pageSize = request.getSession().getAttribute("pageSize")==null?10:Integer.valueOf(request.getSession().getAttribute("pageSize").toString());
		
		Map<String,Object> map = new HashMap<String,Object>();
		//获取分页数据总量
		int recordCount = new JobApplyDao().getPageDataJobApplyCount(job_id, applicant_id, apply_state, deleted);
		//实例化分页对象
		Pagination pagination = new Pagination(recordCount,pageNo,pageSize);
		//获取分页数据
		List<JobApply> jobApplys = new JobApplyDao().getPageDataJobApply(pagination.getPageNo(), pageSize, job_id, applicant_id, apply_state, deleted, sortField);
		List<Job> jobs = new ArrayList<Job>();
		JobDao jobDao = new JobDao();
		List<BasicInfo> basicInfos = new ArrayList<BasicInfo>();
		BasicInfoDao basicInfoDao = new BasicInfoDao();
		for(JobApply jobApply : jobApplys) {
			jobs.add(jobDao.queryJobByID(jobApply.getJobID()));
			basicInfos.add(basicInfoDao.queryBasicInfoByID(jobApply.getApplicantID()));
		}
		map.put("jobs", jobs);
		map.put("basicInfos", basicInfos);
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
