package com.blog.ssh.dao;

import com.blog.ssh.pojo.BlogInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wy on 2016/6/18 0018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class BlogInfoDAOTest {
    @Autowired
    private BlogInfoDAO bloginfoDAO;
    @Test
    public void testFindById(){
        BlogInfo bi = bloginfoDAO.findById(1);
        System.out.println(bi.getIntro());
        BlogInfo bi1 = (BlogInfo) bloginfoDAO.findById(1);
        System.out.println(bi.toString());
    }
}
