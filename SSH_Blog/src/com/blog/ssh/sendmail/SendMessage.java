package com.blog.ssh.sendmail;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class SendMessage {
	public static void sendMessage(String phoneNumber,String message)throws Exception{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://gbk.sms.webchinese.cn"); 
		post.setHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("Uid", "卧颜沉默"));
		formparams.add(new BasicNameValuePair("Key", "4e02b140ad6fc6dc8184"));
		formparams.add(new BasicNameValuePair("smsMob", phoneNumber));
		formparams.add(new BasicNameValuePair("smsText", message));
		UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(formparams, "GBK");
		post.setEntity(entity1);
		HttpResponse response = client.execute(post);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("statusCode:"+statusCode);
		if(statusCode == 200){
			System.out.println("短信发送成功");
		}
		post.releaseConnection();
	}
//	public static void main(String[] args)throws Exception{
//		DefaultHttpClient client = new DefaultHttpClient();
//		HttpPost post = new HttpPost("http://gbk.sms.webchinese.cn"); 
//		post.setHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
//		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//		formparams.add(new BasicNameValuePair("Uid", "卧颜沉默"));
//		formparams.add(new BasicNameValuePair("Key", "4e02b140ad6fc6dc8184"));
//		formparams.add(new BasicNameValuePair("smsMob", "13088280860"));
//		formparams.add(new BasicNameValuePair("smsText", "这是专用于测试的信息，能否正常发短信呢？啦啦啦"));
//		UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(formparams, "GBK");
//		post.setEntity(entity1);
//		HttpResponse response = client.execute(post);
//		int statusCode = response.getStatusLine().getStatusCode();
//		System.out.println("statusCode:"+statusCode);
//		if(statusCode == 200){
//			System.out.println("短信发送成功");
//		}
//		post.releaseConnection();
//	}
}