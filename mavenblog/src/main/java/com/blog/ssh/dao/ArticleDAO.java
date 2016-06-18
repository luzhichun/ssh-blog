package com.blog.ssh.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.Articletype;

@Repository
@Transactional
public class ArticleDAO {
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

	public void save(Article transientInstance) {
		//log.debug("saving Article instance");
		try {
			getCurrentSession().save(transientInstance);
			//log.debug("save successful");
		} catch (RuntimeException re) {
			//log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Article persistentInstance) {
		//log.debug("deleting Article instance");
		try {
			getCurrentSession().delete(persistentInstance);
			//log.debug("delete successful");
		} catch (RuntimeException re) {
			//log.error("delete failed", re);
			throw re;
		}
	}
	public void update(Article persistentInstance){
		getCurrentSession().update(persistentInstance);
	}
	public Article findById(java.lang.Integer id) {
		//log.debug("getting Article instance with id: " + id);
		try {
			Article instance = (Article) getCurrentSession().get(
					"com.blog.ssh.pojo.Article", id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Article instances");
		try {
			String queryString = "from Article";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			//log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 插入文章
	 * @param a
	 * @param articletype_id
	 */
	public void insertArticle(Article a,int articletype_id){
		Session session = getCurrentSession();
		Articletype at = (Articletype)session.get(Articletype.class, articletype_id);
		a.setArticletype(at);
		try{
			session.merge(a);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取所有文章列表，以及该文章用户和该文章类型(不包括content)
	 * @return 所有文章列表
	 */
	public List<Article> getAllArticle(){
		String hql = "select new Article(a.id, a.title," +
				"a.beagincontent, a.releasetime, a.filename," +
				"u.id, u.username, u.url," +
				"atype.id, atype.value, atype.linkname, " +
				"a.imagename, a.visits) from " +
				"Article as a,Articletype as atype,User as u " +
				"where a.articletype.id = atype.id and a.user.id = u.id order by a.id desc";
//		hql = "select new Article(id, c.id) from Article as a,Comment as c where a.id = c.article.id order by a.id desc";
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Article> list = query.list();
		return list;
	}
	public List<Article> getAllArticle1(){
		String hql = "from com.blog.ssh.pojo.Article ";
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery(hql);
		List<Article> articleList = query.list();
		return articleList;
	}
	/**
	 * 获取最热文章
	 * @return 5篇访问量最多的文章列表
	 */
	public List<Article> getHotArticleTitle(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select new Article(id,title) from Article as u order by u.visits desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Article> list = query.list();
		return list;
	}
	/**
	 * 获取最热文章
	 * @return 5篇访问量最多的文章列表
	 */
	public List<Article> getHotArticleTitle(Integer user_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select new Article(id,title) from Article as a where a.user.id=" + user_id + " order by a.visits desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Article> list = query.list();
		return list;
	}
	/**
	 * 获取最新文章
	 * @return 5篇最新文章列表
	 */
	public List<Article> getLatestArticleTitle(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select new Article(id,title) from Article as u order by u.id desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Article> list = query.list();
		return list;
	}
	/**
	 * 获取最新文章
	 * @return 5篇最新文章列表
	 */
	public List<Article> getLatestArticleTitle(Integer user_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select new Article(id,title) from Article as a where a.user.id=" + user_id + " order by a.id desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Article> list = query.list();
		return list;
	}
	/**
	 * 随机获取文章
	 * @return 5篇随机文章列表
	 * 此处还需要优化
	 */
	public List<Article> getRandomArticleTitle(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select new Article(id,title) from Article as u");
		List<Article> list = query.list();

		Collections.shuffle(list);//将list顺序打乱
		List<Article> randomList = new ArrayList<Article>();
		for(int i = 0;i < (list.size() <= 5?list.size():5);i++){
			randomList.add(list.get(i));
		}
		return randomList;
	}
	/**
	 * 根据用户id随机获取文章
	 * @return 5篇随机文章列表
	 * 此处还需要优化
	 */
	public List<Article> getRandomArticleTitle(Integer user_id){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select new Article(id,title) from Article as a where a.user.id=" + user_id);
		List<Article> list = query.list();
		
		Collections.shuffle(list);//将list顺序打乱
		List<Article> randomList = new ArrayList<Article>();
		for(int i = 0;i < (list.size() <= 5?list.size():5);i++){
			randomList.add(list.get(i));
		}
		return randomList;
	}
	/**
	 * 通过id查找Article
	 * @param id 文章id
	 * @return Article
	 */
	public Article getArticle(Integer id){
		Article article;
		article = (Article) getCurrentSession().get(Article.class, id);
		return article;
	}
	/**
	 * 通过文件名查找Article
	 * @param fileName 文章对应页面的文件名
	 * @return Article
	 */
	public Article getArticle(String fileName){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("from Article where fileName='" + fileName + "'");
		@SuppressWarnings("unchecked")
		List<Article> list = query.list();
		return list.get(0);
	}
	/**
	 * 获取数据库中文章数量
	 * @return 文章数量
	 */
	public int getArticleCount(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select count(*) from Article as a");
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
	public List<Article> serach(String value){
		Session session = getCurrentSession();//得到一个Session对象
		String hql="from Article as a where a.title like ? or a.content like ?";
		Query query = session.createQuery(hql); 
		query.setString(0,"%"+value+"%");
		query.setString(1,"%"+value+"%");
		List list=query.list(); 
		return list;
	}
	public static void main(String args[]){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		String hql="from Article as a where a.title like ? or a.content like ?";
		Query query = s.createQuery(hql);
		query.setString(0,"%"+"java"+"%");
		query.setString(1,"%"+"java"+"%");
		List list=query.list();
	}
}