package com.blog.ssh.dao;

import com.blog.ssh.pojo.Comment;
import com.blog.ssh.util.MySpringJUnit4ClassRunner;
import com.blog.ssh.pojo.ArticleContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * Created by wy on 2016/6/5 0005.
 */
@RunWith(MySpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class BaseDAOTest extends HibernateSessionManage{
    @Autowired
    private BaseDAO baseDAO;
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private ArticleContentDAO articleContentDAO;
    @Test
    public void testSave(){
        ArticleContent ac = new ArticleContent();
        ac.setContent("test1111");
        articleContentDAO.save(ac);
    }
    @Test
    public void testGet(){
        Comment c = (Comment) commentDAO.findById(24);
        System.out.println(c.toString());

    }
    /**
     * 测试delete方法删除评论
     */
    @Test
    public void testDelete(){
        commentDAO.delete(27);
    }
    @Test
    public void testGetByProperty(){
        List<Comment> list = commentDAO.findByProperty("id", 24);
        System.out.println(list.get(0).toString());
    }
}
