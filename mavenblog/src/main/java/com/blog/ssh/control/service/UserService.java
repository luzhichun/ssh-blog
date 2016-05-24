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
	 * 检测用户名是否已经存在
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
	 * @return 0表示密码错误 1登录成功 2表示用户被封禁
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
					//用户被封禁
					return 2;
				}
			}
			return 0;
		}
	}
	/**
	 * 用户注销，清楚session
	 * @return
	 */
	public boolean loginOut(){
		Map session = ActionContext.getContext().getSession();
		session.remove("user");
		return true;
	}
	/**
	 * 根据url判断是否存在用户
	 * @param url
	 */
	public boolean hasUserByurl(String url){
		if(userDAO.findByProperty("url", url).size() == 0){
			//不存在该用户
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
	 * 审核评论
	 * @param id 评论id
	 * @param flag 审核标志，1表示通过，0表示不通过
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
	 * 当用户处理消息后，刷新用户session
	 * @param user_id
	 */
	public void setUserSession(Integer user_id){
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = userDAO.findById(user_id);
		user.setBlogComments(userDAO.getBlogComments(user_id));
		user.setMsgCounts(userDAO.getmsgCounts(user_id));
		user.setArticleCounts(userDAO.getArticleCounts(user_id));
		session.put("user", user);
		System.out.println("设置用户session");
	}
	public int getmsgCounts(Integer user_id){
		return userDAO.getmsgCounts(user_id);
	}
}
