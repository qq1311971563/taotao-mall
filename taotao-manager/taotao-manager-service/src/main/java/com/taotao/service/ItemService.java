package com.taotao.service;

import com.taotao.common.entity.EasyUiDataGridResult;
import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbItem;

public interface ItemService {
	public EasyUiDataGridResult getItemList(int page, int rows);

	public WebResult insertsItem(TbItem item,String itemParams);
}
