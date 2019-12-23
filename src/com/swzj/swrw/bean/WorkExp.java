package com.swzj.swrw.bean;

import java.util.Date;

/**
* 工作经验信息类
* @author 节奏葳
* @version 1.0
*/
public class WorkExp {
	private int work_id;			//工作经验标识
	private int basicinfo_id;		//简历标识
	private String work_period;		//工作时间
	private String department;		//工作部门
	private String work_title;		//工作职称
	private Date created_at;		//新增时间
    private Date updated_at;		//修改时间
    private Date deleted_at;		//删除时间
    private boolean deleted;		//是否已删除
    
    public WorkExp() {}
    
    //获取工作经验信息完整度
    public int getIntegrity() {
    	int count = 0;
    	if(work_period!=null&&work_period.length()!=0) count++;
    	if(department!=null&&department.length()!=0) count++;
    	if(work_title!=null&&work_title.length()!=0) count++;
    	return count;
    }
    
	public int getID() {
		return work_id;
	}
	
	public void setID(int work_id) {
		this.work_id = work_id;
	}
	
	public int getBasicinfoID() {
		return basicinfo_id;
	}
	
	public void setBasicinfoID(int basicinfo_id) {
		this.basicinfo_id = basicinfo_id;
	}
	
	public String getPeriod() {
		return work_period;
	}
	
	public void setPeriod(String work_period) {
		this.work_period = work_period;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getWorkTitle() {
		return work_title;
	}
	
	public void setWorkTitle(String work_title) {
		this.work_title = work_title;
	}
    
	public Date getCreated() {
        return created_at;
    }
    
    public void setCreated(Date created_at) {
    	this.created_at = created_at;
    }

    public Date getUpdated() {
        return updated_at;
    }
    
    public void setUpdated(Date updated_at) {
    	this.updated_at = updated_at;
    }
    
    public Date getDeleted() {
    	return deleted_at;
    }
    
    public void setDeleted(Date deleted_at) {
    	this.deleted_at = deleted_at;
    }
    
    public boolean getIsDeleted() {
    	return deleted;
    }
    
    public void setIsDeleted(boolean deleted) {
    	this.deleted = deleted;
    }
	
}
