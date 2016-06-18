package com.blog.ssh.dao;

import com.blog.ssh.pojo.Message;
import com.blog.ssh.util.MySpringJUnit4ClassRunner;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by wy on 2016/6/18 0018.
 */
@RunWith(MySpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class MessageDAOTest {
    @Autowired
    private MessageDAO messageDAO;
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    @Before
    public void openSession()  throws Exception {
        session = sessionFactory.openSession();
        session.setFlushMode(FlushMode.MANUAL);
        TransactionSynchronizationManager.bindResource(sessionFactory,new SessionHolder(session));
    }

    @After
    public void closeSession()  throws Exception {
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        SessionFactoryUtils.closeSession(session);
    }
    @Test
    public void testFindById(){
        Message m = messageDAO.findById(4);
        System.out.println(m.getChiMessages().size());
        System.out.println(m.getContent());
    }
}
