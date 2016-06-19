package com.blog.ssh.service;


import java.util.List;
import java.util.Map;

import com.blog.ssh.dao.UserDAO;
import com.blog.ssh.pojo.User;
import com.blog.ssh.util.MD5;
import com.blog.ssh.vo.UserVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserDAO userDAO;
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
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
		log.debug("username:" + username + "---password:" + password);
		List<User> us = userDAO.findByProperty("username",username);
		if(us.size() == 0){
			return 0;
		}
		else{
			if(us.get(0).getPassword().equals(MD5.getInstance().getMD5(password))){
				log.debug("登录成功!");
				if(us.get(0).getThroughFlag() == 1){
					setUserSession(us.get(0).getId());
					return 1;
				}
				else{
					log.debug("用户被封禁!");
					return 2;
				}
			}
			log.debug("密码错误!");
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
	 * 刷新用户session
	 * @param user_id
	 */
	public UserVO setUserSession(Integer user_id){
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = userDAO.findById(user_id);
		UserVO uv = new UserVO();
		uv.setBlogComments(userDAO.getBlogComments(user_id));
		uv.setMsgCounts(userDAO.getmsgCounts(user_id));
		uv.setArticleCounts(userDAO.getArticleCounts(user_id));
		BeanUtils.copyProperties(user, uv);
		session.put("user", uv);
		System.out.println("用户session设置成功");
		return uv;
	}
	public int getmsgCounts(Integer user_id){
		return userDAO.getmsgCounts(user_id);
	}
}
