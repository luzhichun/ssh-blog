package com.blog.ssh.control.action.admin;

import java.util.List;

import com.blog.ssh.control.service.MessageService;
import com.blog.ssh.model.pojo.Message;

public class ShowMessages {
	private String sort;//�������
	private List<Message> messages;
	private MessageService messageService;
	public ShowMessages(){
		
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public MessageService getMessageService() {
		return messageService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	public String execute(){
		if(!RightsManagement.adminIsLogin()){
			return "notlogin";
		}
		if(sort != null && !sort.equals("all")){
			//�������
			this.messages = messageService.getUnAuditing();
		}
		else{
			this.messages = messageService.getAllMessage();
		}
		return "success";
	}
}
