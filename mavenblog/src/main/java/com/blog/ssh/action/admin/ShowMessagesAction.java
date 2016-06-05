package com.blog.ssh.action.admin;

import java.util.List;

import com.blog.ssh.service.MessageService;
import com.blog.ssh.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowMessagesAction {
	private String sort;//分类管理
	private List<Message> messages;
	@Autowired
	private MessageService messageService;
	public ShowMessagesAction(){
		
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
		if(!RightsManagementAction.adminIsLogin()){
			return "notlogin";
		}
		if(sort != null && !sort.equals("all")){
			//分类管理
			this.messages = messageService.getUnAuditing();
		}
		else{
			this.messages = messageService.getAllMessage();
		}
		return "success";
	}
}
