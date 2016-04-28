package com.blog.ssh.control.service;

import java.util.List;




import com.blog.ssh.control.dao.ArticleHbmSQL;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.Articletype;
public class ArticleService {
	private ArticleHbmSQL articleDAO;
	public ArticleHbmSQL getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(ArticleHbmSQL articleDAO) {
		this.articleDAO = articleDAO;
	}
	/**
	 * ��������
	 * @param c
	 * @param article_id
	 */
	public void insertArticle(Article a,int articletype_id){
		articleDAO.insertArticle(a, articletype_id);
	}
	/**
	 * ��ȡ��������
	 * @return ���������б�
	 */
	public List<Article> getAllArticle(){
		return articleDAO.getAllArticle();
	}
	/**
	 * ��ȡ��������
	 * @return 5ƪ���������������б�
	 */
	public List<Article> getHotArticleTitle(){
		return articleDAO.getHotArticleTitle();
	}
	/**
	 * ��ȡ��������
	 * @return 5ƪ���������б�
	 */
	public List<Article> getLatestArticleTitle(){
		return articleDAO.getLatestArticleTitle();
	}
	/**
	 * �����ȡ����
	 * @return 5ƪ��������б�
	 */
	public List<Article> getRandomArticleTitle(Integer user_id){
		return articleDAO.getRandomArticleTitle(user_id);
	}
	/**
	 * ��ȡ��������
	 * @return 5ƪ���������������б�
	 */
	public List<Article> getHotArticleTitle(Integer user_id){
		return articleDAO.getHotArticleTitle(user_id);
	}
	/**
	 * ��ȡ��������
	 * @return 5ƪ���������б�
	 */
	public List<Article> getLatestArticleTitle(Integer user_id){
		return articleDAO.getLatestArticleTitle(user_id);
	}
	/**
	 * �����ȡ����
	 * @return 5ƪ��������б�
	 */
	public List<Article> getRandomArticleTitle(){
		return articleDAO.getRandomArticleTitle();
	}
	/**
	 * ͨ��id����Article
	 * @param id ����id
	 * @return Article
	 */
	public Article getArticle(Integer id){
		return articleDAO.getArticle(id);
	}
	/**
	 * ͨ���ļ�������Article
	 * @param ���¶�Ӧҳ����ļ���
	 * @return Article
	 */
	public Article getArticle(String fileName){
		return articleDAO.getArticle(fileName);
	}
	/**
	 * ͨ��idɾ������
	 * @param id
	 * 
	 */
	public void deleteArticle(Integer id){
		articleDAO.delete(articleDAO.findById(id));
	}
	/**
	 * �������·�����
	 * @param fileName
	 */
	public void setArticleVisits(int id){
		Article a = articleDAO.getArticle(id);
		a.setVisits(a.getVisits() + 1);
		articleDAO.update(a);
		
	}
	/**
	 * ��ȡ���ݿ�����������
	 * @return ��������
	 */
	public int getArticleCount(){
		return articleDAO.getArticleCount();
	}
	public void update(Article a){
		articleDAO.update(a);
	}
	public List<Article> serach(String value){
		return articleDAO.serach(value);
	}
	public static void main(String args []){
	}
}
