package com.taotao.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.entity.EasyUiDataGridResult;
import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemParam;
import com.taotao.service.ItemParamItemService;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemParamService itemParamService;
	@Autowired
	ItemParamItemService itemParamItemService;

	@RequestMapping("/list")
	@ResponseBody
	public EasyUiDataGridResult itemList(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer rows) {
		EasyUiDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public WebResult insertItem(TbItem item, String itemParams) {
		WebResult result = itemService.insertsItem(item, itemParams);
		return result;

	}

	@RequestMapping(value = "/param/list")
	@ResponseBody
	public EasyUiDataGridResult selectItemP(@RequestParam(defaultValue = "1") Integer page, Integer rows) {
		EasyUiDataGridResult result = itemParamService.getItemParamsList(page, rows);
		return result;
	}

	@RequestMapping(value = "/param/query/itemcatid/{itemcatid}")
	@ResponseBody
	public WebResult selectItemParamById(@PathVariable Long itemcatid) {
		return itemParamService.getItemParamByItemCatId(itemcatid);
	}

	@RequestMapping(value = "/param/save/{itemcatid}")
	@ResponseBody
	public WebResult saveItemParam(@PathVariable Long itemcatid, String paramData) {
		TbItemParam itemParam = new TbItemParam();
		// 补全数据内容
		itemParam.setParamData(paramData);
		itemParam.setItemCatId(itemcatid);
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		return itemParamService.addItemParam(itemParam);
	}

	@RequestMapping(value = "/param/itemid/{itemid}")
	@ResponseBody
	public WebResult getItemParams(@PathVariable Long itemid) {
		return itemParamItemService.getItemParamItem(itemid);
	}

}
