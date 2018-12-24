package com.taotao.sso.service.impl;

import com.taotao.common.entity.CookieUtils;
import com.taotao.common.entity.WebResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.entity.TbUser;
import com.taotao.entity.TbUserExample;
import com.taotao.mapper.TbUserMapper;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    @Override
    public WebResult checkData(String content, Integer type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        //用户名验证
        if (1 == type) {
            criteria.andUsernameEqualTo(content);
            //电话号码验证
        } else if (2 == type) {
            criteria.andPhoneEqualTo(content);
            //邮箱验证
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

    @Override
    public WebResult userLogin(String username, String password,
                               HttpServletRequest request, HttpServletResponse response) {
        //根据用户名密码查询是否有对应记录
        TbUserExample userExample = new TbUserExample();
        TbUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
        List<TbUser> tbUsers = userMapper.selectByExample(userExample);
        //根据查询结果,进行处理
        if (tbUsers == null || tbUsers.size() == 0) {
            return WebResult.build(400, "用户名或者密码不正确");
        }
        TbUser tbUser = tbUsers.get(0);
        String token = UUID.randomUUID().toString();
        tbUser.setPassword(null);//清空密码,前台不保存密码信息
        //保存用户登录信息到redis中
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(tbUser));
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        //把用户信息写入Cookie
        CookieUtils.setCookie(request,response,"TT_TOKEN",token);

        return WebResult.ok(token);
    }

    @Override
    public WebResult getUserByToken(String token) {
        String user = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        if (StringUtils.isBlank(user)) {
            return WebResult.build(400, "此Session已过期,请重新登录");
        }
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        return WebResult.ok(JsonUtils.jsonToPojo(user, TbUser.class));
    }

    @Override
    public WebResult userLogOut(HttpServletRequest request,HttpServletResponse response,String token) {
        //清除用户本地信息
        CookieUtils.deleteCookie(request,response,"TT_TOKEN");
        //清理redis
        jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
        return WebResult.ok();
    }
}
