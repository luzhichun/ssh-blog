package com.blog.ssh.control.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemManage {
	/**
	 * 判断当前运行环境是否在阿里云服务器上
	 * @return
	 */
	public static boolean isAliServer(){
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("host ip:" + ip);
        if(ip.equals("10.252.19.85")){
        	return true;
        }
        return false;
	}
	public static void main(String args []){
		isAliServer();
	}
}
