package com.taotao.portal.service.impl;

import com.taotao.common.entity.WebResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class SearchServiceImpl implements SearchService {
    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;
    @Override
    public SearchResult search(String queryString, int page) {
        Map<String ,String> param = new HashMap<>();
        param.put("q",queryString);
        param.put("page",page+ "");
        String json = HttpClientUtil.doGet(this.SEARCH_BASE_URL,param);
        WebResult result = WebResult.formatToPojo(json,SearchResult.class);
        if (result.getStatus() == 200) {
            SearchResult searchResult = (SearchResult) result.getData();
            return searchResult;
        }
        return null;
    }
}
