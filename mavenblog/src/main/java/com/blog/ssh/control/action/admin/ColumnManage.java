package com.blog.ssh.control.action.admin;

import com.blog.ssh.control.service.ArticletypeService;
import com.blog.ssh.control.service.HeaderSiderService;
import com.blog.ssh.model.pojo.Articletype;

public class ColumnManage {
	private String what;
	private int pid;
	private String columnName;
	private String linkName;
	private ArticletypeService articletypeService;
	private HeaderSiderService headerSiderService;
	public ColumnManage(){
		
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
	public String execute(){
		if(!RightsManagement.adminIsLogin()){
			return "notlogin";
		}
		if(what.equals("addColumn")){
			//ÐÂÔöÀ¸Ä¿
			Articletype pat = articletypeService.getArticletype(pid);
			Articletype at = new Articletype();
			at.setValue(columnName);
			at.setLinkname(linkName);
			at.setParArticletype(pat);
			articletypeService.insertArticletype(at);
			headerSiderService.setApplication();
		}
		return "success";
	}
}
