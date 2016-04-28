package com.blog.ssh.control.action;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.blog.ssh.control.service.ArticleService;
import com.blog.ssh.control.service.ArticletypeService;
import com.blog.ssh.control.service.HeaderSiderService;
import com.blog.ssh.control.service.TagService;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.Articletype;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SortAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sortByColumn;
	private Integer sortByTag;
	private Articletype articletype;
	private TagService tagService;
	private String tagValue;
	private Set articles;
	private ArticletypeService articletypeService;
	private HeaderSiderService headerSiderService;
	private String q;
	private ArticleService articleService;
	public SortAction(){
	}
	public String getSortByColumn() {
		return sortByColumn;
	}
	public void setSortByColumn(String sortByColumn) {
		this.sortByColumn = sortByColumn;
	}
	
	public Integer getSortByTag() {
		return sortByTag;
	}
	public void setSortByTag(Integer sortByTag) {
		this.sortByTag = sortByTag;
	}
	public Articletype getArticletype() {
		return articletype;
	}
	public void setArticletype(Articletype articletype) {
		this.articletype = articletype;
	}
	
	public TagService getTagService() {
		return tagService;
	}
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	public ArticletypeService getArticletypeService() {
		return articletypeService;
	}

	public void setArticletypeService(ArticletypeService articletypeService) {
		this.articletypeService = articletypeService;
	}

	public HeaderSiderService getHeaderSiderService() {
		return headerSiderService;
	}

	public void setHeaderSiderService(HeaderSiderService headerSiderService) {
		this.headerSiderService = headerSiderService;
	}
	
	public String getTagValue() {
		return tagValue;
	}
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	public Set getArticles() {
		return articles;
	}
	public void setArticles(Set articles) {
		this.articles = articles;
	}
	
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	@SuppressWarnings("unchecked")
	public String execute(){
		Map request = (Map) ActionContext.getContext().get("request");
		headerSiderService.SessionManage();
		if(sortByColumn != null){
			this.articletype = articletypeService.getArticletype(this.sortByColumn);
			this.articles = articletypeService.findArticleByArticletype(this.sortByColumn);
			request.put("title", articletype.getValue());
			return "success";
		}
		if(sortByTag != null){
			this.articles = tagService.findById(sortByTag).getArticles();
			this.tagValue = tagService.findById(sortByTag).getValue();
			request.put("title", "����  " + tagValue + " ��ǩ������");
			return "success";
		}
		if(q != null && !q.equals("") && !q.equals("������ؼ��ֽ�������")){
			String ts = null;
			//System.out.println(q);
			List<Article> as = articleService.serach(q);
//			System.out.println("articles size:" + as.size());
//			for(int i = 0;i < as.size();i++){
//				ts = as.get(i).getTitle();
//				//System.out.println(ts);
//				ts = ts.replaceAll(q, "<font color=\"red\">" + q + "</font>");
//				as.get(i).setTitle(ts);
//				System.out.println(ts);
//			}
			this.articles = new HashSet();
			this.articles.addAll(as);
			request.put("title","���� " + q + " �ؼ��ֵ��������");
			return "success";
		}
		return "notinput";
	}
}
