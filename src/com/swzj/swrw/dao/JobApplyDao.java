package com.swzj.swrw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.swzj.swrw.bean.JobApply;
import com.swzj.swrw.util.DBUtil;

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
	
	/**
	* 职位申请
	* @param jobApply 职位申请对象
	* @return 添加的职位申请数据主键
	*/
	public int createJobApply(JobApply jobApply){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int id = 0;
		try{
			String sql = "";
			sql=
			"insert into tb_jobapply("
			+ "job_id,"
			+ "applicant_id,"
			+ "apply_state)"
    		+ "values(?,?,?)";
			pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, jobApply.getJobID());
			pstmt.setInt(2, jobApply.getApplicantID());
			pstmt.setInt(3, jobApply.getState());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getInt(1);
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
	        return id;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return id;
	}
	
	/**
	* 修改职位申请信息
	* @param jobApply 职位申请对象
	* @return 是否成功
	*/
    public boolean updateJobApply(JobApply jobApply) {
    	Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
        try{
			String sql = null;
			sql=
			"update tb_jobapply set "
			+ "job_id=?,"
			+ "applicant_id=?,"
			+ "apply_state=?,"
			+ "deleted=?,"
			+ "created_at=?,"
			+ "updated_at=? "
			+ "where apply_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, jobApply.getJobID());
			pstmt.setInt(2, jobApply.getApplicantID());
			pstmt.setInt(3, jobApply.getState());
			pstmt.setInt(4, jobApply.getIsDeleted()?1:0);
			pstmt.setTimestamp(5, new Timestamp(jobApply.getCreated().getTime()));
			pstmt.setTimestamp(6, new Timestamp(jobApply.getUpdated().getTime()));
			pstmt.setInt(7, jobApply.getID());
			pstmt.executeUpdate();
	    }catch(Exception e) {
	    	e.printStackTrace();
	        return false;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return true;
    }
    
    /**
	* 单项数据查询职位申请
	* @param str 查询字段
	* @param value 查询数据
	* @return 职位对象
	*/
	public List<JobApply> queryJobApplyBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<JobApply> obj = new ArrayList<JobApply>();
		try{
			String sql="select * from tb_jobapply where "+str+"=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				obj.add(loadData(rs));
			}
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return obj;
	}
	
	/**
	* 按职位申请ID查找职位申请信息
	* @param apply_id
	* @return 职位申请对象
	*/
	public JobApply queryJobApplyByID(int apply_id){
		List<JobApply> jobApplys = queryJobApplyBySingleData("apply_id",String.valueOf(apply_id));
		return jobApplys.size()==0?null:jobApplys.get(0);
	}
	
	/**
	* 按职位ID查找职位申请信息
	* @param job_id
	* @return 职位申请对象列表
	*/
	public List<JobApply> queryJobApplyByJobID(int job_id){
		List<JobApply> jobApplys = queryJobApplyBySingleData("job_id",String.valueOf(job_id));
		return jobApplys;
	}
	
	/**
	* 按求职者ID查找职位申请信息
	* @param applicant_id
	* @return 职位申请对象列表
	*/
	public List<JobApply> queryJobApplyByApplicantID(int applicant_id){
		List<JobApply> jobApplys = queryJobApplyBySingleData("applicant_id",String.valueOf(applicant_id));
		return jobApplys;
	}
	
	/**
	* 查询职位申请信息
	* @param job_id 职位ID
	* @param applicant_id 求职者ID
	* @return 职位申请对象
	*/
	public JobApply queryJobApply(int job_id,int applicant_id){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    JobApply obj = null;
		try{
			String sql="select * from tb_jobapply where job_id=? and applicant_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, job_id);
			pstmt.setInt(2, applicant_id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
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
	* 获取分页职位申请信息数据总数量
	* @param job_id 职位ID 0(不考虑)
	* @param company_id 所属企业ID 0(不考虑)
	* @param applicant_id 0(不考虑)
	* @param apply_state 职位申请状态 0(不考虑)、1(申请)、2(面试)、3(驳回)
	* @param deleted 是否已删除 0(未删除)、1(已删除)
	* @return 数据总数量
	*/
	public int getPageDataJobApplyCount(int job_id,int company_id,int applicant_id,int apply_state,int deleted) {
		int count = 0;
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery = 
    		"select count(*) from tb_jobapply "
    		+ "where deleted=? "
    		+ (job_id==0?(company_id==0?"":"and job_id in (select job_id from tb_job where company_id=?) "):"and job_id=? ")
    		+ (applicant_id==0?"":"and applicant_id=? ")
    		+ (apply_state==0?"":"and apply_state=? ");
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setInt(index++, deleted);
	        if(job_id==0&&company_id!=0) {
	        	pstmt.setInt(index++, company_id);
	        }else if(job_id!=0) {
	        	pstmt.setInt(index++, job_id);
	        }
	        if(applicant_id!=0)	pstmt.setInt(index++, applicant_id);
	        if(apply_state!=0)	pstmt.setInt(index++, apply_state);
	        rs = pstmt.executeQuery();
	        if(rs.next()) {
            	count = rs.getInt(1);
            }
	    }catch(Exception e) {
	        e.printStackTrace();
	        return 0;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return count;
    }
	
	/**
	* 获取分页职位申请信息数据
	* @param pageNo 当前页
	* @param pageSize 每页记录数
	* @param job_id 职位ID 0(不考虑)
	* @param company_id 所属企业ID 0(不考虑)
	* @param applicant_id 0(不考虑)
	* @param apply_state 职位申请状态 0(不考虑)、1(申请)、2(面试)、3(驳回)
	* @param deleted 是否已删除 0(未删除)、1(已删除)
	* @param sortField 排序字段及排序方式 ASC(升序)、DESC(降序)
	* @return 职位申请对象List
	*/
	public List<JobApply> getPageDataJobApply(int pageNo,int pageSize,int job_id,int company_id,int applicant_id,int apply_state,int deleted,String sortField) {
		List<JobApply> list = new ArrayList<JobApply>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery = 
    		"select * from tb_jobapply "
    		+ "where deleted=? "
    		+ (job_id==0?(company_id==0?"":"and job_id in (select job_id from tb_job where company_id=?) "):"and job_id=? ")
    		+ (applicant_id==0?"":"and applicant_id=? ")
    		+ (apply_state==0?"":"and apply_state=? ")
    		+ "order by "+(sortField.length()==0?"apply_id":sortField)+" limit ?,?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setInt(index++, deleted);
	        if(job_id==0&&company_id!=0) {
	        	pstmt.setInt(index++, company_id);
	        }else if(job_id!=0) {
	        	pstmt.setInt(index++, job_id);
	        }
	        if(applicant_id!=0)	pstmt.setInt(index++, applicant_id);
	        if(apply_state!=0)	pstmt.setInt(index++, apply_state);
	        pstmt.setInt(index++, pageSize * (pageNo-1));
	        pstmt.setInt(index++, pageSize);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	list.add(loadData(rs));
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return list;
    }
}
