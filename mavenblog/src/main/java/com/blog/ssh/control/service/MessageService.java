package com.blog.ssh.control.service;

import java.util.List;

import com.blog.ssh.control.dao.MessageHbmSQL;
import com.blog.ssh.model.pojo.Message;

public class MessageService {
	private MessageHbmSQL messageDAO;

	public MessageHbmSQL getMessageDAO() {
		return messageDAO;
	}

	public void setMessageDAO(MessageHbmSQL messageDAO) {
		this.messageDAO = messageDAO;
	}
	public void insertMessage(Message message){
		messageDAO.save(message);
	}
	/**
	 * ��̨����ʱ
	 * ��ȡ��������
	 * @return ���������б�
	 */
	public List<Message> getAllMessage(){
		return messageDAO.getAllMessage();
	}
	/**
	 * ��ȡ���и�����
	 * @return ���������б�
	 */
	public List<Message> getAllParMessages(){
		return messageDAO.getAllParMessages();
	}
	/**
	 * ɾ������,ͬʱɾ�����Ե�user��Ϣ
	 * @param id
	 */
	public void deleteMsg(int id){
		messageDAO.deleteMsg(id);
	}
	/**
	 * �������
	 * @param id ����id
	 * @param flag ��˱�־
	 */
	public void auditing(int id ,int flag){
		messageDAO.auditing(id, flag);
	}
	/**
	 * ����parent_id��ȡ�Ѿ�ͨ���������������б�
	 * @param parent_id
	 * @return
	 */
	public List<Message> getChildrenMsgs(int parent_id){
		return messageDAO.getChildrenMsgs(parent_id);
	}
	/**
	 * ��ȡ����δ��˵�����
	 * @return
	 */
	public List<Message> getUnAuditing(){
		return messageDAO.getUnAuditing();
	}
	/**
	 * ��ȡ����ͨ����˵�����
	 * @return 5����������
	 */
	public List<Message> getLatestMessage(){
		return messageDAO.getLatestMessage();
	}
	/**
	 * ������
	 * @param id
	 */
	public void setMessageLight(int id){
		messageDAO.setMessageLight(id);
	}
	/**
	 * ��ȡ���ݿ�����������
	 * @return ��������
	 */
	public int getMessageCount(){
		return messageDAO.getMessageCount();
	}
	public Message getMessage(Integer id){
		return messageDAO.getMessage(id);
	}
}
