package com.qst.itoffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.itoffer.bean.JobApply;
import com.qst.itoffer.util.DBUtil;

/**
* JobApply类Dao层
* @author 节奏葳
* @version 1.0
*/
public class JobApplyDao {
	/**
	* 装载数据
	* @param rs 结果集
	* @return 职位申请对象
	*/
	public JobApply loadData(ResultSet rs) throws SQLException {
		JobApply obj = new JobApply();
		obj.setID(rs.getInt("apply_id"));
    	obj.setJobID(rs.getInt("job_id"));
    	obj.setApplicantID(rs.getInt("applicant_id"));
    	obj.setApplyDate(rs.getTimestamp("apply_date"));
    	obj.setState(rs.getInt("apply_state"));
    	obj.setCreated(rs.getTimestamp("created_at"));
    	obj.setUpdated(rs.getTimestamp("updated_at"));
    	obj.setDeleted(rs.getTimestamp("deleted_at"));
    	obj.setIsDeleted(rs.getBoolean("deleted"));
    	return obj;
	}
	
	/**
	* 获取所有职位申请信息
	* @return 职位申请对象List
	*/
	public List<JobApply> getBasicInfoInfo() {
		List<JobApply> list = new ArrayList<JobApply>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from tb_jobapply";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	JobApply obj = loadData(rs);
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
