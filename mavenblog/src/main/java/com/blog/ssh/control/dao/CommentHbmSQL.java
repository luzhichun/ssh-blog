package com.blog.ssh.control.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.Comment;
@Transactional
public class CommentHbmSQL {
	private final Logger log = LoggerFactory.getLogger(CommentHbmSQL.class);
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Comment transientInstance) {
		log.debug("saving Comment instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Comment persistentInstance) {
		log.debug("deleting Comment instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public void update(Comment c){
		getCurrentSession().update(c);
	}
	public Comment findById(java.lang.Integer id) {
		log.debug("getting Comment instance with id: " + id);
		try {
			Comment instance = (Comment) getCurrentSession().get(
					"com.blog.ssh.model.pojo.Comment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Comment instance) {
		log.debug("finding Comment instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.blog.ssh.model.pojo.Comment")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Comment instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Comment as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	/**
	 * ��ȡ���û��������µ���������
	 * @param user_id
	 * @return
	 */
	public List findAll(Integer user_id) {
		log.debug("finding all Comment instances");
		try {
			String queryString = "from Comment as c where c.article.user.id=" + user_id + " order by c.id desc";
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
			String queryString = "from Comment as c order by c.id desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * ��������
	 * @param c ����
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
	 * @return ��������
	 */
	public List<Comment> getAllComment(){
		Session session = getCurrentSession();
		Query query = session.createQuery("from Comment as c");
		List<Comment> cl = query.list();
		return cl;
	}
	/**
	 * @return ����δ��������б�
	 */
	public List<Comment> getUnauditing(){
		Session session = getCurrentSession();
		Query query = session.createQuery("from Comment as c where c.auditingFlag=0");
		List<Comment> cl = query.list();
		return cl;
	}
	public List<Comment> getUnauditing(Integer user_id){
		Session session = getCurrentSession();
		Query query = session.createQuery("from Comment as c where c.auditingFlag=0 and c.article.user.id=" + user_id);
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
	 * ͨ��article_id��ѯ��������
	 * @param article_id
	 * @return ����������(��ͨ�����)��һ��Comment�б�
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getParentComments(int article_id){
		Session session = getCurrentSession();
		Query query = session.createQuery("from Comment as c where c.throughFlag=1 and parentId=0 and article_id=" + article_id);
		List<Comment> cl = query.list();
		return cl;
	}
	/**
	 * ͨ��parent_id��ѯ����������
	 * @param parent_id
	 * @return parent_id��Ӧ�������б�
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getChildrenComments(int parent_id){
		Session session = getCurrentSession();
		Query query = session.createQuery("from Comment as c where c.throughFlag=1 and parentId=" + parent_id);
		List<Comment> cl = query.list();
		return cl;
	}
	/**
	 * ��ȡ��������
	 * @return ���5������List
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getLatestComments(){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("from Comment as c where c.throughFlag=1 order by c.id desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Comment> list = query.list();
		return list;
	}
	public List<Comment> getLatestComments(Integer user_id){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("from Comment as c where c.article.user.id=" + user_id + " and c.throughFlag=1 order by c.id desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Comment> list = query.list();
		return list;
	}
	/**
	 * ͨ��Comment id��ѯuser_id
	 * @param id
	 * @return user_id
	 */
	public int getUser_id(int id){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("select c.userId from Comment as c where id=" + id);
		int userId = ((Number)query.uniqueResult()).intValue();
		return userId;
	}
	/**
	 * ɾ��article_id��Ӧ���µ���������
	 * @param article_id
	 */
	public void deleteArticleAllComment(int article_id){
		String hql = "DELETE FROM Comment as c WHERE c.articleId =" + article_id;
		Session session = getCurrentSession();
		try{
			Query q = session.createQuery(hql);
			q.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * �������
	 * @param id ����id
	 * @param flag ��˱�־��1��ʾͨ����0��ʾ��ͨ��
	 */
	public void auditing(int id ,int flag){
		String hql = "UPDATE Comment c SET c.throughFlag=" + flag + " ,c.auditingFlag=1 WHERE ID="+id;
		Session session = getCurrentSession();
		try{
			Query q = session.createQuery(hql);
			q.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ����������
	 * @return
	 */
	public int getCommentCount(){
		String hql = "select count(*) from Comment";
		Session session = getCurrentSession();
		Query q = session.createQuery(hql);
		int count = ((Number)q.uniqueResult()).intValue();
		return count;
	}
	/**
	 * ����idɾ������
	 * @param id
	 */
	public void deleteComment(int id){
		String hql = "delete from Comment as c where c.id=" + id;
		Session session = getCurrentSession();
		try{
			Query q = session.createQuery(hql);
			q.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} 
	}
	/**
	 *������
	 * @param id
	 */
	public void setCommentLight(int id){
		String hql = "update Comment as c set c.light=c.light+1 where c.id=" + id;
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
	 * @return article_id��Ӧ����������
	 */
	public int getArticleCommentCount(int article_id){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("select count(*) from Comment as c where c.throughFlag=1 and c.articleId=" + article_id);
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
	public static void main(String args[]){
		
	}
}