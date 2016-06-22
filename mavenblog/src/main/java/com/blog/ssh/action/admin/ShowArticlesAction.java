package com.blog.ssh.action.admin;

import java.util.List;
import com.blog.ssh.service.ArticleService;
import com.blog.ssh.service.ArticleTypeService;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.ArticleType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 后台管理，显示所有文章
 */
@Controller
public class ShowArticlesAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<Article> articles;
	private ArticleType articletype;
	private List<ArticleType> articletypes;
	private String sort;//分类管理
	@Autowired
	private ArticleTypeService articletypeService;
	@Autowired
	private ArticleService articleService;
	public ShowArticlesAction(){
		
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
	
	public ArticleType getArticletype() {
		return articletype;
	}
	public void setArticletype(ArticleType articletype) {
		this.articletype = articletype;
	}
	
	public List<ArticleType> getArticletypes() {
		return articletypes;
	}
	public void setArticletypes(List<ArticleType> articletypes) {
		this.articletypes = articletypes;
	}
	
	public ArticleTypeService getArticletypeService() {
		return articletypeService;
	}
	public void setArticletypeService(ArticleTypeService articletypeService) {
		this.articletypeService = articletypeService;
	}
	
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	public String execute(){
		if(!RightsManagementAction.adminIsLogin()){
			return "notlogin";
		}
		ActionContext cxt = ActionContext.getContext();
		if(sort != null && !sort.equals("all")){
			//分类管理文
			this.articletype = articletypeService.getArticletype(sort);
			this.articletypes = articletypeService.getAllChildrenArticleType();
			cxt.put("articles", articletype.getArticles());
			System.out.println("分类查看全部文章");
		}
		else{
			this.articles = articleService.getAllArticle();
			this.articletypes = articletypeService.getAllChildrenArticleType();
			cxt.put("articles", articles);
			System.out.println("查看全部文章");
		}
		return "success";
	}
}
