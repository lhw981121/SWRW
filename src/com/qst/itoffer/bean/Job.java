package com.qst.itoffer.bean;

import java.util.Date;

/**
* 职位信息类
* @author 节奏葳
* @version 1.0
*/
public class Job{
	private int job_id;	        //职位标识
	private int company_id;		//所属企业
    private String job_name;   	//职位名称
    private int job_hiringnum;	//招聘人数
    private String job_salary;	//职位薪资区间
    private String job_area;	//所在地
    private String job_desc;	//职位描述
    private Date job_endtime;	//结束日期
    private int job_state;		//招聘状态：0审核中 1招聘中 2已暂停 3已结束
    private Date created_at;	//新增时间
    private Date updated_at;	//修改时间
    private Date deleted_at;	//删除时间
    private boolean deleted;	//是否已删除
    
    public Job() {}
	 
    public int getID() {
    	return job_id;
    }
    
    public void setID(int job_id) {
    	this.job_id = job_id;
    }
    
    public int getCompanyID() {
        return company_id;
    }
 
    public void setCompanyID(int company_id) {
        this.company_id = company_id;
    }
    
    public String getName() {
        return job_name;
    }
 
    public void setName(String job_name) {
        this.job_name = job_name;
    }
    
    public int getHiringNum() {
        return job_hiringnum;
    }
 
    public void setHiringNum(int job_hiringnum) {
        this.job_hiringnum = job_hiringnum;
    }
    
    public String getSalary() {
        return job_salary;
    }
 
    public void setSalary(String job_salary) {
        this.job_salary = job_salary;
    }
 
    public String getArea() {
        return job_area;
    }
 
    public void setArea(String job_area) {
        this.job_area = job_area;
    }
    
    public String getDesc() {
        return job_desc;
    }
 
    public void setDesc(String job_desc) {
        this.job_desc = job_desc;
    }
    
    public Date getEndDate() {
        return job_endtime;
    }
    
    public void setEndDate(Date job_endtime) {
    	this.job_endtime = job_endtime;
    }
    
    public int getState() {
        return job_state;
    }
 
    public void setState(int job_state) {
        this.job_state = job_state;
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

