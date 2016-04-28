package com.blog.ssh.control.action;

import com.blog.ssh.control.dao.Time;
import com.blog.ssh.control.service.MessageService;
import com.blog.ssh.control.service.UserService;
import com.blog.ssh.model.pojo.Message;
import com.blog.ssh.model.pojo.User;
import com.blog.ssh.sensitivewordsfilter.SensitivewordFilter;
import com.opensymphony.xwork2.ActionContext;

public class AddMessageAction {
	private String content;
	private int parent_id;
	private int reply_id;
	private MessageService messageService;
	private UserService userService;
	public AddMessageAction(){

	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	
	public MessageService getMessageService() {
		return messageService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String execute(){
		System.out.println("parent_id:" + parent_id);
		System.out.println("reply_id:" + parent_id);
		User user = (User)ActionContext.getContext().getSession().get("user");
		if(parent_id == 0 && reply_id == 0){
			//ֱ������
			Message msg = new Message();
			msg.setTime(Time.time());
			msg.setAuditingFlag(0);
			msg.setThroughFlag(0);
			msg.setLight(0);
			msg.setContent(SensitivewordFilter.filter(content));
			msg.setUser(user);
			messageService.insertMessage(msg);
		}else{
			//�ظ�����
			Message msg = new Message();
			msg.setTime(Time.time());
			msg.setAuditingFlag(0);
			msg.setThroughFlag(0);
			msg.setLight(0);
			msg.setContent(SensitivewordFilter.filter(content));
			msg.setUser(user);
			msg.setParMessage(messageService.getMessage(parent_id));
			msg.setReplyMessage(messageService.getMessage(reply_id));
			System.out.println(messageService.getMessage(parent_id).getId());
			messageService.insertMessage(msg);
		}
		return "success";
	}
	public static void main(String args []){
		new AddMessageAction().execute();
	}
}
