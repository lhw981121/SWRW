package com.swzj.swrw.bean;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.swzj.swrw.dao.UserDao;
import com.swzj.swrw.util.DBUtil;

import java.util.Date;
 
/**
* 消息类
* @author 节奏葳
* @version 1.0
*/
public class Message {
	private int message_id;				//消息主键
    private String message_identifier;	//消息编号
    private int message_type;			//消息类型
    private String message_summary;		//消息概述
    private String message_content;		//消息内容
    private boolean message_readed;		//消息是否已阅读
    private int sender_id;				//发送者外键
    private int receiver_id;			//接收者外键
    private boolean deleted;			//消息是否已删除
    private Date created_at;			//新增时间
    private Date updated_at;			//修改时间
    private Date deleted_at;			//删除时间
    private static Logger logger = Logger.getLogger(User.class);	//输出日志
 
    public Message() {}
 
    public int getID() {
    	return message_id;
    }
    
    public void setID(int message_id) {
    	this.message_id = message_id;
    }
    
    public String getIden() {
        return message_identifier;
    }
 
    public void setIden(String message_identifier) {
        this.message_identifier = message_identifier;
    }
 
    public String getTypeStr() {
    	switch(message_type) {
    	case 0:return "dot bg-info";
    	case 1:return "dot bg-success";
    	case 2:return "dot bg-danger";
    	case 3:return "dot bg-warning";
    	case 4:return "dot bg-primary";
    	default:return "dot bg-info";
    	}
    }
    
    public int getType() {
    	return message_type;
    }
 
    public void setType(int message_type) {
        this.message_type = message_type;
    }
 
    public String getSummary() {
    	return message_summary;
    }
    
    public void setSummary(String message_summary) {
    	this.message_summary = message_summary;
    }
    
    public String getContent() {
    	return message_content;
    }
    
    public void setContent(String message_content) {
    	this.message_content = message_content;
    }
    
    public String getReadedStr() {
    	if(message_readed == true) {
    		return "已阅读";
    	}else {
    		return "未阅读";
    	}
    }
    
    public boolean getReaded() {
        return message_readed;
    }
 
    public void setReaded(boolean message_readed) {
        this.message_readed = message_readed;
    }
    
    public String getIsDeleteStr() {
    	if(deleted == true) {
    		return "已删除";
    	}else {
    		return "未删除";
    	}
    }
    
    public boolean getIsDeleted() {
    	return deleted;
    }
    
    public void setIsDeleted(boolean deleted) {
    	this.deleted = deleted;
    }
 
    public String getSender() {
    	if(sender_id!=0) {
    		return new UserDao().queryUserByID(sender_id).getName();
    	}else {
    		return "系统通知";
    	}
    }
    
    public int getSenderID() {
        return sender_id;
    }
 
    public void setSenderID(int sender_id) {
        this.sender_id = sender_id;
    }
    
    public String getReceiver() {
    	if(receiver_id!=0) {
    		return new UserDao().queryUserByID(receiver_id).getName();
    	}else {
    		return "";
    	}
    }
    
    public int getReceiverID() {
        return receiver_id;
    }
 
    public void setReceiverID(int receiver_id) {
        this.receiver_id = receiver_id;
    }
    
    public String getCreated() {
        if(created_at!=null) {
        	DateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String Created = t.format(created_at);
            return Created;
    	}else {
    		return "";
    	}
    }
    
    public void setCreated(Date created_at) {
    	this.created_at = created_at;
    }

    public String getUpdated() {
        if(updated_at!=null) {
        	DateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String Updated = t.format(updated_at);
            return Updated;
    	}else {
    		return "";
    	}
    }
    
    public void setUpdated(Date updated_at) {
    	this.updated_at = updated_at;
    }
    
    public String getDeleted() {
    	if(deleted_at!=null) {
    		DateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String Deleted = t.format(deleted_at);
            return Deleted;
    	}else {
    		return "";
    	}
    }
    
