package com.blog.ssh.action.admin;
import org.apache.struts2.ServletActionContext;

import com.blog.ssh.service.ArticleService;
import com.blog.ssh.service.ArticleTypeService;
import com.blog.ssh.service.FileManage;
import com.blog.ssh.service.HeaderSiderService;
import com.blog.ssh.service.SystemManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleManageAction {
	private Integer id;
	private String ids;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleTypeService articletypeService;
	@Autowired
	private HeaderSiderService headerSiderService;
	public ArticleManageAction(){
		
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
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
	/**
	 * 删除文章时，先删除文章缩略图
	 * @param picName
	 */
	public void deletePic(String picName){
		try {
			FileManage.deleteFile(ServletActionContext.getRequest().getRealPath("/file/pic/" + picName));
			//删除服务器上的文章缩略图
			if(!SystemManage.isAliServer()){
				FileManage.deleteFile("D:/myworkspaces/myeclipse/SSH_Blog/WebRoot/file/pic/" + picName);
				//删除本地workspace图片
			}
		} catch (Exception e) {
			System.out.println("删除缩略图失败");
			e.printStackTrace();
		}
	}
	public String execute(){
		if(!RightsManagementAction.adminIsLogin()  && !RightsManagementAction.userIsLogin()){
			//用户和管理员都未登录
			return "notlogin";
		}
		String picName = null;
		if(id != null){
			//单个删除
			picName = articleService.getArticle(id).getImagename();
			if(!picName.equals("default.jpg")){
				//当缩略图不为default时，才删除图片
				deletePic(picName);
			}
			articleService.deleteArticle(id);
		}
		else{
			//批量删除
			String[] idArr = ids.split(",");
			try {
				for(int i = 0;i < idArr.length;i++){
					int articleNumber = Integer.parseInt(idArr[i]);
					picName = articleService.getArticle(articleNumber).getImagename();
					if(!picName.equals("default.jpg")){
						//当缩略图不为default时，才删除图片
						deletePic(picName);
					}
					articleService.deleteArticle(articleNumber);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		headerSiderService.setApplication();//删除文章后，重新设置Application
		return "success";
	}
}
