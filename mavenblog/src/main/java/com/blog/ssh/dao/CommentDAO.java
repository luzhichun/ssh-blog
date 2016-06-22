package com.blog.ssh.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.Comment;
@Repository
@Transactional
public class CommentDAO extends BaseDAO<Comment>{
	private final Logger log = LoggerFactory.getLogger(CommentDAO.class);
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void update(Comment c){
		getCurrentSession().update(c);
	}
	public Comment findById(java.lang.Integer id) {
		log.debug("getting Comment instance with id: " + id);
		try {
			Comment instance = (Comment) getCurrentSession().get(
					"com.blog.ssh.pojo.Comment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 获取该用户博客文章的所有评论
	 * @param user_id
	 * @return
	 */
	public List findAll(Integer user_id) {
		log.debug("finding all Comment instances");
		try {
			String queryString = "from com.blog.ssh.pojo.Comment as c where c.article.user.id=" + user_id + " order by c.id desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll() {
		log.debug("finding all Comment instances");
		try {
			String queryString = "from com.blog.ssh.pojo.Comment as c order by c.id desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 插入评论
	 * @param c 评论
	 * @param article_id
	 */
	@SuppressWarnings("unchecked")
	public void insertComment(Comment c,int article_id){
		Session session = getCurrentSession();
		Article article = (Article)session.get(Article.class, article_id);
		c.setArticle(article);
		try{
			session.save(c);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @return 所有评论
	 */
	public List<Comment> getAllComment(){
		Session session = getCurrentSession();
		Query query = session.createQuery("from com.blog.ssh.pojo.Comment as c");
		List<Comment> cl = query.list();
		return cl;
	}
	/**
	 * @return 所有未审核评论列表
	 */
	public List<Comment> getUnauditing(){
		Session session = getCurrentSession();
		Query query = session.createQuery("from com.blog.ssh.pojo.Comment as c where c.auditingFlag=0");
		List<Comment> cl = query.list();
		return cl;
	}
	public List<Comment> getUnauditing(Integer user_id){
		Session session = getCurrentSession();
		Query query = session.createQuery("from com.blog.ssh.pojo.Comment as c where c.auditingFlag=0 and c.article.user.id=" + user_id);
		List<Comment> cl = query.list();
		return cl;
	}
	public Comment getComment(Integer id){
		Session session = getCurrentSession();
		Comment comment;
		comment = (Comment) session.get(Comment.class, id);
		
		return comment;
	}
	/**
	 * 通过article_id查询所有评论
	 * @param article_id
	 * @return 该文章所有(已通过审核)第一级Comment列表
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getParentComments(int article_id){
		Session session = getCurrentSession();
		Query query = session.createQuery("from com.blog.ssh.pojo.Comment as c where c.throughFlag=1 and parentId=0 and article_id=" + article_id);
		List<Comment> cl = query.list();
		return cl;
	}
	/**
	 * 通过parent_id查询所有子评论
	 * @param parent_id
	 * @return parent_id对应子评论列表
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getChildrenComments(int parent_id){
		Session session = getCurrentSession();
		Query query = session.createQuery("from com.blog.ssh.pojo.Comment as c where c.throughFlag=1 and parentId=" + parent_id);
		List<Comment> cl = query.list();
		return cl;
	}
	/**
	 * 获取最新评论
	 * @return 最近5条评论List
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getLatestComments(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("from com.blog.ssh.pojo.Comment as c where c.throughFlag=1 order by c.id desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Comment> list = query.list();
		return list;
	}
	public List<Comment> getLatestComments(Integer user_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("from com.blog.ssh.pojo.Comment as c where c.article.user.id=" + user_id + " and c.throughFlag=1 order by c.id desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Comment> list = query.list();
		return list;
	}
	/**
	 * 通过Comment id查询user_id
	 * @param id
	 * @return user_id
	 */
	public int getUser_id(int id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select c.userId from com.blog.ssh.pojo.Comment as c where id=" + id);
		int userId = ((Number)query.uniqueResult()).intValue();
		return userId;
	}
	/**
	 * 删除article_id对应文章的所有评论
	 * @param article_id
	 */
	public void deleteArticleAllComment(int article_id){
		String hql = "DELETE FROM com.blog.ssh.pojo.Comment as c WHERE c.articleId =" + article_id;
		Session session = getCurrentSession();
		try{
			Query q = session.createQuery(hql);
			q.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 审核评论
	 * @param id 评论id
	 * @param flag 审核标志，1表示通过，0表示不通过
	 */
	public void auditing(int id ,int flag){
		String hql = "UPDATE com.blog.ssh.pojo.Comment c SET c.throughFlag=" + flag + " ,c.auditingFlag=1 WHERE ID="+id;
		Session session = getCurrentSession();
		try{
			Query q = session.createQuery(hql);
			q.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取所有评论数
	 * @return
	 */
	public int getCommentCount(){
		String hql = "select count(*) from com.blog.ssh.pojo.Comment";
		Session session = getCurrentSession();
		Query q = session.createQuery(hql);
		int count = ((Number)q.uniqueResult()).intValue();
		return count;
	}
	/**
	 * 根据id删除评论
	 * @param id
	 */
	public void deleteComment(int id){
		String hql = "delete from com.blog.ssh.pojo.Comment as c where c.id=" + id;
		Session session = getCurrentSession();
		try{
			Query q = session.createQuery(hql);
			q.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} 
	}
	/**
	 *顶评论
	 * @param id
	 */
	public void setCommentLight(int id){
		String hql = "update com.blog.ssh.pojo.Comment as c set c.light=c.light+1 where c.id=" + id;
		Session session = getCurrentSession();
		try{
			Query q = session.createQuery(hql);
			q.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} 
	}
	/**
	 * @param article_id
	 * @return article_id对应文章评论数
	 */
	public int getArticleCommentCount(int article_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select count(*) from com.blog.ssh.pojo.Comment as c where c.throughFlag=1 and c.article.id=" + article_id);
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
	public static void main(String args[]){
		
	}
}
