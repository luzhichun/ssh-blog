package com.blog.ssh.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity(name="user")
public class User implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String username;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String registertime;
	@Column
	private String url;
	@Column
	private String headpicname;//头像文件名
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	private Set<Article> articles;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "bloginfo_id", unique = true)
	private BlogInfo bloginfo;
	@Column(name="through_flag")
	private Integer throughFlag;

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Integer id, String username, String email, String password,
				String registertime, String url, String headpicname, Set articles,
				BlogInfo bloginfo, Integer throughFlag) {
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

	public BlogInfo getBloginfo() {
		return bloginfo;
	}

	public void setBloginfo(BlogInfo bloginfo) {
		this.bloginfo = bloginfo;
	}

	public Integer getThroughFlag() {
		return throughFlag;
	}

	public void setThroughFlag(Integer throughFlag) {
		this.throughFlag = throughFlag;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", registertime='" + registertime + '\'' +
				", url='" + url + '\'' +
				", headpicname='" + headpicname + '\'' +
				", bloginfo=" + bloginfo +
				", throughFlag=" + throughFlag +
				'}';
	}
}