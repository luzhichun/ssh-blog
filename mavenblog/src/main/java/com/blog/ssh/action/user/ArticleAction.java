package com.blog.ssh.action.user;
import com.blog.ssh.service.ArticleService;
import com.blog.ssh.service.UserSiderService;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.vo.UserSider;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 显示文章页面
 * @author wy
 */
@Controller
public class ArticleAction extends ActionSupport{
	private final Log log = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private int id;
	private Article article;
	@Autowired
	private ArticleService articleService;
	private UserSider userSider;
	@Autowired
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
			log.info("action名：" + action);
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