package com.blog.ssh.service;

import java.util.List;


import com.blog.ssh.dao.ArticleContentDAO;
import com.blog.ssh.dao.ArticleDAO;
import com.blog.ssh.dao.CommentDAO;
import com.blog.ssh.dao.TagDAO;
import com.blog.ssh.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class ArticleService {
	@Autowired
	private ArticleDAO articleDAO;
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private ArticleContentDAO articleContentDAO;
	@Autowired
	private TagDAO tagDAO;

	public ArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public ArticleContentDAO getArticleContentDAO() {
		return articleContentDAO;
	}

	public void setArticleContentDAO(ArticleContentDAO articleContentDAO) {
		this.articleContentDAO = articleContentDAO;
	}

	public TagDAO getTagDAO() {
		return tagDAO;
	}

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

	/**
	 * 插入文章
	 * @param a
	 * @param articletype_id
	 */
	public void insertArticle(Article a,int articletype_id){
		a.getArticleContent().setId(articleContentDAO.save(a.getArticleContent()));
		articleDAO.insertArticle(a, articletype_id);
	}
	/**
	 * 获取所有文章
	 * @return 所有文章列表
	 */
	public List<Article> getAllArticle(){
		List<Article> articleList = articleDAO.getAllArticle1();
		return articleList;
	}
	/**
	 * 获取最热文章
	 * @return 5篇访问量最多的文章列表
	 */
	public List<Article> getHotArticleTitle(){
		return articleDAO.getHotArticleTitle();
	}
	/**
	 * 获取最新文章
	 * @return 5篇最新文章列表
	 */
	public List<Article> getLatestArticleTitle(){
		return articleDAO.getLatestArticleTitle();
	}
	/**
	 * 随机获取文章
	 * @return 5篇随机文章列表
	 */
	public List<Article> getRandomArticleTitle(Integer user_id){
		return articleDAO.getRandomArticleTitle(user_id);
	}
	/**
	 * 获取最热文章
	 * @return 5篇访问量最多的文章列表
	 */
	public List<Article> getHotArticleTitle(Integer user_id){
		return articleDAO.getHotArticleTitle(user_id);
	}
	/**
	 * 获取最新文章
	 * @return 5篇最新文章列表
	 */
	public List<Article> getLatestArticleTitle(Integer user_id){
		return articleDAO.getLatestArticleTitle(user_id);
	}
	/**
	 * 随机获取文章
	 * @return 5篇随机文章列表
	 */
	public List<Article> getRandomArticleTitle(){
		return articleDAO.getRandomArticleTitle();
	}
	/**
	 * 通过id查找Article
	 * @param id 文章id
	 * @return Article
	 */
	public Article getArticle(Integer id){
		return articleDAO.findById(id);
	}
	/**
	 * 通过id删除文章
	 * @param id
	 * 
	 */
	public void deleteArticle(Integer id){
		articleDAO.delete(articleDAO.findById(id));
	}
	/**
	 * 设置文章访问量
	 * @param id 文章id
	 */
	public void setArticleVisits(int id){
		Article a = articleDAO.findById(id);
		a.setVisits(a.getVisits() + 1);
		articleDAO.update(a);
	}
	public void update(Article a){
		articleDAO.update(a);
	}
	public List<Article> search(String value){
		return articleDAO.search(value);
	}
}
