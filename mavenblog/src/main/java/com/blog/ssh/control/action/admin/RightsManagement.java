package com.blog.ssh.control.action.admin;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class RightsManagement {
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
