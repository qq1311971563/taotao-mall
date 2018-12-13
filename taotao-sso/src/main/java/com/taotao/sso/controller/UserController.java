package com.taotao.sso.controller;

import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbUser;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
        //参数有效性校验
        WebResult result = null;
        if (StringUtils.isBlank(param)) {
            result = WebResult.build(400, "校验内容不能为空");
        }
        if (type == null) {
            result = WebResult.build(400, "校验内容类型不能为空");
        }
        if (type != 1 && type != 2 && type != 3) {
            result = WebResult.build(400, "校验内容类型错误");
        }
        /*校验出错*/
        if (result != null) {
            if (callback != null) {
                MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
                jacksonValue.setJsonpFunction(callback);
                return jacksonValue;
            } else {
                return result;
            }
        }
        //调用服务
        try {
            result = userService.checkData(param, type);
        } catch (Exception e) {
            return WebResult.build(500, e.getMessage());
        }
        if (callback != null) {
            MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
            jacksonValue.setJsonpFunction(callback);
            return jacksonValue;
        }
        return result;
    }

    @RequestMapping("/register")
    @ResponseBody
    public WebResult createUser(TbUser user ) {
        try {
            return userService.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return WebResult.build(500,e.getMessage());
        }
    }
}



