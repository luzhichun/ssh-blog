package com.blog.ssh.control.action.admin;

import java.util.List;
import com.blog.ssh.control.service.ArticleService;
import com.blog.ssh.control.service.ArticletypeService;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.Articletype;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ��̨������ʾ��������
 */
public class ShowArticles extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<Article> articles;
	private Articletype articletype;
	private List<Articletype> articletypes;
	private String sort;//�������
	private ArticletypeService articletypeService;
	private ArticleService articleService;
	public ShowArticles(){
		
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public Articletype getArticletype() {
		return articletype;
	}
	public void setArticletype(Articletype articletype) {
		this.articletype = articletype;
	}
	
	public List<Articletype> getArticletypes() {
		return articletypes;
	}
	public void setArticletypes(List<Articletype> articletypes) {
		this.articletypes = articletypes;
	}
	
	public ArticletypeService getArticletypeService() {
		return articletypeService;
	}
	public void setArticletypeService(ArticletypeService articletypeService) {
		this.articletypeService = articletypeService;
	}
	
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	public String execute(){
		if(!RightsManagement.adminIsLogin()){
			return "notlogin";
		}
		ActionContext cxt = ActionContext.getContext();
		if(sort != null && !sort.equals("all")){
			//���������
			this.articletype = articletypeService.getArticletype(sort);
			this.articletypes = articletypeService.getAllChildrenArticletype();
			cxt.put("articles", articletype.getArticles());
			System.out.println("����鿴ȫ������");
		}
		else{
			this.articles = articleService.getAllArticle();
			this.articletypes = articletypeService.getAllChildrenArticletype();
			cxt.put("articles", articles);
			System.out.println("�鿴ȫ������");
		}
		return "success";
	}
}
