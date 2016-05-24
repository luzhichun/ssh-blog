package com.blog.ssh.control.action.user;

import com.blog.ssh.control.service.UserService;
import com.blog.ssh.control.service.UserSiderService;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.User;
import com.blog.ssh.model.pojo.UserSider;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class UserIndex {
	private User user;
	private UserService userService;
	private UserSider userSider;
	private UserSiderService userSiderService;
	
	public UserIndex() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserIndex(UserService userService) {
		super();
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserSiderService getUserSiderService() {
		return userSiderService;
	}

	public void setUserSiderService(UserSiderService userSiderService) {
		this.userSiderService = userSiderService;
	}
	
	public UserSider getUserSider() {
		return userSider;
	}

	public void setUserSider(UserSider userSider) {
		this.userSider = userSider;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getUserindex(){
		this.user = (User) ActionContext.getContext().getSession().get("user");
		this.userSider = userSiderService.getUserSider(user.getId());
		return "success";
	}
	/**
	 * 获取某个用户的主页
	 * @return
	 */
	public String execute(){
		ActionInvocation ai = (ActionInvocation) ActionContext.getContext().get(ActionContext.ACTION_INVOCATION);
		final String action = ai.getProxy().getActionName();//用户url
		System.out.println("action:" + action);
		if(userService.hasUserByurl(action)){
			User u = userService.findUserByurl(action);
			this.user = u;
			this.userSider = userSiderService.getUserSider(u.getId());
			return "success";
		}
		else{
			return "404";
		}
	}
	/**
	 * 获取文章
	 * @return
	 */
}
