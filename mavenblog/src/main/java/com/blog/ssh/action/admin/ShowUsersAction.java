package com.blog.ssh.action.admin;

import java.util.List;

import com.blog.ssh.service.UserService;
import com.blog.ssh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowUsersAction {
	private List<User> users;
	@Autowired
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
		if(!RightsManagementAction.adminIsLogin()){
			return "botlogin";
		}
		this.users = userService.findAllUsers();
		return "success";
	}
}
