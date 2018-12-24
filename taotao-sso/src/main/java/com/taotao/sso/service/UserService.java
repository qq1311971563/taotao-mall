package com.taotao.sso.service;

import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    WebResult checkData(String content, Integer type);

    WebResult createUser(TbUser user);

    WebResult userLogin(String username , String password, HttpServletRequest request, HttpServletResponse response);

    WebResult getUserByToken(String token);

    WebResult userLogOut(HttpServletRequest request,HttpServletResponse response,String token);
}
