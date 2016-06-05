package com.blog.ssh.action.user;

import com.blog.ssh.action.user.ArticleAction;
import com.blog.ssh.util.MySpringJUnit4ClassRunner;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsSpringTestCase;
import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

/**
 * Created by wy on 2016/6/5 0005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TestArticleAction extends StrutsSpringTestCase {
    @Test
    public void testExecute(){
        try {
            executeAction("/article/123456");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
