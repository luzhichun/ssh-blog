package com.blog.ssh.action.admin;

import com.blog.ssh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserManageAction {
	private Integer id;
	private String ids;
	private Integer flag;
	@Autowired
	private UserService userService;
	public UserManageAction(){
		
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
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String execute(){
		if(!RightsManagementAction.adminIsLogin()){
			return "notlogin";
		}
		if(id != null && flag != null){
			//审核单个用户
			userService.auditing(id, flag);
		}
		if(ids != null && flag != null){
			//批量审核
			String [] idArr = ids.split(",");
			for(int i = 0;i < idArr.length;i++ ){
				userService.auditing(Integer.valueOf(idArr[i]), Integer.valueOf(flag));
			}
		}
		return "success";
	}
	
}
