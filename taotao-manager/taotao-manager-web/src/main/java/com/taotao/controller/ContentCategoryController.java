package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.entity.EasyUiTreeNode;
import com.taotao.common.entity.WebResult;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping(value = "/list")
	@ResponseBody
	public List<EasyUiTreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentid) {
		return contentCategoryService.getContentCategoryList(parentid);
	}

	@RequestMapping(value = "/create")
	@ResponseBody
	public WebResult createContentCategoryList(String name, Long parentId) {
		return contentCategoryService.addNode(parentId, name);
	}
	@RequestMapping(value = "/update")
	@ResponseBody
	public WebResult updateContentCategoryList(String name, Long id) {
		return contentCategoryService.updateNode(id, name);
	}
	@RequestMapping(value = "/delete")
	@ResponseBody
	public WebResult updateContentCategoryList(Long id) {
		return contentCategoryService.deleteNode(id);
	}
}
