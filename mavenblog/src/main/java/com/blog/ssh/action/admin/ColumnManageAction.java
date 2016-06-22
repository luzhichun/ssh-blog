package com.blog.ssh.action.admin;

import com.blog.ssh.service.ArticleTypeService;
import com.blog.ssh.service.HeaderSiderService;
import com.blog.ssh.pojo.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ColumnManageAction {
	private String what;
	private int pid;
	private String columnName;
	private String linkName;
	@Autowired
	private ArticleTypeService articletypeService;
	@Autowired
	private HeaderSiderService headerSiderService;
	public ColumnManageAction(){
		
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
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
	public String execute(){
		if(!RightsManagementAction.adminIsLogin()){
			return "notlogin";
		}
		if(what.equals("addColumn")){
			//新增栏目
			ArticleType pat = articletypeService.getArticleType(pid);
			ArticleType at = new ArticleType();
			at.setValue(columnName);
			at.setLinkname(linkName);
			at.setParArticleType(pat);
			articletypeService.insertArticleType(at);
			headerSiderService.setApplication();
		}
		return "success";
	}
}
