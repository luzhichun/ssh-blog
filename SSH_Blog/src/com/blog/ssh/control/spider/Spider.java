package com.blog.ssh.control.spider;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Spider {
	/**
	 * 解析源代码，获取身份证信息
	 * 
	 * @param URL 地址
	 */
	public static String[] getCardIDMsg(String URL){
		String[] msg = new String[6]; 
		Document doc = null;
		try {
			doc = Jsoup.connect(URL).timeout(100000).get();
			//System.out.println(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(doc.select(".tabs td a").text());
		msg[0] = doc.select(".tabs td").get(5).text();
		msg[1] = doc.select(".tabs td").get(7).text();
		msg[2] = doc.select(".tabs td").get(9).text();
		msg[3] = doc.select(".tabs td").get(11).text();
		msg[4] = doc.select(".tabs td").get(13).text();
		msg[5] = doc.select(".tabs td a").text();
		return msg;
	}
	public static void main(String args[]){
		getCardIDMsg("http://www.gpsspg.com/sfz/?q=511623199411096713");
	}
}
