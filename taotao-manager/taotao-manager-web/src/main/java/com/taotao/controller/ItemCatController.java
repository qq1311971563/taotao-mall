package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.entity.EasyUiTreeNode;
import com.taotao.entity.TbItemCat;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUiTreeNode> select(@RequestParam(value = "id", defaultValue = "0") Long id) throws Exception {
		List<TbItemCat> list = itemCatService.getItemCatList(id);
		List<EasyUiTreeNode> result = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUiTreeNode node = new EasyUiTreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent() ? "closed" : "open");
			result.add(node);
		}
		return result;
	}

}
