package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP.SERVER.HOST}")
	private String FTP_SERVER_HOST;
	@Value("${FTP.SERVER.PORT}")
	private Integer FTP_SERVER_PORT;
	@Value("${FTP.SERVER.BASE_PATH}")
	private String FTP_SERVER_BASE_PATH;
	@Value("${FTP.SERVER.USERNAME}")
	private String FTP_SERVER_USERNAME;
	@Value("${FTP.SERVER.PASSWORD}")
	private String FTP_SERVER_PASSWORD;
	@Value("${IMAGES.SAVE.BASE_PATH}")
	private String IMAGE_SAVE_BASE_PATH;

	@SuppressWarnings("rawtypes")
	@Override
	public Map uploadPicture(MultipartFile file) {
		String oldFile = file.getOriginalFilename();// 获取原来的文件名称
		String filePath = new DateTime().toString("/yyyy/MM/dd");// 保存路径
		String newFileName = IDUtils.genImageName() + oldFile.substring(oldFile.lastIndexOf('.'));// 获取文件新的名称
		Boolean result;
		Map<String, String> resultMap = new HashMap<>();
		try {
			result = FtpUtil.uploadFile(FTP_SERVER_HOST, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD,
					FTP_SERVER_BASE_PATH, filePath, newFileName, file.getInputStream());
			if (result) {
				resultMap.put("error", "0");
				resultMap.put("url", IMAGE_SAVE_BASE_PATH + filePath + "/" + newFileName);
				return resultMap;
			}
			resultMap.put("error", "1");
			resultMap.put("message", "上传失败");
			return resultMap;

		} catch (IOException e) {
			resultMap.put("error", "1");
			resultMap.put("message", "上传失败");
			return resultMap;
		}
	}

}
