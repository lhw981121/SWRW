package com.swzj.swrw.bean;

import java.util.Date;

import com.swzj.swrw.dao.ResumeDao;

/**
* 用户类
* @author 节奏葳
* @version 1.0
*/
public class User{
	private int user_id;	       	 	//用户标识
	private int user_type;				//用户类型：1(求职者)、2(企业)、8(管理员)
	private String user_name;			//用户名
    private String user_email;   		//用户邮箱
    private String user_phone;			//用户手机号
    private String user_pwd;			//用户密码
    private int applicant_id;			//求职者标识
    private int company_id;				//企业标识
	private Date created_at;			//新增时间
    private Date updated_at;			//修改时间
    private Date deleted_at;			//删除时间
    private boolean deleted;			//是否已删除
    
    public User() {}
	 
    public int getID() {
    	return user_id;
    }
    
    public void setID(int user_id) {
    	this.user_id = user_id;
    }
    
    public int getType() {
        return user_type;
    }
    
    public String getTypeStr(String language) {
    	String typeStr = "";
    	switch(user_type) {
    	case 1:typeStr = language.equals("zh_CN")?"求职者":"Applicant";break;
    	case 2:typeStr = language.equals("zh_CN")?"企业":"Company";break;
    	case 8:typeStr = language.equals("zh_CN")?"管理员":"Administrator";break;
    	}
		return typeStr;
    }
 
    public void setType(int user_type) {
        this.user_type = user_type;
    }
    
    public String getName() {
        return user_name;
    }
 
    public void setName(String user_name) {
        this.user_name = user_name;
    }
    
    public String getEmail() {
        return user_email;
    }
 
    public void setEmail(String user_email) {
        this.user_email = user_email;
    }
    
    public String getPhone() {
        return user_phone;
    }
 
    public void setPhone(String user_phone) {
        this.user_phone = user_phone;
    }
    
    public String getPwd() {
        return user_pwd;
    }
 
    public void setPwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }
    
    public int getApplicantID() {
        return applicant_id;
    }
    
    public void setApplicantID(int applicant_id) {
    	this.applicant_id = applicant_id;
    }
    
    public int getCompanyID() {
        return company_id;
    }
    
    public void setCompanyID(int company_id) {
    	this.company_id = company_id;
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
    
    /**
	* 获取简历完整度
	* @param applicant_id 求职者标识
	* @return 简历完整度
	*/
	public static String getResumeIntegrity(int applicant_id) {
		return ResumeDao.getResumeIntegrity(applicant_id);
    }
	
	
	public static void main(String[] args) {
	}
}