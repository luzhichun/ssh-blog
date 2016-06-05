package com.blog.ssh.action.admin;

import java.net.InetAddress;
import java.util.Map;
import java.util.Properties;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

public class ShowIndexAction extends ActionSupport{
	private String osName;//操作系统名称
	private String javaVersion;//java运行环境版本
	private String jvmName;//java虚拟机名称
	private String ip;//服务器ip地址
	private String hostName;//主机名
	
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getJavaVersion() {
		return javaVersion;
	}
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}
	public String getJvmName() {
		return jvmName;
	}
	public void setJvmName(String jvmName) {
		this.jvmName = jvmName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public static void Config(){ 
	        try{ 
	            InetAddress addr = InetAddress.getLocalHost();  
	            String ip=addr.getHostAddress().toString(); //获取本机ip  
	            String hostName=addr.getHostName().toString(); //获取本机计算机名称或者域名
	            System.out.println("本机IP："+ip+"\n本机名称:"+hostName); 
	            Properties props=System.getProperties(); 
	            System.out.println("Java的运行环境版本："+props.getProperty("java.version")); 
	            System.out.println("操作系统的名称："+props.getProperty("os.name")); 
	            System.out.println("Java的虚拟机实现名称："+props.getProperty("java.vm.name")); 
	        }catch(Exception e){ 
	            e.printStackTrace(); 
	        } 
	} 
	public String execute(){
		Map session = ActionContext.getContext().getSession();
		if(session.get("admin") == null){
			return "notlogin";
		}
		try{ 
            InetAddress addr = InetAddress.getLocalHost();  
            this.ip=addr.getHostAddress().toString(); //获取本机ip  
            this.hostName=addr.getHostName().toString(); //获取本机计算机名称或者域名 
            Properties props=System.getProperties();
            this.javaVersion = props.getProperty("java.version");
            this.osName = props.getProperty("os.name");
            this.jvmName = props.getProperty("java.vm.name");
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
		return "success";
	}
	public static void main(String args []){
		Config();
	}
}
