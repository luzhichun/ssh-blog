package com.blog.ssh.control.service;

import com.blog.ssh.control.dao.BloginfoDAO;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.Bloginfo;

public class BloginfoService {
	private BloginfoDAO bloginfoDAO;
	public BloginfoDAO getBloginfoDAO() {
		return bloginfoDAO;
	}
	public void setBloginfoDAO(BloginfoDAO bloginfoDAO) {
		this.bloginfoDAO = bloginfoDAO;
	}
	/**
	 * 设置博客访问量
	 * @param fileName
	 */
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
