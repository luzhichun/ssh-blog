package com.blog.ssh.control.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * �ļ�������ɾ�Ĳ�
 * @author wy
 *
 */
public class FileManage {
	/**
	 * 
	 * @param fileRealPath �ļ�·��
	 * @return 
	 * @throws Exception
	 */
	public static boolean deleteFile(String fileRealPath) throws Exception{
		System.out.println(fileRealPath);
		boolean flag = false;  
		File file = new File(fileRealPath);  
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
		if (file.isFile() && file.exists()) {  
		    file.delete();  
		    flag = true;  
		 }  
		 return flag; 
	}
	/**
	 * 
	 * @param sourceFilePath Ҫ�����ļ���·��
	 * @param targetFilePath Ŀ���ļ�·��
	 * @throws IOException
	 */
    public static void copyFile(String sourceFilePath, String targetFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);
    	BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // �½��ļ����������������л���
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // �½��ļ���������������л���
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // ��������
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // ˢ�´˻���������
            outBuff.flush();
        } finally {
            // �ر���
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
}
