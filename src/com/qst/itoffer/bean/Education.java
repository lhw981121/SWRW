package com.qst.itoffer.bean;

import java.util.Date;

/**
* 教育经历信息类
* @author 节奏葳
* @version 1.0
*/
public class Education{
	private int education_id;	    	//教育标识
	private int basicinfo_id;			//简历标识
    private String graduate_school;   	//毕业学校
    private Date graduation_date;		//毕业时间
    private String education_degree;	//学历
    private String profession;			//专业
    private Date created_at;			//新增时间
    private Date updated_at;			//修改时间
    private Date deleted_at;			//删除时间
    private boolean deleted;			//是否已删除
    
    public Education() {}
	 
    //获取简历基本信息完整度
    public int getIntegrity() {
    	int count = 0;
    	if(graduate_school!=null&&graduate_school.length()!=0) count++;
    	if(graduation_date!=null) count++;
    	if(education_degree!=null&&education_degree.length()!=0) count++;
    	if(profession!=null&&profession.length()!=0) count++;
    	return count;
    }
    
    public int getID() {
    	return education_id;
    }
    
    public void setID(int education_id) {
    	this.education_id = education_id;
    }
    
    public int getBasicInfoID() {
        return basicinfo_id;
    }
 
    public void setBasicInfoID(int basicinfo_id) {
        this.basicinfo_id = basicinfo_id;
    }
    
    public String getSchool() {
        return graduate_school;
    }
 
    public void setSchool(String graduate_school) {
        this.graduate_school = graduate_school;
    }
    
    public Date getGraduationDate() {
        return graduation_date;
    }
    
    public void setGraduationDate(Date graduation_date) {
    	this.graduation_date = graduation_date;
    }
    
    public String getDegree() {
        return education_degree;
    }
 
    public void setDegree(String education_degree) {
        this.education_degree = education_degree;
    }
    
    public String getProfession() {
        return profession;
    }
 
    public void setProfession(String profession) {
        this.profession = profession;
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