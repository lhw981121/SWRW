package com.qst.itoffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import com.qst.itoffer.bean.BasicInfo;
import com.qst.itoffer.bean.Education;
import com.qst.itoffer.bean.ProjectExp;
import com.qst.itoffer.bean.WorkExp;
import com.qst.itoffer.util.DBUtil;

public class ResumeDao {
	/**
	* 获取简历完整度
	* @param applicant_id 求职者标识
	* @return 简历完整度
	*/
	public static String getResumeIntegrity(int applicant_id) {
		if(applicant_id==0)	return "0%";
		String integrity = "";
		BasicInfo basicInfo = new BasicInfoDao().queryBasicInfoByID(applicant_id);
		Education education = new EducationDao().queryEducationByBasicinfoID(basicInfo.getID());
		ProjectExp projectExp = new ProjectExpDao().queryProjectExpByBasicinfoID(basicInfo.getID());
		WorkExp workExp = new WorkExpDao().queryWorkExpByBasicinfoID(basicInfo.getID());
		int n = basicInfo.getIntegrity()+education.getIntegrity()+projectExp.getIntegrity()+workExp.getIntegrity();
		double d = 10+4+4+3;
		double percent = n/d;
		percent = (double) Math.round(percent * 100) / 100;
		integrity = String.valueOf((int)(percent*100));
		integrity += "%";
	    return integrity;
    }
	
	/**
	* 更新简历
	* @param basicInfo_id 简历基本信息主键
	* @return 是否成功
	*/
	@SuppressWarnings("resource")
	public static boolean updateResume(String basicInfo_id,String realName,Date birthday,String email,String gender,String job_experience,
									String telephone,String current_loc,String resident_loc,String job_intension,String graduate_school,
									String profession,Date graduation_date,String education_degree,String project_name,String project_job,
									String project_period,String project_desc,String work_title,String department,String work_period) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		try{
			String sql = null;
			conn.setAutoCommit(false);//开启事务
			//更新简历基本信息
			sql=
			"update tb_resume_basicinfo set "
			+ "realname=?,"
			+ "gender=?,"
			+ "birthday=?,"
			+ "current_loc=?,"
			+ "resident_loc=?,"
			+ "telephone=?,"
			+ "email=?,"
			+ "job_intension=?,"
			+ "job_experience=? "
			+ "where basicinfo_id=?";
			pstmt=conn.prepareStatement(sql);
			if(realName.length()==0)		pstmt.setNull(1, Types.VARCHAR);	else	pstmt.setString(1,realName);
			if(gender.length()==0)			pstmt.setNull(2, Types.VARCHAR);	else	pstmt.setString(2,gender);
			if(birthday==null)				pstmt.setNull(3, Types.DATE);		else	pstmt.setTimestamp(3,new Timestamp(birthday.getTime()));
			if(current_loc.length()==0)		pstmt.setNull(4, Types.VARCHAR);	else	pstmt.setString(4,current_loc);
			if(resident_loc.length()==0)	pstmt.setNull(5, Types.VARCHAR);	else	pstmt.setString(5,resident_loc);
			if(telephone.length()==0)		pstmt.setNull(6, Types.VARCHAR);	else	pstmt.setString(6,telephone);
			if(email.length()==0)			pstmt.setNull(7, Types.VARCHAR);	else	pstmt.setString(7,email);
			if(job_intension.length()==0)	pstmt.setNull(8, Types.VARCHAR);	else	pstmt.setString(8,job_intension);
			if(job_experience.length()==0)	pstmt.setNull(9, Types.VARCHAR);	else	pstmt.setString(9,job_experience);
			pstmt.setString(10, basicInfo_id);
			pstmt.executeUpdate();

			//更新教育经历信息
			sql=
			"update tb_resume_education set "
			+ "graduate_school=?,"
			+ "graduation_date=?,"
			+ "education_degree=?,"
			+ "profession=? "
			+ "where basicinfo_id=?";
			pstmt=conn.prepareStatement(sql);
			if(graduate_school.length()==0)	pstmt.setNull(1, Types.VARCHAR);	else	pstmt.setString(1,graduate_school);
			if(graduation_date==null)		pstmt.setNull(2, Types.DATE);		else	pstmt.setTimestamp(2,new Timestamp(graduation_date.getTime()));
			if(education_degree.length()==0)pstmt.setNull(3, Types.DATE);		else	pstmt.setString(3,education_degree);
			if(profession.length()==0)		pstmt.setNull(4, Types.VARCHAR);	else	pstmt.setString(4,profession);
			pstmt.setString(5, basicInfo_id);
			pstmt.executeUpdate();
			
			//更新项目经验信息
			sql=
			"update tb_resume_project_experience set "
			+ "project_name=?,"
			+ "project_period=?,"
			+ "project_job=?,"
			+ "project_desc=? "
			+ "where basicinfo_id=?";
			pstmt=conn.prepareStatement(sql);
			if(project_name.length()==0)	pstmt.setNull(1, Types.VARCHAR);	else	pstmt.setString(1,project_name);
			if(project_period.length()==0)	pstmt.setNull(2, Types.VARCHAR);	else	pstmt.setString(2,project_period);
			if(project_job.length()==0)		pstmt.setNull(3, Types.DATE);		else	pstmt.setString(3,project_job);
			if(project_desc.length()==0)	pstmt.setNull(4, Types.VARCHAR);	else	pstmt.setString(4,project_desc);
			pstmt.setString(5, basicInfo_id);
			pstmt.executeUpdate();
			
			//更新工作经验信息
			sql=
			"update tb_resume_work_experience set "
			+ "work_period=?,"
			+ "department=?,"
			+ "work_title=? "
			+ "where basicinfo_id=?";
			pstmt=conn.prepareStatement(sql);
			if(work_period.length()==0)		pstmt.setNull(1, Types.VARCHAR);	else	pstmt.setString(1,work_period);
			if(department.length()==0)		pstmt.setNull(2, Types.VARCHAR);	else	pstmt.setString(2,department);
			if(work_title.length()==0)		pstmt.setNull(3, Types.DATE);		else	pstmt.setString(3,work_title);
			pstmt.setString(4, basicInfo_id);
			pstmt.executeUpdate();

			conn.commit();//提交事务 
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	try {
	    		conn.rollback();//回滚事务
	    		return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	        return false;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return true;
	}
	
}
