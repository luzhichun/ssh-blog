package com.blog.ssh.pojo;

import java.util.Set;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields
	
	private Integer id;
	private Article article;
	private String content;
	private String time;
	private User user;
	private Integer throughFlag;
	private Integer auditingFlag;
	private Integer light;
	private Comment parComment;
	private Set<Comment> chiComments;
	private Comment replyComment;
	private Set<Comment> byreplyComments;

	/** default constructor */
	public Comment() {
	}

	/** full constructor */
	public Comment(Integer id, Article article, String content, String time,
			User user, Integer throughFlag, Integer auditingFlag,
			Integer light, Comment parComment, Set<Comment> chiComments,
			Comment replyComment, Set<Comment> byreplyComments) {
		super();
		this.id = id;
		this.article = article;
		this.content = content;
		this.time = time;
		this.user = user;
		this.throughFlag = throughFlag;
		this.auditingFlag = auditingFlag;
		this.light = light;
		this.parComment = parComment;
		this.chiComments = chiComments;
		this.replyComment = replyComment;
		this.byreplyComments = byreplyComments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Comment getParComment() {
		return parComment;
	}

	public void setParComment(Comment parComment) {
		this.parComment = parComment;
	}

	public Set<Comment> getChiComments() {
		return chiComments;
	}

	public void setChiComments(Set<Comment> chiComments) {
		this.chiComments = chiComments;
	}

	public Comment getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(Comment replyComment) {
		this.replyComment = replyComment;
	}

	public Set<Comment> getByreplyComments() {
		return byreplyComments;
	}

	public void setByreplyComments(Set<Comment> byreplyComments) {
		this.byreplyComments = byreplyComments;
	}
	
	
}