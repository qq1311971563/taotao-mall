package com.taotao.service;

import com.taotao.common.entity.WebResult;

public interface ItemParamItemService {
	public WebResult insertItemParamItem(Long itemId, String itemParam);

	public WebResult getItemParamItem(Long itemId);
}
	