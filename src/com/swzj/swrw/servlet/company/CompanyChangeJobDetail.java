package com.swzj.swrw.servlet.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.swzj.swrw.bean.Job;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.JobDao;
import com.swzj.swrw.util.COMUtil;

/**
 * Servlet implementation class CompanyChangeJobDetail
 */
@WebServlet("/CompanyChangeJobDetail")
public class CompanyChangeJobDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyChangeJobDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("job_id")==null||request.getParameter("job_name")==null||request.getParameter("job_desc")==null||
				request.getParameter("job_area")==null||request.getParameter("job_salary")==null||request.getParameter("job_hiringnum")==null||
				request.getParameter("job_endtime")==null||request.getParameter("job_state")==null){
				response.sendRedirect("/SWRW/index");
				return;
			}
			HttpSession session = request.getSession();
	        User user = (User)session.getAttribute("user");
	        
	        int job_id = Integer.valueOf(request.getParameter("job_id"));
			String job_name = request.getParameter("job_name");
			String job_desc = request.getParameter("job_desc");
			String job_area = request.getParameter("job_area");
			String job_salary = request.getParameter("job_salary");
			int job_hiringnum = Integer.valueOf(request.getParameter("job_hiringnum"));
			Date job_endtime = COMUtil.strToDate(request.getParameter("job_endtime"));
			int job_state = Integer.valueOf(request.getParameter("job_state"));
			String mode = request.getParameter("mode")==null?"":request.getParameter("mode");
			Job job = new JobDao().queryJobByID(job_id);

			job.setName(job_name);
			job.setDesc(job_desc);
			job.setArea(job_area);
			job.setSalary(job_salary);
			job.setHiringNum(job_hiringnum);
			job.setEndDate(job_endtime);
			if(mode.length()!=0) {//修改职位信息模式
				if(job_endtime.after(new Date())) {
					job.setState(1);
				}else if(job_endtime.before(new Date())) {
					job.setState(3);
				}
			}else {
				job.setState(job_state);
			}
			
			boolean isOK = false;

			isOK = new JobDao().updateJob(job);

	        if(isOK==true) {
	        	Logger.getLogger(getClass()).info("企业 "+user.getCompanyID()+" 成功修改了职位 "+job.getID()+" 信息。");
	        }else {
	        	Logger.getLogger(getClass()).error("企业"+user.getCompanyID()+"修改职位 "+job.getID()+" 信息出错失败。");
	        }
	        
	        Map<String,Object> map = new HashMap<String,Object>();
			map.put("isOK", isOK);

			if(session.getAttribute("language").equals("zh_CN")) {
				map.put("errorMes","遇到未知问题，请重试或联系管理员！");
				map.put("successMes","职位信息修改成功！\n窗口即将关闭。。。");
			}else if(session.getAttribute("language").equals("en_US")){
				map.put("errorMes","Encounter unknown problem, please try again or contact administrator!");
				map.put("successMes","Position information modified successfully!\nThe window is about to close...");
			}
			
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
