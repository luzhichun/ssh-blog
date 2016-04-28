package com.blog.ssh.control.action.admin;

import java.net.InetAddress;
import java.util.Map;
import java.util.Properties;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShowIndex extends ActionSupport{
	private String osName;//����ϵͳ����
	private String javaVersion;//java���л����汾
	private String jvmName;//java���������
	private String ip;//������ip��ַ
	private String hostName;//������
	
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
	            String ip=addr.getHostAddress().toString(); //��ȡ����ip  
	            String hostName=addr.getHostName().toString(); //��ȡ������������ƻ�������
	            System.out.println("����IP��"+ip+"\n��������:"+hostName); 
	            Properties props=System.getProperties(); 
	            System.out.println("Java�����л����汾��"+props.getProperty("java.version")); 
	            System.out.println("����ϵͳ�����ƣ�"+props.getProperty("os.name")); 
	            System.out.println("Java�������ʵ�����ƣ�"+props.getProperty("java.vm.name")); 
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
            this.ip=addr.getHostAddress().toString(); //��ȡ����ip  
            this.hostName=addr.getHostName().toString(); //��ȡ������������ƻ������� 
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
