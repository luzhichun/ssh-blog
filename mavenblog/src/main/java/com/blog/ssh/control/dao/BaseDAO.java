package com.blog.ssh.control.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wy on 2016/6/5 0005.
 */
@Transactional
public class BaseDAO {
    private SessionFactory sessionFactory;

    public BaseDAO() {
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    public void save(Object object){
        try {
            Session s = getCurrentSession();
            s.save(object);
            s.flush();
            //log.debug("save successful");
        } catch (RuntimeException re) {
            //log.error("save failed", re);
            throw re;
        }
    }
}
