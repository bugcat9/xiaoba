package com.xiaoba;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

public class Auth2Filter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse) throws Exception {
        return null;
    }

    @Override
    protected boolean onAccessDenied(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse) throws Exception {
        return false;
    }
}
