package com.taotao.sso.service.impl;

import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbUser;
import com.taotao.entity.TbUserExample;
import com.taotao.mapper.TbUserMapper;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper userMapper;

    @Override
    public WebResult checkData(String content, Integer type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (1 == type) {
            criteria.andUsernameEqualTo(content);
        } else if (2 == type) {
            criteria.andPhoneEqualTo(content);
        } else {
            criteria.andEmailEqualTo(content);
        }
        List<TbUser> list = userMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return WebResult.ok(false);
        }
        return WebResult.ok(true);
    }

    @Override
    public WebResult createUser(TbUser user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        //MD5加密密码
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return WebResult.ok();
    }
}
