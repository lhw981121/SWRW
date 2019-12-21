package com.swzj.swrw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.swzj.swrw.bean.Company;
import com.swzj.swrw.bean.Job;
import com.swzj.swrw.util.COMUtil;
import com.swzj.swrw.util.DBUtil;

/**
* Company类Dao层
* @author 节奏葳
* @version 1.0
*/
public class CompanyDao {
	/**
	* 装载数据
	* @param rs 结果集
	* @return 企业对象
	*/
	public Company loadData(ResultSet rs) throws SQLException {
		Company obj = new Company();
		obj.setID(rs.getInt("company_id"));
    	obj.setName(rs.getString("company_name"));
    	obj.setLegal(rs.getString("company_legal"));
    	obj.setArea(rs.getString("company_area"));
    	obj.setSize(rs.getString("company_size"));
    	obj.setType(rs.getString("company_type"));
    	obj.setBrief(rs.getString("company_brief"));
    	obj.setState(rs.getInt("company_state"));
    	obj.setSort(rs.getInt("company_sort"));
    	obj.setViewNum(rs.getInt("company_viewnum"));
    	obj.setPic(rs.getString("company_pic"));
    	obj.setLicense(rs.getString("company_license"));
    	obj.setCreated(rs.getTimestamp("created_at"));
    	obj.setUpdated(rs.getTimestamp("updated_at"));
    	obj.setDeleted(rs.getTimestamp("deleted_at"));
    	obj.setIsDeleted(rs.getBoolean("deleted"));
    	return obj;
	}
	
