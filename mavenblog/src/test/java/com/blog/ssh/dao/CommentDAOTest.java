package com.blog.ssh.dao;

import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.Comment;
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
public class CommentDAOTest {
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private ArticleDAO articleDAO;
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
        Comment m = commentDAO.findById(17);
        System.out.println(m.getChiComments().size());
        System.out.println(m.getContent());
    }
    @Test
    public void testMerage(){
        Comment c = new Comment();
        c.setContent("test");
        Article a = articleDAO.findById(40);
        a.getComments().add(c);
        session.update(a);
    }
    @Test
    public void testSave(){
        Comment comment = new Comment();
        comment.setContent("test");
//        commentDAO.insertComment(comment, 40);
        session.save(comment);
    }
}
