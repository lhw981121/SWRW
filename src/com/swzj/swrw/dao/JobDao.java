package com.swzj.swrw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.swzj.swrw.bean.Job;
import com.swzj.swrw.util.COMUtil;
import com.swzj.swrw.util.DBUtil;

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
    	obj.setCompanyName(new CompanyDao().queryCompanyByID(obj.getCompanyID()).getName());
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
	
	/**
	* 获取所有职位信息
	* @return 职位对象List
	*/
	public List<Job> getJobInfo() {
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
	
	/**
	* 企业发布招聘
	* @param job 职位对象
	* @return 添加的职位数据主键
	*/
	public int createJob(Job job){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int id = 0;
		try{
			String sql = "";
			sql=
			"insert into tb_job("
			+ "company_id,"
			+ "job_name,"
			+ "job_hiringnum,"
			+ "job_salary,"
			+ "job_area,"
    		+ "job_desc,"
    		+ "job_endtime,"
    		+ "job_state)"
    		+ "values(?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, job.getCompanyID());
			pstmt.setString(2, job.getName());
			pstmt.setInt(3, job.getHiringNum());
			pstmt.setString(4, job.getSalary());
			pstmt.setString(5, job.getArea());
			pstmt.setString(6, job.getDesc());
			pstmt.setTimestamp(7, new Timestamp(job.getEndDate().getTime()));
			pstmt.setInt(8, job.getState());
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
	* 单项数据查询职位
	* @param str 查询字段
	* @param value 查询数据
	* @return 职位对象
	*/
	public List<Job> queryJobBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<Job> obj = new ArrayList<Job>();
		try{
			String sql="select * from tb_job where "+str+"=?";
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
	* 按职位ID查找职位信息
	* @param job_id
	* @return 职位对象
	*/
	public Job queryJobByID(int job_id){
		List<Job> jobs = queryJobBySingleData("job_id",String.valueOf(job_id));
		return jobs.size()==0?null:jobs.get(0);
	}
	
	/**
	* 按所属企业查找职位信息
	* @param company_id
	* @return 职位对象
	*/
	public List<Job> queryJobByCompanyID(int company_id){
		List<Job> jobs = queryJobBySingleData("company_id",String.valueOf(company_id));
		return jobs;
	}
	
	/**
	* 获取分页职位信息数据总数量
	* @param company_id 所属企业ID
	* @param job_state 职位招聘状态：-2(审核未通过)、-1(审核中)、0(不可用)、1(招聘中)、2(已暂停)、3(已结束)、4(审核通过)、5(不考虑)
	* @param job_area 职位所在地
	* @param queryStr 查询字串
	* @param deleted 是否已删除 0(未删除)、1(已删除)
	* @return 数据总数量
	*/
	public int getPageDataJobCount(int company_id,int job_state,String job_area,String queryStr,int deleted) {
		int count = 0;
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery = 
    		"select count(*) from tb_job "
    		+ "where "+ (company_id==0?"":"company_id=? ")
    		+(job_state==4?""+(company_id==0?"":"and ")+"job_state>0":(job_state==5?"":""+(company_id==0?"":"and ")+"job_state=?"))+" "
    		+ "and job_area like ? "
    		+ "and concat(ifnull(job_name, ''),ifnull(job_area,''),ifnull(job_desc,'')) like ? "
    		+ "and deleted=?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        if(company_id!=0)	pstmt.setInt(index++, company_id);
	        if(job_state!=4&&job_state!=5)	pstmt.setInt(index++, job_state);
	        pstmt.setString(index++, "%"+job_area+"%");
	        pstmt.setString(index++, "%"+queryStr+"%");
	        pstmt.setInt(index++, deleted);
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
	* 获取分页职位信息数据
	* @param pageNo 当前页
	* @param pageSize 每页记录数
	* @param company_id 所属企业ID
	* @param job_state 职位招聘状态：-2(审核未通过)、-1(审核中)、0(不可用)、1(招聘中)、2(已暂停)、3(已结束)、4(审核通过)
	* @param job_area 职位所在地
	* @param queryStr 查询字串
	* @param deleted 是否已删除 0(未删除)、1(已删除)
	* @param sortField 排序字段及排序方式 ASC(升序)、DESC(降序)
	* @return 职位对象List
	*/
	public List<Job> getPageDataJob(int pageNo,int pageSize,int company_id,int job_state,String job_area,String queryStr,int deleted,String sortField) {
		List<Job> list = new ArrayList<Job>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery = 
    		"select * from tb_job "
			+ "where "+ (company_id==0?"":"company_id=? ")
			+(job_state==4?""+(company_id==0?"":"and ")+"job_state>0":(job_state==5?"":""+(company_id==0?"":"and ")+"job_state=?"))+" "
    		+ "and job_area like ? "
    		+ "and concat(ifnull(job_name, ''),ifnull(job_area,''),ifnull(job_desc,'')) like ? "
    		+ "and deleted=? "
    		+ "order by "+(sortField.length()==0?"job_id":sortField)+" limit ?,?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        if(company_id!=0)	pstmt.setInt(index++, company_id);
	        if(job_state!=4&&job_state!=5)	pstmt.setInt(index++, job_state);
	        pstmt.setString(index++, "%"+job_area+"%");
	        pstmt.setString(index++, "%"+queryStr+"%");
	        pstmt.setInt(index++, deleted);
	        pstmt.setInt(index++, pageSize * (pageNo-1));
	        pstmt.setInt(index++, pageSize);
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
	
	/**
	* 获取分页职位招聘信息数据总数量（职位列表）
	* @param job_state 职位招聘状态：-2(审核未通过)、-1(审核中)、0(不可用)、1(招聘中)、2(已暂停)、3(已结束)、4(审核通过)、5(不考虑)
	* @param job_area 职位所在地
	* @param company_state 企业招聘状态：-2(审核未通过)、-1(审核中)、0(不可用)、1(招聘中)、2(已暂停)、3(已结束)、4(审核通过)
	* @param company_size 企业规模
	* @param company_type 企业性质
	* @param queryStr 查询字串
	* @return 数据总数量
	*/
	public int getPageDataJobCount(int job_state,String job_area,int company_state,String company_size,String company_type,String queryStr) {
		int count = 0;
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery = 
    		"select count(*) from tb_job where deleted=0 "
    		+ (job_state==4?"and job_state>0":(job_state==5?"":"and job_state=?"))+" "
    		+ "and job_area like ? "
    		+ "and company_id in (select company_id from tb_company where deleted=0 and "
    		+ (company_state==4?"company_state>0":"company_state=?")+" "
    		+ (company_size.length()==0?"":"and company_size=? ")
    		+ (company_type.length()==0?"":"and company_type=? ")
    		+ (queryStr.length()==0?") ":"and concat(ifnull(company_name,''),ifnull(company_area,'')) like ?) " 
    		+ "or concat(ifnull(job_name,''),ifnull(job_area,''),ifnull(job_desc,'')) like ? ");
	        pstmt = conn.prepareStatement(sqlQuery);
	        if(job_state!=4&&job_state!=5)	pstmt.setInt(index++, job_state);
	        pstmt.setString(index++, "%"+job_area+"%");
	        if(company_state!=4)	 		pstmt.setInt(index++, company_state);
	        if(company_size.length()!=0)	pstmt.setString(index++, company_size);
	        if(company_type.length()!=0)	pstmt.setString(index++, company_type);
	        if(queryStr.length()!=0) {
	        	pstmt.setString(index++, "%"+queryStr+"%");
		        pstmt.setString(index++, "%"+queryStr+"%");
	        }
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
	* 获取分页职位招聘信息数据（职位列表）
	* @param pageNo 当前页
	* @param pageSize 每页记录数
	* @param job_state 职位招聘状态：-2(审核未通过)、-1(审核中)、0(不可用)、1(招聘中)、2(已暂停)、3(已结束)、4(审核通过)、5(不考虑)
	* @param job_area 职位所在地
	* @param company_state 企业招聘状态：-2(审核未通过)、-1(审核中)、0(不可用)、1(招聘中)、2(已暂停)、3(已结束)、4(审核通过)
	* @param company_size 企业规模
	* @param company_type 企业性质
	* @param queryStr 查询字串
	* @param sortField 排序字段及排序方式 ASC(升序)、DESC(降序)
	* @return 职位对象List
	*/
	public List<Job> getPageDataJob(int pageNo,int pageSize,int job_state,String job_area,int company_state,String company_size,String company_type,String queryStr,String sortField) {
		List<Job> list = new ArrayList<Job>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery = 
    		"select * from tb_job where deleted=0 "
    		+ (job_state==4?"and job_state>0":(job_state==5?"":"and job_state=?"))+" "
			+ "and job_area like ? "
			+ "and company_id in (select company_id from tb_company where deleted=0 and "
			+ (company_state==4?"company_state>0":"company_state=?")+" "
			+ (company_size.length()==0?"":"and company_size=? ")
			+ (company_type.length()==0?"":"and company_type=? ")
			+ (queryStr.length()==0?") ":"and concat(ifnull(company_name,''),ifnull(company_area,'')) like ?) " 
			+ "or concat(ifnull(job_name,''),ifnull(job_area,''),ifnull(job_desc,'')) like ? ")
    		+ "order by "+(sortField.length()==0?"job_id":sortField)+" limit ?,?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        if(job_state!=4&&job_state!=5)	pstmt.setInt(index++, job_state);
	        pstmt.setString(index++, "%"+job_area+"%");
	        if(company_state!=4)	 		pstmt.setInt(index++, company_state);
	        if(company_size.length()!=0)	pstmt.setString(index++, company_size);
	        if(company_type.length()!=0)	pstmt.setString(index++, company_type);
	        if(queryStr.length()!=0) {
	        	pstmt.setString(index++, "%"+queryStr+"%");
		        pstmt.setString(index++, "%"+queryStr+"%");
	        }
	        pstmt.setInt(index++, pageSize * (pageNo-1));
	        pstmt.setInt(index++, pageSize);
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
	
	/**
	* 修改职位信息
	* @param job 职位对象
	* @return 是否成功
	*/
    public boolean updateJob(Job job) {
    	Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
        try{
			String sql = null;
			sql=
			"update tb_job set "
			+ "job_name=?,"
			+ "job_hiringnum=?,"
			+ "job_salary=?,"
			+ "job_area=?,"
			+ "job_desc=?,"
			+ "job_endtime=?,"
			+ "job_state=?,"
			+ "deleted_at=?,"
			+ "deleted=? "
			+ "where job_id=?";
			pstmt=conn.prepareStatement(sql);
			if(COMUtil.isNull(job.getName()))	pstmt.setNull(1, Types.VARCHAR);	else	pstmt.setString(1,job.getName());
			pstmt.setInt(2, job.getHiringNum());
			if(COMUtil.isNull(job.getSalary()))	pstmt.setNull(3, Types.VARCHAR);	else	pstmt.setString(3,job.getSalary());
			if(COMUtil.isNull(job.getArea()))	pstmt.setNull(4, Types.VARCHAR);	else	pstmt.setString(4,job.getArea());
			if(COMUtil.isNull(job.getDesc()))	pstmt.setNull(5, Types.VARCHAR);	else	pstmt.setString(5,job.getDesc());
			if(job.getEndDate()==null)			pstmt.setNull(6, Types.DATE);		else	pstmt.setTimestamp(6,new Timestamp(job.getEndDate().getTime()));
			pstmt.setInt(7, job.getState());
			if(job.getDeleted()==null)			pstmt.setNull(8, Types.DATE);		else	pstmt.setTimestamp(8,new Timestamp(job.getDeleted().getTime()));
			pstmt.setInt(9, job.getIsDeleted()?1:0);
			pstmt.setInt(10, job.getID());
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
	* 刷新所有职位招聘状态
	* @return 是否成功
	*/
    public boolean refreshJobState() {
    	Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
        try{
			String sql = null;
			sql=
			"update tb_job set job_state=3 where job_endtime <= now() and job_state >= 1";
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
	    }catch(Exception e) {
	    	e.printStackTrace();
	        return false;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return true;
    }
	
}
