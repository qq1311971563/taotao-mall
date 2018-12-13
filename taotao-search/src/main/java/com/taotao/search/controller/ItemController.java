package com.taotao.search.controller;

import com.taotao.common.entity.WebResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manager")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/importall")
    @ResponseBody
    public String importAllItems(){
        WebResult result = itemService.importAllItems();
        return JsonUtils.objectToJson(result);
    }
}
