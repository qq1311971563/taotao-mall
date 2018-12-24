package com.taotao.portal.service.impl;

import com.taotao.common.entity.WebResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.entity.TbUser;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 用户管理Service
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;
    @Value("${SSO_USER_LOGIN}")
    private String SSO_USER_LOGIN;
    @Override
    public TbUser getUserByToken(String token) {
        try {
            //调用SSO系统的服务,根据TOKEN取用户信息
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            //把json转换成webResult
            WebResult result = WebResult.formatToPojo(json, TbUser.class);
            if (result.getStatus() == 200) {
                return (TbUser) result.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public String getLoinUrl() {
        return SSO_BASE_URL + SSO_USER_LOGIN;
    }
}
