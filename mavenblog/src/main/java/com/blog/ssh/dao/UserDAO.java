package com.blog.ssh.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ssh.pojo.User;
@Repository
@Transactional
public class UserDAO extends BaseDAO<User>{
	@Autowired
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

	public void save(User transientInstance) {
		try {
			getCurrentSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void merge(User u){
		getCurrentSession().merge(u);
	}
	public void update(User u){
		getCurrentSession().update(u);
	}
	public void delete(User persistentInstance) {
		try {
			getCurrentSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		try {
			User instance = (User) getCurrentSession().get(
					"com.blog.ssh.pojo.User", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByExample(User instance) {
		try {
			List results = getCurrentSession()
					.createCriteria("com.blog.ssh.pojo.User")
					.add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from com.blog.ssh.pojo.User as u where u."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findAll() {
		try {
			String queryString = "from com.blog.ssh.pojo.User";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public User getUser(int id){
		Session session = getCurrentSession();
		User user = (User)session.get(User.class, id);
		return user;
	}
	public int findMaxId(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select max(id) from com.blog.ssh.pojo.User as u");
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
	public int getBlogComments(Integer user_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select count(*) from com.blog.ssh.pojo.Article as a right join a.comments as c where c.auditingFlag=1 and a.user.id=" + user_id);
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}

	public int getmsgCounts(Integer user_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select count(*) from com.blog.ssh.pojo.Comment as c where c.auditingFlag=0 and c.article.user.id=" + user_id);
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
	public int getArticleCounts(Integer user_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select count(*) from com.blog.ssh.pojo.Article as a where a.user.id=" + user_id);
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
}
