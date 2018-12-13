package com.taotao.rest.controller;

import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemDesc;
import com.taotao.entity.TbItemParamItem;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/iteminfo")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{id}")
    @ResponseBody
    public WebResult getItemById(@PathVariable Long id) {
        if (id == null) {
            return WebResult.build(400, "参数中必须包含ID");
        }
        TbItem item = null;
        try {
            item = itemService.getItemById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return WebResult.build(500, e.getMessage());
        }
        return WebResult.ok(item);
    }

    @RequestMapping("/itemdesc/{id}")
    @ResponseBody
    public WebResult getItemDescById(@PathVariable Long id) {
        if (id == null) {
            return WebResult.build(400, "参数中必须包含ID");
        }
        TbItemDesc itemDesc = null;
        try {
            itemDesc = itemService.getItemDestById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return WebResult.build(500, e.getMessage());
        }
        return WebResult.ok(itemDesc);
    }

    @RequestMapping("/itemparam/{id}")
    @ResponseBody
    public WebResult getItemParamItemById(@PathVariable Long id) {
        if (id == null) {
            return WebResult.build(400, "参数中必须包含ID");
        }
        TbItemParamItem paramItem = null;
        try {
            paramItem = itemService.getItemParamItemById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return WebResult.build(500,e.getMessage());
        }
        return WebResult.ok(paramItem);
    }
}

