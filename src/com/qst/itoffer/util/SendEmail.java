package com.qst.itoffer.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;


public class SendEmail {
	
	private static String from = "lhw981121@foxmail.com";
	private static String password = "mnnimlualsrshibf";
	private static String to = "";
	private static String title = "";
	private static String content = "";
    
    @SuppressWarnings("static-access")
	public boolean send(String to,String title,String content) throws Exception{
    	if(!checkEmail(to))	return false;
    	this.to=to;
    	this.title=title;
    	this.content=content;
    	Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        //session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect("smtp.qq.com", from , password);
        //4、创建邮件
        Message message = createSimpleMail(session);
        if(message==null) return false;
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
        return true;
    }
    
	public static MimeMessage createSimpleMail(Session session) throws Exception{
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        /*//指明邮件的发件人
        message.setFrom(new InternetAddress(from));*/
    	//设置自定义发件人昵称  
        String nick="";  
        try {  
            nick=javax.mail.internet.MimeUtility.encodeText("神葳总局");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }
		message.setFrom(new InternetAddress(nick+" <"+from+">"));
		//指明邮件的收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //设置发送日期  
        message.setSentDate(new Date()); 
        //邮件的标题
        message.setSubject(title);
        //邮件的文本内容
        message.setContent(content, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
        //指明邮件的收件人
        /*InternetAddress[] tos= new InternetAddress[address.length];
        for (int i = 0; i < address.length; i++) {
            if(!address[i].trim().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)+$")){
        System.exit(-1);
            }
            tos[i] = new InternetAddress(address[i].trim());
        }
        mimeMsg.setRecipients(MimeMessage.RecipientType.TO,tos);//设置收件人，抄送*/        
    }
	
	public boolean checkEmail(String email) {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            return false;
        }

        String host = "";
        String hostName = email.split("@")[1];
        Record[] result = null;
        SMTPClient client = new SMTPClient();

        try {
            // 查找MX记录
            Lookup lookup = new Lookup(hostName, Type.MX);
            lookup.run();
            if (lookup.getResult() != Lookup.SUCCESSFUL) {
                return false;
            } else {
                result = lookup.getAnswers();
            }

            // 连接到邮箱服务器
            for (int i = 0; i < result.length; i++) {
                host = result[i].getAdditionalName().toString();
                client.connect(host);
                if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                    client.disconnect();
                    continue;
                } else {
                    break;
                }
            }

            //以下2项自己填写快速的，有效的邮箱
            client.login("163.com");
            client.setSender("sxgkwei@163.com");
            client.addRecipient(email);
            if (250 == client.getReplyCode()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
            }
        }
        return false;
    }
}