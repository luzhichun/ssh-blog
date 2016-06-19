package com.blog.ssh.action.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.blog.ssh.util.Time;
import com.blog.ssh.service.BloginfoService;
import com.blog.ssh.util.MD5;
import com.blog.ssh.service.UserService;
import com.blog.ssh.pojo.User;
import com.opensymphony.xwork2.ActionContext;
import com.blog.ssh.util.sendmail.TestEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserAction {
	private String username;
	private String email;
	private String password;
	private String result;
	private String loginState;
	@Autowired
	private UserService userService;
	@Autowired
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

	/**
	 * user登录
	 * @return 登录状态
     */
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(username == null || password == null){
			request.setAttribute("loginState", "请输入用户名和密码");
			return "failed";
		}
		try{
			System.out.println(username);
			int rVal = userService.checkLogin(username,password);
			System.out.println("登录状态:" + rVal);
			if(rVal == 1){
				//登录成功
				request.setAttribute("loginState", "登录成功");
				return "success";
			}
			else if(rVal == 2){
				request.setAttribute("loginState", "该账户已被封禁，请联系管理员");
				return "failed";
			}
			else{
				request.setAttribute("loginState", "账号或密码错误");
				return "failed";
				//登录失败
			}
		} catch(Exception e){
			e.printStackTrace();
			request.setAttribute("loginState", "账号或密码错误");
			return "failed";
		}
	}
	/**
	 * 注销登录
	 * @return
     */
	public String loginOut(){
		userService.loginOut();
		return "success";
	}
	/**
	 * 注册时检测用户名
	 * @return
	 */
	public String checkUsername(){
		if(!userService.hasUser(username)){
			setResult("该用户名可用！"); 
		}
		else{
			setResult("该用户名已存在！");
		}
		return "success";
	}
	/**
	 * 发送验证码到邮箱
	 * @return
	 */
	public String sendVlidatecode(){
		try {
			String vlidateCode = (int)(Math.random()*10000) + "";
			Map map = ActionContext.getContext().getSession();
			map.put("vlidateCode", vlidateCode);//将验证码存入session中
			String content = "【梦想博客】账号注册验证码<br>验证码为：<span style=\"font-size:20px;color:red\">" + vlidateCode +
		"</span>";
			TestEmail.send_email(email,"【梦想博客】账号注册验证码",content);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		setResult("验证码发送成功!");
		return "success";
	}
	/**
	 * 注册时邮箱验证
	 * @return
	 */
	public String emailVlidate(){
		String sessionCode = null;
		sessionCode = (String) ActionContext.getContext().getSession().get("vlidateCode");
		if(sessionCode == null || vlidatecode == null || !vlidatecode.equals(sessionCode)){
			setResult("验证码错误！"); 
		}
		else{
			setResult("验证码正确！");
		}
		return "success";
	}
	/**
	 * 注册
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
			user.setPassword(MD5.getInstance().getMD5(password));//对用户密码进行md5加密
			user.setHeadpicname("wycm.jpg");
			user.setRegistertime(Time.time());
			user.setUrl(System.currentTimeMillis() + "");
			user.setBloginfo(bloginfoService.createDefaultBloginfo());
			user.setThroughFlag(1);
			userService.insertUser(user);
		} catch(Exception e){
			return "failed";
		}
		//注册成功后，直接登录
		userService.setUserSession(userService.findMaxId());
		return "success";
	}
	public String execute(){
		return "success";
	}
}
