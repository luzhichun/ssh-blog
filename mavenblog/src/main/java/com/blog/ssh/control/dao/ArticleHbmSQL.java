package com.blog.ssh.control.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.springframework.transaction.annotation.Transactional;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.Articletype;


@Transactional
public class ArticleHbmSQL{
	//private final Logger log = LoggerFactory.getLogger(ArticleHbmSQL.class);
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	/**
	 * �ù��췽��Ϊ�˷���ֱ����main�����в���
	 */
	public ArticleHbmSQL(){
		this.sessionFactory = new Configuration().configure().buildSessionFactory();
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
					"com.blog.ssh.model.pojo.Article", id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Article instance) {
		//log.debug("finding Article instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.blog.ssh.model.pojo.Article")
					.add(Example.create(instance)).list();
			//log.debug("find by example successful, result size: "+ results.size());
			return results;
		} catch (RuntimeException re) {
			//log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		//log.debug("finding Article instance with property: " + propertyName+ ", value: " + value);
		try {
			String queryString = "from Article as model where model.pojo."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			//log.error("find by property name failed", re);
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
	 * ��������
	 * @param c
	 * @param article_id
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
	 * ��ȡ��������
	 * @return ���������б�
	 */
	public List<Article> getAllArticle(){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("from Article as a order by a.id desc");
		@SuppressWarnings("unchecked")
		List<Article> list = query.list();
		
		return list;
	}
	/**
	 * ��ȡ��������
	 * @return 5ƪ���������������б�
	 */
	public List<Article> getHotArticleTitle(){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("from Article as u order by u.visits desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Article> list = query.list();
		return list;
	}
	/**
	 * ��ȡ��������
	 * @return 5ƪ���������������б�
	 */
	public List<Article> getHotArticleTitle(Integer user_id){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("from Article as a where a.user.id=" + user_id + " order by a.visits desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Article> list = query.list();
		return list;
	}
	/**
	 * ��ȡ��������
	 * @return 5ƪ���������б�
	 */
	public List<Article> getLatestArticleTitle(){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("from Article as u order by u.id desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Article> list = query.list();
		return list;
	}
	/**
	 * ��ȡ��������
	 * @return 5ƪ���������б�
	 */
	public List<Article> getLatestArticleTitle(Integer user_id){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("from Article as a where a.user.id=" + user_id + " order by a.id desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Article> list = query.list();
		return list;
	}
	/**
	 * �����ȡ����
	 * @return 5ƪ��������б�
	 */
	public List<Article> getRandomArticleTitle(){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("from Article as u");
		List<Article> list = query.list();
		
		Collections.shuffle(list);//��list˳�����
		List<Article> randomList = new ArrayList<Article>();
		for(int i = 0;i < (list.size() <= 5?list.size():5);i++){
			randomList.add(list.get(i));
		}
		return randomList;
	}
	/**
	 * �����û�id�����ȡ����
	 * @return 5ƪ��������б�
	 */
	public List<Article> getRandomArticleTitle(Integer user_id){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("from Article as a where a.user.id=" + user_id);
		List<Article> list = query.list();
		
		Collections.shuffle(list);//��list˳�����
		List<Article> randomList = new ArrayList<Article>();
		for(int i = 0;i < (list.size() <= 5?list.size():5);i++){
			randomList.add(list.get(i));
		}
		return randomList;
	}
	/**
	 * ͨ��id����Article
	 * @param id ����id
	 * @return Article
	 */
	public Article getArticle(Integer id){
		Article article;
		article = (Article) getCurrentSession().get(Article.class, id);
		return article;
	}
	/**
	 * ͨ���ļ�������Article
	 * @param ���¶�Ӧҳ����ļ���
	 * @return Article
	 */
	public Article getArticle(String fileName){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("from Article where fileName='" + fileName + "'");
		@SuppressWarnings("unchecked")
		List<Article> list = query.list();
		return list.get(0);
	}
	/**
	 * ��ȡ���ݿ�����������
	 * @return ��������
	 */
	public int getArticleCount(){
		Session session = getCurrentSession();//�õ�һ��Session����
		Query query = session.createQuery("select count(*) from Article as a");
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}
	public List<Article> serach(String value){
		Session session = getCurrentSession();//�õ�һ��Session����
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