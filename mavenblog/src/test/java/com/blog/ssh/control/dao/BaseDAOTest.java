package com.blog.ssh.control.dao;

import com.blog.ssh.MySpringJUnit4ClassRunner;
import com.blog.ssh.model.pojo.ArticleContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by wy on 2016/6/5 0005.
 */
@RunWith(MySpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class BaseDAOTest {
    @Autowired
    private BaseDAO baseDAO;
    @Autowired
    private ArticleContentDAO articleContentDAO;
    @Test
    public void testSave(){
        ArticleContent ac = new ArticleContent();
        ac.setContent("test1111");
        articleContentDAO.save(ac);
    }
}
