package com.qst.itoffer.bean;

import java.util.Date;

/**
* 职位申请信息类
* @author 节奏葳
* @version 1.0
*/
public class JobApply{
	private int apply_id;	    //职位申请标识
	private int job_id;			//职位标识
    private int applicant_id;   //求职者标识
    private Date apply_date;	//职位申请日期	
    private int apply_state;	//职位申请处理状态：1(申请)、2(审核)、3(通知)
    private Job job;			//职位
    private Date created_at;	//新增时间
    private Date updated_at;	//修改时间
    private Date deleted_at;	//删除时间
    private boolean deleted;	//是否已删除
    
    public JobApply() {}
	 
    public int getID() {
    	return apply_id;
    }
    
    public void setID(int apply_id) {
    	this.apply_id = apply_id;
    }
    
    public int getJobID() {
        return job_id;
    }
 
    public void setJobID(int job_id) {
        this.job_id = job_id;
    }
    
    public int getApplicantID() {
        return applicant_id;
    }
 
    public void setApplicantID(int applicant_id) {
        this.applicant_id = applicant_id;
    }
    
    public Date getApplyDate() {
    	return apply_date;
    }
    
    public void setApplyDate(Date apply_date) {
    	this.apply_date = apply_date;
    }
    
    public int getState() {
        return apply_state;
    }
 
    public void setState(int apply_state) {
        this.apply_state = apply_state;
    }
    
    public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
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