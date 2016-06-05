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

import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.Articletype;
@Repository
@Transactional
public class ArticletypeDAO {
	private final Logger log = LoggerFactory.getLogger(getClass());
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

	public void save(Articletype transientInstance) {
		log.debug("saving Articletype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Articletype persistentInstance) {
		log.debug("deleting Articletype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public void update(Articletype at){
		getCurrentSession().update(at);
	}
	public Articletype findById(java.lang.Integer id) {
		log.debug("getting Articletype instance with id: " + id);
		try {
			Articletype instance = (Articletype) getCurrentSession().get(
					"com.blog.ssh.pojo.Articletype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Articletype instance) {
		log.debug("finding Articletype instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.blog.ssh.pojo.Articletype")
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
		log.debug("finding Articletype instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Articletype as atype where atype."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	/*
	 * 根据栏目链接名获取该栏目下所有文章
	 */
	public List findArticleByArticletype(String linkname){
		Session session = getCurrentSession();
		String hql = "from Article as a where a.articletype.linkname='" + linkname +  "' or a.articletype.parArticletype.linkname='" + linkname +  "'";
		Query q = session.createQuery(hql);
		List<Article> ats = q.list();
		return ats;
	}
	public List findAll() {
		log.debug("finding all Articletype instances");
		try {
			String queryString = "from Articletype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * 获取所有子分类
	 * @return
	 */
	public List<Articletype> getAllChildrenArticletype(){
		Session session = getCurrentSession();
		String hql = "from Articletype as at where at.parArticletype is not null";
		Query q = session.createQuery(hql);
		List<Articletype> ats = q.list();
		return ats;
	}
	/**
	 * 根据pid获取所有子分类
	 * @param pid 父id
	 * @return
	 */
	public List<Articletype> getChildrenArticletype(int pid){
		Session session = getCurrentSession();
		String hql = "from Articletype as at where at.pid =" + pid;
		Query q = session.createQuery(hql);
		List<Articletype> ats = q.list();
		//System.out.println(ats);
		
		return ats;
	}
	/**
	 * 获取所有父分类
	 * @return
	 */
	public List<Articletype> getAllParentArticletype(){
		Session session = getCurrentSession();
		String hql = "from Articletype as at where at.parArticletype is null";
		Query q = session.createQuery(hql);
		List<Articletype> ats = q.list();
		//System.out.println(ats);
		
		return ats;
	}
	/**
	 * 通过id查询Articletype
	 * @param id
	 * @return
	 */
	public Articletype getArticletype(int id){
		Session session = getCurrentSession();
		Articletype at = (Articletype)session.load(Articletype.class, id);
		
		//System.out.println(at.getArticles().size());
		return at;
	}
	public static void main(String args []){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		String hql = "from Article as a where a.articletype.linkname=? or a.articletype.parArticletype.linkname=?";
		Query q = s.createQuery(hql);
		q.setParameter(0, "else");
		q.setParameter(1, "else");
		System.out.println(q.list().size());
	}
}
