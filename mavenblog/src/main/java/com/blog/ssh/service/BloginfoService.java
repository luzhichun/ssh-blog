package com.blog.ssh.service;

import com.blog.ssh.dao.BloginfoDAO;
import com.blog.ssh.pojo.Bloginfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloginfoService {
	@Autowired
	private BloginfoDAO bloginfoDAO;
	public BloginfoDAO getBloginfoDAO() {
		return bloginfoDAO;
	}
	public void setBloginfoDAO(BloginfoDAO bloginfoDAO) {
		this.bloginfoDAO = bloginfoDAO;
	}
	public Bloginfo createDefaultBloginfo(){
		Bloginfo bloginfo = new Bloginfo();
		bloginfo.setBackground("black.jpg");
		bloginfo.setIntro("欢迎来到梦想博客");
		bloginfo.setVisits(0);
		bloginfo.setEmailNoticeflag(1);
		bloginfoDAO.save(bloginfo);
		return bloginfoDAO.findById(bloginfoDAO.findMaxId());
	}
	public void update(Bloginfo bi){
		bloginfoDAO.update(bi);
	}
}
