package com.qst.itoffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qst.itoffer.bean.BasicInfo;
import com.qst.itoffer.util.DBUtil;

/**
* BasicInfo类Dao层
* @author 节奏葳
* @version 1.0
*/
public class BasicInfoDao {
	/**
	* 装载数据
	* @param rs 结果集
	* @return 简历对象
	*/
	public BasicInfo loadData(ResultSet rs) throws SQLException {
		BasicInfo obj = new BasicInfo();
		obj.setID(rs.getInt("basicinfo_id"));
    	obj.setRealName(rs.getString("realname"));
    	obj.setGender(rs.getString("gender"));
    	obj.setBirthday(rs.getTimestamp("birthday"));
    	obj.setCurrentLoc(rs.getString("current_loc"));
    	obj.setResidentLoc(rs.getString("resident_loc"));
    	obj.setTelephone(rs.getString("telephone"));
    	obj.setEmail(rs.getString("email"));
    	obj.setJobIntension(rs.getString("job_intension"));
    	obj.setJobExp(rs.getString("job_experience"));
    	obj.setHeadShot(rs.getString("head_shot"));
    	obj.setCreated(rs.getTimestamp("created_at"));
    	obj.setUpdated(rs.getTimestamp("updated_at"));
    	obj.setDeleted(rs.getTimestamp("deleted_at"));
    	obj.setIsDeleted(rs.getBoolean("deleted"));
    	return obj;
	}
	
	/**
	* 获取所有简历信息
	* @return 简历对象List
	*/
	public List<BasicInfo> getBasicInfoInfo() {
		List<BasicInfo> list = new ArrayList<BasicInfo>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from tb_resume_basicinfo";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	BasicInfo obj = loadData(rs);
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
	 * 简历基本信息添加
	 * @param basicinfo
	 * @param applicantID
	 * @return
	 */
	public int add(BasicInfo basicinfo, int applicantID) {
		int basicinfoID = 0;
		String sql = "INSERT INTO tb_resume_basicinfo("
				+ "basicinfo_id, realname, gender, birthday, current_loc, "
				+ "resident_loc, telephone, email, job_intension, job_experience, head_shot,applicant_id) "
				+ "VALUES(SEQ_ITOFFER_RESUMEBASICINFO.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			// 关闭自动提交
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, basicinfo.getRealName());
			pstmt.setString(2, basicinfo.getGender());
			pstmt.setTimestamp(3, basicinfo.getBirthday() == null ? null: new Timestamp(basicinfo.getBirthday().getTime()));
			pstmt.setString(4, basicinfo.getCurrentLoc());
			pstmt.setString(5, basicinfo.getResidentLoc());
			pstmt.setString(6, basicinfo.getTelephone());
			pstmt.setString(7, basicinfo.getEmail());
			pstmt.setString(8, basicinfo.getJobIntension());
			pstmt.setString(9, basicinfo.getJobExp());
			pstmt.setString(10, basicinfo.getHeadShot());
			pstmt.setInt(11, applicantID);
			pstmt.executeUpdate();
			pstmt.close();
			// 获取当前生成的简历标识
			String sql2 = "SELECT SEQ_ITOFFER_RESUMEBASICINFO.CURRVAL FROM dual";
			pstmt = conn.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				basicinfoID = rs.getInt(1);
			// 事务提交
			conn.commit();
		} catch (SQLException e) {
			try {
				// 事务回滚
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		return basicinfoID;
	}
	
	/**
	* 更改简历照片
	* @param id basicinfo_id
	* @param String head_shot
	* @return 是否成功
	*/
    public boolean updateHeadShot(int basicinfo_id,String head_shot) {
    	StringBuffer updateStr = new StringBuffer();
    	Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
        String updateSql = "update tb_resume_basicinfo set head_shot=? where basicinfo_id=?";
        try {
        	pstmt = conn.prepareStatement(updateSql);
        	pstmt.setString(1, head_shot);
        	pstmt.setInt(2, basicinfo_id);
        	pstmt.executeUpdate();
            updateStr.append(" 简历照片:"+head_shot);
        } catch (SQLException e1) {
        	Logger.getLogger(getClass()).error("数据库语句检查或执行出错！");
            e1.printStackTrace();
            return false;
        }
        DBUtil.closeJDBC(rs, pstmt, conn);
        Logger.getLogger(getClass()).info("更新简历基本信息 "+basicinfo_id+" 信息成功："+updateStr);
        return true;
    }
	
	/**
	* 单项数据查询简历基本信息
	* @param str 查询字段
	* @param value 查询数据
	* @return 简历基本信息对象
	*/
	public BasicInfo queryBasicInfoBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    BasicInfo obj = null;
		try{
			String sql="select * from tb_resume_basicinfo where "+str+"=?";
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
	 * 根据简历基本信息标识（求职者标识）查询简历基本信息
	 * @param basicinfo_id 简历基本信息标识（求职者标识）
	 * @return 简历基本信息对象
	 */
	public BasicInfo queryBasicInfoByID(int basicinfo_id){
		return queryBasicInfoBySingleData("basicinfo_id",String.valueOf(basicinfo_id));
	}
	
	/**
	 * 更新简历基本信息
	 * @param basicinfo
	 */
	public void update(BasicInfo basicinfo) {
		String sql = "UPDATE tb_resume_basicinfo "
				+ "SET realname=?, gender=?,birthday=?,current_loc=?,resident_loc=?,"
				+ "telephone=?,email=?,job_intension=?,job_experience=? "
				+ "WHERE basicinfo_id=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, basicinfo.getRealName());
			pstmt.setString(2, basicinfo.getGender());
			pstmt.setTimestamp(3, basicinfo.getBirthday() == null ? null: new Timestamp(basicinfo.getBirthday().getTime()));
			pstmt.setString(4, basicinfo.getCurrentLoc());
			pstmt.setString(5, basicinfo.getResidentLoc());
			pstmt.setString(6, basicinfo.getTelephone());
			pstmt.setString(7, basicinfo.getEmail());
			pstmt.setString(8, basicinfo.getJobIntension());
			pstmt.setString(9, basicinfo.getJobExp());
			pstmt.setInt(10, basicinfo.getID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
}
