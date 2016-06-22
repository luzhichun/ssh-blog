package com.blog.ssh.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */
@Entity(name="comment")
public class Comment implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name="article_id")
	private Article article;
	@Column
	private String content;
	@Column
	private String time;
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	@Column(name="through_flag")
	private Integer throughFlag;
	@Column(name="auditing_flag")
	private Integer auditingFlag;
	@Column
	private Integer light;
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private Comment parComment;
	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "parComment")
	private Set<Comment> chiComments;
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name = "reply_id")
	private Comment replyComment;
	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "replyComment")
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

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", content='" + content + '\'' +
				", time='" + time + '\'' +
				", throughFlag=" + throughFlag +
				", auditingFlag=" + auditingFlag +
				", light=" + light +
				", parComment=" + parComment +
				", replyComment=" + replyComment +
				'}';
	}
}