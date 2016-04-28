package com.blog.ssh.sendmail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Test_Email {

public static void main(String args []){
	 try {
         send_email("355530569@qq.com","����","���Բ���");
     }catch (Exception e) {
         e.printStackTrace();
     }
}
    public static void send_email(String sendMailAddress,String title,String content) throws Exception{
        String to = sendMailAddress;
        //String subject = "�ɼ�";//�ʼ�����
        //String content = "<center>���䷢�ͳɹ���</center>";//�ʼ�����
        Properties properties = new Properties();
        InputStream resourceAsStream = null;
        try {
             resourceAsStream = Test_Email.class.getResourceAsStream("email.properties");
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
        Authenticator authenticator = new Email_Authenticator(properties.get("username").toString(), properties.get("password").toString());
        javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
        MimeMessage mailMessage = new MimeMessage(sendMailSession);
        mailMessage.setFrom(new InternetAddress(properties.get("username").toString()));
        // Message.RecipientType.TO���Ա�ʾ�����ߵ�����ΪTO
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mailMessage.setSubject(title, "UTF-8");
        mailMessage.setSentDate(new Date());
        // MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���
        Multipart mainPart = new MimeMultipart();
        // ����һ������HTML���ݵ�MimeBodyPart
        BodyPart html = new MimeBodyPart();
        html.setContent(content.trim(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        mailMessage.setContent(mainPart);
        Transport.send(mailMessage);
        System.err.println("�ʼ��ѷ�����:"+sendMailAddress);
    }
}