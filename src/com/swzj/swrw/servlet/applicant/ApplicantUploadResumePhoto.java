package com.swzj.swrw.servlet.applicant;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.swzj.swrw.dao.BasicInfoDao;
import com.swzj.swrw.util.COMUtil;

/**
 * Servlet implementation class ApplicantUploadResumePhoto
 */
@WebServlet("/ApplicantUploadResumePhoto")
public class ApplicantUploadResumePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicantUploadResumePhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("basicInfo_id")==null||request.getParameter("dataURL")==null){
			response.sendRedirect("/SWRW/index");
			return;
		}
		
		String basicInfo_id = request.getParameter("basicInfo_id");
		String dataURL = request.getParameter("dataURL");
		boolean isOK = false;

		String path=request.getSession().getServletContext().getRealPath("/");
		path += "/public/images/applicant";
		String imgName=basicInfo_id+".jpg";
		
		isOK = new BasicInfoDao().updateHeadShot(Integer.valueOf(basicInfo_id), imgName);

        if(isOK==true) {
        	COMUtil.decodeBase64DataURLToImageAndUpload(dataURL, path, imgName);
        	Logger.getLogger(getClass()).info("简历基本信息"+basicInfo_id+"更换简历照片成功。");
        }else {
        	Logger.getLogger(getClass()).error("简历基本信息"+basicInfo_id+"更换简历照片失败。");
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);

		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","遇到未知问题，请重试或联系管理员！");
			map.put("successMes","简历照片上传成功！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","Encounter unknown problem, please try again or contact administrator!");
			map.put("successMes","Resume photo uploaded successfully!");
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
