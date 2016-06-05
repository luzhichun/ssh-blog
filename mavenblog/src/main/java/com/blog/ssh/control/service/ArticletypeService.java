package com.blog.ssh.control.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blog.ssh.control.dao.ArticletypeDAO;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.Articletype;

public class ArticletypeService {
	private ArticletypeDAO articletypeDAO;

	public ArticletypeDAO getArticletypeDAO() {
		return articletypeDAO;
	}

	public void setArticletypeDAO(ArticletypeDAO articletypeDAO) {
		this.articletypeDAO = articletypeDAO;
	}
	public void insertArticletype(Articletype articletype){
		articletypeDAO.save(articletype);
	}
	/**
	 * 获取所有子分类
	 * @return
	 */
	public List<Articletype> getAllChildrenArticletype(){
		return articletypeDAO.getAllChildrenArticletype();
	}
	/**
	 * 根据pid获取所有子分类
	 * @param pid 父id
	 * @return
	 */
	public List<Articletype> getChildrenArticletype(int pid){
		return articletypeDAO.getChildrenArticletype(pid);
	}
	/**
	 * 获取所有父分类
	 * @return
	 */
	public List<Articletype> getAllParentArticletype(){
		return articletypeDAO.getAllParentArticletype();
	}
	/**
	 * 通过id查询Articletype
	 * @param id
	 * @return
	 */
	public Articletype getArticletype(int id){
		return articletypeDAO.getArticletype(id);
	}
	/**
	 * 通过linkName获取Articletype
	 * @param linkName
	 * @return
	 */
	public Articletype getArticletype(String linkName){
		return (Articletype) articletypeDAO.findByProperty("linkname", linkName).get(0);
	}
	public Set findArticleByArticletype(String linkname){
		List list = articletypeDAO.findArticleByArticletype(linkname);
		Set<Article> set=new HashSet<Article>();
		set.addAll(list);
		return set;
	}
	public void removeArticle(Article a){
		Articletype at = a.getArticletype();
		at.getArticles().remove(a);
		articletypeDAO.update(at);
	}
}
