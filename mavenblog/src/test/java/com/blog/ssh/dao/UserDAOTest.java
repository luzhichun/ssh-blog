package com.blog.ssh.dao;

import com.blog.ssh.util.MySpringJUnit4ClassRunner;
import com.blog.ssh.service.BloginfoService;
import com.blog.ssh.pojo.BlogInfo;
import com.blog.ssh.pojo.User;
import com.blog.ssh.vo.UserVO;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by wy on 2016/6/5 0005.
 */
@RunWith(MySpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BlogInfoDAO bloginfoDAO;
    @Autowired
    private BloginfoService bloginfoService;
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
    /**
     * 测试一对一插入
     */
    @Test
    public void testMerge(){
        BlogInfo bi = new BlogInfo();
        bi.setVisits(10);
        bloginfoDAO.save(bi);
        User u = new User();
        u.setUsername("test");
        u.setBloginfo(bi);
        userDAO.save(u);
    }
    @Test
    public void testFindById(){
        User u = (User) userDAO.findById(35);
//        System.out.println(u.getBloginfo().getIntro());
        UserVO uv = new UserVO();
        System.out.println(u.toString());
        System.out.println(uv.toString());
        BeanUtils.copyProperties(u, uv);
        System.out.println(u.toString());
        System.out.println(uv.getArticles().size());
    }
    @Test
    public void testGetMsgCounts(){
        int i = userDAO.getArticleCounts(35);
        int j  = userDAO.getBlogComments(35);
        System.out.println(i);
        System.out.println(j);
    }
}
