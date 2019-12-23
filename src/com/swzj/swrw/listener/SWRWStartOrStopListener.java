package com.swzj.swrw.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.swzj.swrw.util.RefreshJobState;

/**
 * Application Lifecycle Listener implementation class RefreshJobState
 *
 */
@WebListener
public class SWRWStartOrStopListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SWRWStartOrStopListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	//定时刷新所有职位的招聘状态
        new RefreshJobState();
    }
	
}
