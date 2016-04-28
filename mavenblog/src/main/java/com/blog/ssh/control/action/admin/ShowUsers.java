package com.blog.ssh.control.action.admin;

import java.util.List;
import java.util.Map;

import com.blog.ssh.control.service.UserService;
import com.blog.ssh.model.pojo.User;
import com.opensymphony.xwork2.ActionContext;

public class ShowUsers {
	private List<User> users;
	private UserService userService;
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String execute(){
		if(!RightsManagement.adminIsLogin()){
			return "botlogin";
		}
		this.users = userService.findAllUsers();
		return "success";
	}
}
