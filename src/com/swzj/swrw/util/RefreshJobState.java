package com.swzj.swrw.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.swzj.swrw.dao.JobDao;

/**
 * 刷新职位状态，每天定时执行
* @author 节奏葳
* @version 1.0
 *
 */
public class RefreshJobState {
	//时间间隔
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	//构造函数
	public RefreshJobState() {
		new RefreshJobStateTask().run();//立即刷新一次职位状态
		Calendar calendar = Calendar.getInstance();       
		/*** 定制每日00:00:01执行方法 ***/
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 1);
		Date date=calendar.getTime(); //第一次执行定时任务的时间
		//如果第一次执行定时任务的时间 小于 当前的时间
		//此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。循环执行的周期则以当前时间为准
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}
		Timer timer = new Timer();
		RefreshJobStateTask task = new RefreshJobStateTask();
		//安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.schedule(task,date,PERIOD_DAY);
	}
	
	// 增加或减少天数
	public Date addDay(Date date, int day) {
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(date);
		startDate.add(Calendar.DAY_OF_MONTH, day);
		return startDate.getTime();
	}
	
	//刷新职位状态任务
	public class RefreshJobStateTask extends TimerTask {
		@Override
		public void run() {
			try {
				//在这里写你要执行的内容
				if(new JobDao().refreshJobState()) {
					Logger.getLogger(getClass()).info("所有职位状态已刷新");
				}else {
					Logger.getLogger(getClass()).error("刷新所有职位状态出错失败");
				}
			} catch (Exception e) {
				Logger.getLogger(getClass()).error("-------------刷新职位状态发生异常--------------");
			}
		}
	}
	
	public static void main(String[] args) {
	}
}
