package com.xiaoba.handlerinterceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhouning
 */
public class LoginHandlerIntercep implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object token = request.getAttribute("token");
        if (token==null){
            //说明没有登录，进拦截

            return false;
        }else {
            return true;
        }
    }
}
