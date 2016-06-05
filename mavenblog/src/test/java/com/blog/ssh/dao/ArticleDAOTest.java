package com.blog.ssh.dao;

import com.blog.ssh.util.MySpringJUnit4ClassRunner;
import com.blog.ssh.service.ArticleService;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.ArticleContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

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
    @Test
    public void testGetLatestArticleTitle(){
        List<Article> articleList = articleDAO.getLatestArticleTitle();
        String s = articleList.get(0).getContent();
        assertEquals(null, s);
    }
//    @Test
//    public void testGetLatestArticleTitle(Integer user_id){
//        List<Article> articleList = articleDAO.getLatestArticleTitle(51);
//        String s = articleList.get(0).getContent();
//        assertEquals(null, s);
//    }
    @Test
    public void testGetAllArticle(){
        articleList = articleDAO.getAllArticle();
        System.out.println("文章数目:" + articleList.size());
        System.out.println(articleList.get(0).getContent());
    }
    @Test
    public void testGetAllArticle1(){
        articleList = articleDAO.getAllArticle1();
        System.out.println("文章数目:" + articleList.size());
        System.out.println(articleList.get(0).getArticleContent().getContent());
    }
    @Test
    public void testInsertArticle(){
        Article a = new Article();
        ArticleContent ac = new ArticleContent();
        ac.setContent("test");
        a.setArticleContent(ac);
        articleService.insertArticle(a, 1);
    }
}
