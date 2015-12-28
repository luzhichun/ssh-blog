package com.blog.ssh.control.service;

import java.util.Map;

import com.blog.ssh.control.dao.ArticleHbmSQL;
import com.blog.ssh.control.dao.ArticletypeHbmSQL;
import com.blog.ssh.control.dao.CommentHbmSQL;
import com.blog.ssh.control.dao.MessageHbmSQL;
import com.blog.ssh.control.dao.TagDAO;
import com.blog.ssh.model.pojo.HeaderAndSider;
import com.opensymphony.xwork2.ActionContext;

/**
 * ������վherader��sider��������
 * ���ò���������������Ϊsession
 * @author wy
 *
 */
public class HeaderSiderService {
	private ArticleHbmSQL articleDAO;
	private ArticletypeHbmSQL articletypeDAO;
	private CommentHbmSQL commentDAO;
	private MessageHbmSQL messageDAO;
	private TagDAO tagDAO;
	private HeaderAndSider headerAndSider;
	public HeaderSiderService(){
		headerAndSider = new HeaderAndSider();
	}
	public ArticleHbmSQL getArticleDAO() {
		return articleDAO;
	}
	public void setArticleDAO(ArticleHbmSQL articleDAO) {
		this.articleDAO = articleDAO;
	}
	public ArticletypeHbmSQL getArticletypeDAO() {
		return articletypeDAO;
	}
	public void setArticletypeDAO(ArticletypeHbmSQL articletypeDAO) {
		this.articletypeDAO = articletypeDAO;
	}
	public CommentHbmSQL getCommentDAO() {
		return commentDAO;
	}
	public void setCommentDAO(CommentHbmSQL commentDAO) {
		this.commentDAO = commentDAO;
	}
	public MessageHbmSQL getMessageDAO() {
		return messageDAO;
	}
	public void setMessageDAO(MessageHbmSQL messageDAO) {
		this.messageDAO = messageDAO;
	}
	public HeaderAndSider getHeaderAndSider() {
		return headerAndSider;
	}
	public void setHeaderAndSider(HeaderAndSider headerAndSider) {
		this.headerAndSider = headerAndSider;
	}
	
	public TagDAO getTagDAO() {
		return tagDAO;
	}
	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}
	/**
	 * ����վhearder��sider��������Ϊsession
	 * @return
	 */
	public boolean setApplication(){
		this.headerAndSider.setHotArticles(articleDAO.getHotArticleTitle());
		this.headerAndSider.setLatestArticles(articleDAO.getLatestArticleTitle());
		this.headerAndSider.setRandomArticles(articleDAO.getRandomArticleTitle());
		this.headerAndSider.setLatestComments(commentDAO.getLatestComments());
		this.headerAndSider.setLatestMsgs(messageDAO.getLatestMessage());
		this.headerAndSider.setParArticletypes(articletypeDAO.getAllParentArticletype());
		this.headerAndSider.setHotTags(tagDAO.findHotTags(20));
		Map application = (Map) ActionContext.getContext().getApplication();
		application.put("headerAndSider", headerAndSider);
		return true;
	}
	/**
	 * �ж��Ƿ����hs���ֵ�session��������ڣ�������session�������ڣ�����session
	 * @return
	 */
	public void SessionManage(){
		Map application = (Map) ActionContext.getContext().getApplication();
		if(application.get("headerAndSider") == null){
			setApplication();
		}
	}
}
