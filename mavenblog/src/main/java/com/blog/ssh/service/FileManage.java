package com.blog.ssh.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 文件管理，增删改查
 * @author wy
 *
 */
public class FileManage {
	/**
	 * 
	 * @param fileRealPath 文件路径
	 * @return 
	 * @throws Exception
	 */
	public static boolean deleteFile(String fileRealPath) throws Exception{
		System.out.println(fileRealPath);
		boolean flag = false;  
		File file = new File(fileRealPath);  
		// 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {  
		    file.delete();  
		    flag = true;  
		 }  
		 return flag; 
	}
	/**
	 * 
	 * @param sourceFilePath 要复制文件的路径
	 * @param targetFilePath 目标文件路径
	 * @throws IOException
	 */
    public static void copyFile(String sourceFilePath, String targetFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);
    	BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
}
