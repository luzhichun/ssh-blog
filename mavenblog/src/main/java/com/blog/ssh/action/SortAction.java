package com.blog.ssh.action;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.blog.ssh.service.ArticleService;
import com.blog.ssh.service.ArticleTypeService;
import com.blog.ssh.service.HeaderSiderService;
import com.blog.ssh.service.TagService;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.ArticleType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SortAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String sortByColumn;
	private Integer sortByTag;
	private ArticleType articletype;
	@Autowired
	private TagService tagService;
	private String tagValue;
	private Set articles;
	@Autowired
	private ArticleTypeService articletypeService;
	@Autowired
	private HeaderSiderService headerSiderService;
	private String q;
	@Autowired
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
	public ArticleType getArticletype() {
		return articletype;
	}
	public void setArticletype(ArticleType articletype) {
		this.articletype = articletype;
	}
	
	public TagService getTagService() {
		return tagService;
	}
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	public ArticleTypeService getArticletypeService() {
		return articletypeService;
	}

	public void setArticletypeService(ArticleTypeService articletypeService) {
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
			this.articletype = articletypeService.getArticleType(this.sortByColumn);
			this.articles = articletypeService.findArticleByArticleType(this.sortByColumn);
			request.put("title", articletype.getValue());
			return "success";
		}
		if(sortByTag != null){
			this.articles = tagService.findById(sortByTag).getArticles();
			this.tagValue = tagService.findById(sortByTag).getValue();
			request.put("title", "包含  " + tagValue + " 标签的文章");
			return "success";
		}
		if(q != null && !q.equals("") && !q.equals("请输入关键字进行搜索")){
			String ts = null;
			List<Article> as = articleService.search(q);
			this.articles = new HashSet();
			this.articles.addAll(as);
			request.put("title","包含 " + q + " 关键字的搜索结果");
			return "success";
		}
		return "notinput";
	}
}
