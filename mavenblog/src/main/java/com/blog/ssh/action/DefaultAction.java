package com.blog.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

@Controller
public class DefaultAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DefaultAction(){
	}
	public String execute(){
		return "success";
	}
}
