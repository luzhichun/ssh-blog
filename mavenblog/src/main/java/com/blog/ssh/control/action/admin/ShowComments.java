package com.blog.ssh.control.action.admin;

import java.util.List;

import com.blog.ssh.control.service.CommentService;
import com.blog.ssh.model.pojo.Comment;

public class ShowComments {
	private List<Comment> comments;
	private String sort;//分类管理
	private CommentService commentService;
	public ShowComments(){
		
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
		if(!RightsManagement.adminIsLogin()){
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
		new ShowComments().execute();
	}
}
