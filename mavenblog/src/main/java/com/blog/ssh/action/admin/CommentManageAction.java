package com.blog.ssh.action.admin;

import java.util.Map;

import com.blog.ssh.service.CommentService;
import com.blog.ssh.service.HeaderSiderService;
import com.blog.ssh.service.UserService;
import com.blog.ssh.pojo.User;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommentManageAction {
	private int id;
	private String ids;
	private String flag;
	@Autowired
	private CommentService commentService;
	@Autowired
	private HeaderSiderService headerSiderService;
	private UserService userService;
	public CommentManageAction(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public CommentService getCommentService() {
		return commentService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	public HeaderSiderService getHeaderSiderService() {
		return headerSiderService;
	}
	public void setHeaderSiderService(HeaderSiderService headerSiderService) {
		this.headerSiderService = headerSiderService;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String execute(){
		if(!RightsManagementAction.adminIsLogin()  && !RightsManagementAction.userIsLogin()){
			//用户和管理员都未登录
			return "notlogin";
		}
		if(flag == null && id != 0){
			//删除单个留言
			commentService.deleteComment(id);
		}
		if(flag == null && id == 0 && ids != null){
			//批量删除留言
			String[] idArr = ids.split(",");
			for(int i = 0;i < idArr.length;i++){
				commentService.deleteComment(Integer.valueOf(idArr[i]));
			}
		}
		if(flag != null && id != 0 && ids == null){
			//审核单个留言
				commentService.auditing(id, Integer.valueOf(flag));
		}
		if(flag != null && id == 0 && ids != null){
			//批量通过留言
			String [] idArr = ids.split(",");
			for(int i = 0;i < idArr.length;i++ ){
				commentService.auditing(Integer.valueOf(idArr[i]), Integer.valueOf(flag));
			}
 		}
		headerSiderService.setApplication();//设置评论后，重新设置网站session
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		//TODO Auto-generated method stub
//		user.setMsgCounts(userService.getmsgCounts(user.getId()));
		session.put("user", user);
		return "success";
	}
	
}
