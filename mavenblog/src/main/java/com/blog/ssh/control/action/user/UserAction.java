package com.blog.ssh.control.action.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.blog.ssh.control.dao.Time;
import com.blog.ssh.control.service.BloginfoService;
import com.blog.ssh.control.service.MD5;
import com.blog.ssh.control.service.UserService;
import com.blog.ssh.model.pojo.Bloginfo;
import com.blog.ssh.model.pojo.User;
import com.opensymphony.xwork2.ActionContext;
import com.blog.ssh.sendmail.Test_Email;

public class UserAction {
	private String username;
	private String email;
	private String password;
	private String result;
	private String loginState;
	private UserService userService;
	private BloginfoService bloginfoService;
	private String vlidatecode;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}
	
	public BloginfoService getBloginfoService() {
		return bloginfoService;
	}

	public void setBloginfoService(BloginfoService bloginfoService) {
		this.bloginfoService = bloginfoService;
	}
	
	public String getVlidatecode() {
		return vlidatecode;
	}

	public void setVlidatecode(String vlidatecode) {
		this.vlidatecode = vlidatecode;
	}

	@SuppressWarnings("unchecked")
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(username == null || password == null){
			request.setAttribute("loginState", "�������û���������");
			return "failed";
		}
		try{
			System.out.println(username);
			int rVal = userService.checkLogin(username,password);
			if(rVal == 1){
				//��¼�ɹ�
				request.setAttribute("loginState", "��¼�ɹ�");
				return "success";
			}
			else if(rVal == 2){
				request.setAttribute("loginState", "���˻��ѱ����������ϵ����Ա");
				return "failed";
			}
			else{
				request.setAttribute("loginState", "�˺Ż��������");
				return "failed";
				//��¼ʧ��
			}
		} catch(Exception e){
			request.setAttribute("loginState", "�˺Ż��������");
			return "failed";
		}
	}
	public String loginOut(){
		userService.loginOut();
		return "success";
	}
	/**
	 * ע��ʱ����û���
	 * @return
	 */
	public String checkUsername(){
		if(!userService.hasUser(username)){
			setResult("���û������ã�"); 
		}
		else{
			setResult("���û����Ѵ��ڣ�");
		}
		return "success";
	}
	/**
	 * ������֤�뵽����
	 * @return
	 */
	public String sendVlidatecode(){
		try {
			String vlidateCode = (int)(Math.random()*10000) + "";
			Map map = ActionContext.getContext().getSession();
			map.put("vlidateCode", vlidateCode);//����֤�����session��
			String content = "�����벩�͡��˺�ע����֤��<br>��֤��Ϊ��<span style=\"font-size:20px;color:red\">" + vlidateCode +
		"</span>";
			Test_Email.send_email(email,"�����벩�͡��˺�ע����֤��",content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed";
		}
		setResult("��֤�뷢�ͳɹ�!");
		return "success";
	}
	/**
	 * ע��ʱ������֤
	 * @return
	 */
	public String emailVlidate(){
		String sessionCode = null;
		sessionCode = (String) ActionContext.getContext().getSession().get("vlidateCode");
		if(sessionCode == null || vlidatecode == null || !vlidatecode.equals(sessionCode)){
			setResult("��֤�����"); 
		}
		else{
			setResult("��֤����ȷ��");
		}
		return "success";
	}
	/**
	 * ע��
	 * @return
	 */
	public String addUser(){
		System.out.println(email + username + password);
		if(email == null || username == null || password == null){
			return "failed";
		}
		if(userService.hasUser(username)){
			return "failed"; 
		}
		try{
			User user = new User();
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(MD5.getInstance().getMD5(password));//���û��������md5����
			user.setHeadpicname("wycm.jpg");
			user.setRegistertime(Time.time());
			user.setUrl(System.currentTimeMillis() + "");
			user.setBloginfo(bloginfoService.createDefaultBloginfo());
			user.setThroughFlag(1);
			userService.insertUser(user);
		} catch(Exception e){
			return "failed";
		}
		//ע��ɹ���ֱ�ӵ�¼
		userService.setUserSession(userService.findMaxId());
		return "success";
	}
	public String execute(){
		return "success";
	}
}
