package com.blog.ssh.control.service;

import java.util.List;

import com.blog.ssh.control.dao.ArticleHbmSQL;
import com.blog.ssh.control.dao.CommentHbmSQL;
import com.blog.ssh.model.pojo.Comment;

public class CommentService {
	private CommentHbmSQL commentDAO;
	public CommentHbmSQL getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentHbmSQL commentDAO) {
		this.commentDAO = commentDAO;
	}
	/**
	 * ��������
	 * @param c ����
	 * @param article_id
	 */
	@SuppressWarnings("unchecked")
	public void insertComment(Comment c,int article_id){
		//�����û��������
		commentDAO.insertComment(c, article_id);
		
	}
	/**
	 * @return ��������
	 */
	public List<Comment> getAllComment(){
		return commentDAO.findAll();
	}
	/**
	 * @return ����δ��������б�
	 */
	public List<Comment> getUnauditing(){
		return commentDAO.getUnauditing();
	}
	public Comment getComment(Integer id){
		return commentDAO.getComment(id);
	}
	/**
	 * ͨ��article_id��ѯ��������
	 * @param article_id
	 * @return ����������(��ͨ�����)��һ��Comment�б�
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getParentComments(int article_id){
		return commentDAO.getParentComments(article_id);
	}
	/**
	 * ͨ��parent_id��ѯ����������
	 * @param parent_id
	 * @return parent_id��Ӧ�������б�
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getChildrenComments(int parent_id){
		return commentDAO.getChildrenComments(parent_id);
	}
	/**
	 * ��ȡ��������
	 * @return ���5������List
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getLatestComments(){
		return commentDAO.getLatestComments();
	}
	/**
	 * ͨ��Comment id��ѯuser_id
	 * @param id
	 * @return user_id
	 */
	public int getUser_id(int id){
		return commentDAO.getUser_id(id);
	}
	/**
	 * ɾ��article_id��Ӧ���µ���������
	 * @param article_id
	 */
	public void deleteArticleAllComment(int article_id){
		commentDAO.deleteArticleAllComment(article_id);
	}
	/**
	 * �������
	 * @param id ����id
	 * @param flag ��˱�־��1��ʾͨ����0��ʾ��ͨ��
	 */
	public void auditing(int id ,int flag){
		Comment c = commentDAO.findById(id);
		c.setAuditingFlag(1);//��ʾ�����
		c.setThroughFlag(flag);
		commentDAO.update(c);
	}
	/**
	 * ��ȡ����������
	 * @return
	 */
	public int getCommentCount(){
		return commentDAO.getCommentCount();
	}
	/**
	 * ����idɾ������
	 * @param id
	 */
	public void deleteComment(int id){
		commentDAO.delete(commentDAO.findById(id));
	}
	/**
	 *������
	 * @param id
	 */
	public void setCommentLight(int id){
		commentDAO.setCommentLight(id);
	}
	/**
	 * @param article_id
	 * @return article_id��Ӧ����������
	 */
	public int getArticleCommentCount(int article_id){
		return commentDAO.getArticleCommentCount(article_id);
	}
	/**
	 * ��ȡ���û����͵���������
	 * @param user_id
	 * @return
	 */
	public List<Comment> getCommentsByUser(Integer user_id){
		return commentDAO.findAll(user_id);
	}
	/**
	 * ��ȡ���û��������д���˵�����
	 * @return
	 */
	public List<Comment> getUnauditing(Integer user_id){
		return commentDAO.getUnauditing(user_id);
	}
	public List<Comment> getLatestComment(Integer user_id){
		return commentDAO.getLatestComments(user_id);
	}
	
}
