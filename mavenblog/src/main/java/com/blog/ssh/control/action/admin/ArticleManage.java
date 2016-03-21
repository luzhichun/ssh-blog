package com.blog.ssh.control.action.admin;
import org.apache.struts2.ServletActionContext;

import com.blog.ssh.control.service.ArticleService;
import com.blog.ssh.control.service.ArticletypeService;
import com.blog.ssh.control.service.FileManage;
import com.blog.ssh.control.service.HeaderSiderService;
import com.blog.ssh.control.service.SystemManage;

public class ArticleManage {
	private Integer id;
	private String ids;
	private ArticleService articleService;
	private ArticletypeService articletypeService;
	private HeaderSiderService headerSiderService;
	public ArticleManage(){
		
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
	/**
	 * ɾ������ʱ����ɾ����������ͼ
	 * @param picName
	 */
	public void deletePic(String picName){
		try {
			FileManage.deleteFile(ServletActionContext.getRequest().getRealPath("/file/pic/" + picName));
			//ɾ���������ϵ���������ͼ
			if(!SystemManage.isAliServer()){
				FileManage.deleteFile("D:/myworkspaces/myeclipse/SSH_Blog/WebRoot/file/pic/" + picName);
				//ɾ������workspaceͼƬ
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ɾ������ͼʧ��");
			e.printStackTrace();
		}
	}
	public String execute(){
		if(!RightsManagement.adminIsLogin()  && !RightsManagement.userIsLogin()){
			//�û��͹���Ա��δ��¼
			return "notlogin";
		}
		String picName = null;
		if(id != null){
			//����ɾ��
			picName = articleService.getArticle(id).getImagename();
			if(!picName.equals("default.jpg")){
				//������ͼ��Ϊdefaultʱ����ɾ��ͼƬ
				deletePic(picName);
			}
			articleService.deleteArticle(id);
		}
		else{
			//����ɾ��
			String[] idArr = ids.split(",");
			try {
				for(int i = 0;i < idArr.length;i++){
					int articleNumber = Integer.parseInt(idArr[i]);
					picName = articleService.getArticle(articleNumber).getImagename();
					if(!picName.equals("default.jpg")){
						//������ͼ��Ϊdefaultʱ����ɾ��ͼƬ
						deletePic(picName);
					}
					articleService.deleteArticle(articleNumber);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		headerSiderService.setApplication();//ɾ�����º���������Application
		return "success";
	}
}
