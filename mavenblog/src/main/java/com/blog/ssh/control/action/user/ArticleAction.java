package com.blog.ssh.control.action.user;
import com.blog.ssh.control.service.ArticleService;
import com.blog.ssh.control.service.UserSiderService;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.User;
import com.blog.ssh.model.pojo.UserSider;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 显示文章页面
 * @author wy
 *
 */
public class ArticleAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private int id;
	private Article article;
	private ArticleService articleService;
	private UserSider userSider;
	private UserSiderService userSiderService;
	public ArticleAction(){
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	public UserSider getUserSider() {
		return userSider;
	}
	public void setUserSider(UserSider userSider) {
		this.userSider = userSider;
	}
	public UserSiderService getUserSiderService() {
		return userSiderService;
	}
	public void setUserSiderService(UserSiderService userSiderService) {
		this.userSiderService = userSiderService;
	}
	public String execute(){
		try{
			ActionInvocation ai = (ActionInvocation) ActionContext.getContext().get(ActionContext.ACTION_INVOCATION);
			final String action = ai.getProxy().getActionName();//文章id
			id = Integer.valueOf(action);
			this.userSider = userSiderService.getUserSider(articleService.getArticle(id).getUser().getId());
			articleService.setArticleVisits(id);
			this.article = articleService.getArticle(id);
			return "success";
		} catch(Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
			return "404";
		}
	}
}