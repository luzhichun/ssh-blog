package com.blog.ssh.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


/**
 * Message entity. @author MyEclipse Persistence Tools
 */
@Entity(name="message")
public class Message implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String time;
	@Column
	private String content;
	@Column(name="through_flag")
	private Integer throughFlag;
	@Column(name="auditing_flag")
	private Integer auditingFlag;
	@Column
	private Integer light;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Message parMessage;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parMessage")
	private Set<Message> chiMessages;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reply_id")
	private Message replyMessage;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "replyMessage")
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