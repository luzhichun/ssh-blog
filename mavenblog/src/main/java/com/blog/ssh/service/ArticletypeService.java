package com.blog.ssh.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blog.ssh.dao.ArticleTypeDAO;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleTypeService {
	@Autowired
	private ArticleTypeDAO articleTypeDAO;

	public ArticleTypeDAO getArticleTypeDAO() {
		return articleTypeDAO;
	}

	public void setArticleTypeDAO(ArticleTypeDAO articleTypeDAO) {
		this.articleTypeDAO = articleTypeDAO;
	}
	public void insertArticleType(ArticleType at){
		articleTypeDAO.save(at);
	}
	/**
	 * 获取所有子分类
	 * @return
	 */
	public List<ArticleType> getAllChildrenArticleType(){
		return articleTypeDAO.getAllChildrenArticleType();
	}
	/**
	 * 通过linkName获取Articletype
	 * @param linkName
	 * @return
	 */
	public ArticleType getArticletype(String linkName){
		return articleTypeDAO.findByProperty("linkname", linkName).get(0);
	}
	/**
	 * 根据pid获取所有子分类
	 * @param pid 父id
	 * @return
	 */
	public List<ArticleType> getChildrenArticleType(int pid){
		return articleTypeDAO.getChildrenArticleType(pid);
	}
	/**
	 * 获取所有父分类
	 * @return
	 */
	public List<ArticleType> getAllParentArticleType(){
		return articleTypeDAO.getAllParentArticleType();
	}
	/**
	 * 通过id查询ArticleType
	 * @param id
	 * @return
	 */
	public ArticleType getArticleType(int id){
		return articleTypeDAO.findById(id);
	}
	public ArticleType getArticleType(String linkeName){
		return articleTypeDAO.findByProperty("linkname", linkeName).get(0);
	}
	public Set findArticleByArticleType(String linkname){
		List list = articleTypeDAO.findArticleByArticleType(linkname);
		Set<Article> set=new HashSet<Article>();
		set.addAll(list);
		return set;
	}
	public void removeArticle(Article a){
		ArticleType at = a.getArticleType();
		at.getArticles().remove(a);
		articleTypeDAO.update(at);
	}
}
