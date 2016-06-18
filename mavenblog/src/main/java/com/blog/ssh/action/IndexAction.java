package com.blog.ssh.action;
import java.util.List;

import com.blog.ssh.pojo.Article;
import com.blog.ssh.service.ArticleService;
import com.blog.ssh.service.HeaderSiderService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class IndexAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<Article> articles;//首页文章介绍，包含相关联的属性
	@Autowired
	private ArticleService articleService;
	@Autowired
	private HeaderSiderService headerSiderService;
	public IndexAction() {
	
	}
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	/**
	 * 访问index.action执行的方法
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