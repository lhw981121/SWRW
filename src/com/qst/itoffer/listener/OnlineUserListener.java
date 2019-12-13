package com.qst.itoffer.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.qst.itoffer.bean.User;

/**
 * Application Lifecycle Listener implementation class OnlineUserListener
 *
 */
@WebListener
public class OnlineUserListener implements HttpSessionListener {

    public OnlineUserListener() {}

    public void sessionCreated(HttpSessionEvent se)  {}

    //session销毁时让用户离线
    @SuppressWarnings("unchecked")
	public void sessionDestroyed(HttpSessionEvent se)  { 
		HttpSession session = se.getSession();
		// 判断用户是否登录
		if (session.getAttribute("user") != null) {
			// 取得登录的用户
			User user = (User) session.getAttribute("user");
			// 更新用户为离线状态
			ServletContext application = session.getServletContext();
			Map<String, Object> userMap = application.getAttribute("userMap") == null ? new HashMap<String, Object>()
					: (Map<String, Object>) application.getAttribute("userMap");
			if (userMap.containsKey(session.getId())) {
				userMap.remove(session.getId());
				Logger.getLogger(getClass()).info("用户" + user.getID() + user.getName() + "状态超时已离线。");
			}
			application.setAttribute("userMap", userMap);
		}
    }
	
}
