package com.blog.ssh.action;

import java.util.List;
import com.blog.ssh.service.HeaderSiderService;
import com.blog.ssh.service.MessageService;
import com.blog.ssh.pojo.Message;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowMsgAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Message> parMsgs;
	private HeaderSiderService hs;
	@Autowired
	private MessageService messageService;
	@Autowired
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
