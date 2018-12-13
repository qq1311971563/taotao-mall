package com.taotao.service;

import com.taotao.common.entity.EasyUiDataGridResult;
import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbContent;

public interface ContentService {

	EasyUiDataGridResult getContentPageDate(Long categoryId, int page, int rows);

	WebResult saveContent(TbContent content);

}
