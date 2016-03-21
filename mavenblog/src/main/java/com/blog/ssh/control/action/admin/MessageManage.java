package com.blog.ssh.control.action.admin;
import com.blog.ssh.control.service.HeaderSiderService;
import com.blog.ssh.control.service.MessageService;

public class MessageManage {
	private int id;
	private String ids;
	private String flag;
	private MessageService messageService;
	private HeaderSiderService headerSiderService;
	public MessageManage(){
		
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
	
	public MessageService getMessageService() {
		return messageService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public HeaderSiderService getHeaderSiderService() {
		return headerSiderService;
	}
	public void setHeaderSiderService(HeaderSiderService headerSiderService) {
		this.headerSiderService = headerSiderService;
	}
	public String execute(){
		if(!RightsManagement.adminIsLogin()){
			return "notlogin";
		}
		if(flag == null && id != 0){
			//删除单个留言
			messageService.deleteMsg(id);
		}
		if(flag == null && id == 0 && ids != null){
			//批量删除留言
			String[] idArr = ids.split(",");
			for(int i = 0;i < idArr.length;i++){
				messageService.deleteMsg(Integer.valueOf(idArr[i]));
			}
		}
		if(flag != null && id != 0 && ids == null){
			//审核单个留言
				messageService.auditing(id, Integer.valueOf(flag));
		}
		if(flag != null && id == 0 && ids != null){
			//批量通过留言
			String [] idArr = ids.split(",");
			for(int i = 0;i < idArr.length;i++ ){
				messageService.auditing(Integer.valueOf(idArr[i]), Integer.valueOf(flag));
			}
 		}
		headerSiderService.setApplication();//管理留言后，重新设置session
		return "success";
	}
}
