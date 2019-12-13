package com.qst.itoffer.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
* 常用工具类
* @author 节奏葳
* @version 1.0
*/
public class COMUtil {
	/**
	* 判断String是否为空或""
	* @param str
	* @return yyyy-MM-dd HH:mm:ss
	*/
	public static boolean isNull(String str) {
		if(str != null && str.length() != 0) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	* 将时间转换为字符串 yyyy-MM-dd HH:mm:ss
	* @param date
	* @return yyyy-MM-dd HH:mm:ss
	*/
	public static String dataToStrLong(Date date) {
		if(date!=null) {
			DateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        return t.format(date);
		}else {
			return "";
		}
	}
	
	/**
	* 将字符串 yyyy-MM-dd HH:mm:ss 转换为时间
	* @param dateStr
	* @return Date
	*/
	public static Date strLongToDate(String dateStr) {
		if(dateStr.length() == 0)	return null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	* 将时间转换为字符串 yyyy-MM-dd
	* @param date
	* @return yyyy-MM-dd
	*/
	public static String dataToStr(Date date) {
		if(date!=null) {
			DateFormat t = new SimpleDateFormat("yyyy-MM-dd");
	        return t.format(date);
		}else {
			return "";
		}
	}
	
	/**
	* 将字符串 yyyy-MM-dd 转换为时间
	* @param dateStr
	* @return Date
	*/
	public static Date strToDate(String dateStr) {
		if(dateStr.length() == 0)	return null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	* 将时间转换为字符串 yyyy年MM月dd日
	* @param date
	* @return yyyy年MM月dd日
	*/
	public static String dataToData(Date date) {
		if(date!=null) {
			DateFormat t = new SimpleDateFormat("yyyy年MM月dd日");
	        return t.format(date);
		}else {
			return "";
		}
	}
	
	/**
	* 将时间段字符串转化为 yyyy年MM月dd日 至 yyyy年MM月dd日
	* @param periodStr
	* @return yyyy年MM月dd日
	*/
	public static String periodStrToPeriod(String periodStr) {
		if(periodStr.length()==0)	return "";
		String start = periodStr.split("_")[0];
		String end = periodStr.split("_")[1];
		start = dataToData(strToDate(start));
		end = dataToData(strToDate(end));
		return start+" 至 "+end;
	}
	
	/**
	* 根据生日计算实际年龄
	* @param birthday
	* @return 年龄
	*/
    public static int dataToAge(Date birthDay){
    	if(birthDay == null)	return 0;
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { 
        	return 0;
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }
    
    /**
	* 隐藏手机号
	* @param phone
	* @return 隐藏中间部分的手机号
	*/
    public static String HidePhone(String phone) {
    	if(phone==null||phone.length()==0)	return "";
    	else	return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
    
    /**
	* 隐藏邮箱
	* @param email
	* @return 隐藏中间部分的邮箱
	*/
    public static String HideEmail(String email) {
    	if(email==null||email.length()==0)	return "";
    	else	return email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
    }

	/**
	* 解析Base64格式图片并上传
	* @param dataURL
	* @param path
	* @param imgName
	* @return 
	*/
    public static void decodeBase64DataURLToImageAndUpload(String dataURL, String path, String imgName) throws IOException {
        //将dataURL开头的非base64字符删除
        String base64 = dataURL.substring(dataURL.indexOf(",") + 1);
        //讲Base64格式转化为二进制流
        byte[] decoderBytes = Base64.getDecoder().decode(base64);
        //目录不存在先创建目录
        File file = new File(path);if(!file.exists()){file.mkdirs();}
        //输出文件到服务器目录
        FileOutputStream write = new FileOutputStream(new File(path  + "/" + imgName));
        write.write(decoderBytes);
        write.close();
        System.out.print(path);
        //输出文件到本机目录
        write = new FileOutputStream(new File("C:/Wokespaces/SWRW/WebRoot/" + path.substring(44) + "/" + imgName));
        write.write(decoderBytes);
        write.close();
    }
    
    public static void main(String[] args) {
	}

}
