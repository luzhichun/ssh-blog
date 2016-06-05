package com.blog.ssh.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Articletype entity. @author MyEclipse Persistence Tools
 */

public class Articletype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String value;
	private Articletype parArticletype;
	private String linkname;
	private Set<Article> articles = new HashSet<Article>(0);
	private Set<Articletype> chiArticletypes = new HashSet<Articletype>(0);

	// Constructors

	/** default constructor */
	public Articletype() {
	
	}
	
	public Articletype(Integer id, String value, Articletype parArticletype,
			String linkname, Set articles, Set chiArticletypes) {
		super();
		this.id = id;
		this.value = value;
		this.parArticletype = parArticletype;
		this.linkname = linkname;
		this.articles = articles;
		this.chiArticletypes = chiArticletypes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Articletype getParArticletype() {
		return parArticletype;
	}

	public void setParArticletype(Articletype parArticletype) {
		this.parArticletype = parArticletype;
	}

	public String getLinkname() {
		return linkname;
	}

	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	public Set getArticles() {
		return articles;
	}

	public void setArticles(Set articles) {
		this.articles = articles;
	}

	public Set getChiArticletypes() {
		return chiArticletypes;
	}

	public void setChiArticletypes(Set chiArticletypes) {
		this.chiArticletypes = chiArticletypes;
	}
}