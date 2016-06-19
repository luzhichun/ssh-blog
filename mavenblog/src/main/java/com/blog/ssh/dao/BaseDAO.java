package com.blog.ssh.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wy on 2016/6/5 0005.
 */
@Repository
@Transactional
public class BaseDAO<T> {
    private Logger log = Logger.getLogger(getClass());
    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entity;
    public BaseDAO() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            this.entity = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            this.entity = null;
        }
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
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    public Object get(Integer id){
        Object object = getCurrentSession().get(this.entity, id);
        return object;
    }
}
