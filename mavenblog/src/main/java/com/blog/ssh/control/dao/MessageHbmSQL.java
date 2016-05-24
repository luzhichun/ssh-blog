package com.blog.ssh.control.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ssh.model.pojo.Message;
@Transactional
public class MessageHbmSQL {
	private final Logger log = LoggerFactory.getLogger(MessageHbmSQL.class);
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

	public void save(Message transientInstance) {
		log.debug("saving Message instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Message persistentInstance) {
		log.debug("deleting Message instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Message findById(java.lang.Integer id) {
		log.debug("getting Message instance with id: " + id);
		try {
			Message instance = (Message) getCurrentSession().get(
					"com.blog.ssh.model.pojo.Message", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Message instance) {
		log.debug("finding Message instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.blog.ssh.model.pojo.Message")
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
		log.debug("finding Message instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Message as model where model.pojo."
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
		log.debug("finding all Message instances");
		try {
			String queryString = "from Message";
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
		Query query = session.createQuery("from Message as m order by m.id desc");
		List<Message> list = query.list();
		
		return list;
	}
	/**
	 * 获取所有父留言
	 * @return 所有留言列表
	 */
	public List<Message> getAllParMessages(){
		Session session = getCurrentSession();
		String hql = "from Message as m where m.parMessage is null order by m.id desc";
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
		String hql = "UPDATE Message as m SET m.throughFlag=" + flag + " ,m.auditingFlag=1 WHERE m.id="+id;
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
		String hql = "from Message as m where throughFlag=1 and parentFid=" + parent_id;
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
		String hql = "from Message as m where auditingFlag=0";
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
		Query query = session.createQuery("from Message as m where m.throughFlag=1 order by m.id desc");
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
		String hql = "update Message as m set m.light=m.light+1 where m.id=" + id;
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
		Query query = session.createQuery("select count(*) from Message as m");
		int count = ((Number)query.uniqueResult()).intValue();
		
		System.out.println(count);
		return count;
	}
	public Message getMessage(Integer id){
		Session session = getCurrentSession();
		Message message;
		message = (Message) session.get(Message.class, id);
		return message;
	}
	public void main(String args []){
		System.out.println(getLatestMessage().get(1).getUser().getUsername());
	}
}
