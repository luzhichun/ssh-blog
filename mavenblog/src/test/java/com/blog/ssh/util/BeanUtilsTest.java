package com.blog.ssh.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * Created by wy on 2016/6/19 0019.
 */
public class BeanUtilsTest {
    @Test
    public void testCopyProperties(){
        POTest p = new POTest();
        p.setUsername("wangyang");
        p.setPsd("123");
        VOTest v = new VOTest();
        BeanUtils.copyProperties(p, v);
        Assert.assertEquals("wangyang", v.getUsername());
    }
}
