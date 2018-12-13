package com.taotao.sso.service;

import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbUser;
import org.springframework.stereotype.Service;

public interface UserService {

    WebResult checkData(String content, Integer type);

    WebResult createUser(TbUser user);
}
