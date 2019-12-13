package com.qst.itoffer.controller.message;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qst.itoffer.bean.Message;
import com.qst.itoffer.bean.Pagination;
import com.qst.itoffer.bean.User;

/**
 * Servlet implementation class HistoryMessage
 */
@WebServlet("/message/history_message")
public class HistoryMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		//获取当前页码
		int pageNo = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		//消息是否已读
		int message_readed = 1;
		//发送者
		int sender_id = 1;
		//接收者
		int receiver_id = user.getID();
		//查询字段
		String queryStr = "";
		//是否已删除
		int deleted = 0;
		//排序字段
		String sortField = "created_at DESC";
		//获取单页记录数
		int pageSize = session.getAttribute("pageSize")==null?10:Integer.valueOf(session.getAttribute("pageSize").toString());
		
		//获取分页数据总量
		int recordCount = new Message().getPageDataMessageCount(message_readed, sender_id, receiver_id, queryStr, deleted);
		//实例化分页对象
		Pagination pagination = new Pagination(recordCount,pageNo,pageSize);
		//获取分页数据
		List<Message> message = new Message().getPageDataMessage(pagination.getPageNo(), pageSize, message_readed, sender_id, receiver_id, queryStr, deleted, sortField);
		
		request.setAttribute("pagination", pagination);
		request.setAttribute("message", message);
		request.setAttribute("startFloor", (pageNo-1)*pageSize);
		request.setAttribute("notUseScriptPagination", true);
		request.setAttribute("urlQueryStr", "?");
		
		request.getRequestDispatcher("/WEB-INF/view/message/historyMessage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
