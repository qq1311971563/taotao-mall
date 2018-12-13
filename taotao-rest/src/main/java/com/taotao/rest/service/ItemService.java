package com.taotao.rest.service;

import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemDesc;
import com.taotao.entity.TbItemParam;
import com.taotao.entity.TbItemParamItem;
import org.apache.commons.net.bsd.RExecClient;

public interface ItemService {
    /**
     * 根据ID 获取商品信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    TbItem getItemById(Long id) throws Exception;

    /**
     * 根据ID获取商品描述
     */
    TbItemDesc getItemDestById(Long id) throws Exception;

    TbItemParamItem getItemParamItemById(Long id) throws Exception;


}
