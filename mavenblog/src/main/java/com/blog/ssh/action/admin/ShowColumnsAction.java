package com.blog.ssh.action.admin;

import java.util.List;

import com.blog.ssh.service.ArticletypeService;
import com.blog.ssh.pojo.Articletype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 获取栏目管理页面所需要的页面
 * @author wy
 *
 */
@Controller
public class ShowColumnsAction {
	private List<Articletype> chiArticletypes;
	private List<Articletype> parArticletypes;
	@Autowired
	private ArticletypeService articletypeService;
	public ShowColumnsAction(){
		
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
		if(!RightsManagementAction.adminIsLogin()){
			return "notlogin";
		}
		this.chiArticletypes = articletypeService.getAllChildrenArticletype();
		this.parArticletypes = articletypeService.getAllParentArticletype();
		return "success";
	}
}
