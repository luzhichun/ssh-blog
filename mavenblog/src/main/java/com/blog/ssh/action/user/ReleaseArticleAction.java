package com.blog.ssh.action.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import com.blog.ssh.pojo.ArticleContent;
import org.apache.struts2.ServletActionContext;
import org.jsoup.Jsoup;

import com.blog.ssh.util.Time;
import com.blog.ssh.service.ArticleService;
import com.blog.ssh.service.FileManage;
import com.blog.ssh.service.HeaderSiderService;
import com.blog.ssh.service.SystemManage;
import com.blog.ssh.service.TagService;
import com.blog.ssh.pojo.Article;
import com.blog.ssh.pojo.Tag;
import com.blog.ssh.pojo.User;
import com.blog.ssh.util.sensitivewordsfilter.SensitivewordFilter;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
 * 发布文章
 */
@Controller
public class ReleaseArticleAction {
	private String title;
	private String content;
	private String tagsValue;
	private int articletypeId;
	private String imageFileName;
	 //上传的文件对象  
	private File uploadFile;  
	//文件名称  
	private String uploadFileFileName;  
	//文件类型  
	private String uploadFileContentType;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TagService tagService;
	@Autowired
	private HeaderSiderService headerSiderService;
	public ReleaseArticleAction(){
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getArticletypeId() {
		return articletypeId;
	}
	public void setArticletypeId(int articletypeId) {
		this.articletypeId = articletypeId;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	public String getUploadFileContentType() {
		return uploadFileContentType;
	}
	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	public String getTagsValue() {
		return tagsValue;
	}
	public void setTagsValue(String tagsValue) {
		this.tagsValue = tagsValue;
	}
	public TagService getTagService() {
		return tagService;
	}
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	public HeaderSiderService getHeaderSiderService() {
		return headerSiderService;
	}
	public void setHeaderSiderService(HeaderSiderService headerSiderService) {
		this.headerSiderService = headerSiderService;
	}
	/**
	 * 设置struts2上传文件的存储路径
	 * @param path 存储的相对路径
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void uploadPath(String path) throws Exception{
		String [] ufn = null;
        path = ServletActionContext.getRequest().getRealPath(path); 
        System.out.println(path);
        //输出流  
        InputStream is;
        OutputStream os;
        ufn = uploadFileFileName.split("\\.");//获取pic后缀名
        uploadFileFileName =System.currentTimeMillis() + "." + ufn[ufn.length - 1];//修改文件名为当前时间
        System.out.println(uploadFileFileName);
        os = new FileOutputStream(new File(path,uploadFileFileName));  
		is = new FileInputStream(uploadFile); 
        byte[] buf = new byte[is.available()];  
        int length = 0 ;  
        while(-1 != (length = is.read(buf) ) )  
        {  
            os.write(buf, 0, length) ;  
        }  
        is.close();  
        os.close(); 
	}
	public String getBeaginContent(String content){
		content = Jsoup.parse(content).text();
		int length = content.length();
		if(length < 100){
			return content;
		}
		else{
			return content.substring(0,100);
		}
	}
	private Boolean validate(){
		if(title == null || content == null){
			return false;
		}
		return true;
	}
	public Set getArticleTags(){
		String[] tagsArray = this.tagsValue.split("\\|");
		System.out.println(tagsArray.length);
		Set<Tag> s = new HashSet<Tag>(0);
		for(int i = 0;i < tagsArray.length;i++){
			Tag t = null;
			if(tagService.hasValue(tagsArray[i])){
				//该标签数据库中已经存在
				t = (Tag) tagService.findByValue(tagsArray[i]).get(0);
				System.out.println("数据库中存在一个标签");
			}
			else{
				t = new Tag();
				t.setValue(tagsArray[i]);
				tagService.insertTag(t);
				t = (Tag) tagService.findByValue(tagsArray[i]).get(0);
			}
			s.add(t);
		}
		System.out.println(s.size());
		return s;
	}
	@SuppressWarnings("deprecation")
	public String execute() throws Exception{
		try{
			if(!validate()){
				return "error";
			}
			Article a = new Article();
			if(uploadFile == null){
				a.setImagename("default.jpg");
			}
			else{
				uploadPath("/upload");
				a.setImagename(uploadFileFileName);
				if(!SystemManage.isAliServer()){
					FileManage.copyFile(ServletActionContext.getRequest().getRealPath("/upload/" + uploadFileFileName) , "D:/myworkspaces/myeclipse/SSH_Blog/WebRoot/upload/" + uploadFileFileName);//复制文件本地
				}
			}
			content = SensitivewordFilter.filter(content);
			User user = (User)ActionContext.getContext().getSession().get("user");
			a.setTitle(SensitivewordFilter.filter(title));
			a.setContent(content);
			a.setUser(user);
			a.setVisits(0);
			a.setBeagincontent(getBeaginContent(content));
			a.setReleasetime(Time.time());
			a.setTags(getArticleTags());
			ArticleContent ac = new ArticleContent();
			ac.setContent(content);
			a.setArticleContent(ac);
			articleService.insertArticle(a, articletypeId);
			headerSiderService.setApplication();//发布文章后，重新设置session
			return "success";
		} catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}	
	public void main(String args []){
	}
}
