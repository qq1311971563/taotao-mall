package com.taotao.service;

import java.util.List;

import com.taotao.entity.TbItemCat;

public interface ItemCatService {
	List<TbItemCat> getItemCatList(Long parentId) throws Exception;
}
