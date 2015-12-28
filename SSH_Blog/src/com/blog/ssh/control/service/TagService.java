package com.blog.ssh.control.service;

import java.util.List;
import java.util.Set;

import com.blog.ssh.control.dao.TagDAO;
import com.blog.ssh.model.pojo.Tag;

public class TagService {
	private TagDAO tagDAO;

	public TagDAO getTagDAO() {
		return tagDAO;
	}

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}
	public void insertTag(Tag tag){
		tagDAO.save(tag);
	}
	public void update(Tag tag){
		tagDAO.update(tag);
	}
	public Tag findById(Integer id){
		return tagDAO.findById(id);
	}
	/**
	 * 判断标签表中是否存在该标签
	 * @param value
	 * @return
	 */
	public boolean hasValue(String value){
		if(tagDAO.findByValue(value).size() == 0){
			return false;
		}
		return true;
	}
	/**
	 * 通过value查找tag
	 * @param value
	 * @return
	 */
	public List findByValue(String value){
		return tagDAO.findByValue(value);
	}
	/**
	 * 获取所有tags
	 * @return
	 */
	public List<Tag> getAllTags(){
		return tagDAO.findAll();
	}
	public List<Tag> getHotTags(int size){
		return tagDAO.findHotTags(size);
	}
}
