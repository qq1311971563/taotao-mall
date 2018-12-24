package com.taotao.portal.interceptor;

import com.taotao.common.entity.CookieUtils;
import com.taotao.entity.TbUser;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 强制登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //在 handler执行之前处理,返回Modelandview

        // 判断用户是否登录
        // 从cookie中取出用户信息
        String token = CookieUtils.getCookieValue(httpServletRequest, "TT_TOKEN");
        TbUser user = userService.getUserByToken(token);
        if (null == user) {
            httpServletResponse.sendRedirect(userService.getLoinUrl()
                    + "?redirect=" + httpServletRequest.getRequestURL());
            return false;
        }
        return true;//返回值决定handler是否执行
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
