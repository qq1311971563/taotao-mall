package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.entity.EasyUiDataGridResult;
import com.taotao.common.entity.WebResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.entity.TbContent;
import com.taotao.entity.TbContentExample;
import com.taotao.mapper.TbContentMapper;
import com.taotao.service.ContentService;

/**
 * @Description: 内容管理业务
 * @author: Pan
 * @date: 2018年11月8日 上午10:54:01
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_REDIS_CLEAR_CASH}")
	private String REST_REDIS_CLEAR_CASH;

	@Override
	public EasyUiDataGridResult getContentPageDate(Long categoryId, int page, int rows) {
		TbContentExample example = new TbContentExample();
		com.taotao.entity.TbContentExample.Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EasyUiDataGridResult result = new EasyUiDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public WebResult saveContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		int ret = contentMapper.insert(content);
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_REDIS_CLEAR_CASH + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return WebResult.ok(ret);
	}

}
