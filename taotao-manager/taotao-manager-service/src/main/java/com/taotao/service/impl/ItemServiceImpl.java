package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.entity.EasyUiDataGridResult;
import com.taotao.common.entity.WebResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemExample;
import com.taotao.entity.TbItemParamItem;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public EasyUiDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		// com.taotao.entity.TbItemExample.Criteria criteria = example.createCriteria();
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		EasyUiDataGridResult result = new EasyUiDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * @Description: 添加商品方法
	 * @return: 返回结果描述
	 */
	@Override
	public WebResult insertsItem(TbItem item, String itemParams) {
		// 补全用户提交的商品信息
		item.setId(IDUtils.genItemId());// 补全商品id
		item.setStatus((byte) 1);// 补全商品状态，默认正常 1-正常 2-下架 3-删除
		item.setCreated(new Date());// 补全创建时间
		item.setUpdated(new Date());// 补全修改时间
		// 插入数据库
		itemMapper.insert(item);
		TbItemParamItem record = new TbItemParamItem();
		record.setItemId(item.getId());
		record.setParamData(itemParams);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		itemParamItemMapper.insert(record);

		return WebResult.ok();
	}

}
