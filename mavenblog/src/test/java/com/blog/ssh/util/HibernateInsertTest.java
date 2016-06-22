package com.blog.ssh.util;

import com.blog.ssh.dao.ArticleDAO;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.Comment;
import com.blog.ssh.service.ArticleService;
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

import java.util.List;

/**
 * Created by wy on 2016/6/22 0022.
 */
@RunWith(MySpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class HibernateInsertTest {
    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private ArticleService articleService;
    private List<Article> articleList;
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
    /**
     * 测试插入一对多插入多端
     * 插入Comment(评论)
     */
    public void testManyToOneInsertMany(){
        Comment comment = new Comment();
        comment.setContent("test");
        Article a = new Article();
        a.setId(40);
        comment.setArticle(a);
        System.out.println(session.save(comment));
    }
}
