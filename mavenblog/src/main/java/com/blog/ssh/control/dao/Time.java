package com.blog.ssh.control.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time{
	public static String time(){
			//���ص�ǰ���ʱ��
		     SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		     return matter.format(new Date());
	}
}