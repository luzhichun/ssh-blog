package com.blog.ssh.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ssh.pojo.Bloginfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Bloginfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see Bloginfo
 * @author MyEclipse Persistence Tools
 */
@Repository
@Transactional
public class BloginfoDAO extends BaseDAO<Bloginfo>{
	private static final Logger log = LoggerFactory
			.getLogger(BloginfoDAO.class);
	// property constants
	public static final String INTRO = "intro";
	public static final String VISTITS = "vistits";
	public static final String USER_ID = "userId";
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

	public void save(Bloginfo transientInstance) {
		log.debug("saving Bloginfo instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Bloginfo persistentInstance) {
		log.debug("deleting Bloginfo instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bloginfo findById(java.lang.Integer id) {
		log.debug("getting Bloginfo instance with id: " + id);
		try {
			Bloginfo instance = (Bloginfo) getCurrentSession().get(
					"com.blog.ssh.pojo.Bloginfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public void update(Bloginfo instance){
		getCurrentSession().update(instance);
	}
	public List findByExample(Bloginfo instance) {
		log.debug("finding Bloginfo instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.blog.ssh.pojo.Bloginfo")
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
		log.debug("finding Bloginfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Bloginfo as where."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIntro(Object intro) {
		return findByProperty(INTRO, intro);
	}

	public List findByVistits(Object vistits) {
		return findByProperty(VISTITS, vistits);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all Bloginfo instances");
		try {
			String queryString = "from Bloginfo";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Bloginfo merge(Bloginfo detachedInstance) {
		log.debug("merging Bloginfo instance");
		try {
			Bloginfo result = (Bloginfo) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Bloginfo instance) {
		log.debug("attaching dirty Bloginfo instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bloginfo instance) {
		log.debug("attaching clean Bloginfo instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public static BloginfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BloginfoDAO) ctx.getBean("BloginfoDAO");
	}
	public int findMaxId(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select max(id) from Bloginfo as b");
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
}