package com.qst.itoffer.util;

import java.net.URLEncoder;

public class PhoneCode {
	
	// 根据相应的手机号发送验证码
	public boolean sendPhoneCode(String phone,String code) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("accountSid").append("=").append(Config.ACCOUNT_SID);
		sb.append("&to").append("=").append(phone);
		//sb.append("&param").append("=").append(URLEncoder.encode("","UTF-8"));
		//sb.append("&templateid").append("=").append("1251");
		sb.append("&smsContent").append("=").append( URLEncoder.encode("【神葳总局】尊敬的用户，您好，您的验证码为："+code+"，如非本人操作，请忽略此短信。","UTF-8"));
		String body = sb.toString() + HttpUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
		String result = HttpUtil.post(Config.BASE_URL, body);
		if(result.indexOf("成功")!=-1) {
			return true;
		}else {
			return false;
		}
	}
 
	// 创建验证码
	public static String smsCode() {
		String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
		return random;
	}
}
