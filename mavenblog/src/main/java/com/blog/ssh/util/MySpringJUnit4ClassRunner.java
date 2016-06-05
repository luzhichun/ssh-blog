package com.blog.ssh.util;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

/**
 * Created by wy on 2016/6/3 0003.
 * Junit时打印日志
 */
public class MySpringJUnit4ClassRunner extends SpringJUnit4ClassRunner {
    static {
        try {
            Log4jConfigurer.initLogging("classpath:locallog4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }
    public MySpringJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }
}
