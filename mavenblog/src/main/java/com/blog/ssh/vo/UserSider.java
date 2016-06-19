package com.blog.ssh.vo;

import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.Comment;
import com.blog.ssh.pojo.Tag;
import com.blog.ssh.pojo.User;

import java.util.List;

public class UserSider {
	private List<Article> userLatestArticles;
	private List<Article> userHotArticles;
	private List<Article> userRandomArticles;
	private List<Comment> userLatestComments;
	private List<Tag> userHotTags;
	private User user;
	private Integer blogComments;
	public UserSider() {
	}
	public List<Article> getUserLatestArticles() {
		return userLatestArticles;
	}
	public void setUserLatestArticles(List<Article> userLatestArticles) {
		this.userLatestArticles = userLatestArticles;
	}
	public List<Article> getUserHotArticles() {
		return userHotArticles;
	}
	public void setUserHotArticles(List<Article> userHotArticles) {
		this.userHotArticles = userHotArticles;
	}
	public List<Article> getUserRandomArticles() {
		return userRandomArticles;
	}
	public void setUserRandomArticles(List<Article> userRandomArticles) {
		this.userRandomArticles = userRandomArticles;
	}
	public List<Comment> getUserLatestComments() {
		return userLatestComments;
	}
	public void setUserLatestComments(List<Comment> userLatestComments) {
		this.userLatestComments = userLatestComments;
	}
	public List<Tag> getUserHotTags() {
		return userHotTags;
	}
	public void setUserHotTags(List<Tag> userHotTags) {
		this.userHotTags = userHotTags;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getBlogComments() {
		return blogComments;
	}
	public void setBlogComments(Integer blogComments) {
		this.blogComments = blogComments;
	}
	
}
