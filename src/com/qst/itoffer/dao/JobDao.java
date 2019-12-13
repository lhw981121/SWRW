package com.qst.itoffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.itoffer.bean.Job;
import com.qst.itoffer.util.DBUtil;

/**
* Job类Dao层
* @author 节奏葳
* @version 1.0
*/
public class JobDao {
	/**
	* 装载数据
	* @param rs 结果集
	* @return 职位对象
	*/
	public Job loadData(ResultSet rs) throws SQLException {
		Job obj = new Job();
		obj.setID(rs.getInt("job_id"));
    	obj.setCompanyID(rs.getInt("company_id"));
    	obj.setName(rs.getString("job_name"));
    	obj.setHiringNum(rs.getInt("job_hiringnum"));
    	obj.setSalary(rs.getString("job_salary"));
    	obj.setArea(rs.getString("job_area"));
    	obj.setDesc(rs.getString("job_desc"));
    	obj.setEndDate(rs.getTimestamp("job_endtime"));
    	obj.setState(rs.getInt("job_state"));
    	obj.setCreated(rs.getTimestamp("created_at"));
    	obj.setUpdated(rs.getTimestamp("updated_at"));
    	obj.setDeleted(rs.getTimestamp("deleted_at"));
    	obj.setIsDeleted(rs.getBoolean("deleted"));
    	return obj;
	}
	
	/**
	* 获取所有职位信息
	* @return 职位对象List
	*/
	public List<Job> getBasicInfoInfo() {
		List<Job> list = new ArrayList<Job>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from tb_job";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Job obj = loadData(rs);
	        	list.add(obj);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return list;
    }
}
