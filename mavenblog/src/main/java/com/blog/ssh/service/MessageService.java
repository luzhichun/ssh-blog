package com.blog.ssh.service;

import java.util.List;

import com.blog.ssh.dao.MessageDAO;
import com.blog.ssh.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired
	private MessageDAO messageDAO;

	public MessageDAO getMessageDAO() {
		return messageDAO;
	}

	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	public void insertMessage(Message message){
		messageDAO.save(message);
	}
	/**
	 * 后台管理时
	 * 获取所有文章
	 * @return 所有留言列表
	 */
	public List<Message> getAllMessage(){
		return messageDAO.getAllMessage();
	}
	/**
	 * 获取所有父留言
	 * @return 所有留言列表
	 */
	public List<Message> getAllParMessages(){
		return messageDAO.getAllParMessages();
	}
	/**
	 * 删除留言,同时删除留言的user信息
	 * @param id
	 */
	public void deleteMsg(int id){
		messageDAO.deleteMsg(id);
	}
	/**
	 * 审核留言
	 * @param id 留言id
	 * @param flag 审核标志
	 */
	public void auditing(int id ,int flag){
		messageDAO.auditing(id, flag);
	}
	/**
	 * 根据parent_id获取已经通过的子留言留言列表
	 * @param parent_id
	 * @return
	 */
	public List<Message> getChildrenMsgs(int parent_id){
		return messageDAO.getChildrenMsgs(parent_id);
	}
	/**
	 * 获取所有未审核的留言
	 * @return
	 */
	public List<Message> getUnAuditing(){
		return messageDAO.getUnAuditing();
	}
	/**
	 * 获取最新通过审核的评论
	 * @return 5条最新评论
	 */
	public List<Message> getLatestMessage(){
		return messageDAO.getLatestMessage();
	}
	/**
	 * 顶留言
	 * @param id
	 */
	public void setMessageLight(int id){
		messageDAO.setMessageLight(id);
	}
	/**
	 * 获取数据库中留言数量
	 * @return 留言数量
	 */
	public int getMessageCount(){
		return messageDAO.getMessageCount();
	}
	public Message getMessage(Integer id){
		return messageDAO.getMessage(id);
	}
}
