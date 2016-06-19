package com.blog.ssh.action.user;

import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

/**
 * Created by wy on 2016/6/5 0005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TestUserAction extends StrutsSpringTestCase {
    @Before
    @Override
    public void setUp() {
        try{
            super.setUp();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 在执行每个test之后，都要执行tearDown
    @After
    public void tearDown() {}
    @Test
    public void testExecute(){
        try {
            String result = executeAction("/user/sublogin.action");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
