package com.swzj.swrw.util;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.swzj.swrw.dao.JobDao;

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