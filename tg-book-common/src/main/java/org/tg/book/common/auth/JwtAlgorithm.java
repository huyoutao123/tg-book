package org.tg.book.common.auth;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;

public interface JwtAlgorithm {

    /**
     * 获取JWT使用的算法
     */
    Algorithm getAlgorithm();

    /**
     * 获取JWT使用的验证方法
     */
    JWTVerifier getJwtVerifier();
}
