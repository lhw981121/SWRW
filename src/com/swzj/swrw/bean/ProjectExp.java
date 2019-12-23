package com.swzj.swrw.bean;

import java.util.Date;

/**
* 项目经验信息类
* @author 节奏葳
* @version 1.0
*/
public class ProjectExp{
	private int project_id;	   		//项目经验标识
	private int basicinfo_id;		//简历标识
    private String project_name;   	//项目名称
    private String project_period;	//项目参考与时间段
    private String project_job;		//担任职务
    private String project_desc;	//项目简述
    private Date created_at;		//新增时间
    private Date updated_at;		//修改时间
    private Date deleted_at;		//删除时间
    private boolean deleted;		//是否已删除
    
    public ProjectExp() {}
	
    //获取项目经验信息完整度
    public int getIntegrity() {
    	int count = 0;
    	if(project_name!=null&&project_name.length()!=0) count++;
    	if(project_period!=null&&project_period.length()!=0) count++;
    	if(project_job!=null&&project_job.length()!=0) count++;
    	if(project_desc!=null&&project_desc.length()!=0) count++;
    	return count;
    }
    
    public int getID() {
    	return project_id;
    }
    
    public void setID(int project_id) {
    	this.project_id = project_id;
    }
    
    public int getBasicInfoID() {
        return basicinfo_id;
    }
 
    public void setBasicInfoID(int basicinfo_id) {
        this.basicinfo_id = basicinfo_id;
    }
    
    public String getName() {
        return project_name;
    }
 
    public void setName(String project_name) {
        this.project_name = project_name;
    }
    
    public String getPeriod() {
        return project_period;
    }
 
    public void setPeriod(String project_period) {
        this.project_period = project_period;
    }
    
    public String getProjectJob() {
        return project_job;
    }
 
    public void setProjectJob(String project_job) {
        this.project_job = project_job;
    }
    
    public String getProjectDesc() {
        return project_desc;
    }
 
    public void setProjectDesc(String project_desc) {
        this.project_desc = project_desc;
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