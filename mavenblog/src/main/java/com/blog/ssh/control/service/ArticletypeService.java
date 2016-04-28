package com.blog.ssh.control.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blog.ssh.control.dao.ArticletypeHbmSQL;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.Articletype;

public class ArticletypeService {
	private ArticletypeHbmSQL articletypeDAO;

	public ArticletypeHbmSQL getArticletypeDAO() {
		return articletypeDAO;
	}

	public void setArticletypeDAO(ArticletypeHbmSQL articletypeDAO) {
		this.articletypeDAO = articletypeDAO;
	}
	public void insertArticletype(Articletype articletype){
		articletypeDAO.save(articletype);
	}
	/**
	 * ��ȡ�����ӷ���
	 * @return
	 */
	public List<Articletype> getAllChildrenArticletype(){
		return articletypeDAO.getAllChildrenArticletype();
	}
	/**
	 * ����pid��ȡ�����ӷ���
	 * @param pid ��id
	 * @return
	 */
	public List<Articletype> getChildrenArticletype(int pid){
		return articletypeDAO.getChildrenArticletype(pid);
	}
	/**
	 * ��ȡ���и�����
	 * @return
	 */
	public List<Articletype> getAllParentArticletype(){
		return articletypeDAO.getAllParentArticletype();
	}
	/**
	 * ͨ��id��ѯArticletype
	 * @param id
	 * @return
	 */
	public Articletype getArticletype(int id){
		return articletypeDAO.getArticletype(id);
	}
	/**
	 * ͨ��linkName��ȡArticletype
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
