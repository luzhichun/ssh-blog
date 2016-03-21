package com.blog.ssh.control.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.User;
@Transactional
public class UserHbmSQL {
	private final Logger log = LoggerFactory.getLogger(UserHbmSQL.class);
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
		log.debug("saving User instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void update(User u){
		getCurrentSession().update(u);
	}
	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getCurrentSession().load(
					"com.blog.ssh.model.pojo.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.blog.ssh.model.pojo.User")
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
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
		Query query = session.createQuery("select max(id) from User as u");
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
	public static void main(String args[]){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		User u = (User) s.get(User.class, 35);
		System.out.println(u.getArticles().size());
	}
	public int getBlogComments(Integer user_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select count(*) from Article as a right join a.comments as c where c.auditingFlag=1 and a.user.id=" + user_id);
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}

	public int getmsgCounts(Integer user_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select count(*) from Comment as c where c.auditingFlag=0 and c.article.user.id=" + user_id);
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
	public int getArticleCounts(Integer user_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select count(*) from Article as a where a.user.id=" + user_id);
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
}
