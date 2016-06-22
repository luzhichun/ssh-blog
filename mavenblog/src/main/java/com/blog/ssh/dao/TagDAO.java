package com.blog.ssh.dao;

import java.util.ArrayList;
import java.util.List;

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

import com.blog.ssh.pojo.Tag;
@Repository
@Transactional
public class TagDAO extends BaseDAO<Tag>{
	private static final Logger log = LoggerFactory.getLogger(TagDAO.class);
	public static final String VALUE = "value";
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 根据文章id获取tags
	 * @param id
	 * @return
     */
	public List<Tag> getTagsByArticleId(Integer id){
		String sql = "SELECT\n" +
				"\tt.id,\n" +
				"\tt.\n" +
				"value\n" +
				"\n" +
				"FROM\n" +
				"\tarticle_tag AS atag,\n" +
				"\ttag AS t\n" +
				"WHERE\n" +
				"\tatag.article_id = 40\n" +
				"AND t.id = atag.tag_id;";
		Session s = getCurrentSession();
		List<Object []> objects = s.createSQLQuery(sql).list();
		List<Tag> tList = new ArrayList<Tag>();
		for(Object [] o:objects){
			Tag t = new Tag();
			t.setId((Integer) o[0]);
			t.setValue((String) o[1]);
			tList.add(t);
		}
		return tList;
	}
	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}
	public List findHotTags(int size){
		log.debug("finding all Tag instances");
		try {
			String queryString = "from com.blog.ssh.pojo.Tag";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(0);
			queryObject.setMaxResults(size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public Tag merge(Tag detachedInstance) {
		log.debug("merging Tag instance");
		try {
			Tag result = (Tag) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
}