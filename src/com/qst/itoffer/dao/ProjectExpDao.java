package com.qst.itoffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.itoffer.bean.ProjectExp;
import com.qst.itoffer.util.DBUtil;

/**
* ProjectExp类Dao层
* @author 节奏葳
* @version 1.0
*/
public class ProjectExpDao {
	/**
	* 装载数据
	* @param rs 结果集
	* @return 项目经验对象
	*/
	public ProjectExp loadData(ResultSet rs) throws SQLException {
		ProjectExp obj = new ProjectExp();
		obj.setID(rs.getInt("project_id"));
    	obj.setBasicInfoID(rs.getInt("basicinfo_id"));
    	obj.setName(rs.getString("project_name"));
    	obj.setPeriod(rs.getString("project_period"));
    	obj.setProjectJob(rs.getString("project_job"));
    	obj.setProjectDesc(rs.getString("project_desc"));
    	obj.setCreated(rs.getTimestamp("created_at"));
    	obj.setUpdated(rs.getTimestamp("updated_at"));
    	obj.setDeleted(rs.getTimestamp("deleted_at"));
    	obj.setIsDeleted(rs.getBoolean("deleted"));
    	return obj;
	}
	
	/**
	* 获取所有项目经验信息
	* @return 项目经验对象List
	*/
	public List<ProjectExp> getBasicInfoInfo() {
		List<ProjectExp> list = new ArrayList<ProjectExp>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from tb_resume_project_experience";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	ProjectExp obj = loadData(rs);
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
	* 单项数据查询项目经验信息
	* @param str 查询字段
	* @param value 查询数据
	* @return 项目经验信息对象
	*/
	public ProjectExp queryProjectExpBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    ProjectExp obj = null;
		try{
			String sql="select * from tb_resume_project_experience where "+str+"=?";
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
	* 按简历基本信息标识查询项目经验信息
	* @param basicinfo_id 简历基本信息标识
	* @return 项目经验信息对象
	*/
	public ProjectExp queryProjectExpByBasicinfoID(int basicinfo_id){
		return queryProjectExpBySingleData("basicinfo_id",String.valueOf(basicinfo_id));
	}
}
