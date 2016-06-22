package com.blog.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.ArticleType;
@Repository
@Transactional
public class ArticleTypeDAO extends BaseDAO<ArticleType>{
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * 根据栏目链接名获取该栏目下所有文章
	 */
	public List<Article> findArticleByArticleType(String linkname){
		Session session = getCurrentSession();
		String hql = "from com.blog.ssh.pojo.Article as a where a.ArticleType.linkname='" + linkname +  "' or a.ArticleType.parArticleType.linkname='" + linkname +  "'";
		Query q = session.createQuery(hql);
		List<Article> ats = q.list();
		return ats;
	}
	/**
	 * 获取所有子分类
	 * @return
	 */
	public List<ArticleType> getAllChildrenArticleType(){
		Session session = getCurrentSession();
		String hql = "from com.blog.ssh.pojo.ArticleType as at where at.parArticleType is not null";
		Query q = session.createQuery(hql);
		List<ArticleType> ats = q.list();
		return ats;
	}
	/**
	 * 根据pid获取所有子分类
	 * @param pid 父id
	 * @return
	 */
	public List<ArticleType> getChildrenArticleType(int pid){
		Session session = getCurrentSession();
		String hql = "from com.blog.ssh.pojo.ArticleType as at where at.parArticleType.id =" + pid;
		Query q = session.createQuery(hql);
		List<ArticleType> ats = q.list();
		return ats;
	}
	/**
	 * 获取所有父分类
	 * @return
	 */
	public List<ArticleType> getAllParentArticleType(){
		Session session = getCurrentSession();
		String hql = "from com.blog.ssh.pojo.ArticleType as at where at.parArticleType is null";
		Query q = session.createQuery(hql);
		List<ArticleType> ats = q.list();
		return ats;
	}
}
