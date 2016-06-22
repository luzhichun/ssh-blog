package com.blog.ssh.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.Articletype;

@Repository
@Transactional
public class ArticleDAO extends BaseDAO<Article>{
	private final Log log = LogFactory.getLog(getClass());
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void update(Article persistentInstance){
		getCurrentSession().update(persistentInstance);
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
		String sql = "select new com.blog.ssh.pojo.Article(id,title) from com.blog.ssh.pojo.Article as u order by u.visits desc";
		Query query = session.createQuery(sql);
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
		Query query = session.createQuery("select new com.blog.ssh.pojo.Article(id,title) from com.blog.ssh.pojo.Article as a where a.user.id=" + user_id + " order by a.visits desc");
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
		Query query = session.createQuery("select new com.blog.ssh.pojo.Article(id,title) from com.blog.ssh.pojo.Article as u order by u.id desc");
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
		Query query = session.createQuery("select new com.blog.ssh.pojo.Article(id,title) from com.blog.ssh.pojo.Article as a where a.user.id=" + user_id + " order by a.id desc");
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
		Query query = session.createQuery("select new com.blog.ssh.pojo.Article(id,title) from com.blog.ssh.pojo.Article as u");
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
		Query query = session.createQuery("select new com.blog.ssh.pojo.Article(id,title) from com.blog.ssh.pojo.Article as a where a.user.id=" + user_id);
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
		Query query = session.createQuery("from com.blog.ssh.pojo.Article as a where a.filename='" + fileName + "'");
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
		Query query = session.createQuery("select count(*) from com.blog.ssh.pojo.Article as a");
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
	public List<Article> serach(String value){
		Session session = getCurrentSession();//得到一个Session对象
		String hql="from com.blog.ssh.pojo.Article as a where a.title like ? or a.content like ?";
		Query query = session.createQuery(hql); 
		query.setString(0,"%"+value+"%");
		query.setString(1,"%"+value+"%");
		List list=query.list(); 
		return list;
	}
}