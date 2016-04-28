package com.blog.ssh.control.action.admin;

import java.util.List;

import com.blog.ssh.control.service.ArticletypeService;
import com.blog.ssh.model.pojo.Articletype;
/**
 * ��ȡ��Ŀ����ҳ������Ҫ��ҳ��
 * @author wy
 *
 */
public class ShowColumns {
	private List<Articletype> chiArticletypes;
	private List<Articletype> parArticletypes;
	private ArticletypeService articletypeService;
	public ShowColumns(){
		
	}
	
	public List<Articletype> getChiArticletypes() {
		return chiArticletypes;
	}

	public void setChiArticletypes(List<Articletype> chiArticletypes) {
		this.chiArticletypes = chiArticletypes;
	}

	public List<Articletype> getParArticletypes() {
		return parArticletypes;
	}

	public void setParArticletypes(List<Articletype> parArticletypes) {
		this.parArticletypes = parArticletypes;
	}
	
	public ArticletypeService getArticletypeService() {
		return articletypeService;
	}

	public void setArticletypeService(ArticletypeService articletypeService) {
		this.articletypeService = articletypeService;
	}
	
	public String execute(){
		if(!RightsManagement.adminIsLogin()){
			return "notlogin";
		}
		this.chiArticletypes = articletypeService.getAllChildrenArticletype();
		this.parArticletypes = articletypeService.getAllParentArticletype();
		return "success";
	}
}
