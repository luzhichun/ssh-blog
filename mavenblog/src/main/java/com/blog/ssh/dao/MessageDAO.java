package com.blog.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ssh.pojo.Message;
@Repository
@Transactional
public class  MessageDAO extends BaseDAO<Message>{
	private final Logger log = LoggerFactory.getLogger(MessageDAO.class);
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public  com.blog.ssh.pojo.Message findById(java.lang.Integer id) {
		log.debug("getting  com.blog.ssh.pojo.Message instance with id: " + id);
		try {
			Message instance = (Message) getCurrentSession().get(
					"com.blog.ssh.pojo.Message", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Message instance) {
		log.debug("finding  com.blog.ssh.pojo.Message instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.blog.ssh.pojo.Message")
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
		log.debug("finding  com.blog.ssh.pojo.Message instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from  com.blog.ssh.pojo.Message as where.pojo."
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
		log.debug("finding all  com.blog.ssh.pojo.Message instances");
		try {
			String queryString = "from com.blog.ssh.pojo.Message";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 后台管理时
	 * 获取所有文章
	 * @return 所有留言列表
	 */
	public List<Message> getAllMessage(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("from  com.blog.ssh.pojo.Message as m order by m.id desc");
		List<Message> list = query.list();
		
		return list;
	}
	/**
	 * 获取所有父留言
	 * @return 所有留言列表
	 */
	public List<Message> getAllParMessages(){
		Session session = getCurrentSession();
		String hql = "from  com.blog.ssh.pojo.Message as m where m.parMessage is null order by m.id desc";
		Query q = session.createQuery(hql);
		List<Message> msgs = q.list();
		//System.out.println(msgs.get(0).getUser().getUsername());
		return msgs;
	}
	/**
	 * 删除留言,同时删除留言的user信息
	 * @param id
	 */
	public void deleteMsg(int id){
		Session session = getCurrentSession();
		try{
			Message m = (Message)session.get(Message.class, id);
			//System.out.println(m.getUser().getEmail());
			session.delete(m);
			session.delete(m.getUser());
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 审核留言
	 * @param id 留言id
	 * @param flag 审核标志
	 */
	public void auditing(int id ,int flag){
		Session session = getCurrentSession();
		String hql = "UPDATE  com.blog.ssh.pojo.Message as m SET m.throughFlag=" + flag + " ,m.auditingFlag=1 WHERE m.id="+id;
		try{
			Query q = session.createQuery(hql);
			q.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} 
	}
	/**
	 * 根据parent_id获取已经通过的子留言留言列表
	 * @param parent_id
	 * @return
	 */
	public List<Message> getChildrenMsgs(int parent_id){
		Session session = getCurrentSession();
		String hql = "from  com.blog.ssh.pojo.Message as m where throughFlag=1 and m.parMessage.id=" + parent_id;
		Query q = session.createQuery(hql);
		List<Message> cMsgs = q.list();
		
		return cMsgs;
	}
	/**
	 * 获取所有未审核的留言
	 * @return
	 */
	public List<Message> getUnAuditing(){
		Session session = getCurrentSession();
		String hql = "from  com.blog.ssh.pojo.Message as m where auditingFlag=0";
		Query q = session.createQuery(hql);
		List<Message> cMsgs = q.list();
		
		return cMsgs;
	}
	/**
	 * 获取最新通过审核的评论
	 * @return 5条最新评论
	 */
	public List<Message> getLatestMessage(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("from  com.blog.ssh.pojo.Message as m where m.throughFlag=1 order by m.id desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Message> list = query.list();
		//System.out.println(list.get(0).getUser().getUsername());
		
		return list;
	}
	/**
	 * 顶留言
	 * @param id
	 */
	public void setMessageLight(int id){
		String hql = "update  com.blog.ssh.pojo.Message as m set m.light=m.light+1 where m.id=" + id;
		Session session = getCurrentSession();
		try{
			Query q = session.createQuery(hql);
			q.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} 
	}
	/**
	 * 获取数据库中留言数量
	 * @return 留言数量
	 */
	public int getMessageCount(){
		Session session = getCurrentSession();//得到一个Session对象
		Query query = session.createQuery("select count(*) from  com.blog.ssh.pojo.Message as m");
		int count = ((Number)query.uniqueResult()).intValue();
		
		System.out.println(count);
		return count;
	}
	public  com.blog.ssh.pojo.Message getMessage(Integer id){
		Session session = getCurrentSession();
		Message message;
		message = (Message) session.get(Message.class, id);
		return message;
	}
}
