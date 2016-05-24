package com.blog.ssh.control.service;
import com.blog.ssh.model.pojo.UserSider;

public class UserSiderService {
	private ArticleService articleService;
	private CommentService commentService;
	private UserService userService;
	private BloginfoService bloginfoService;
	private UserSider userSider;
	public UserSiderService() {
		this.userSider = new UserSider();
	}
	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public BloginfoService getBloginfoService() {
		return bloginfoService;
	}
	public void setBloginfoService(BloginfoService bloginfoService) {
		this.bloginfoService = bloginfoService;
	}
	public UserSider getUserSider(Integer user_id){
		userSider.setUserHotArticles(articleService.getHotArticleTitle(user_id));
		userSider.setUserRandomArticles(articleService.getRandomArticleTitle(user_id));
		userSider.setUserLatestArticles(articleService.getLatestArticleTitle(user_id));
		userSider.setUserLatestComments(commentService.getLatestComment(user_id));
		userSider.setUser(userService.getUser(user_id));
		userSider.setBlogComments(userService.getBlogComments(user_id));
		userService.setBlogVisits(user_id);//设置博客访问量
		return userSider;
	}
}
