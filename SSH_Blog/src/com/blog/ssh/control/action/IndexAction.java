package com.blog.ssh.control.action;
import java.util.List;

import com.blog.ssh.control.service.ArticleService;
import com.blog.ssh.control.service.HeaderSiderService;
import com.blog.ssh.model.pojo.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Article> articles;//��ҳ���½��ܣ����������������
	private ArticleService articleService;
	private HeaderSiderService headerSiderService;
	public IndexAction() {
	
	}
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	public HeaderSiderService getHeaderSiderService() {
		return headerSiderService;
	}
	public void setHeaderSiderService(HeaderSiderService headerSiderService) {
		this.headerSiderService = headerSiderService;
	}
	/**
	 * ����index.actionִ�еķ���
	 * @return
	 */
	public String execute(){
		headerSiderService.SessionManage();
		this.articles = articleService.getAllArticle();
 		return "success";
	}
	public static void main(String args []){
		new IndexAction().execute();
	}
}