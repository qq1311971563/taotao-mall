package com.taotao.portal.service;

import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemDesc;
import com.taotao.portal.pojo.Item;

public interface ItemService {
    Item getItemById(Long id);
    String getItemDescById(Long id);
    String getItemParamItem(Long id);
}
