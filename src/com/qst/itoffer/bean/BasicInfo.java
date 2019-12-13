package com.qst.itoffer.bean;

import java.util.Date;

/**
* 简历信息类
* @author 节奏葳
* @version 1.0
*/
public class BasicInfo{
	private int basicinfo_id;		//简历标识（对应求职者标识）
    private String realname;   		//真实姓名
    private String gender;			//性别
    private Date birthday;			//出生日期
    private String current_loc;		//当前所在地
    private String resident_loc;	//户口所在地
    private String telephone;		//手机号
    private String email;			//邮箱地址
    private String job_intension;	//求职意向
    private String job_experience;	//工作经验
    private String head_shot;		//简历照片
    private Date created_at;		//新增时间
    private Date updated_at;		//修改时间
    private Date deleted_at;		//删除时间
    private boolean deleted;		//是否已删除
    
    public BasicInfo() {}
	
    //获取简历基本信息完整度
    public int getIntegrity() {
    	int count = 0;
    	if(realname!=null&&realname.length()!=0) count++;
    	if(gender!=null&&gender.length()!=0) count++;
    	if(birthday!=null) count++;
    	if(current_loc!=null&&current_loc.length()!=0) count++;
    	if(resident_loc!=null&&resident_loc.length()!=0) count++;
    	if(telephone!=null&&telephone.length()!=0) count++;
    	if(email!=null&&email.length()!=0) count++;
    	if(job_intension!=null&&job_intension.length()!=0) count++;
    	if(job_experience!=null&&job_experience.length()!=0) count++;
    	if(head_shot!=null&&head_shot.length()!=0) count++;
    	return count;
    }
    
    public int getID() {
    	return basicinfo_id;
    }
    
    public void setID(int basicinfo_id) {
    	this.basicinfo_id = basicinfo_id;
    }
    
    public String getRealName() {
        return realname;
    }
 
    public void setRealName(String realname) {
        this.realname = realname;
    }
    
    public String getGender() {
        return gender;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
    	this.birthday = birthday;
    }
    
    public String getCurrentLoc() {
        return current_loc;
    }
 
    public void setCurrentLoc(String current_loc) {
        this.current_loc = current_loc;
    }
 
    public String getResidentLoc() {
        return resident_loc;
    }
 
    public void setResidentLoc(String resident_loc) {
        this.resident_loc = resident_loc;
    }
    
    public String getTelephone() {
        return telephone;
    }
 
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getJobIntension() {
        return job_intension;
    }
 
    public void setJobIntension(String job_intension) {
        this.job_intension = job_intension;
    }
    
    public String getJobExp() {
        return job_experience;
    }
 
    public void setJobExp(String job_experience) {
        this.job_experience = job_experience;
    }
    
    public String getHeadShot() {
        return head_shot;
    }
 
    public void setHeadShot(String head_shot) {
        this.head_shot = head_shot;
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