    public void setDeleted(Date deleted_at) {
    	this.deleted_at = deleted_at;
    }
    
    //获取随机编号
  	public static String getRandomIden() {
  		String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
                  "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
                  "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
                  "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
                  "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
                  "W", "X", "Y", "Z" };  
          Random r = new Random();
          StringBuffer shortBuffer = new StringBuffer();  
          String uuid = UUID.randomUUID().toString().replace("-", "");  
          for (int i = 0; i < 8; i++) {  
              String str = uuid.substring(i * 4, i * 4 + 4);  
              int x = Integer.parseInt(str, 16);  
              shortBuffer.append(chars[x % 0x3E]);  
          }
          return shortBuffer.toString()+(r.nextInt(899)+100);  
  	}
  	
  	 //判断编号是否存在
    public boolean isExist_identifier(String message_identifier) {
    	if(queryMessageByIden(message_identifier).size() != 0)	return true;
    	else return false;
    }
  	
  	/**
  	* 装载数据
  	* @param rs 结果集
  	* @return 职位对象
  	*/
  	public Message loadData(ResultSet rs) throws SQLException {
  		Message obj = new Message();
  		obj.setID(rs.getInt("message_id"));
  		obj.setIden(rs.getString("message_identifier"));
  		obj.setType(rs.getInt("message_type"));
  		obj.setSummary(rs.getString("message_summary"));
  		obj.setContent(rs.getString("message_content"));
  		obj.setReaded(rs.getBoolean("message_readed"));
  		obj.setIsDeleted(rs.getBoolean("deleted"));
  		obj.setSenderID(rs.getInt("sender_id"));
  		obj.setReceiverID(rs.getInt("receiver_id"));
  		obj.setCreated(rs.getTimestamp("created_at"));
  		obj.setUpdated(rs.getTimestamp("updated_at"));
  		obj.setDeleted(rs.getTimestamp("deleted_at"));
      	return obj;
  	}
 
  	/**
  	* 发送多条消息信息
  	* @param message_type 消息类型 0:info 1:success 2:danger 3:warning 4:primary
  	* @param message_summary 消息主题
  	* @param message_content 消息内容
  	* @param sender_id 发送者
  	* @param receiver_list 接收者列表
  	* @return 是否成功
  	*/
	public boolean sendMoreMessage(int message_type,String message_summary,String message_content,int sender_id,List<Integer> receiver_list) {
		for(int id:receiver_list) {
			sendSingleMessage(message_type, message_summary, message_content, sender_id, id);
		}
        logger.info(sender_id+"群发消息成功："+message_identifier+" "+message_type+" "+message_summary+" "+message_content+" "+sender_id+" "+receiver_list);
        return true;
    }
 
	/**
  	* 发送单条消息信息
  	* @param message_type 消息类型 0:info 1:success 2:danger 3:warning 4:primary
  	* @param message_summary 消息主题
  	* @param message_content 消息内容
  	* @param sender_id 发送者
  	* @param receiver_id 接收者
  	* @return 是否成功
  	*/
	public boolean sendSingleMessage(int message_type,String message_summary,String message_content,int sender_id,int receiver_id) {
		String insertSql = 
        		"insert into tb_message("
        			+ "message_identifier,"
        			+ "message_type,"
        			+ "message_summary,"
        			+ "message_content,"
        			+ "sender_id,"
	        		+ "receiver_id,"
	        		+ "created_at) "
        		+ "values(?,?,?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        String message_identifier = getRandomIden();
        while(true) {
    		if(isExist_identifier(message_identifier)) {
    			message_identifier = getRandomIden();
    		}else {
    			break;
    		}
    	}
        try {
        	ps = conn.prepareStatement(insertSql);
        	ps.setString(1, message_identifier);
        	ps.setInt(2, message_type);
        	ps.setString(3, message_summary);
        	if(message_content.length()==0)	ps.setNull(4, Types.VARCHAR);	else	ps.setString(4, message_content);
        	ps.setInt(5, sender_id);
        	ps.setInt(6, receiver_id);
        	ps.setTimestamp(7, new Timestamp(new Date().getTime()));
            ps.executeUpdate();
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("数据库语句检查或执行出错！添加消息失败："+message_identifier+" "+message_type+" "+message_summary+" "+message_content+" "+sender_id+" "+receiver_id);
            return false;
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                logger.error("添加消息"+message_identifier+" "+message_type+" "+message_summary+" "+message_content+" "+sender_id+" "+receiver_id+"后数据库关闭出错！");
            }
        }
        logger.info("添加消息成功："+message_identifier+" "+message_type+" "+message_summary+" "+message_content+" "+sender_id+" "+receiver_id);
        return true;
    }
	
	//软删除消息信息
    public boolean softDeleteGradeByID(int message_id) {
    	Connection conn = DBUtil.getConnection();
    	String updateSql = "update tb_message set deleted='"+1+"' where message_id=" + message_id;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(updateSql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据库语句检查或执行出错！软删除消息 "+message_id+" 失败。");
            return false;
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                logger.error("软删除消息 "+message_id+" 后数据库关闭出错！");
            }
        }
        logger.info("软删除消息 "+message_id+" 成功。");
        return true;
    }
	
    //删除消息信息
    public boolean deleteMessageByID(int message_id) {
    	Connection conn = DBUtil.getConnection();
        String deleteSql = "delete from tb_message where message_id=" + message_id;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(deleteSql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据库语句检查或执行出错！删除消息 "+message_id+" 失败。");
            return false;
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                logger.error("删除消息 "+message_id+" 后数据库关闭出错！");
            }
        }
        logger.info("删除消息 "+message_id+" 成功。");
        return true;
    }
    
    //删除接收者所有消息
    public boolean deleteMessageAll(int receiver_id) {
    	Connection conn = DBUtil.getConnection();
        String deleteSql = "delete from tb_message where receiver_id=" + receiver_id;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(deleteSql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据库语句检查或执行出错！删除接收者为 "+receiver_id+" 的所有消息失败。");
            return false;
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                logger.error("删除接收者为 "+receiver_id+" 的所有消息后数据库关闭出错！");
            }
        }
        logger.info("删除接收者为 "+receiver_id+" 的所有消息成功。");
        return true;
    }
    
    //删除接收者所有已读消息
    public boolean deleteMessageRead(int receiver_id) {
    	Connection conn = DBUtil.getConnection();
        String deleteSql = "delete from tb_message where message_readed=1 and receiver_id=" + receiver_id;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(deleteSql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据库语句检查或执行出错！删除接收者为 "+receiver_id+" 的所有已读消息失败。");
            return false;
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                logger.error("删除接收者为 "+receiver_id+" 的所有已读消息后数据库关闭出错！");
            }
        }
        logger.info("删除接收者为 "+receiver_id+" 的所有已读消息成功。");
        return true;
    }
    
    //删除接收者所有未读消息
    public boolean deleteMessageUnRead(int receiver_id) {
    	Connection conn = DBUtil.getConnection();
        String deleteSql = "delete from tb_message where message_readed=0 and receiver_id=" + receiver_id;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(deleteSql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据库语句检查或执行出错！删除接收者为 "+receiver_id+" 的所有未读消息失败。");
            return false;
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                logger.error("删除接收者为 "+receiver_id+" 的所有未读消息后数据库关闭出错！");
            }
        }
        logger.info("删除接收者为 "+receiver_id+" 的所有未读消息成功。");
        return true;
    }
 
    //查询消息信息
    public List<Message> queryMessage(String querySql) {
        List<Message> mesList = new ArrayList<Message>();
        Message mes = null;
        ResultSet queryRS = null;
        PreparedStatement queryStatement = null;
        Connection queryConn = null;
        try{
        	queryConn = DBUtil.getConnection();
            queryStatement = queryConn.prepareStatement(querySql);
            queryRS = queryStatement.executeQuery();
            while(queryRS.next()) {
            	mes = new Message();
                mes.message_id = queryRS.getInt("message_id");
                mes.message_identifier = queryRS.getString("message_identifier");
                mes.message_type = queryRS.getInt("message_type");
                mes.message_summary = queryRS.getString("message_summary");
                mes.message_content = queryRS.getString("message_content");
                mes.message_readed = queryRS.getBoolean("message_readed");
                mes.deleted = queryRS.getBoolean("deleted");
                mes.sender_id = queryRS.getInt("sender_id");
                mes.receiver_id = queryRS.getInt("receiver_id");
                mes.created_at = queryRS.getTimestamp("created_at");
                mes.updated_at = queryRS.getTimestamp("updated_at");
                mes.deleted_at = queryRS.getTimestamp("deleted_at");
                mesList.add(mes);
            }
        }catch(Exception e2) {
            e2.printStackTrace();
            logger.error("数据库语句检查或执行出错！消息查询失败。");
            return mesList;
        }finally{
            try {
                queryRS.close();
                queryStatement.close();
                queryConn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                logger.error("查询消息后数据库关闭出错！");
            }
        }
        return mesList;
    }
    
    
    //查询消息信息（按主键查找）
    public Message queryMessageByID(int message_id) {
        String querySql = "select * from tb_message where message_id="+"'"+message_id+"'";
        if(queryMessage(querySql).size()!=0) {
        	return queryMessage(querySql).get(0);
        }else {
        	return new Message();
        }
    }
 
    //查询消息信息（按编号查找）
    public List<Message> queryMessageByIden(String message_identifier) {
    	String querySql = "select * from tb_message where message_identifier="+"'"+message_identifier+"'";
    	return queryMessage(querySql);
    }
    
    //查询消息信息（按类型查找）
    public List<Message> queryMessageByType(int message_type) {
    	String querySql = "select * from tb_message where message_type="+"'"+message_type+"'";
        return queryMessage(querySql);
    }
    
    //查询消息信息（按是否阅读查找）
    public List<Message> queryMessageByReaded(boolean message_readed) {
    	int readed = message_readed?1:0;
    	String querySql = "select * from tb_message where message_readed="+"'"+readed+"'";
        return queryMessage(querySql);
    }
    
    //查询消息信息（按发送者查找）
    public List<Message> queryMessageBySenderID(int sender_id) {
    	String querySql = "select * from tb_message where sender_id="+"'"+sender_id+"'";
        return queryMessage(querySql);
    }
    
    //查询消息信息（按接收者查找）
    public List<Message> queryMessageByReceiverID(int receiver_id) {
    	String querySql = "select * from tb_message where receiver_id="+"'"+receiver_id+"'";
        return queryMessage(querySql);
    }
    
    //查询消息信息（按接收者和是否阅读查找）
    /**
  	* 获取消息信息
  	* @param receiver_id 接收者
  	* @param message_readed 是否已阅读
  	* @return 消息集合
  	*/
    public List<Message> queryMessageOfNew(int receiver_id,boolean message_readed) {
    	int readed = message_readed?1:0;
    	String querySql = "select * from tb_message where receiver_id="+"'"+receiver_id+"' and message_readed="+"'"+readed+"' order by created_at DESC";
        return queryMessage(querySql);
    }
    
    //查询消息信息（按是否已删除查找）
    public List<Message> queryMessageByIsDeleted(boolean deleted) {
    	int isdeleted = deleted?1:0;
    	String querySql = "select * from tb_message where deleted="+"'"+isdeleted+"'";
        return queryMessage(querySql);
    }
    
    //修改消息信息
    public boolean updateMessage(Message mes,String message_identifier,int message_type,String message_summary,String message_content,int sender_id,int receiver_id,Date updated_at) {
    	
    	String updateStr = "";
    	int count = 0;//记录是否有修改
    	Connection conn = DBUtil.getConnection();
        //信息有改动时才修改
        if(message_identifier.equals(mes.message_identifier) == false) {
        	count++;
            String updateSql = "update tb_message set message_identifier='"+message_identifier+"' where message_id=" + mes.message_id;
            try {
                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.executeUpdate();
                ps.close();
                updateStr += " 编号:"+mes.message_identifier+"->"+message_identifier;
            } catch (SQLException e1) {
            	logger.error("数据库语句检查或执行出错！修改消息 "+mes.message_id+" 编号"+mes.message_identifier+"为"+message_identifier+"失败。");
                e1.printStackTrace();
                return false;
            }
        }
        
        if(message_type!=mes.message_type) {
        	count++;
        	String updateSql = "update tb_message set message_type='"+message_type+"' where message_id=" + mes.message_id;
            try {
                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.executeUpdate();
                ps.close();
                updateStr += " 类型:"+mes.message_type+"->"+message_type;
            } catch (SQLException e1) {
            	logger.error("数据库语句检查或执行出错！修改消息 "+mes.message_id+" 类型"+mes.message_type+"为"+message_type+"失败。");
                e1.printStackTrace();
                return false;
            }
        }
        
        if(message_summary.equals(mes.message_summary) == false) {
        	count++;
        	String updateSql = "update tb_message set message_summary='"+message_summary+"' where message_id=" + mes.message_id;
            try {
                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.executeUpdate();
                ps.close();
                updateStr += " 概述:"+mes.message_summary+"->"+message_summary;
            } catch (SQLException e1) {
            	logger.error("数据库语句检查或执行出错！修改消息 "+mes.message_id+" 概述"+mes.message_summary+"为"+message_summary+"失败。");
                e1.printStackTrace();
                return false;
            }
        }
        
        if(message_content.equals(mes.message_content) == false) {
        	count++;
        	String updateSql = "update tb_message set message_content='"+message_content+"' where message_id=" + mes.message_id;
            try {
                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.executeUpdate();
                ps.close();
                updateStr += " 内容:"+mes.message_content+"->"+message_content;
            } catch (SQLException e1) {
            	logger.error("数据库语句检查或执行出错！修改消息 "+mes.message_id+" 内容"+mes.message_content+"为"+message_content+"失败。");
                e1.printStackTrace();
                return false;
            }
        }
 
        if(sender_id != mes.sender_id) {
        	count++;
        	String updateSql = "update tb_message set sender_id='"+sender_id+"' where message_id=" + mes.message_id;
            try {
                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.executeUpdate();
                ps.close();
                updateStr += " 发送者ID:"+mes.sender_id+"->"+sender_id;
            } catch (SQLException e1) {
            	logger.error("数据库语句检查或执行出错！修改消息 "+mes.message_id+" 发送者ID"+mes.sender_id+"为"+sender_id+"失败。");
                e1.printStackTrace();
                return false;
            }
        }
        
        if(receiver_id != mes.receiver_id) {
        	count++;
        	String updateSql = "update tb_message set receiver_id='"+receiver_id+"' where message_id=" + mes.message_id;
            try {
                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.executeUpdate();
                ps.close();
                updateStr += " 接收者ID:"+mes.receiver_id+"->"+receiver_id;
            } catch (SQLException e1) {
            	logger.error("数据库语句检查或执行出错！修改消息 "+mes.message_id+" 接收者ID"+mes.receiver_id+"为"+receiver_id+"失败。");
                e1.printStackTrace();
                return false;
            }
        }
        
        if(updated_at != mes.updated_at&&count != 0) {
        	String updateSql = "update tb_message set updated_at = ? where message_id="+ mes.message_id;
            try {
                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.setTimestamp(1, new Timestamp(updated_at.getTime()));
                ps.executeUpdate();
                ps.close();
                updateStr += " 修改时间:"+mes.updated_at+"->"+updated_at;
            } catch (SQLException e1) {
            	logger.error("数据库语句检查或执行出错！修改消息 "+mes.message_id+" 修改时间"+mes.updated_at+"为"+updated_at+"失败。");
                e1.printStackTrace();
                return false;
            }
        }
        
        try {
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
            logger.error("修改消息 "+mes.message_id+" 信息"+updateStr+"后数据库关闭出错！");
        }
        if(count != 0 ) {
        	logger.info("修改消息 "+mes.message_id+" 信息成功！"+updateStr);
        }
        return true;
    }
    
    //标记是否阅读
    public boolean updateMessageOfReaded(boolean message_readed) {
    	int readed = message_readed?1:0;
    	int count = 0;//记录是否有修改
    	Date nowTime = new Date();
    	Connection conn = DBUtil.getConnection();
        //信息有改动时才修改
        if(message_readed != this.message_readed) {
        	count++;
            String updateSql = "update tb_message set message_readed='"+readed+"' where message_id=" + this.message_id;
            try {
                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                logger.error("数据库语句检查或执行出错！标记消息 "+this.message_id+" 阅读状态为"+message_readed+"失败！");
                return false;
            }
        }
        if(nowTime != this.updated_at&&count != 0) {
        	String updateSql = "update tb_message set updated_at = ? where message_id="+ this.message_id;
            try {
                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.setTimestamp(1, new Timestamp(nowTime.getTime()));
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                logger.error("数据库语句检查或执行出错！标记消息 "+this.message_id+" 阅读状态为"+message_readed+"后更改修改时间失败！");
                return false;
            }
        }
        try {
            conn.close();
        } catch (SQLException e1) {
        	logger.error("标记消息 "+this.message_id+" 阅读状态为"+message_readed+"后数据库关闭出错！");
            e1.printStackTrace();
        }
        logger.info("标记消息 "+this.message_id+" 阅读状态为"+message_readed+"成功！");
        return true;
    }
    
    //标记接收者所有消息已经阅读
    public boolean updateMessageAllReaded(int receiver_id) {
    	Connection conn = DBUtil.getConnection();
    	List<Message> mesList = queryMessageByReceiverID(receiver_id);
		for(Message message:mesList) {
			message.updateMessageOfReaded(true);
		}
		try {
			conn.close();
			logger.info("标记接收者 "+receiver_id+" 所有消息为已阅读成功！");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("数据库关闭出错！标记接收者 "+receiver_id+" 所有消息为已阅读失败！");
			return false;
		}
    }

    //获取所有数据量（无条件）
    public int CountOfAll() {
    	int count = 0;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try{
        	conn = DBUtil.getConnection();
            ps = conn.prepareStatement("select count(*) from tb_message");
            rs = ps.executeQuery();
            if(rs.next()) {
            	count = rs.getInt(1);
            }
        }catch(Exception e2) {
            e2.printStackTrace();
            logger.error("数据库语句检查或执行出错！无条件获取消息数据量失败！");
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e1) {
            	logger.error("无条件获取消息数据量后数据库关闭出错！");
                e1.printStackTrace();
            }
        }
        return count;
    }
    
    //从数据库获取所有消息信息返回消息表
    public List<Message> getMessageInfo() {
        List<Message> mesList = new ArrayList<Message>();
        Message mes = null;
        ResultSet queryRS = null;
        PreparedStatement queryStatement = null;
        Connection queryConn = null;
        try{
        	queryConn = DBUtil.getConnection();
            String sqlQuery = "select * from tb_message";
            queryStatement = queryConn.prepareStatement(sqlQuery);
            queryRS = queryStatement.executeQuery();
            while(queryRS.next()) {
                mes = new Message();
                mes.message_id = queryRS.getInt("message_id");
                mes.message_identifier = queryRS.getString("message_identifier");
                mes.message_type = queryRS.getInt("message_type");
                mes.message_summary = queryRS.getString("message_summary");
                mes.message_content = queryRS.getString("message_content");
                mes.message_readed = queryRS.getBoolean("message_readed");
                mes.deleted = queryRS.getBoolean("deleted");
                mes.sender_id = queryRS.getInt("sender_id");
                mes.receiver_id = queryRS.getInt("receiver_id");
                mes.created_at = queryRS.getTimestamp("created_at");
                mes.updated_at = queryRS.getTimestamp("updated_at");
                mes.deleted_at = queryRS.getTimestamp("deleted_at");
                mesList.add(mes);
            }
        }catch(Exception e2) {
            e2.printStackTrace();
            logger.error("数据库语句检查或执行出错！获取消息信息失败！");
        }finally{
            try {
                queryRS.close();
                queryStatement.close();
                queryConn.close();
            } catch (SQLException e1) {
            	logger.error("获取消息信息后数据库关闭出错！");
                e1.printStackTrace();
            }
        }
        return mesList;
    }
    
    /**
	* 获取分页消息信息数据总数量
	* @param message_readed 是否已读 0(未读)、1(已读)
	* @param sender_id 发送者
	* @param receiver_id 接收者
	* @param queryStr 查询字串
	* @param deleted 是否已删除 0(未删除)、1(已删除)
	* @return 数据总数量
	*/
	public int getPageDataMessageCount(int message_readed,int sender_id,int receiver_id,String queryStr,int deleted) {
		int count = 0;
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery = 
    		"select count(*) from tb_message "
    		+ "where sender_id=? "
			+ "and receiver_id=? "
			+ (message_readed==2?"":"and message_readed=? ")
			+ "and concat(ifnull(message_identifier, ''),ifnull(message_summary,''),ifnull(message_content,'')) like ? "
    		+ "and deleted=?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setInt(index++, sender_id);
	        pstmt.setInt(index++, receiver_id);
	        if(message_readed!=2)	pstmt.setInt(index++, message_readed);
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
	* 获取分页消息信息数据
	* @param pageNo 当前页
	* @param pageSize 每页记录数
	* @param message_readed 是否已读 0(未读)、1(已读)
	* @param sender_id 发送者
	* @param receiver_id 接收者
	* @param queryStr 查询字串
	* @param deleted 是否已删除 0(未删除)、1(已删除)
	* @param sortField 排序字段及排序方式 ASC(升序)、DESC(降序)
	* @return 消息对象List
	*/
	public List<Message> getPageDataMessage(int pageNo,int pageSize,int message_readed,int sender_id,int receiver_id,
											String queryStr,int deleted,String sortField) {
		List<Message> list = new ArrayList<Message>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery = 
    		"select * from tb_message "
    		+ "where sender_id=? "
			+ "and receiver_id=? "
			+ (message_readed==2?"":"and message_readed=? ")
    		+ "and concat(ifnull(message_identifier, ''),ifnull(message_summary,''),ifnull(message_content,'')) like ? "
    		+ "and deleted=? "
    		+ "order by "+sortField+" limit ?,?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setInt(index++, sender_id);
	        pstmt.setInt(index++, receiver_id);
	        if(message_readed!=2)	pstmt.setInt(index++, message_readed);
	        pstmt.setString(index++, "%"+queryStr+"%");
	        pstmt.setInt(index++, deleted);
	        pstmt.setInt(index++, pageSize * (pageNo-1));
	        pstmt.setInt(index++, pageSize);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Message obj = loadData(rs);
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
  	* 获取新消息数量
  	* @param receiver_id 接收者
  	* @return 数量
  	*/
    public int getNewMessageCount(int receiver_id) {
    	int count = 0;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try{
        	conn = DBUtil.getConnection();
            ps = conn.prepareStatement("select count(*) from tb_message where receiver_id=?");
            ps.setInt(1, receiver_id);
            rs = ps.executeQuery();
            if(rs.next()) {
            	count = rs.getInt(1);
            }
        }catch(Exception e2) {
            e2.printStackTrace();
            logger.error("数据库语句检查或执行出错！");
            return 0;
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e1) {
            	logger.error("数据库关闭出错！");
                e1.printStackTrace();
            }
        }
        return count;
    }
}