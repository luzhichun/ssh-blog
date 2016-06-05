package com.blog.ssh.model.pojo;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.bytecode.internal.javassist.FieldHandled;
import org.hibernate.bytecode.internal.javassist.FieldHandler;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractArticle entity provides the base persistence definition of the
 * Article entity. @author MyEclipse Persistence Tools
 */

public class Article implements java.io.Serializable {
	// Fields
	private Integer id;
	private String title;
	private String content;
	private byte[] byteContent;
	private String beagincontent;
	private String releasetime;
	private String filename;
	private User user;
	private Integer userId;
	private Articletype articletype;
	private Integer articletypeId;
	private String imagename;
	private Integer visits;
	private Set comments = new HashSet();
	private Integer commentCount;
	private Set tags = new HashSet();
	private ArticleContent articleContent;
	// Constructors

	/** default constructor */
	public Article() {
	}
	/** 显示文章列表*/
	public Article(Integer id, String title, String beagincontent,
				   String releasetime, String filename,
				   Integer userId, String username, String url,
				   Integer articletypeId, String value, String linkname,
				   String imagename, Integer visits) {
		this.id = id;
		this.title = title;
		this.beagincontent = beagincontent;
		this.releasetime = releasetime;
		this.filename = filename;
		this.user= new User();
		user.setId(userId);
		user.setUsername(username);
		user.setUrl(url);
		this.articletype = new Articletype();
		articletype.setId(articletypeId);
		articletype.setValue(value);
		articletype.setLinkname(linkname);
		this.articletypeId = articletypeId;
		this.imagename = imagename;
		this.visits = visits;
	}

	/**
	 * @param id
	 * @param title
     */
	public Article(Integer id, String title) {
		this.id = id;
		this.title = title;
	}
	public Article(Integer id, String title,
				   String beagincontent, String releasetime, String filename,
				   User user, Articletype articletype, String imagename,
				   Integer visits, Set comments, Set tags) {
		this.id = id;
		this.title = title;
		this.beagincontent = beagincontent;
		this.releasetime = releasetime;
		this.filename = filename;
		this.user = user;
		this.articletype = articletype;
		this.imagename = imagename;
		this.visits = visits;
		this.comments = comments;
		this.tags = tags;
	}

	/** full constructor */
	public Article(Integer id, String title, String content,
			String beagincontent, String releasetime, String filename,
			User user, Articletype articletypeId, String imagename,
			Integer visits, Set comments, Set tags) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.beagincontent = beagincontent;
		this.releasetime = releasetime;
		this.filename = filename;
		this.user = user;

		this.articletype = articletypeId;
		this.imagename = imagename;
		this.visits = visits;
		this.comments = comments;
		this.tags = tags;
	}

	// Property accessors
	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBeagincontent() {
		return this.beagincontent;
	}

	public void setBeagincontent(String beagincontent) {
		this.beagincontent = beagincontent;
	}

	public String getReleasetime() {
		return this.releasetime;
	}

	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Articletype getArticletype() {
		return this.articletype;
	}

	public void setArticletype(Articletype articletypeId) {
		this.articletype = articletypeId;
	}

	public String getImagename() {
		return this.imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public Integer getVisits() {
		return this.visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public Set getComments() {
		return comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getTags() {
		return tags;
	}

	public void setTags(Set tags) {
		this.tags = tags;
	}

	public ArticleContent getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(ArticleContent articleContent) {
		this.articleContent = articleContent;
	}
}