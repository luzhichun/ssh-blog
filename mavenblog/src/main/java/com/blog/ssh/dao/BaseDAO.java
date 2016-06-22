package com.blog.ssh.dao;

import com.blog.ssh.util.StaticConstants;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

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

    /**
     * 插入数据库
     * @param object
     */
    public Integer save(Object object){
        Integer id = null;
        try {
            Session s = getCurrentSession();
            id = (Integer) s.save(object);
            s.flush();
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
        return id;
    }

    /**
     * 更新
     * @param object
     */
    public void update(T object){
        getCurrentSession().update(object);
    }
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public T findById(Integer id){
        T object = (T) getCurrentSession().get(this.entity, id);
        return object;
    }

    /**
     * 删除记录
     * @param object
     */
    public void delete(Object object){
        getCurrentSession().delete(object);
    }

    /**
     * 根据id删除数据库记录
     * @param id
     */
    public void delete(Integer id){
        Object o = findById(id);
        getCurrentSession().delete(o);
    }

    /**
     * 根据字段获取相关记录
     * @param propertyName
     * @param value
     * @return
     */
    public List<T> findByProperty(String propertyName, Object value) {
        try {
            String queryString = "from " + StaticConstants.POJO_PACKAGE_NAME + "." + entity.getSimpleName() +" where "
                    + propertyName + "= ?";
            Query queryObject = getCurrentSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }
    public List<T> findAll(){
        try {
            String queryString = "from " + StaticConstants.POJO_PACKAGE_NAME + "." + entity.getSimpleName();
            Query queryObject = getCurrentSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}
