package com.taotao.service;

import com.taotao.common.entity.EasyUiDataGridResult;
import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbItemParam;

public interface ItemParamService {
	public EasyUiDataGridResult getItemParamsList(Integer page, Integer rows);

	public WebResult getItemParamByItemCatId(Long itemcatid);

	public WebResult addItemParam(TbItemParam itemParam);
	
}
