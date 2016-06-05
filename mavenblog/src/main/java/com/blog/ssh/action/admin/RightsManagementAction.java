package com.blog.ssh.action.admin;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import org.springframework.stereotype.Controller;

public class RightsManagementAction {
	/**
	 * 判断管理员是否登录
	 * @return
	 */
	public static boolean adminIsLogin(){
		Map session = ActionContext.getContext().getSession();
		if(session.get("admin") == null){
			return false;
		}
		else{
			return true;
		}
	}
	/**
	 * 判断用户是否登录
	 * @return
	 */
	public static boolean userIsLogin(){
		Map session = ActionContext.getContext().getSession();
		if(session.get("user") == null){
			return false;
		}
		else{
			return true;
		}
	}
}
