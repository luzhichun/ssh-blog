package com.blog.ssh.control.action.admin;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class RightsManagement {
	/**
	 * �жϹ���Ա�Ƿ��¼
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
	 * �ж��û��Ƿ��¼
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
