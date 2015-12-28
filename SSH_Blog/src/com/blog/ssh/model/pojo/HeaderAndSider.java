package com.blog.ssh.model.pojo;

import java.util.List;

import com.blog.ssh.control.dao.ArticleHbmSQL;
import com.blog.ssh.control.dao.ArticletypeHbmSQL;
import com.blog.ssh.control.dao.CommentHbmSQL;
import com.blog.ssh.control.dao.MessageHbmSQL;
/**
 * 网页header部分和sider部分所需要的数据
 */
public class HeaderAndSider {
	private List<Article> latestArticles;
	private List<Article> hotArticles;
	private List<Article> randomArticles;
	private List<Message> latestMsgs;
	private List<Comment> latestComments;
	private List<Articletype> parArticletypes;
	private List<Tag> hotTags;
	public HeaderAndSider(){
		
	}
	public List<Article> getLatestArticles() {
		return latestArticles;
	}
	public void setLatestArticles(List<Article> latestArticles) {
		this.latestArticles = latestArticles;
	}
	public List<Article> getHotArticles() {
		return hotArticles;
	}
	public void setHotArticles(List<Article> hotArticles) {
		this.hotArticles = hotArticles;
	}
	public List<Article> getRandomArticles() {
		return randomArticles;
	}
	public void setRandomArticles(List<Article> randomArticles) {
		this.randomArticles = randomArticles;
	}
	public List<Message> getLatestMsgs() {
		return latestMsgs;
	}
	public void setLatestMsgs(List<Message> latestMsgs) {
		this.latestMsgs = latestMsgs;
	}
	public List<Comment> getLatestComments() {
		return latestComments;
	}
	public void setLatestComments(List<Comment> latestComments) {
		this.latestComments = latestComments;
	}
	public List<Articletype> getParArticletypes() {
		return parArticletypes;
	}
	public void setParArticletypes(List<Articletype> parArticletypes) {
		this.parArticletypes = parArticletypes;
	}
	public List<Tag> getHotTags() {
		return hotTags;
	}
	public void setHotTags(List<Tag> hotTags) {
		this.hotTags = hotTags;
	}
	
}
