package org.tg.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tg.book.common.auth.TokenProvider;
import org.tg.book.common.auth.eneity.AuthContextInfo;
import org.tg.book.common.utils.CsrfTokenUtils;
import org.tg.book.dal.mapper.UserMapper;
import org.tg.book.dal.po.User;
import org.tg.book.common.utils.CookieUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenProvider tokenProvider;
    @Value("${auth.cookie.secure:false}")
    private boolean secure;
    @Value("${auth.jwt.expire}")
    private int expire;

    @Override
    public String loginByPassword(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
        // 第一步：校验用户名和密码，校验通过后得到用户信息
        User user = userMapper.selectByUserNamePassword(userName, password);
        if (user == null) {
            // 用户名或密码不正确
            return null;
        }
        // 第二步：根据用户信息，创建token
        AuthContextInfo authContextInfo = new AuthContextInfo();
        authContextInfo.setUserId(String.valueOf(user.getUserId()));
        authContextInfo.setUserName(userName);
        String token = tokenProvider.create(authContextInfo);
        // 第三步：将token写入cookie
        CookieUtils.addToken(response, token, secure, expire);
        // 第四步：通过token生成CsrfToken，返回
        return CsrfTokenUtils.getCsrfToken(token);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.clearToken(response, secure);
    }

}
