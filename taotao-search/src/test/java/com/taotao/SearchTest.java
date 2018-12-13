package com.taotao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLOutput;

public class SearchTest {
    @Test
    public void addDocument() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.180.132:8080/solr");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id",536563);
        document.addField("item_title","new2 - 阿尔卡特 (OT-927) 炭黑 联通3G手机 双卡双待");
        document.addField("item_sell_point","清仓！仅北京，武汉仓有货！");
        document.addField("item_price","29900000");
        document.addField("item_image","http://image.taotao.com/jd/4ef8861cf6854de9889f3db9b24dc371.jpg");
        document.addField("item_category_name","测试");
        document.addField("item_desc","测试用例");
        solrServer.add(document);
        solrServer.commit();
    }
    @Test
    public void queryTest() throws SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.180.132:8080/solr");
        //创建一个查询对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery("*:*");
        query.setStart(20);
        query.setRows(50);
        //执行查询
        QueryResponse response = solrServer.query(query);
        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("共查询到记录：" + solrDocumentList.getNumFound());
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
        }
    }
}
