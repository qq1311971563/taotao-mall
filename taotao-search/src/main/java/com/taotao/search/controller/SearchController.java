package com.taotao.search.controller;

import com.taotao.common.entity.WebResult;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public WebResult query(@RequestParam("q") String queryString,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "60") Integer rows) {
        if (StringUtils.isBlank(queryString)) {
            return WebResult.build(400, "查询条件不能为空");
        }
        SearchResult searchResult = null;
        try {
            queryString = new String(queryString.getBytes("iso-8859-1"), "utf-8");
            searchResult = searchService.search(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return WebResult.build(500, e.getMessage());
        }
        return WebResult.ok(searchResult);
    }
}
