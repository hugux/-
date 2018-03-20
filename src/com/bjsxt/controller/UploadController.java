package com.bjsxt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	//创建上传单元控制方法
	/**
	 * 配置form表单提交方式和类型
	 * 配置字节流解析bean
	 * 创建控制单元方法
	 * 		声明普通表单项形参
	 * 		声明MultipartFile形参--接受上传数据的对象
	 * 			getOriginalFilename() 获取上传文件名
	 * 			getContentType()      获取上传文件类型
	 * 将上传的资源存储到指定路径下
	 * 		//使用Tomcat支持的扩展包中功能
	 * 		FileUtils.copyInputStreamToFile(photo.getInputStream(),new File("D:/image",photo.getOriginalFilename()));
	 * 		//使用MultipartFile的转存
	 * 		photo.transferTo(new File("D:/image",photo.getOriginalFilename()));
	 * @param uname
	 * @param photo
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("upload")
	public String upload(String uname,MultipartFile photo) throws IOException{
		System.out.println(uname);
		System.out.println(photo.getOriginalFilename());//获取文件名
		System.out.println(photo.getContentType());//获取文件类型
		//将资源上传资源存到指定的位置
			FileUtils.copyInputStreamToFile(photo.getInputStream(),new File("D:/image",photo.getOriginalFilename()));
			//photo.transferTo(new File("D:/image",photo.getOriginalFilename()));
		return "forward:/index.jsp";
	}
	
	/**
	 * 优化上传
	 * 	 	设置上传的文件编码格式
	 * 		设置上传文件限制大小
	 * 		设置上传文件的类型
	 * 		设置上传文件的存储名
	 * @param uname
	 * @param photo
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	/*@RequestMapping("upload2")
	public String upload2(String uname,MultipartFile photo) throws IllegalStateException, IOException{
		//处理普通表单项数据
		//将上传数据存储到指定位置
			//获取文件上传类型
			String con=photo.getContentType();
			System.out.println(con);
			//校验文件类型
			if("image/jpeg".equals(con) || "image/bmp".equals(con)){
				photo.transferTo(new File("D:/image",photo.getOriginalFilename()));
				return "forward:/index.jsp";
			}else{
				return "forward:/error.jsp";
			}
	}	*/
	/*@RequestMapping("upload3")
	public String upload3(String uname,MultipartFile photo) throws IllegalStateException, IOException{
		//处理普通表单项数据
		//将上传数据存储到指定位置
			//获取文件上传类型
			String con=photo.getContentType();
			//获取文件后缀名
			String suffix=photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));
			System.out.println(suffix);
			//校验文件类型
			if(".jpg".equals(suffix) ||".png".equals(suffix)){
				//自定义文件名
				String photoName=UUID.randomUUID()+""+new Random().nextInt(10000)+suffix;
				photo.transferTo(new File("D:/image",photoName));
				return "forward:/index.jsp";
			}else{
				return "forward:/error.jsp";
			}	
	}	*/
	/**
	 * 下载
	 * @throws IOException 
	 * 
	 */
	/*@RequestMapping("down")
	public void downLoad(String filename,HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//设置响应头
			resp.setHeader("Content-Disposition","attachment;filename=a.jpg");
		//设置响应类型
			resp.setContentType("application/octet-stream");
		//获取要下载的图片资源路径
			String path=req.getServletContext().getRealPath("/images/"+filename);
			System.out.println(path);
		//获取图片资源流对象
			//InputStream in=new FileInputStream(new File(path,filename));
			OutputStream out=resp.getOutputStream();
		//将图片资源响应给浏览器
			//IOUtils.copy(in, out);
			out.write(FileUtils.readFileToByteArray(new File(path)));
	}
	*/
	
}
