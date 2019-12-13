package com.qst.itoffer.servlet.user;

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
import com.qst.itoffer.util.COMUtil;

/**
 * Servlet implementation class UserUploadAvatar
 */
@WebServlet("/UserUploadAvatar")
public class UserUploadAvatar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUploadAvatar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_id")==null||request.getParameter("dataURL")==null){
			response.sendRedirect("/SWRW/index");
			return;
		}
		
		String user_id = request.getParameter("user_id");
		String dataURL = request.getParameter("dataURL");
		boolean isOK = false;

		String path=request.getSession().getServletContext().getRealPath("/");
		path += "public/images/user/avatar";
		String imgName=user_id+".jpg";
		
		isOK = true;
		
		COMUtil.decodeBase64DataURLToImageAndUpload(dataURL, path, imgName);

        if(isOK==true) {
        	Logger.getLogger(getClass()).info("用户"+user_id+"上传头像成功。");
        }else {
        	Logger.getLogger(getClass()).error("用户"+user_id+"上传头像失败。");
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);

		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","遇到未知问题，请重试或联系管理员！");
			map.put("successMes","头像上传成功！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","Encounter unknown problem, please try again or contact administrator!");
			map.put("successMes","Avatar uploaded successfully!");
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
