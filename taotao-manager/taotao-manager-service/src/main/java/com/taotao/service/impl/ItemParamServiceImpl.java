package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.entity.EasyUiDataGridResult;
import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbItemParam;
import com.taotao.entity.TbItemParamExample;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public EasyUiDataGridResult getItemParamsList(Integer page, Integer rows) {
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		EasyUiDataGridResult result = new EasyUiDataGridResult();
		result.setRows(list);
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public WebResult getItemParamByItemCatId(Long itemcatid) {
		TbItemParamExample example = new TbItemParamExample();
		com.taotao.entity.TbItemParamExample.Criteria creiteria = example.createCriteria();
		creiteria.andItemCatIdEqualTo(itemcatid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() >= 1) {
			return WebResult.ok(list.get(0));
		}
		return WebResult.ok();
	}

	@Override
	public WebResult addItemParam(TbItemParam itemParam) {
		int ret = itemParamMapper.insert(itemParam);
		if (ret > 0) {
			return WebResult.ok("插入成功");
		}
		return WebResult.ok();
	}
}
