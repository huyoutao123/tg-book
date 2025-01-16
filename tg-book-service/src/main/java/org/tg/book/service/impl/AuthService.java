package org.tg.book.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    /**
     * 用户名+密码登录
     */
    String loginByPassword(String userName, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 登出
     */
    void logout(HttpServletRequest request, HttpServletResponse response);

}

