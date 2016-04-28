package com.blog.ssh.control.action;
import org.apache.struts2.ServletActionContext;

import com.blog.ssh.control.service.CommentService;
import com.blog.ssh.control.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;

public class LightAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private MessageService messageService;
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
