package com.blog.ssh.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity(name="article")
public class Article implements java.io.Serializable {
	// Fields
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	@Column
	private String title;
	private String content;
	@Column
	private String beagincontent;
	@Column
	private String releasetime;
	@Column
	private String filename;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "articletype_id")
	private ArticleType articleType;
	@Column
	private String imagename;
	@Column
	private Integer visits;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "article")
	private Set<Comment> comments = new HashSet();
	@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinTable(name = "article_tag",
			joinColumns = {@JoinColumn(name="article_id")},
			inverseJoinColumns = {@JoinColumn(name="tag_id")})
	private Set<Tag> tags = new HashSet();
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="content_id")
	private ArticleContent articleContent;
	/** default constructor */
	public Article() {
	}
	/** 显示文章列表*/
	public Article(Integer id, String title, String beagincontent,
				   String releasetime, String filename,
				   Integer userId, String username, String url,
				   String value, String linkname,
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
		this.articleType = new ArticleType();
		articleType.setValue(value);
		articleType.setLinkname(linkname);
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
				   User user, ArticleType articletype, String imagename,
				   Integer visits, Set comments, Set tags) {
		this.id = id;
		this.title = title;
		this.beagincontent = beagincontent;
		this.releasetime = releasetime;
		this.filename = filename;
		this.user = user;
		this.articleType = articletype;
		this.imagename = imagename;
		this.visits = visits;
		this.comments = comments;
		this.tags = tags;
	}

	/** full constructor */
	public Article(Integer id, String title, String content,
				   String beagincontent, String releasetime, String filename,
				   User user, ArticleType articletypeId, String imagename,
				   Integer visits, Set comments, Set tags) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.beagincontent = beagincontent;
		this.releasetime = releasetime;
		this.filename = filename;
		this.user = user;
		this.articleType = articletypeId;
		this.imagename = imagename;
		this.visits = visits;
		this.comments = comments;
		this.tags = tags;
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

	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public ArticleType getArticletype() {
		return this.articleType;
	}

	public void setArticletype(ArticleType articletypeId) {
		this.articleType = articletypeId;
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

	@Override
	public String toString() {
		return "Article{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", beagincontent='" + beagincontent + '\'' +
				", releasetime='" + releasetime + '\'' +
				", filename='" + filename + '\'' +
				", user=" + user +
				", articletype=" + articleType +
				", imagename='" + imagename + '\'' +
				", visits=" + visits +
				", comments=" + comments +
				", tags=" + tags +
				", articleContent=" + articleContent +
				'}';
	}
}