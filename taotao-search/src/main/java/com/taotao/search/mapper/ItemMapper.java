package com.taotao.search.mapper;

import com.taotao.search.pojo.Item;

import java.util.List;

/**
 * 商品
 * @author Administrator
 */
public interface ItemMapper {
    /**
     * 获取商品列表
     * @return
     */
    List<Item> getItemList();
}
