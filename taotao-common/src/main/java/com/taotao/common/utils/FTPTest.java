package com.taotao.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FTPTest {
	/*
	 * public static void main(String[] args) throws SocketException, IOException {
	 * // 1创建一个ftpclient对象 FTPClient ftpClient = new FTPClient(); // 2创建ftp连接
	 * ftpClient.connect("192.168.180.130", 21); // 3登录ftp服务器
	 * ftpClient.login("ftpuser", "123456"); // 4设置服务器图片保存位置
	 * ftpClient.changeWorkingDirectory("/home/ftpuser/www/images"); // 5准备上传的文件
	 * FileInputStream inputStream = new FileInputStream( new
	 * File("G:\\Pictures\\workTest\\upload\\spring_uoload\\abc.jpg")); //
	 * 6修改上传文件的格式(默认是字符上传,需要修改为二进制上传) ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	 * // 7上传文件 ftpClient.storeFile("hello1.jpg", inputStream); // 8关闭连接
	 * ftpClient.logout(); }
	 */

	public static void main(String[] args) throws FileNotFoundException {

		FileInputStream inputStream = new FileInputStream(
				new File("G:\\Pictures\\workTest\\upload\\spring_uoload\\abc.jpg"));
		FtpUtil.uploadFile("192.168.180.130", 21, "ftpuser", "123456", "/home/ftpuser/www/images", "/2018/10/26",
				"abc.jpg", inputStream);
		System.out.println(1);
	}
}
