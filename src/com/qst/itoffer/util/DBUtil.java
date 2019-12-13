package com.qst.itoffer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
* 数据库工具类
* @author 节奏葳
* @version 1.0
*/
public class DBUtil {
	//数据库用户名
	static String user = "root";
	//数据库密码
	static String password = "root";
	//数据库连接地址
	static String url = "jdbc:mysql://localhost/swrw?useSSL=true&serverTimezone=Asia/Shanghai";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* 获取数据库连接
	* @return Connection
	*/
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	* 关闭数据库连接
	* @param rs 查询数据库的结果集
	* @param stmt 数据库的操作语句
	* @param conn 连接数据库的对象
	* @return Connection
	*/
	public static void closeJDBC(ResultSet rs,Statement stmt,Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
