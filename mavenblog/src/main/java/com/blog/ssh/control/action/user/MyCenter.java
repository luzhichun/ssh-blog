package com.blog.ssh.control.action.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.blog.ssh.control.service.ArticletypeService;
import com.blog.ssh.control.service.BloginfoService;
import com.blog.ssh.control.service.CommentService;
import com.blog.ssh.control.service.TagService;
import com.blog.ssh.control.service.UserService;
import com.blog.ssh.control.service.UserSiderService;
import com.blog.ssh.model.pojo.Articletype;
import com.blog.ssh.model.pojo.Bloginfo;
import com.blog.ssh.model.pojo.Comment;
import com.blog.ssh.model.pojo.Tag;
import com.blog.ssh.model.pojo.User;
import com.opensymphony.xwork2.ActionContext;

/**
 * �û���¼��ĸ������ģ��������ۣ��������µȵ�
 * @author wy
 *
 */
public class MyCenter {
	private String sort;
	private UserService userService;
	private BloginfoService bloginfoService;
	private User user;
	private List<Articletype> chiArticletypes;
	private ArticletypeService articletypeService;
	private List<Comment> comments;
	private CommentService commentService;
	private List<Tag> tags;
	private TagService tagService;
	private String username;
	private String personinfo;
	private String skin;//Ƥ��
	private String currentPage;//�����жϵ�ǰ�����ĸ�ҳ��
	private String imageFileName;
	 //�ϴ����ļ�����  
	private File uploadFile;  
	//�ļ�����  
	private String uploadFileFileName;  
	//�ļ�����  
	private String uploadFileContentType;
	private UserSiderService userSiderService;
	private String background;
	private Integer email_notice;//����֪ͨ��־
	private String key;//��������ֱ�ӵ�¼key
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
	
	public List<Articletype> getChiArticletypes() {
		return chiArticletypes;
	}
	public void setChiArticletypes(List<Articletype> chiArticletypes) {
		this.chiArticletypes = chiArticletypes;
	}
	public ArticletypeService getArticletypeService() {
		return articletypeService;
	}
	public void setArticletypeService(ArticletypeService articletypeService) {
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
	 * ����struts2�ϴ��ļ��Ĵ洢·��
	 * @param path �洢�����·��
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void uploadPath(String path) throws Exception{
		String [] ufn = null;
        path = ServletActionContext.getRequest().getRealPath(path); 
        System.out.println(path);
        //�����  
        InputStream is;
        OutputStream os;
        ufn = uploadFileFileName.split("\\.");//��ȡpic��׺��
        uploadFileFileName =System.currentTimeMillis() + "." + ufn[ufn.length - 1];//�޸��ļ���Ϊ��ǰʱ��
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
			Bloginfo bi = u.getBloginfo();
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
				//���ܵ�¼
				return "notlogin";
			}
			else{
				//��ʾ�û�ͨ��session��¼
				int user_id = u.getId();
				this.user = userService.getUser(user_id);
				if(sort == null  || sort.equals("all")){
					//��ʾ���û���������
					this.comments = commentService.getCommentsByUser(user_id);
				}
				else{
					//����˵�����
					this.comments = commentService.getUnauditing(user_id);
				}
				this.currentPage = "commentManage";//��ʾ��ǰҳ�洦�����۹���ҳ��
				this.background = u.getBloginfo().getBackground();
				return "success";
			}
		}
		else{
			//ͨ��keyֵ��¼
			int user_id = (Integer) application.get(key);
			userService.setUserSession(user_id);
			this.user = userService.getUser(user_id);
			if(sort == null  || sort.equals("all")){
				//��ʾ���û���������
				this.comments = commentService.getCommentsByUser(user_id);
			}
			else{
				//����˵�����
				this.comments = commentService.getUnauditing(user_id);
			}
			this.currentPage = "commentManage";//��ʾ��ǰҳ�洦�����۹���ҳ��
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
			chiArticletypes = articletypeService.getAllChildrenArticletype();
			tags = tagService.getHotTags(10);
			this.currentPage = "releaseArticle";//��ʾ��ǰҳ�洦�ڷ�������ҳ��
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
			this.currentPage = "modifyInfo";//��ʾ��ǰҳ�洦���޸ĸ�������ҳ��
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