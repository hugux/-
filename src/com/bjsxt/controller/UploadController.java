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
	//�����ϴ���Ԫ���Ʒ���
	/**
	 * ����form���ύ��ʽ������
	 * �����ֽ�������bean
	 * �������Ƶ�Ԫ����
	 * 		������ͨ�����β�
	 * 		����MultipartFile�β�--�����ϴ����ݵĶ���
	 * 			getOriginalFilename() ��ȡ�ϴ��ļ���
	 * 			getContentType()      ��ȡ�ϴ��ļ�����
	 * ���ϴ�����Դ�洢��ָ��·����
	 * 		//ʹ��Tomcat֧�ֵ���չ���й���
	 * 		FileUtils.copyInputStreamToFile(photo.getInputStream(),new File("D:/image",photo.getOriginalFilename()));
	 * 		//ʹ��MultipartFile��ת��
	 * 		photo.transferTo(new File("D:/image",photo.getOriginalFilename()));
	 * @param uname
	 * @param photo
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("upload")
	public String upload(String uname,MultipartFile photo) throws IOException{
		System.out.println(uname);
		System.out.println(photo.getOriginalFilename());//��ȡ�ļ���
		System.out.println(photo.getContentType());//��ȡ�ļ�����
		//����Դ�ϴ���Դ�浽ָ����λ��
			FileUtils.copyInputStreamToFile(photo.getInputStream(),new File("D:/image",photo.getOriginalFilename()));
			//photo.transferTo(new File("D:/image",photo.getOriginalFilename()));
		return "forward:/index.jsp";
	}
	
	/**
	 * �Ż��ϴ�
	 * 	 	�����ϴ����ļ������ʽ
	 * 		�����ϴ��ļ����ƴ�С
	 * 		�����ϴ��ļ�������
	 * 		�����ϴ��ļ��Ĵ洢��
	 * @param uname
	 * @param photo
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	/*@RequestMapping("upload2")
	public String upload2(String uname,MultipartFile photo) throws IllegalStateException, IOException{
		//������ͨ��������
		//���ϴ����ݴ洢��ָ��λ��
			//��ȡ�ļ��ϴ�����
			String con=photo.getContentType();
			System.out.println(con);
			//У���ļ�����
			if("image/jpeg".equals(con) || "image/bmp".equals(con)){
				photo.transferTo(new File("D:/image",photo.getOriginalFilename()));
				return "forward:/index.jsp";
			}else{
				return "forward:/error.jsp";
			}
	}	*/
	/*@RequestMapping("upload3")
	public String upload3(String uname,MultipartFile photo) throws IllegalStateException, IOException{
		//������ͨ��������
		//���ϴ����ݴ洢��ָ��λ��
			//��ȡ�ļ��ϴ�����
			String con=photo.getContentType();
			//��ȡ�ļ���׺��
			String suffix=photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));
			System.out.println(suffix);
			//У���ļ�����
			if(".jpg".equals(suffix) ||".png".equals(suffix)){
				//�Զ����ļ���
				String photoName=UUID.randomUUID()+""+new Random().nextInt(10000)+suffix;
				photo.transferTo(new File("D:/image",photoName));
				return "forward:/index.jsp";
			}else{
				return "forward:/error.jsp";
			}	
	}	*/
	/**
	 * ����
	 * @throws IOException 
	 * 
	 */
	/*@RequestMapping("down")
	public void downLoad(String filename,HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//������Ӧͷ
			resp.setHeader("Content-Disposition","attachment;filename=a.jpg");
		//������Ӧ����
			resp.setContentType("application/octet-stream");
		//��ȡҪ���ص�ͼƬ��Դ·��
			String path=req.getServletContext().getRealPath("/images/"+filename);
			System.out.println(path);
		//��ȡͼƬ��Դ������
			//InputStream in=new FileInputStream(new File(path,filename));
			OutputStream out=resp.getOutputStream();
		//��ͼƬ��Դ��Ӧ�������
			//IOUtils.copy(in, out);
			out.write(FileUtils.readFileToByteArray(new File(path)));
	}
	*/
	
}
