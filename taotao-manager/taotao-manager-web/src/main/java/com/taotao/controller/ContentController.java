package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.entity.EasyUiDataGridResult;
import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	ContentService contentService;

	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUiDataGridResult getContentPageDate(Long categoryId, int page, int rows) {
		return contentService.getContentPageDate(categoryId, page, rows);
	}

	@RequestMapping("/save")
	@ResponseBody
	public WebResult saveContent(TbContent content) {
		// 此处应该加载用户输入信息合法性校验
		return contentService.saveContent(content);
	}
}
