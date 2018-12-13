package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.entity.EasyUiTreeNode;
import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbContentCategory;
import com.taotao.entity.TbContentCategoryExample;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUiTreeNode> getContentCategoryList(Long id) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		com.taotao.entity.TbContentCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EasyUiTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUiTreeNode node = new EasyUiTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public WebResult addNode(Long parentId, String name) {
		TbContentCategory record = new TbContentCategory();
		record.setIsParent(false);
		record.setName(name);
		record.setParentId(parentId);
		record.setSortOrder(1);
		record.setStatus(1);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		int x = contentCategoryMapper.insert(record);
		if (x > 0) {
			return WebResult.ok(x);
		}
		return WebResult.ok();
	}

	@Override
	public WebResult updateNode(Long id, String name) {
		TbContentCategory temp = contentCategoryMapper.selectByPrimaryKey(id);
		if (temp != null) {
			temp.setName(name);
			int ret = contentCategoryMapper.updateByPrimaryKeySelective(temp);
			return WebResult.ok(ret);
		}
		return WebResult.ok();
	}

	@Override
	public WebResult deleteNode(Long id) {
		TbContentCategory temp = contentCategoryMapper.selectByPrimaryKey(id);
		if(temp!=null) {
			int ret = contentCategoryMapper.deleteByPrimaryKey(id);
			return WebResult.ok(ret);
		}
		return WebResult.ok("error");
	}
}
