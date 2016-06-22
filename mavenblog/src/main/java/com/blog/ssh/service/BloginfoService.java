package com.blog.ssh.service;

import com.blog.ssh.dao.BlogInfoDAO;
import com.blog.ssh.pojo.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloginfoService {
	@Autowired
	private BlogInfoDAO bloginfoDAO;
	public BlogInfoDAO getBloginfoDAO() {
		return bloginfoDAO;
	}
	public void setBloginfoDAO(BlogInfoDAO bloginfoDAO) {
		this.bloginfoDAO = bloginfoDAO;
	}
	public BlogInfo createDefaultBloginfo(){
		BlogInfo bloginfo = new BlogInfo();
		bloginfo.setBackground("black.jpg");
		bloginfo.setIntro("欢迎来到梦想博客");
		bloginfo.setVisits(0);
		bloginfo.setEmailNoticeflag(1);
		bloginfoDAO.save(bloginfo);
		return bloginfoDAO.findById(bloginfoDAO.findMaxId());
	}
	public void update(BlogInfo bi){
		bloginfoDAO.update(bi);
	}
}
