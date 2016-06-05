package com.blog.ssh.control.dao;

import com.blog.ssh.MySpringJUnit4ClassRunner;
import com.blog.ssh.control.service.BloginfoService;
import com.blog.ssh.model.pojo.Bloginfo;
import com.blog.ssh.model.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by wy on 2016/6/5 0005.
 */
@RunWith(MySpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserHbmSQLTest {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BloginfoDAO bloginfoDAO;
    @Autowired
    private BloginfoService bloginfoService;

    /**
     * 测试一对一插入
     */
    @Test
    public void testMerge(){
        Bloginfo bi = new Bloginfo();
        bi.setVisits(10);
        bloginfoDAO.save(bi);
        User u = new User();
        u.setUsername("test");
        u.setBloginfo(bi);
        userDAO.save(u);
    }
}
