package com.blog.ssh.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ssh.pojo.BlogInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Bloginfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see BlogInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
@Transactional
public class BlogInfoDAO extends BaseDAO<BlogInfo>{
	private static final Logger log = LoggerFactory
			.getLogger(BlogInfoDAO.class);
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public BlogInfo merge(BlogInfo detachedInstance) {
		log.debug("merging Bloginfo instance");
		try {
			BlogInfo result = (BlogInfo) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	public int findMaxId(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select max(id) from BlogInfo as b");
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
}