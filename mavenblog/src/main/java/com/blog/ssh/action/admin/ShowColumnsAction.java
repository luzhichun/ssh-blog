package com.blog.ssh.action.admin;

import java.util.List;

import com.blog.ssh.service.ArticleTypeService;
import com.blog.ssh.pojo.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 获取栏目管理页面所需要的页面
 * @author wy
 *
 */
@Controller
public class ShowColumnsAction {
	private List<ArticleType> chiArticletypes;
	private List<ArticleType> parArticletypes;
	@Autowired
	private ArticleTypeService articletypeService;
	public ShowColumnsAction(){
		
	}
	
	public List<ArticleType> getChiArticletypes() {
		return chiArticletypes;
	}

	public void setChiArticletypes(List<ArticleType> chiArticletypes) {
		this.chiArticletypes = chiArticletypes;
	}

	public List<ArticleType> getParArticletypes() {
		return parArticletypes;
	}

	public void setParArticletypes(List<ArticleType> parArticletypes) {
		this.parArticletypes = parArticletypes;
	}
	
	public ArticleTypeService getArticletypeService() {
		return articletypeService;
	}

	public void setArticletypeService(ArticleTypeService articletypeService) {
		this.articletypeService = articletypeService;
	}
	
	public String execute(){
		if(!RightsManagementAction.adminIsLogin()){
			return "notlogin";
		}
		this.chiArticletypes = articletypeService.getAllChildrenArticleType();
		this.parArticletypes = articletypeService.getAllParentArticleType();
		return "success";
	}
}
