package com.blog.ssh.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Bloginfo entity. @author MyEclipse Persistence Tools
 */
@Entity(name="article_content")
public class ArticleContent implements java.io.Serializable {

	// Fields
	@GenericGenerator(name="ac", strategy = "increment")
	@Id
	private Integer id;
	@Column
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