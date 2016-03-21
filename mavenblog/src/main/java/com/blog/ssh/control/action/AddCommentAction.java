package com.blog.ssh.control.action;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.blog.ssh.control.dao.Time;
import com.blog.ssh.control.service.ArticleService;
import com.blog.ssh.control.service.CommentService;
import com.blog.ssh.control.service.MD5;
import com.blog.ssh.control.service.SystemManage;
import com.blog.ssh.control.service.UserService;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.Comment;
import com.blog.ssh.model.pojo.User;
import com.blog.ssh.sendmail.Test_Email;
import com.blog.ssh.sensitivewordsfilter.SensitivewordFilter;
import com.opensymphony.xwork2.ActionContext;

public class AddCommentAction {
	private String content;
	private int parent_id;
	private int reply_id;
	private int article_id;
	private CommentService commentService;
	private UserService userService;
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
	 * ��������id�жϸò���������ʱ���Ƿ���Ҫ����֪ͨ����Ҫ֪ͨ��֪ͨ
	 * @param article_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void noticeUser(Integer article_id){
		Article article = articleService.getArticle(article_id);
		int flag = article.getUser().getBloginfo().getEmailNoticeflag();
		if(flag == 0){
			//��ͨ��ʾ�û�
			return ;
		}
		else{
			//��ʾ�û�
			Map application = ActionContext.getContext().getApplication();
			String key = MD5.getInstance().getMD5(System.currentTimeMillis() + "");
			System.out.println(key);
			application.put(key, article.getUser().getId());
			//����ǰʱ�����md5������Ϊkey�����û�id����ֵ����key�������䣬Ȼ��ͨ��keyֱ�ӵ�¼
			String href = "http://localhost:8080/blog";
			if(SystemManage.isAliServer()){
				href = "http://120.27.36.59:8080/blog";
			}
			String content = "<div class=\"wrapper\" style=\"margin: 20px auto 0; width: 500px; padding-top:16px; padding-bottom:10px;\">" +
						"<div class=\"header clearfix\">" +
						"<a class=\"logo\" href=\"" + href + "/index.jsp\" target=\"_blank\"><b>���벩��</b></a>" +
						"</div>" +
						"<br style=\"clear:both; height:0\">" +
						"<div class=\"content\" style=\"background: none repeat scroll 0 0 #FFFFFF; border: 1px solid #E9E9E9; margin: 2px 0 0; padding: 30px;\">" +
						"<p>" + article.getUser().getUsername() +  "�����ã�</p>" +
						"<p>�������� ���벩�� �ϵ���Ҫ�ʼ���������������¡�" + article.getTitle() + "�������µ����ۣ�������������ӽ������</p>" +
						"<p class=\"answer\" style=\"border-top: 1px solid #DDDDDD;margin: 15px 0 25px;padding: 15px;\">" +
						"�������Ӽ���: <a href=\"" + href + "/user/CommentManage.action?sort=&key=" + key + "\" target=\"_blank\">" + href + "/user/CommentManage.action?sort&key=" + key + "</a>" +
						"</p><p>" +
						"</p><p class=\"footer\" style=\"border-top: 1px solid #DDDDDD; padding-top:6px; margin-top:25px; color:#838383;\">����ظ����ʼ���������δ�ܼ�أ�������õ��κλظ���Ҫ��ð��������¼��վ��<br><a href=\"" + href + "/login.jsp" + "\" target=\"_blank\">���벩��</a></p>" +
						"</div>" +
						"</div>";
			try {
				Test_Email.send_email(article.getUser().getEmail(),"�����벩�͡������������������",content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
			//ֱ������
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
			//�ظ�����
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
