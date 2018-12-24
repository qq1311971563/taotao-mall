package com.taotao.rest.controller.vue;

import com.taotao.common.entity.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/vue")
public class DemoController {
    @RequestMapping("/getLunBo")
    @ResponseBody
    public WebResult getLunBoTu() {
        String[] imgs = {"http://192.168.192.134/images/2018/12/17/1545043743704360.jpg",
                "http://192.168.192.134/images/2018/12/17/1545043899292400.jpg"};
        return WebResult.ok(imgs);
    }
}
