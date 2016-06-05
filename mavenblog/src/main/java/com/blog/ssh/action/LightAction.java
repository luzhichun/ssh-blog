package com.blog.ssh.action;
import org.apache.struts2.ServletActionContext;

import com.blog.ssh.service.CommentService;
import com.blog.ssh.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LightAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	@Autowired
	private MessageService messageService;
	@Autowired
	private CommentService commentService;
	public LightAction(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MessageService getMessageService() {
		return messageService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public CommentService getCommentService() {
		return commentService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	public String CommentLight(){
		commentService.setCommentLight(id);
		return "success";
	}
	public String MessageLight(){
		messageService.setMessageLight(id);
		String contextPath = ServletActionContext.getServletContext().getContextPath();
		System.out.println("contextPath:" + contextPath);
		return "success";
	}
}
