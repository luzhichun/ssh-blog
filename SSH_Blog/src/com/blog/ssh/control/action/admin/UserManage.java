package com.blog.ssh.control.action.admin;

import com.blog.ssh.control.service.UserService;

public class UserManage {
	private Integer id;
	private String ids;
	private Integer flag;
	private UserService userService;
	public UserManage(){
		
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
		if(!RightsManagement.adminIsLogin()){
			return "notlogin";
		}
		if(id != null && flag != null){
			//��˵����û�
			userService.auditing(id, flag);
		}
		if(ids != null && flag != null){
			//�������
			String [] idArr = ids.split(",");
			for(int i = 0;i < idArr.length;i++ ){
				userService.auditing(Integer.valueOf(idArr[i]), Integer.valueOf(flag));
			}
		}
		return "success";
	}
	
}
