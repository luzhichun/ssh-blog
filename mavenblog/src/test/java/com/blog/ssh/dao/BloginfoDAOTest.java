package com.blog.ssh.dao;

import com.blog.ssh.pojo.Bloginfo;
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
public class BloginfoDAOTest {
    @Autowired
    private BloginfoDAO bloginfoDAO;
    @Test
    public void testFindById(){
        Bloginfo bi = bloginfoDAO.findById(1);
        System.out.println(bi.getIntro());
    }
}
