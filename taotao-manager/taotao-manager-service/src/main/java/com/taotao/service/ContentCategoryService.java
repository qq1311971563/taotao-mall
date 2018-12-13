package com.taotao.service;

import java.util.List;

import com.taotao.common.entity.EasyUiTreeNode;
import com.taotao.common.entity.WebResult;

public interface ContentCategoryService {
	List<EasyUiTreeNode> getContentCategoryList(Long id);

	WebResult addNode(Long parentId, String name);

	WebResult updateNode(Long id, String name);

	WebResult deleteNode(Long id);
}
