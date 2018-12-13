package com.taotao.rest.service.impl;

import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemDesc;
import com.taotao.entity.TbItemParamItem;
import com.taotao.entity.TbItemParamItemExample;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Override
    public TbItem getItemById(Long id) throws Exception {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public TbItemDesc getItemDestById(Long id) throws Exception {
        return itemDescMapper.selectByPrimaryKey(id);
    }

    @Override
    public TbItemParamItem getItemParamItemById(Long id) throws Exception {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(id);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
