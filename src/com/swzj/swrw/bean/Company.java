package com.swzj.swrw.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.swzj.swrw.dao.JobDao;

/**
* 企业信息类
* @author 节奏葳
* @version 1.0
*/
public class Company{
	private int company_id;	        //企业标识
	private String company_name;	//企业名称
	private String company_legal;	//企业法人
    private String company_area;   	//企业所在地
    private String company_size;	//企业规模
    private String company_type;	//企业性质
    private String company_brief;	//企业简介
    private int company_state;		//企业招聘状态：-1审核中 0不可用 1招聘中 2已暂停 3已结束
    private int company_sort;		//显示排序	
    private int company_viewnum;	//浏览数
    private String company_pic;		//宣传图片
    private String company_license;	//企业营业执照
    private List<Job> jobs = new ArrayList<Job>();//职位
    private Date created_at;		//新增时间
    private Date updated_at;		//修改时间
    private Date deleted_at;		//删除时间
    private boolean deleted;		//是否已删除
    
    public Company() {}
	 
    public int getID() {
    	return company_id;
    }
    
    public void setID(int company_id) {
    	this.company_id = company_id;
    }
    
    public String getName() {
        return company_name;
    }
 
    public void setName(String company_name) {
        this.company_name = company_name;
    }
    
    public String getLegal() {
        return company_legal;
    }
 
    public void setLegal(String company_legal) {
        this.company_legal = company_legal;
    }
    
    public String getArea() {
        return company_area;
    }
 
    public void setArea(String company_area) {
        this.company_area = company_area;
    }
    
    public String getSize() {
        return company_size;
    }
 
    public void setSize(String company_size) {
        this.company_size = company_size;
    }
 
    public String getType() {
        return company_type;
    }
 
    public void setType(String company_type) {
        this.company_type = company_type;
    }
    
    public String getBrief() {
        return company_brief;
    }
 
    public void setBrief(String company_brief) {
        this.company_brief = company_brief;
    }
    
    public int getState() {
        return company_state;
    }
 
    public void setState(int company_state) {
        this.company_state = company_state;
    }
    
    public int getSort() {
        return company_sort;
    }
 
    public void setSort(int company_sort) {
        this.company_sort = company_sort;
    }
    
    public int getViewNum() {
        return company_viewnum;
    }
 
    public void setViewNum(int company_viewnum) {
        this.company_viewnum = company_viewnum;
    }
    
    public String getPic() {
        return company_pic;
    }
 
    public void setPic(String company_pic) {
        this.company_pic = company_pic;
    }
    
    public String getLicense() {
        return company_license;
    }
 
    public void setLicense(String company_license) {
        this.company_license = company_license;
    }
    
    public List<Job> getJobs() {
    	jobs = new JobDao().queryJobByCompanyID(this.company_id);
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
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
