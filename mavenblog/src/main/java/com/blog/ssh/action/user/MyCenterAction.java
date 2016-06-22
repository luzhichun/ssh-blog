package com.blog.ssh.action.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.blog.ssh.service.ArticleTypeService;
import com.blog.ssh.service.BloginfoService;
import com.blog.ssh.service.CommentService;
import com.blog.ssh.service.TagService;
import com.blog.ssh.service.UserService;
import com.blog.ssh.service.UserSiderService;
import com.blog.ssh.pojo.ArticleType;
import com.blog.ssh.pojo.BlogInfo;
import com.blog.ssh.pojo.Comment;
import com.blog.ssh.pojo.Tag;
import com.blog.ssh.pojo.User;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 用户登录后的个人中心，管理评论，发布文章等等
 * @author wy
 *
 */
@Controller
public class MyCenterAction {
	private String sort;
	@Autowired
	private UserService userService;
	@Autowired
	private BloginfoService bloginfoService;
	private User user;
	private List<ArticleType> chiArticletypes;
	@Autowired
	private ArticleTypeService articletypeService;
	private List<Comment> comments;
	@Autowired
	private CommentService commentService;
	private List<Tag> tags;
	@Autowired
	private TagService tagService;
	private String username;
	private String personinfo;
	private String skin;//皮肤
	private String currentPage;//用于判断当前处于哪个页面
	private String imageFileName;
	 //上传的文件对象  
	private File uploadFile;  
	//文件名称  
	private String uploadFileFileName;  
	//文件类型  
	private String uploadFileContentType;
	private UserSiderService userSiderService;
	private String background;
	private Integer email_notice;//邮箱通知标志
	private String key;//邮箱链接直接登录key
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<ArticleType> getChiArticletypes() {
		return chiArticletypes;
	}
	public void setChiArticletypes(List<ArticleType> chiArticletypes) {
		this.chiArticletypes = chiArticletypes;
	}
	public ArticleTypeService getArticletypeService() {
		return articletypeService;
	}
	public void setArticletypeService(ArticleTypeService articletypeService) {
		this.articletypeService = articletypeService;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public CommentService getCommentService() {
		return commentService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public TagService getTagService() {
		return tagService;
	}
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
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
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPersoninfo() {
		return personinfo;
	}
	public void setPersoninfo(String personinfo) {
		this.personinfo = personinfo;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	
	
	public Integer getEmail_notice() {
		return email_notice;
	}
	public void setEmail_notice(Integer email_notice) {
		this.email_notice = email_notice;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public BloginfoService getBloginfoService() {
		return bloginfoService;
	}
	public void setBloginfoService(BloginfoService bloginfoService) {
		this.bloginfoService = bloginfoService;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
	public String modifyInfo(){
		User u = (User) ActionContext.getContext().getSession().get("user");
		if(u == null){
			return "notlogin";
		}
		try{
			if(username == null || personinfo == null){
				return "failed";
			}
			if(uploadFile != null){
				uploadPath("/upload/headpic");
				u.setHeadpicname(uploadFileFileName);
				userService.update(u);
			}
			BlogInfo bi = u.getBloginfo();
			bi.setBackground(skin);
			bi.setIntro(personinfo);
			bi.setEmailNoticeflag(email_notice);
			bloginfoService.update(bi);
			this.background = u.getBloginfo().getBackground();
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	public String getUserComments(){
		Map session = (Map) ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		Map application = ActionContext.getContext().getApplication();
		if((key == null || !application.containsKey(key))){
			if(u == null){
				//不能登录
				return "notlogin";
			}
			else{
				//表示用户通过session登录
				int user_id = u.getId();
				this.user = userService.getUser(user_id);
				if(sort == null  || sort.equals("all")){
					//显示该用户所有评论
					this.comments = commentService.getCommentsByUser(user_id);
				}
				else{
					//待审核的评论
					this.comments = commentService.getUnauditing(user_id);
				}
				this.currentPage = "commentManage";//表示当前页面处于评论管理页面
				this.background = u.getBloginfo().getBackground();
				return "success";
			}
		}
		else{
			//通过key值登录
			int user_id = (Integer) application.get(key);
			userService.setUserSession(user_id);
			this.user = userService.getUser(user_id);
			if(sort == null  || sort.equals("all")){
				//显示该用户所有评论
				this.comments = commentService.getCommentsByUser(user_id);
			}
			else{
				//待审核的评论
				this.comments = commentService.getUnauditing(user_id);
			}
			this.currentPage = "commentManage";//表示当前页面处于评论管理页面
			this.background = user.getBloginfo().getBackground();
			return "success";
		}
	}
	
	public String showReleaseArticle(){
		User u = (User) ActionContext.getContext().getSession().get("user");
		if(u == null){
			return "notlogin";
		}
		else{
			//this.user = user;
			this.user = userService.getUser(Integer.valueOf(u.getId()));
			chiArticletypes = articletypeService.getAllChildrenArticleType();
			tags = tagService.getHotTags(10);
			this.currentPage = "releaseArticle";//表示当前页面处于发布文章页面
			this.background = u.getBloginfo().getBackground();
			return "success";
		}
	}
	public String getUserinfo(){
		User u = (User) ActionContext.getContext().getSession().get("user");
		if(u == null){
			return "notlogin";
		}
		else{
			this.currentPage = "modifyInfo";//表示当前页面处于修改个人资料页面
			this.background = u.getBloginfo().getBackground();
			return "success";
		}
	}
	public String articleManage(){
		User u = (User) ActionContext.getContext().getSession().get("user");
		if(u == null){
			return "notlogin";
		}
		else{
			//this.user = user;
			this.user = userService.getUser(Integer.valueOf(u.getId()));
			this.currentPage = "articleManage";
			this.background = u.getBloginfo().getBackground();
			return "success";
		}
	}
	public String execute(){
		return "success";
	}
}