	/**
	* 获取所有企业信息
	* @return 企业对象List
	*/
	public List<Company> getCompanyInfo() {
		List<Company> list = new ArrayList<Company>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from tb_company";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Company obj = loadData(rs);
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
	* 企业认证
	* @param company 企业对象
	* @return 是否成功
	*/
	public boolean companyCertificate(Company company){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		try{
			String sql = null;
			//更新企业信息
			sql=
			"update tb_company set "
			+ "company_name=?,"
			+ "company_legal=?,"
			+ "company_area=?,"
			+ "company_size=?,"
			+ "company_type=?,"
			+ "company_brief=?,"
			+ "company_state=?,"
			+ "company_license=? "
			+ "where company_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,company.getName());
			pstmt.setString(2,company.getLegal());
			pstmt.setString(3,company.getArea());
			pstmt.setString(4,company.getSize());
			pstmt.setString(5,company.getType());
			pstmt.setString(6,company.getBrief());
			pstmt.setInt(7,-1);
			pstmt.setString(8, company.getLicense());
			pstmt.setInt(9, company.getID());
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
	* 单项数据查询企业
	* @param str 查询字段
	* @param value 查询数据
	* @return 企业对象
	*/
	public Company queryCompanyBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Company obj = null;
		try{
			String sql="select * from tb_company where "+str+"=?";
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
	* 按企业ID查找企业信息
	* @param company_id
	* @return 企业对象
	*/
	public Company queryCompanyByID(int company_id){
		return queryCompanyBySingleData("company_id",String.valueOf(company_id));
	}
	
	/**
	* 获取分页企业信息数据总数量
	* @param company_state 企业招聘状态：-2(审核未通过)、-1(审核中)、0(不可用)、1(招聘中)、2(已暂停)、3(已结束)、4(审核通过)
	* @param company_area 企业所在地
	* @param company_size 企业规模
	* @param company_type 企业性质
	* @param queryStr 查询字串
	* @param deleted 是否已删除 0(未删除)、1(已删除)
	* @return 数据总数量
	*/
	public int getPageDataCompanyCount(int company_state,String company_area,String company_size,String company_type,String queryStr,int deleted) {
		int count = 0;
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery = 
    		"select count(*) from tb_company "
    		+ "where "+(company_state==4?"company_state>0":"company_state=?")+" "
    		+ "and company_area like ? "
    		+ (company_size.length()==0?"":"and company_size=? ")
    		+ (company_type.length()==0?"":"and company_type=? ")
    		+ "and concat(ifnull(company_name, ''),ifnull(company_area,'')) like ? "
    		+ "and deleted=?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        if(company_state!=4)	 pstmt.setInt(index++, company_state);
	        pstmt.setString(index++, "%"+company_area+"%");
	        if(company_size.length()!=0)	pstmt.setString(index++, company_size);
	        if(company_type.length()!=0)	pstmt.setString(index++, company_type);
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
	* 获取分页企业信息数据
	* @param pageNo 当前页
	* @param pageSize 每页记录数
	* @param company_state 企业招聘状态：-2(审核未通过)、-1(审核中)、0(不可用)、1(招聘中)、2(已暂停)、3(已结束)、4(审核通过)
	* @param company_area 企业所在地
	* @param company_size 企业规模
	* @param company_type 企业性质
	* @param queryStr 查询字串
	* @param deleted 是否已删除 0(未删除)、1(已删除)
	* @param sortField 排序字段及排序方式 ASC(升序)、DESC(降序)
	* @return 企业对象List
	*/
	public List<Company> getPageDataCompany(int pageNo,int pageSize,int company_state,String company_area,String company_size,String company_type,
											String queryStr,int deleted,String sortField) {
		List<Company> list = new ArrayList<Company>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery = 
    		"select * from tb_company "
    		+ "where "+(company_state==4?"company_state>0":"company_state=?")+" "
    		+ "and company_area like ? "
    		+ (company_size.length()==0?"":"and company_size=? ")
    		+ (company_type.length()==0?"":"and company_type=? ")
    		+ "and concat(ifnull(company_name, ''),ifnull(company_area,'')) like ? "
    		+ "and deleted=? "
    		+ "order by "+(sortField.length()==0?"company_id":sortField)+" limit ?,?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        if(company_state!=4)	pstmt.setInt(index++, company_state);
	        pstmt.setString(index++, "%"+company_area+"%");
	        if(company_size.length()!=0)	pstmt.setString(index++, company_size);
	        if(company_type.length()!=0)	pstmt.setString(index++, company_type);
	        pstmt.setString(index++, "%"+queryStr+"%");
	        pstmt.setInt(index++, deleted);
	        pstmt.setInt(index++, pageSize * (pageNo-1));
	        pstmt.setInt(index++, pageSize);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Company obj = loadData(rs);
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
	* 修改企业信息
	* @param company 企业对象
	* @return 是否成功
	*/
    public boolean updateCompany(Company company) {
    	Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
        try{
			String sql = null;
			sql=
			"update tb_company set "
			+ "company_name=?,"
			+ "company_legal=?,"
			+ "company_area=?,"
			+ "company_size=?,"
			+ "company_type=?,"
			+ "company_brief=?,"
			+ "company_state=?,"
			+ "company_sort=?,"
			+ "company_viewnum=?,"
			+ "company_pic=?,"
			+ "company_license=?,"
			+ "deleted_at=?,"
			+ "deleted=? "
			+ "where company_id=?";
			pstmt=conn.prepareStatement(sql);
			if(COMUtil.isNull(company.getName()))	pstmt.setNull(1, Types.VARCHAR);	else	pstmt.setString(1,company.getName());
			if(COMUtil.isNull(company.getLegal()))	pstmt.setNull(2, Types.VARCHAR);	else	pstmt.setString(2,company.getLegal());
			if(COMUtil.isNull(company.getArea()))	pstmt.setNull(3, Types.VARCHAR);	else	pstmt.setString(3,company.getArea());
			if(COMUtil.isNull(company.getSize()))	pstmt.setNull(4, Types.VARCHAR);	else	pstmt.setString(4,company.getSize());
			if(COMUtil.isNull(company.getType()))	pstmt.setNull(5, Types.VARCHAR);	else	pstmt.setString(5,company.getType());
			if(COMUtil.isNull(company.getBrief()))	pstmt.setNull(6, Types.VARCHAR);	else	pstmt.setString(6,company.getBrief());
			pstmt.setInt(7, company.getState());
			pstmt.setInt(8, company.getSort());
			pstmt.setInt(9, company.getViewNum());
			if(COMUtil.isNull(company.getPic()))	pstmt.setNull(10, Types.VARCHAR);	else	pstmt.setString(10,company.getPic());
			if(COMUtil.isNull(company.getLicense()))pstmt.setNull(11, Types.VARCHAR);	else	pstmt.setString(11,company.getLicense());
			if(company.getDeleted()==null)			pstmt.setNull(12, Types.DATE);		else	pstmt.setTimestamp(12,new Timestamp(company.getDeleted().getTime()));
			pstmt.setInt(13, company.getIsDeleted()?1:0);
			pstmt.setInt(14, company.getID());
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
	 * 查询所有正在招聘中的企业信息以及该企业的最新职位信息
	 * 
	 * @return
	 */
	public List<Company> getCompanyList() {
		List<Company> list = new ArrayList<Company>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT tb_company.company_id,company_pic,job_id,job_name,job_salary,job_area,job_endtime "
					+ "FROM tb_company "
					+ "LEFT OUTER JOIN tb_job ON tb_job.company_id=tb_company.company_id "
					+ "WHERE company_state=1 and job_id IN ("
					+ "SELECT MAX(job_id) FROM tb_job WHERE job_state=1 GROUP BY company_id"
					+ ")";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				Job job = new Job();
				company.setID(rs.getInt("company_id"));
				company.setPic(rs.getString("company_pic"));
				job.setID(rs.getInt("job_id"));
				job.setName(rs.getString("job_name"));
				job.setSalary(rs.getString("job_salary"));
				job.setArea(rs.getString("job_area"));
				job.setEndDate(rs.getTimestamp("job_endtime"));
				company.getJobs().add(job);
				list.add(company);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 根据企业标识查询企业详情
	 * 
	 * @param companyID
	 * @return
	 */
	public Company getCompanyByID(String companyID) {
		Company company = new Company();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM tb_company WHERE company_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(companyID));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				company.setID(rs.getInt("company_id"));
				company.setArea(rs.getString("company_area"));
				company.setBrief(rs.getString("company_brief"));
				company.setPic(rs.getString("company_pic"));
				company.setSize(rs.getString("company_size"));
				company.setType(rs.getString("company_type"));
				company.setViewNum(rs.getInt("company_viewnum"));
				company.setName(rs.getString("company_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return company;
	}
	
	/**
	 * 分页查询首页所需要的所有企业信息及职位信息
	 * @return
	 */
	public List<Company> getCompanyPageList(int pageNo,int pageSize) {
		// 定义本页记录索引值
		int firstIndex = pageSize * (pageNo-1);
		List<Company> list = new ArrayList<Company>();
		Connection connection = DBUtil.getConnection();
		if (connection == null)
			return null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM ( SELECT a.* , ROWNUM rn FROM ( "
							+ "SELECT tb_company.company_id,company_pic,job_id,"
							+ "job_name,job_salary,job_area,job_endtime "
							+ "FROM tb_company "
							+ "LEFT OUTER JOIN tb_job ON tb_company.company_id=tb_job.company_id "
							+ "WHERE company_state=1 and job_id IN ("
							+ "SELECT MAX(job_id) FROM tb_job WHERE job_state=1 GROUP BY company_id"
							+ ")) a WHERE ROWNUM<=? ) WHERE rn>? ");
			pstmt.setInt(1, firstIndex+pageSize);
			pstmt.setInt(2, firstIndex);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				Job job = new Job();
				company.setID(rs.getInt("company_id"));
				company.setPic(rs.getString("company_pic"));
				job.setID(rs.getInt("job_id"));
				job.setName(rs.getString("job_name"));
				job.setSalary(rs.getString("job_salary"));
				job.setArea(rs.getString("job_area"));
				job.setEndDate(rs.getTimestamp("job_endtime"));
				company.getJobs().add(job);
				list.add(company);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, connection);
		}
		return list;
	}
	
	/**
	 * 查询所需分页的总记录数
	 * @param pageSize
	 * @return
	 */
	public int getRecordCount() {
		int recordCount = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM tb_company "
					+ "LEFT OUTER JOIN tb_job ON tb_job.company_id=tb_company.company_id "
					+ "WHERE company_state=1 and job_id IN ("
					+ "SELECT MAX(job_id) FROM tb_job WHERE job_state=1 GROUP BY company_id"
					+ ")";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				recordCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return recordCount;
	}
	
	/**
	 * 更新企业的浏览次数
	 * @param id 企业ID
	 */
	public void updateCompanyViewCount(int id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "UPDATE tb_company "
					+ "SET company_viewnum=company_viewnum+1 "
					+ "WHERE company_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
	}
}
