package com.blog.ssh.model.pojo;

/**
 * Bloginfo entity. @author MyEclipse Persistence Tools
 */

public class ArticleContent implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;

	// Constructors

	/** default constructor */
	public ArticleContent() {
	}

	public ArticleContent(Integer id, String content) {
		this.id = id;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}