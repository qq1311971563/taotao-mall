package com.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbContent;
import com.taotao.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public WebResult getContentList(@PathVariable Long contentCategoryId) {
		try {
			List<TbContent> list = contentService.getContentList(contentCategoryId);
			return WebResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return WebResult.build(500, "错误信息。");
		}
	}
}
