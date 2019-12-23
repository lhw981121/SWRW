package com.swzj.swrw.servlet.message;

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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.swzj.swrw.bean.Message;
import com.swzj.swrw.bean.Pagination;
import com.swzj.swrw.bean.User;

/**
 * Servlet implementation class MessagePagination
 */
@WebServlet("/MessagePagination")
public class MessagePagination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagePagination() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("pageNo")==null||request.getParameter("message_readed")==null||request.getParameter("queryStr")==null||
				request.getParameter("deleted")==null||request.getParameter("sortField")==null){
				response.sendRedirect("/SWRW/index");
				return;
			}
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			//获取当前页码
			int pageNo = Integer.valueOf(request.getParameter("pageNo"));
			//消息是否已读
			int message_readed = Integer.valueOf(request.getParameter("message_readed"));
			//发送者
			int sender_id = 1;
			//接收者
			int receiver_id = user.getID();
			//查询字段
			String queryStr = request.getParameter("queryStr");
			//是否已删除
			int deleted = Integer.valueOf(request.getParameter("deleted"));
			//排序字段
			String sortField = request.getParameter("sortField");
			//获取单页记录数
			int pageSize = session.getAttribute("pageSize")==null?10:Integer.valueOf(session.getAttribute("pageSize").toString());
			
			Map<String,Object> map = new HashMap<String,Object>();
			//获取分页数据总量
			int recordCount = new Message().getPageDataMessageCount(message_readed, sender_id, receiver_id, queryStr, deleted);
			//实例化分页对象
			Pagination pagination = new Pagination(recordCount,pageNo,pageSize);
			//获取分页数据
			List<Message> message = new Message().getPageDataMessage(pagination.getPageNo(), pageSize, message_readed, sender_id, receiver_id, queryStr, deleted, sortField);
			map.put("message", message);
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
