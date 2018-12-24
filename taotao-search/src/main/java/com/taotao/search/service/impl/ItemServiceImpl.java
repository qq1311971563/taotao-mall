package com.taotao.search.service.impl;

import com.taotao.common.entity.WebResult;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;


import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    private SolrServer solrServer = new HttpSolrServer("http://192.168.192.132:8080/solr");

    @Override
    public WebResult importAllItems() {
        try {
            //查询商品列表
            List<Item> list = itemMapper.getItemList();
            for (Item item : list) {
                //创建一个SolrInputDocument对象
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSell_point());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategory_name());
                document.setField("item_desc", item.getItem_desc());
                //写入索引库
                solrServer.add(document);
            }
            //提交修改
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return WebResult.build(500,e.getMessage());
        }
        return WebResult.ok();
    }

}

