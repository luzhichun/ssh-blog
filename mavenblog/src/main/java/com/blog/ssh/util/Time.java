package com.blog.ssh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time{
	public static String time(){
			//返回当前输出时间
		     SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		     return matter.format(new Date());
	}
}