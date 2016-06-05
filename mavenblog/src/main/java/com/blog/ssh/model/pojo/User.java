package com.blog.ssh.model.pojo;

import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String email;
	private String password;
	private String registertime;
	private String url;
	private String headpicname;//头像文件名
	private Set articles;
	private Bloginfo bloginfo;
	private Integer throughFlag;
	private Integer blogComments;//数据库中不存在该字段，用于显示用户博客的评论数
	private Integer msgCounts;//数据库中不存在该字段，用于显示用户博客的消息数
	private Integer articleCounts;//数据库中不存在该字段，用于显示用户博客文章数
	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Integer id, String username, String email, String password,
				String registertime, String url,String headpicname,Set articles,
				Bloginfo bloginfo,Integer throughFlag) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.registertime = registertime;
		this.url = url;
		this.headpicname =headpicname;
		this.articles = articles;
		this.bloginfo = bloginfo;
		this.throughFlag = throughFlag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHeadpicname() {
		return headpicname;
	}

	public void setHeadpicname(String headpicname) {
		this.headpicname = headpicname;
	}

	public Set getArticles() {
		return articles;
	}

	public void setArticles(Set articles) {
		this.articles = articles;
	}

	public Bloginfo getBloginfo() {
		return bloginfo;
	}

	public void setBloginfo(Bloginfo bloginfo) {
		this.bloginfo = bloginfo;
	}

	public Integer getBlogComments() {
		return blogComments;
	}

	public void setBlogComments(Integer blogComments) {
		this.blogComments = blogComments;
	}

	public Integer getThroughFlag() {
		return throughFlag;
	}

	public void setThroughFlag(Integer throughFlag) {
		this.throughFlag = throughFlag;
	}

	public Integer getMsgCounts() {
		return msgCounts;
	}

	public void setMsgCounts(Integer msgCounts) {
		this.msgCounts = msgCounts;
	}

	public Integer getArticleCounts() {
		return articleCounts;
	}

	public void setArticleCounts(Integer articleCounts) {
		this.articleCounts = articleCounts;
	}

}