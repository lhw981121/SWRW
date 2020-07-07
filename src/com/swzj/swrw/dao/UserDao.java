package com.swzj.swrw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.swzj.swrw.bean.User;
import com.swzj.swrw.util.DBUtil;

/**
* User类Dao层
* @author 节奏葳
* @version 1.0
*/
public class UserDao {
	/**
	* 装载数据
	* @param rs 结果集
	* @return 用户对象
	*/
	public User loadData(ResultSet rs) throws SQLException {
		User obj = new User();
		obj.setID(rs.getInt("user_id"));
    	obj.setType(rs.getInt("user_type"));
    	obj.setName(rs.getString("user_name"));
    	obj.setEmail(rs.getString("user_email"));
    	obj.setPhone(rs.getString("user_phone"));
    	obj.setPwd(rs.getString("user_pwd"));
    	obj.setApplicantID(rs.getInt("applicant_id"));
    	obj.setCompanyID(rs.getInt("company_id"));
    	obj.setCreated(rs.getTimestamp("created_at"));
    	obj.setUpdated(rs.getTimestamp("updated_at"));
    	obj.setDeleted(rs.getTimestamp("deleted_at"));
    	obj.setIsDeleted(rs.getBoolean("deleted"));
    	return obj;
	}
	
	/**
	* 获取所有用户信息
	* @return 用户对象List
	*/
	public List<User> getUserInfo() {
		List<User> list = new ArrayList<User>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from tb_user";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	User obj = loadData(rs);
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
	* 添加用户
	* @param user 用户对象
	* @return 新增用户的主键
	*/
	public int createUser(User user){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int user_id = 0;
		try{
			String sql = "";
			//添加用户信息
			sql=
			"insert into tb_user("
			+ "user_type,"
			+ "user_name,"
			+ "user_email,"
			+ "user_phone,"
			+ "user_pwd,"
    		+ "applicant_id,"
    		+ "company_id)"
    		+ "values(?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, user.getType());
			pstmt.setString(2, user.getName());
			if(user.getEmail()==null)	pstmt.setNull(3, Types.VARCHAR);	else	pstmt.setString(3,user.getEmail());
			if(user.getPhone()==null)	pstmt.setNull(4, Types.VARCHAR);	else	pstmt.setString(4,user.getPhone());
			pstmt.setString(5, user.getPwd());
			if(user.getApplicantID()==0)	pstmt.setNull(6, Types.INTEGER);	else	pstmt.setInt(6,user.getApplicantID());
			if(user.getCompanyID()==0)	pstmt.setNull(7, Types.INTEGER);	else	pstmt.setInt(7, user.getCompanyID());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				user_id = rs.getInt(1);
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
	        return user_id;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return user_id;
	}
	
	/**
	* 用户登录
	* @param account 账号
	* @param password 密码
	* @return 用户对象
	*/
	public User login(String account,String password){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		User obj = null;
		try{
			String sql="select * from tb_user where (user_email=? or user_phone=?) and user_pwd=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, account);
			pstmt.setString(3, password);
			rs=pstmt.executeQuery();
			if(rs.next()&&(account.equals(rs.getString("user_email"))||account.equals(rs.getString("user_phone")))){
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
	* 用户注册（新增用户）
	* @param user 用户对象
	* @return 新增用户的主键
	*/
	@SuppressWarnings("resource")
	public int register(User user){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int user_id = 0;
		try{
			String sql = "";
			conn.setAutoCommit(false);//开启事务
			//求职者注册（添加简历关联表）
			if(user.getType() == 1) {
				//添加简历基本信息
				sql=
				"insert into tb_resume_basicinfo("
				+ "realname,"
				+ "telephone,"
				+ "email)"
	    		+ "values(?,?,?)";
				pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, user.getName());
				if(user.getPhone()==null)	pstmt.setNull(2, Types.VARCHAR);	else	pstmt.setString(2,user.getPhone());
				if(user.getEmail()==null)	pstmt.setNull(3, Types.VARCHAR);	else	pstmt.setString(3,user.getEmail());
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					user.setApplicantID(rs.getInt(1));
				}
				//添加教育经历信息
				sql=
				"insert into tb_resume_education("
				+ "basicinfo_id)"
	    		+ "values(?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user.getApplicantID());
				pstmt.executeUpdate();
				//添加项目经验信息
				sql=
				"insert into tb_resume_project_experience("
				+ "basicinfo_id)"
	    		+ "values(?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user.getApplicantID());
				pstmt.executeUpdate();
				//添加工作经验信息
				sql=
				"insert into tb_resume_work_experience("
				+ "basicinfo_id)"
	    		+ "values(?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user.getApplicantID());
				pstmt.executeUpdate();
			}
			//企业注册（添加企业信息）
			if(user.getType()==2) {
				//添加企业信息
				sql=
				"insert into tb_company values()";
				pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					user.setCompanyID(rs.getInt(1));
				}
			}
			//添加用户信息
			sql=
			"insert into tb_user("
			+ "user_type,"
			+ "user_name,"
			+ "user_email,"
			+ "user_phone,"
			+ "user_pwd,"
    		+ "applicant_id,"
    		+ "company_id)"
    		+ "values(?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, user.getType());
			pstmt.setString(2, user.getName());
			if(user.getEmail()==null)	pstmt.setNull(3, Types.VARCHAR);	else	pstmt.setString(3,user.getEmail());
			if(user.getPhone()==null)	pstmt.setNull(4, Types.VARCHAR);	else	pstmt.setString(4,user.getPhone());
			pstmt.setString(5, user.getPwd());
			if(user.getApplicantID()==0)	pstmt.setNull(6, Types.INTEGER);	else	pstmt.setInt(6,user.getApplicantID());
			if(user.getCompanyID()==0)	pstmt.setNull(7, Types.INTEGER);	else	pstmt.setInt(7, user.getCompanyID());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				user_id = rs.getInt(1);
			}
			conn.commit();//提交事务 
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	try {
	    		conn.rollback();//回滚事务
	    		return 0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	        return user_id;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return user_id;
	}
	
	/**
	* 按用户账号查找用户
	* @param account 账号
	* @return 用户对象
	*/
	public User queryUserByAccount(String account){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		User obj = null;
		try{
			String sql="select * from tb_user where user_email=? or user_phone=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, account);
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
	* 单项数据查询用户
	* @param str 查询字段
	* @param value 查询数据
	* @return 用户对象
	*/
	public User queryUserBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		User obj = null;
		try{
			String sql="select * from tb_user where "+str+"=?";
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
	* 按用户ID查找用户
	* @param user_id 
	* @return 用户对象
	*/
	public User queryUserByID(int user_id){
		return queryUserBySingleData("user_id",String.valueOf(user_id));
	}
	
	/**
	* 按用户名查找用户
	* @param user_name 用户名
	* @return 用户对象
	*/
	public User queryUserByName(String user_name){
		return queryUserBySingleData("user_name",user_name);
	}
	
	/**
	* 按邮箱查找用户
	* @param user_email 用户邮箱
	* @return 用户对象
	*/
	public User queryUserByEmail(String user_email){
		return queryUserBySingleData("user_email",user_email);
	}
	
	/**
	* 按手机号查找用户
	* @param user_phone 用户手机号
	* @return 用户对象
	*/
	public User queryUserByPhone(String user_phone){
		return queryUserBySingleData("user_phone",user_phone);
	}
	
	/**
	* 按企业标识查找用户
	* @param company_id 企业标识
	* @return 用户对象
	*/
	public User queryUserByCompanyID(int company_id){
		return queryUserBySingleData("company_id",String.valueOf(company_id));
	}
	
	/**
	* 按求职者标识查找用户
	* @param applicant_id 求职者标识
	* @return 用户对象
	*/
	public User queryUserByApplicantID(int applicant_id){
		return queryUserBySingleData("applicant_id",String.valueOf(applicant_id));
	}
	
	/**
	* 修改用户名
	* @param user 用户对象
	* @param user_name 用户名
	* @return 是否成功
	*/
    public boolean updateUserName(User user,String user_name) {
    	String updateStr = "";
    	int count = 0;//记录是否有修改
    	Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
        //信息有改动时才修改
        if(user_name.equals(user.getName()) == false) {
        	count++;
        	updateStr += " 用户名:"+user.getName()+"->"+user_name;
            String updateSql = "update tb_user set user_name=? where user_id=?";
            try {
            	pstmt = conn.prepareStatement(updateSql);
            	pstmt.setString(1, user_name);
            	pstmt.setInt(2, user.getID());
            	pstmt.executeUpdate();
            } catch (SQLException e1) {
            	Logger.getLogger(getClass()).error("数据库语句检查或执行出错！修改用户 "+user.getID()+" 信息失败："+updateStr);
                e1.printStackTrace();
                return false;
            }
        }
        DBUtil.closeJDBC(rs, pstmt, conn);
        if(count != 0) {
        	Logger.getLogger(getClass()).info("修改用户 "+user.getID()+" 信息成功："+updateStr);
        }
        return true;
    }
	
	/**
	* 修改密码
	* @param user 用户对象
	* @param user_password 密码
	* @return 是否成功
	*/
    public boolean updateUserPassword(User user,String user_password) {
    	String updateStr = "";
    	int count = 0;//记录是否有修改
    	Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
        //信息有改动时才修改
        if(user_password.equals(user.getPwd()) == false) {
        	count++;
        	updateStr += " 密码:"+user.getPwd()+"->"+user_password;
            String updateSql = "update tb_user set user_pwd=? where user_id=?";
            try {
            	pstmt = conn.prepareStatement(updateSql);
            	pstmt.setString(1, user_password);
            	pstmt.setInt(2, user.getID());
            	pstmt.executeUpdate();
            } catch (SQLException e1) {
            	Logger.getLogger(getClass()).error("数据库语句检查或执行出错！修改用户 "+user.getID()+" 信息失败："+updateStr);
                e1.printStackTrace();
                return false;
            }
        }
        DBUtil.closeJDBC(rs, pstmt, conn);
        if(count != 0) {
        	Logger.getLogger(getClass()).info("修改用户 "+user.getID()+" 信息成功："+updateStr);
        }
        return true;
    }
    
    /**
	* 修改用户手机号
	* @param user 用户对象
	* @param user_phone 手机号
	* @return 是否成功
	*/
    public boolean updateUserPhone(User user,String user_phone) {
    	String updateStr = "";
    	int count = 0;//记录是否有修改
    	Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
        //信息有改动时才修改
        if(user_phone.equals(user.getPhone()) == false) {
        	count++;
        	updateStr += " 手机号:"+user.getPhone()+"->"+user_phone;
            String updateSql = "update tb_user set user_phone=? where user_id=?";
            try {
            	pstmt = conn.prepareStatement(updateSql);
            	if(user_phone.length()==0)	pstmt.setNull(1, Types.VARCHAR);	else	pstmt.setString(1,user_phone);
            	pstmt.setInt(2, user.getID());
            	pstmt.executeUpdate();
            } catch (SQLException e1) {
            	Logger.getLogger(getClass()).error("数据库语句检查或执行出错！修改用户 "+user.getID()+" 信息失败："+updateStr);
                e1.printStackTrace();
                return false;
            }
        }
        DBUtil.closeJDBC(rs, pstmt, conn);
        if(count != 0) {
        	Logger.getLogger(getClass()).info("修改用户 "+user.getID()+" 信息成功："+updateStr);
        }
        return true;
    }
    
    /**
	* 修改用户邮箱
	* @param user 用户对象
	* @param user_email 邮箱
	* @return 是否成功
	*/
    public boolean updateUserEmail(User user,String user_email) {
    	String updateStr = "";
    	int count = 0;//记录是否有修改
    	Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
        //信息有改动时才修改
        if(user_email.equals(user.getEmail()) == false) {
        	count++;
        	updateStr += " 邮箱:"+user.getEmail()+"->"+user_email;
            String updateSql = "update tb_user set user_email=? where user_id=?";
            try {
            	pstmt = conn.prepareStatement(updateSql);
            	if(user_email.length()==0)	pstmt.setNull(1, Types.VARCHAR);	else	pstmt.setString(1,user_email);
            	pstmt.setInt(2, user.getID());
            	pstmt.executeUpdate();
            } catch (SQLException e1) {
            	Logger.getLogger(getClass()).error("数据库语句检查或执行出错！修改用户 "+user.getID()+" 信息失败："+updateStr);
                e1.printStackTrace();
                return false;
            }
        }
        DBUtil.closeJDBC(rs, pstmt, conn);
        if(count != 0) {
        	Logger.getLogger(getClass()).info("修改用户 "+user.getID()+" 信息成功："+updateStr);
        }
        return true;
    }
	
	public static void main(String[] args) {
	}
}
