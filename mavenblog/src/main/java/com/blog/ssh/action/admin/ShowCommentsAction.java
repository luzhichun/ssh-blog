package com.blog.ssh.action.admin;

import java.util.List;

import com.blog.ssh.service.CommentService;
import com.blog.ssh.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowCommentsAction {
	private List<Comment> comments;
	private String sort;//分类管理
	@Autowired
	private CommentService commentService;
	public ShowCommentsAction(){
		
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public CommentService getCommentService() {
		return commentService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	public String execute(){
		if(!RightsManagementAction.adminIsLogin()){
			return "notlogin";
		}
		if(sort == null  || sort.equals("all")){
			//显示所有评论
			this.comments = commentService.getAllComment();
		}
		else{
			//待审核的评论
			this.comments = commentService.getUnauditing();
		}
		return "success";
	}
	public static void main(String [] args){
		new ShowCommentsAction().execute();
	}
}
