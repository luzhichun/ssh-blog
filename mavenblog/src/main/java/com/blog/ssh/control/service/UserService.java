package com.blog.ssh.control.service;


import java.util.List;
import java.util.Map;

import com.blog.ssh.control.dao.UserHbmSQL;
import com.blog.ssh.model.pojo.Article;
import com.blog.ssh.model.pojo.Comment;
import com.blog.ssh.model.pojo.User;
import com.opensymphony.xwork2.ActionContext;

public class UserService {
	private UserHbmSQL userDAO;
	public UserHbmSQL getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserHbmSQL userDAO) {
		this.userDAO = userDAO;
	}
	public void insertUser(User user){
		userDAO.save(user);
	}
	public User getUser(int id){
		return userDAO.getUser(id);
	}
	public int findMaxId(){
		return userDAO.findMaxId();
	}
	/**
	 * ����û����Ƿ��Ѿ�����
	 * @param username
	 * @return
	 */
	public boolean hasUser(String username){
		List list = userDAO.findByProperty("username", username);
		if(list.size() == 0){
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @return 0��ʾ������� 1��¼�ɹ� 2��ʾ�û������
	 */
	public int checkLogin(String username,String password){
		List<User> us= userDAO.findByProperty("username",username);
		if(us.size() == 0){
			return 0;
		}
		else{
			if(us.get(0).getPassword().equals(MD5.getInstance().getMD5(password))){
				if(us.get(0).getThroughFlag() == 1){
					setUserSession(us.get(0).getId());
					return 1;
				}
				else{
					//�û������
					return 2;
				}
			}
			return 0;
		}
	}
	/**
	 * �û�ע�������session
	 * @return
	 */
	public boolean loginOut(){
		Map session = ActionContext.getContext().getSession();
		session.remove("user");
		return true;
	}
	/**
	 * ����url�ж��Ƿ�����û�
	 * @param url
	 */
	public boolean hasUserByurl(String url){
		if(userDAO.findByProperty("url", url).size() == 0){
			//�����ڸ��û�
			return false;
		}
		return true;
	}
	public List<User> findAllUsers(){
		return userDAO.findAll();
	}
	public User findUserByurl(String url){
		User user = (User) userDAO.findByProperty("url", url).get(0);
		return user;
	}
	public List findByExample(User instance){
		return userDAO.findByExample(instance);
	}
	public int getBlogComments(int user_id){
		return userDAO.getBlogComments(user_id);
	}
	public void setBlogVisits(int user_id){
		User user = userDAO.findById(user_id);
		user.getBloginfo().setVisits(user.getBloginfo().getVisits() + 1);
	}
	/**
	 * �������
	 * @param id ����id
	 * @param flag ��˱�־��1��ʾͨ����0��ʾ��ͨ��
	 */
	public void auditing(int id ,int flag){
		User u = userDAO.findById(id);
		u.setThroughFlag(flag);
		userDAO.update(u);
	}
	public void update(User u){
		userDAO.update(u);
	}
	/**
	 * ���û�������Ϣ��ˢ���û�session
	 * @param user_id
	 */
	public void setUserSession(Integer user_id){
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = userDAO.findById(user_id);
		user.setBlogComments(userDAO.getBlogComments(user_id));
		user.setMsgCounts(userDAO.getmsgCounts(user_id));
		user.setArticleCounts(userDAO.getArticleCounts(user_id));
		session.put("user", user);
		System.out.println("�����û�session");
	}
	public int getmsgCounts(Integer user_id){
		return userDAO.getmsgCounts(user_id);
	}
}
