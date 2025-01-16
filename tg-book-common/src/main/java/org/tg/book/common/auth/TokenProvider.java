package org.tg.book.common.auth;

import org.tg.book.common.auth.eneity.AuthContextInfo;

public interface TokenProvider {

    /**
     * 根据用户授权信息，创建token
     */
    String create(AuthContextInfo authContextInfo);

    /**
     * 校验token，解析出用户授权信息
     */
    AuthContextInfo verify(String token) ;
}
