package com.blog.ssh.control.action;

import java.util.List;
import com.blog.ssh.control.service.HeaderSiderService;
import com.blog.ssh.control.service.MessageService;
import com.blog.ssh.model.pojo.Message;
import com.opensymphony.xwork2.ActionSupport;

public class ShowMsgAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Message> parMsgs;
	private HeaderSiderService hs;
	private MessageService messageService;
	private HeaderSiderService headerSiderService;
	public ShowMsgAction(){
	}
	public List<Message> getParMsgs() {
		return parMsgs;
	}
	public void setParMsgs(List<Message> parMsgs) {
		this.parMsgs = parMsgs;
	}
	
	public HeaderSiderService getHs() {
		return hs;
	}
	public void setHs(HeaderSiderService hs) {
		this.hs = hs;
	}
	
	public MessageService getMessageService() {
		return messageService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public HeaderSiderService getHeaderSiderService() {
		return headerSiderService;
	}
	public void setHeaderSiderService(HeaderSiderService headerSiderService) {
		this.headerSiderService = headerSiderService;
	}
	public String execute(){
		headerSiderService.SessionManage();
		this.parMsgs = messageService.getAllParMessages();
		return "success";
	}
	public static void main(String args []){
		new ShowMsgAction().execute();
	}
}
