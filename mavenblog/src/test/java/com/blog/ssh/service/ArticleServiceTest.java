package com.blog.ssh.service;

import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.ArticleContent;
import com.blog.ssh.util.MySpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by wy on 2016/6/22 0022.
 */
@RunWith(MySpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    @Test
    public void insertArticle(){
        Article a = new Article();
        ArticleContent ac = new ArticleContent();
        ac.setContent("test");
        a.setArticleContent(ac);
        articleService.insertArticle(a, 40);
    }
}
