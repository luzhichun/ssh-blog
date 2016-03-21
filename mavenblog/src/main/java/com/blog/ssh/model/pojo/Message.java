package com.blog.ssh.model.pojo;

import java.util.Set;


/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer id;
	private String time;
	private String content;
	private Integer throughFlag;
	private Integer auditingFlag;
	private Integer light;
	private User user;
	private Message parMessage;
	private Set<Message> chiMessages;
	private Message replyMessage;
	private Set<Message> byreplyMessages;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** full constructor */
	public Message(Integer id, String time, String content,
			Integer throughFlag, Integer auditingFlag, Integer light,
			User user, Message parMessage, Set<Message> chiMessages,
			Message replyMessage, Set<Message> byreplyMessages) {
		super();
		this.id = id;
		this.time = time;
		this.content = content;
		this.throughFlag = throughFlag;
		this.auditingFlag = auditingFlag;
		this.light = light;
		this.user = user;
		this.parMessage = parMessage;
		this.chiMessages = chiMessages;
		this.replyMessage = replyMessage;
		this.byreplyMessages = byreplyMessages;
	}
	
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getThroughFlag() {
		return this.throughFlag;
	}

	public void setThroughFlag(Integer throughFlag) {
		this.throughFlag = throughFlag;
	}

	public Integer getAuditingFlag() {
		return this.auditingFlag;
	}

	public void setAuditingFlag(Integer auditingFlag) {
		this.auditingFlag = auditingFlag;
	}

	public Integer getLight() {
		return this.light;
	}

	public void setLight(Integer light) {
		this.light = light;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message getParMessage() {
		return parMessage;
	}

	public void setParMessage(Message parMessage) {
		this.parMessage = parMessage;
	}

	public Set<Message> getChiMessages() {
		return chiMessages;
	}

	public void setChiMessages(Set<Message> chiMessages) {
		this.chiMessages = chiMessages;
	}

	public Message getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(Message replyMessage) {
		this.replyMessage = replyMessage;
	}

	public Set<Message> getByreplyMessages() {
		return byreplyMessages;
	}

	public void setByreplyMessages(Set<Message> byreplyMessages) {
		this.byreplyMessages = byreplyMessages;
	}
	
}