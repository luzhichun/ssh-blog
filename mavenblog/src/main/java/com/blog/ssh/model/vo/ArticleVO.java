package com.blog.ssh.model.vo;

import com.blog.ssh.model.pojo.Articletype;
import com.blog.ssh.model.pojo.Comment;
import com.blog.ssh.model.pojo.User;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractArticle entity provides the base persistence definition of the
 * Article entity. @author MyEclipse Persistence Tools
 */

public class ArticleVO implements java.io.Serializable {

	// Fields

	private Integer id;
	private Set comments = new HashSet();
	// Constructors

	/** default constructor */
	public ArticleVO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set getComments() {
		return comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}
}