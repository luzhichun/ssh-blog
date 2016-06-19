package com.blog.ssh.action;
import java.util.Map;

import com.blog.ssh.util.Time;
import com.blog.ssh.service.ArticleService;
import com.blog.ssh.service.CommentService;
import com.blog.ssh.util.MD5;
import com.blog.ssh.service.SystemManage;
import com.blog.ssh.service.UserService;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.Comment;
import com.blog.ssh.pojo.User;
import com.blog.ssh.util.sendmail.TestEmail;
import com.blog.ssh.util.sensitivewordsfilter.SensitivewordFilter;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AddCommentAction {
	private String content;
	private int parent_id;
	private int reply_id;
	private int article_id;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	public AddCommentAction(){
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
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
	
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	/**
	 * 根据文章id判断该博主有评论时，是否需要邮箱通知，需要通知则通知
	 * @param article_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void noticeUser(Integer article_id){
		Article article = articleService.getArticle(article_id);
		int flag = article.getUser().getBloginfo().getEmailNoticeflag();
		if(flag == 0){
			//不通提示用户
			return ;
		}
		else{
			//提示用户
			Map application = ActionContext.getContext().getApplication();
			String key = MD5.getInstance().getMD5(System.currentTimeMillis() + "");
			System.out.println(key);
			application.put(key, article.getUser().getId());
			//将当前时间进行md5加密作为key，将用户id当做值，将key发送邮箱，然后通过key直接登录
			String href = "http://localhost:8080/blog";
			if(SystemManage.isAliServer()){
				href = "http://120.27.36.59:8080/blog";
			}
			String content = "<div class=\"wrapper\" style=\"margin: 20px auto 0; width: 500px; padding-top:16px; padding-bottom:10px;\">" +
						"<div class=\"header clearfix\">" +
						"<a class=\"logo\" href=\"" + href + "/index.jsp\" target=\"_blank\"><b>梦想博客</b></a>" +
						"</div>" +
						"<br style=\"clear:both; height:0\">" +
						"<div class=\"content\" style=\"background: none repeat scroll 0 0 #FFFFFF; border: 1px solid #E9E9E9; margin: 2px 0 0; padding: 30px;\">" +
						"<p>" + article.getUser().getUsername() +  "，您好：</p>" +
						"<p>这是您在 梦想博客 上的重要邮件，功能是你的文章【" + article.getTitle() + "】有了新的评论，请点击下面的链接进行审核</p>" +
						"<p class=\"answer\" style=\"border-top: 1px solid #DDDDDD;margin: 15px 0 25px;padding: 15px;\">" +
						"请点击链接继续: <a href=\"" + href + "/user/CommentManage.action?sort=&key=" + key + "\" target=\"_blank\">" + href + "/user/CommentManage.action?sort&key=" + key + "</a>" +
						"</p><p>" +
						"</p><p class=\"footer\" style=\"border-top: 1px solid #DDDDDD; padding-top:6px; margin-top:25px; color:#838383;\">请勿回复本邮件。此邮箱未受监控，您不会得到任何回复。要获得帮助，请登录网站。<br><a href=\"" + href + "/login.jsp" + "\" target=\"_blank\">梦想博客</a></p>" +
						"</div>" +
						"</div>";
			try {
				TestEmail.send_email(article.getUser().getEmail(),"【梦想博客】你的文章有新评论了",content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public String execute(){
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if(user == null){
			return "notlogin";
		}
		if(parent_id == 0 && reply_id == 0){
			//直接评论
			Comment cmt = new Comment();
			cmt.setTime(Time.time());
			cmt.setAuditingFlag(0);
			cmt.setThroughFlag(0);
			cmt.setLight(0);
			cmt.setContent(SensitivewordFilter.filter(content));
			cmt.setUser(user);
			commentService.insertComment(cmt,article_id);
			noticeUser(article_id);
		}else{
			//回复评论
			Comment cmt = new Comment();
			cmt.setTime(Time.time());
			cmt.setAuditingFlag(0);
			cmt.setThroughFlag(0);
			cmt.setLight(0);
			cmt.setContent(SensitivewordFilter.filter(content));
			cmt.setUser(user);
			cmt.setParComment(commentService.getComment(parent_id));
			cmt.setReplyComment(commentService.getComment(reply_id));
			commentService.insertComment(cmt, article_id);
			noticeUser(article_id);
		}
		return "success";
	}
	public static void main(String args []){
		new AddCommentAction().execute();
	}
}
