package com.blog.ssh.control.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFlolder {

    
    //Դ�ļ���
    static String url1="D:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 7.0\\webapps\\blog\\upload";
    //Ŀ���ļ���
    static String url2="D:\\myworkspaces\\myeclipse\\SSH_Blog\\WebRoot\\upload\\";
    
    
    
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        //����Ŀ���ļ���
      (new File(url2)).mkdirs();
      //��ȡԴ�ļ��е�ǰ�µ��ļ���Ŀ¼
       File[] file=(new File(url1)).listFiles();
       for (int i = 0; i < file.length; i++) {
        if(file[i].isFile()){
            //�����ļ�
            copyFile(file[i],new File(url2+file[i].getName()));
        }
        if(file[i].isDirectory()){
            //����Ŀ¼
            String sorceDir=url1+File.separator+file[i].getName();
            String targetDir=url2+File.separator+file[i].getName();
            copyDirectiory(sorceDir, targetDir);
        }
    }
    
    }
  

  public static void copyFile(File sourcefile,File targetFile) throws IOException{
        
        //�½��ļ����������������л���
        FileInputStream input=new FileInputStream(sourcefile);
        BufferedInputStream inbuff=new BufferedInputStream(input);
        
        //�½��ļ���������������л���
        FileOutputStream out=new FileOutputStream(targetFile);
        BufferedOutputStream outbuff=new BufferedOutputStream(out);
        
        //��������
        byte[] b=new byte[1024*5];
        int len=0;
        while((len=inbuff.read(b))!=-1){
            outbuff.write(b, 0, len);
        }
        
        //ˢ�´˻���������
        outbuff.flush();
        
        //�ر���
        inbuff.close();
        outbuff.close();
        out.close();
        input.close();
        
        
    }
    
    public static void copyDirectiory(String sourceDir,String targetDir) throws IOException{
        
        //�½�Ŀ��Ŀ¼
        
        (new File(targetDir)).mkdirs();
        
        //��ȡԴ�ļ��е��µ��ļ���Ŀ¼
        File[] file=(new File(sourceDir)).listFiles();
        
        for (int i = 0; i < file.length; i++) {
            if(file[i].isFile()){
                //Դ�ļ�
                File sourceFile=file[i];
                    //Ŀ���ļ�
                File targetFile=new File(new File(targetDir).getAbsolutePath()+File.separator+file[i].getName());
                
                copyFile(sourceFile, targetFile);
            
            }
            
            
            if(file[i].isDirectory()){
                //׼�����Ƶ�Դ�ļ���
                String dir1=sourceDir+file[i].getName();
                //׼�����Ƶ�Ŀ���ļ���
                String dir2=targetDir+"/"+file[i].getName();
                
                copyDirectiory(dir1, dir2);
            }
        }
        
    }
 }

