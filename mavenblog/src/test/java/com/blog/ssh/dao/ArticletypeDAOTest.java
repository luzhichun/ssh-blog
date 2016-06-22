package com.blog.ssh.dao;

import com.blog.ssh.pojo.ArticleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by wy on 2016/6/12 0012.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ArticleTypeDAOTest {
    @Autowired
    ArticleTypeDAO articletypeDAO;
    @Test
    public void testFindByProperty(){
        List<ArticleType> articletypeList = articletypeDAO.findByProperty("value", "java");
        assertEquals(1, articletypeList.size());
    }
}
