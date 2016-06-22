package com.blog.ssh.dao;

import com.blog.ssh.util.MySpringJUnit4ClassRunner;
import com.blog.ssh.service.ArticleService;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.ArticleContent;
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

import static org.junit.Assert.assertEquals;

/**
 * Created by wy on 2016/6/3 0003.
 */
@RunWith(MySpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ArticleDAOTest {
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
    public void testGetLatestArticleTitle(){
        List<Article> articleList = articleDAO.getLatestArticleTitle();
        String s = articleList.get(0).getContent();
        assertEquals(null, s);
    }
    @Test
    public void testGetAllArticle(){
        articleList = articleDAO.findAll();
        System.out.println("文章数目:" + articleList.size());
        System.out.println(articleList.get(0).getContent());
    }
    @Test
    public void testGetAllArticle1(){
        articleList = articleDAO.getAllArticle1();
        for(Article a:articleList){
            System.out.println(a.toString());
        }
    }
    @Test
    public void testInsertArticle(){
        Article a = new Article();
        ArticleContent ac = new ArticleContent();
        ac.setContent("test");
        a.setArticleContent(ac);
        articleService.insertArticle(a, 1);
    }
    @Test
    public void testGetHotArticleTitle(){
        List<Article> articleList = articleDAO.getHotArticleTitle();
        for(Article a:articleList){
            System.out.println(a.toString());
        }
    }
    @Test
    public void testFindById(){
        Article a = articleDAO.findById(40);
        System.out.println(a.toString());
    }
}
