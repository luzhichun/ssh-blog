package com.blog.ssh.service;

import java.util.List;

import com.blog.ssh.dao.CommentDAO;
import com.blog.ssh.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	@Autowired
	private CommentDAO commentDAO;
	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	/**
	 * 插入评论
	 * @param c 评论
	 * @param article_id
	 */
	@SuppressWarnings("unchecked")
	public void insertComment(Comment c,int article_id){
		//提醒用户审核评论
		commentDAO.insertComment(c, article_id);
		
	}
	/**
	 * @return 所有评论
	 */
	public List<Comment> getAllComment(){
		return commentDAO.findAll();
	}
	/**
	 * @return 所有未审核评论列表
	 */
	public List<Comment> getUnauditing(){
		return commentDAO.getUnauditing();
	}
	public Comment getComment(Integer id){
		return commentDAO.getComment(id);
	}
	/**
	 * 通过article_id查询所有评论
	 * @param article_id
	 * @return 该文章所有(已通过审核)第一级Comment列表
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getParentComments(int article_id){
		return commentDAO.getParentComments(article_id);
	}
	/**
	 * 通过parent_id查询所有子评论
	 * @param parent_id
	 * @return parent_id对应子评论列表
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getChildrenComments(int parent_id){
		return commentDAO.getChildrenComments(parent_id);
	}
	/**
	 * 获取最新评论
	 * @return 最近5条评论List
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getLatestComments(){
		return commentDAO.getLatestComments();
	}
	/**
	 * 通过Comment id查询user_id
	 * @param id
	 * @return user_id
	 */
	public int getUser_id(int id){
		return commentDAO.getUser_id(id);
	}
	/**
	 * 删除article_id对应文章的所有评论
	 * @param article_id
	 */
	public void deleteArticleAllComment(int article_id){
		commentDAO.deleteArticleAllComment(article_id);
	}
	/**
	 * 审核评论
	 * @param id 评论id
	 * @param flag 审核标志，1表示通过，0表示不通过
	 */
	public void auditing(int id ,int flag){
		Comment c = commentDAO.findById(id);
		c.setAuditingFlag(1);//表示已审核
		c.setThroughFlag(flag);
		commentDAO.update(c);
	}
	/**
	 * 获取所有评论数
	 * @return
	 */
	public int getCommentCount(){
		return commentDAO.getCommentCount();
	}
	/**
	 * 根据id删除评论
	 * @param id
	 */
	public void deleteComment(int id){
		commentDAO.delete(commentDAO.findById(id));
	}
	/**
	 *顶评论
	 * @param id
	 */
	public void setCommentLight(int id){
		commentDAO.setCommentLight(id);
	}
	/**
	 * @param article_id
	 * @return article_id对应文章评论数
	 */
	public int getArticleCommentCount(int article_id){
		return commentDAO.getArticleCommentCount(article_id);
	}
	/**
	 * 获取该用户博客的所有评论
	 * @param user_id
	 * @return
	 */
	public List<Comment> getCommentsByUser(Integer user_id){
		return commentDAO.findAll(user_id);
	}
	/**
	 * 获取该用户博客所有待审核的评论
	 * @return
	 */
	public List<Comment> getUnauditing(Integer user_id){
		return commentDAO.getUnauditing(user_id);
	}
	public List<Comment> getLatestComment(Integer user_id){
		return commentDAO.getLatestComments(user_id);
	}
	
}
