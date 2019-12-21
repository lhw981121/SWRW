package com.swzj.swrw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swzj.swrw.bean.Education;
import com.swzj.swrw.util.DBUtil;

/**
* Education类Dao层
* @author 节奏葳
* @version 1.0
*/
public class EducationDao {
	/**
	* 装载数据
	* @param rs 结果集
	* @return 教育经历对象
	*/
	public Education loadData(ResultSet rs) throws SQLException {
		Education obj = new Education();
		obj.setID(rs.getInt("education_id"));
    	obj.setBasicInfoID(rs.getInt("basicinfo_id"));
    	obj.setSchool(rs.getString("graduate_school"));
    	obj.setGraduationDate(rs.getString("graduation_date"));
    	obj.setDegree(rs.getString("education_degree"));
    	obj.setProfession(rs.getString("profession"));
    	obj.setCreated(rs.getTimestamp("created_at"));
    	obj.setUpdated(rs.getTimestamp("updated_at"));
    	obj.setDeleted(rs.getTimestamp("deleted_at"));
    	obj.setIsDeleted(rs.getBoolean("deleted"));
    	return obj;
	}
	
	/**
	* 获取所有教育经历信息
	* @return 教育经历对象List
	*/
	public List<Education> getBasicInfoInfo() {
		List<Education> list = new ArrayList<Education>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from tb_resume_education";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Education obj = loadData(rs);
	        	list.add(obj);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return list;
    }
	
	/**
	* 单项数据查询教育经历信息
	* @param str 查询字段
	* @param value 查询数据
	* @return 教育经历信息对象
	*/
	public Education queryEducationBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Education obj = null;
		try{
			String sql="select * from tb_resume_education where "+str+"=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs=pstmt.executeQuery();
			if(rs.next()){
				obj = loadData(rs);
			}
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return obj;
	}
	
	/**
	* 按简历基本信息标识查询教育经历信息
	* @param basicinfo_id 简历基本信息标识
	* @return 教育经历信息对象
	*/
	public Education queryEducationByBasicinfoID(int basicinfo_id){
		return queryEducationBySingleData("basicinfo_id",String.valueOf(basicinfo_id));
	}
}
