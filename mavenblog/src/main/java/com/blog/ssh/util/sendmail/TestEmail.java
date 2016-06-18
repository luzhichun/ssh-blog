package com.blog.ssh.util.sendmail;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class TestEmail {

public static void main(String args []){
	 try {
         send_email("1057160387@qq.com","测试","测试测试");
     }catch (Exception e) {
         e.printStackTrace();
     }
}
    public static void send_email(String sendMailAddress,String title,String content) throws Exception{
        String to = sendMailAddress;
        //String subject = "成绩";//邮件主题
        //String content = "<center>邮箱发送成功了</center>";//邮件内容
        Properties properties = new Properties();
        InputStream resourceAsStream = null;
        try {
             resourceAsStream = TestEmail.class.getResourceAsStream("/email.properties");
            properties.load(resourceAsStream);
        } finally{
            if (resourceAsStream!=null) {
                resourceAsStream.close();
            }
        }
        //System.err.println("properties:"+properties);
        properties.put("mail.smtp.host", properties.get("mail.smtp.host"));
        properties.put("mail.smtp.port", properties.get("mail.smtp.port"));
        properties.put("mail.smtp.auth", "true");
        Authenticator authenticator = new EmailAuthenticator(properties.get("username").toString(), properties.get("password").toString());
        javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
        MimeMessage mailMessage = new MimeMessage(sendMailSession);
        mailMessage.setFrom(new InternetAddress(properties.get("username").toString()));
        // Message.RecipientType.TO属性表示接收者的类型为TO
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mailMessage.setSubject(title, "UTF-8");
        mailMessage.setSentDate(new Date());
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        html.setContent(content.trim(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        mailMessage.setContent(mainPart);
        Transport.send(mailMessage);
        System.err.println("邮件已发送至:"+sendMailAddress);
    }
}