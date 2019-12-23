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
import com.swzj.swrw.bean.BasicInfo;
import com.swzj.swrw.bean.JobApply;
import com.swzj.swrw.bean.User;
import com.swzj.swrw.dao.BasicInfoDao;
import com.swzj.swrw.dao.JobApplyDao;
import com.swzj.swrw.dao.UserDao;

/**
 * Servlet implementation class ShortlistApplicant
 */
@WebServlet("/ShortlistApplicant")
public class ShortlistApplicant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShortlistApplicant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("apply_id")==null) {
			response.sendRedirect("/SWRW/index");
			return;
		}
		HttpSession session = request.getSession();
		int apply_id = Integer.valueOf(request.getParameter("apply_id"));
		
		JobApplyDao dao = new JobApplyDao();
		JobApply jobApply = dao.queryJobApplyByID(apply_id);
		jobApply.setUpdated(new Date());
        
        boolean isOK = false;
        isOK = dao.updateJobApply(jobApply);
        
        User applicant = new UserDao().queryUserByApplicantID(jobApply.getApplicantID());
        BasicInfo basicInfo = new BasicInfoDao().queryBasicInfoByID(jobApply.getApplicantID());
        if(isOK==true) {
        	Logger.getLogger(getClass()).info("求职者"+applicant.getName()+"的简历列入候选。");
        }else {
        	Logger.getLogger(getClass()).error("将求职者"+applicant.getName()+"的简历列入候选失败。");
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("successMes","已成功将 "+basicInfo.getRealName()+" 的简历列入候选！");
			map.put("errorMes","很抱歉，遇到了未知错误，可能是服务器出错或网络问题。\n请重试或联系管理员！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("successMes","Successfully shortlisted resume!");
			map.put("errorMes","Sorry, encountered unknown error, may be server error or network problem, please try again or contact the administrator!");
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
