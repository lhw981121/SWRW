package com.qst.itoffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.itoffer.bean.WorkExp;
import com.qst.itoffer.util.DBUtil;

/**
* WorkExp类Dao层
* @author 节奏葳
* @version 1.0
*/
public class WorkExpDao {
	/**
	* 装载数据
	* @param rs 结果集
	* @return 工作经验对象
	*/
	public WorkExp loadData(ResultSet rs) throws SQLException {
		WorkExp obj = new WorkExp();
		obj.setID(rs.getInt("work_id"));
    	obj.setBasicinfoID(rs.getInt("basicinfo_id"));
    	obj.setPeriod(rs.getString("work_period"));
    	obj.setDepartment(rs.getString("department"));
    	obj.setWorkTitle(rs.getString("work_title"));
    	obj.setCreated(rs.getTimestamp("created_at"));
    	obj.setUpdated(rs.getTimestamp("updated_at"));
    	obj.setDeleted(rs.getTimestamp("deleted_at"));
    	obj.setIsDeleted(rs.getBoolean("deleted"));
    	return obj;
	}
	
	/**
	* 获取所有工作经验信息
	* @return 工作经验对象List
	*/
	public List<WorkExp> getBasicInfoInfo() {
		List<WorkExp> list = new ArrayList<WorkExp>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from tb_resume_work_experience";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	WorkExp obj = loadData(rs);
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
	* 单项数据查询工作经验信息
	* @param str 查询字段
	* @param value 查询数据
	* @return 工作经验信息对象
	*/
	public WorkExp queryWorkExpBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    WorkExp obj = null;
		try{
			String sql="select * from tb_resume_work_experience where "+str+"=?";
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
	* 按简历基本信息标识查询工作经验信息
	* @param basicinfo_id 简历基本信息标识
	* @return 工作经验信息对象
	*/
	public WorkExp queryWorkExpByBasicinfoID(int basicinfo_id){
		return queryWorkExpBySingleData("basicinfo_id",String.valueOf(basicinfo_id));
	}
}