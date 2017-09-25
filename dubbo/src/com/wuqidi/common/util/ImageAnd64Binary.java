package com.wuqidi.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class ImageAnd64Binary {
    public static void main(String[] args){
    	
		String imgSrcPath 	 = "D:\\uploadFiles\\uploadImages\\dgioc_portal\\zhihuichengshiyingyong\\a9694e6f23904c1591f7bd5fc7b68618.jpg";     //生成64编码的图片的路径
		String imgCreatePath = "E:\\apache-tomcat-6.0.37\\webapps/pro/ueditor2/jsp/upload1/20140318/480ace2bfc6e44608595bd4adbdeb067.jpg";     //将64编码生成图片的路径
		imgSrcPath=imgSrcPath.replaceAll("\\\\", "/");
		System.out.println(imgSrcPath);
		
    	String strImg = getImageStr(imgSrcPath);
    	System.out.println(strImg);
    	
        generateImage(strImg, imgSrcPath);
    }
    
   /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param imgSrcPath 生成64编码的图片的路径
     * @return
     */
    public static String getImageStr(String imgSrcPath){
        InputStream in = null;
        byte[] data = null;
        
        //读取图片字节数组
        try {
            in = new FileInputStream(imgSrcPath);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }
    
    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr            转换为图片的字符串
     * @param imgCreatePath     将64编码生成图片的路径
     * @return
     */
    public static boolean generateImage(String imgStr, String imgCreatePath){
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {//调整异常数据
                    b[i]+=256;
                }
            }
            
            OutputStream out = new FileOutputStream(imgCreatePath);    
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    
    /**
     * 自定义方法
     * 通过字节流的方式加载磁盘中的图片
     * @param response
     * @param imgCreatePath
     */
    public static void showImage(HttpServletResponse response,String imgCreatePath){//imgCreatePath 本地磁盘中图片地址
		String imgStr = ImageAnd64Binary.getImageStr(imgCreatePath);//得到图片的字符流
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {//调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = response.getOutputStream();  
            response.setContentType("image/*"); // 设置返回的文件类型
            out.write(b);
            out.flush();
            out.close();
        }  catch (Exception e) {
			e.printStackTrace();
        }
    }
    
    /**
     * 通过字节流的方式加载磁盘中的图片
     * @param response
     * @param imgCreatePath 图片路径
     * 
     */
    public static void getDiskImage(HttpServletResponse response,HttpServletRequest request, String imgCreatePath){//imgCreatePath 本地磁盘中图片地址
    	
    	File file=null;
    	//获取路径
    	String  filePath=imgCreatePath.substring(0, imgCreatePath.lastIndexOf("/")+1);
    	file=new File(filePath);
    	//不存在 穿件路径
		if(!file.exists()){
			file.mkdirs();
		}
		file=new File(imgCreatePath);
    	//图片不存在显示默认图片
    	if(!file.exists()){
    		 String dir = request.getServletContext().getRealPath("\\");
    	     imgCreatePath=dir+"views\\common\\images\\default_image.png";
    		 imgCreatePath=imgCreatePath.replaceAll("\\\\", "/");
    	}
    	
		String imgStr = ImageAnd64Binary.getImageStr(imgCreatePath);//得到图片的字符流
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {//调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = response.getOutputStream();  
            response.setContentType("image/*"); // 设置返回的文件类型
            response.setHeader("Content-Disposition", "filename=\""+ imgCreatePath + "\"");
            out.write(b);
            out.flush();
            out.close();
        }  catch (Exception e) {
			e.printStackTrace();
        }
    }

    /**
     * 通过字节流的方式加载磁盘中的视频
     * @param response
     * @param videoCreatePath 视频路径
     * 
     */
    public static void getDiskVideo(HttpServletResponse response,HttpServletRequest request, String videoCreatePath){//videoCreatePath 本地磁盘中视频地址
    	
    	File file=null;
    	//获取路径
    	String  filePath=videoCreatePath.substring(0, videoCreatePath.lastIndexOf("/")+1);
    	file=new File(filePath);
    	//不存在 穿件路径
		if(!file.exists()){
			file.mkdirs();
		}
		file=new File(videoCreatePath);
    	//视频不存在显示默认视频
    	if(!file.exists()){
    		 String dir = request.getServletContext().getRealPath("\\");
    		 videoCreatePath=dir+"views\\common\\images\\default_video.jpg";
    		 videoCreatePath=videoCreatePath.replaceAll("\\\\", "/");
    	}
    	
		String videoStr = ImageAnd64Binary.getImageStr(videoCreatePath);//得到视频的字符流
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(videoStr);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {//调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = response.getOutputStream();  
            response.setContentType("video/*"); // 设置返回的文件类型
            response.setHeader("Content-Disposition", "filename=\""+ videoCreatePath + "\"");
            out.write(b);
            out.flush();
            out.close();
        }  catch (Exception e) {
			e.printStackTrace();
        }
    }
    
    
}











