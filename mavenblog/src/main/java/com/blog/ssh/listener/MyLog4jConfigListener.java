package com.blog.ssh.listener;
/**
 * Created by wy on 2016/6/2 0002.
 * 本地开发环境和生产环境用不同日志配置
 */

import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;
//@WebListener
public class MyLog4jConfigListener extends Log4jConfigListener {

    // Public constructor is required by servlet spec
    public MyLog4jConfigListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // 判断当前运行环境，加载对应的log4j配置
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        Properties ipP = new Properties();
        String ip = null;
        try {
            ipP.load(MyLog4jConfigListener.class.getResourceAsStream("/server.properties"));
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress().toString(); //获取本机ip
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ip.equals(ipP.getProperty("innerip"))){
            //运行环境为服务器上，加载远程log4j配置
            sce.getServletContext()
                    .setInitParameter("log4jConfigLocation", "classpath:remotelog4j.properties");
        }else{
            //运行在本地,加载本地配置
            sce.getServletContext()
                    .setInitParameter("log4jConfigLocation", "classpath:locallog4j.properties");
        }
        super.contextInitialized(sce);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        super.contextDestroyed(sce);
    }
}
