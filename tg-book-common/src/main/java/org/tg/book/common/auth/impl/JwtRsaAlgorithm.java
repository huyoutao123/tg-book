package org.tg.book.common.auth.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.tg.book.common.auth.JwtAlgorithm;
import org.tg.book.common.utils.RsaKeyUtils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class JwtRsaAlgorithm implements JwtAlgorithm {

    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;
    @Override
    public Algorithm getAlgorithm() {
        return algorithm;
    }
    @Override
    public JWTVerifier getJwtVerifier() {
        return jwtVerifier;
    }

    /**
     * 有参构造函数，将依赖倒置
     */
    public JwtRsaAlgorithm(String publicKey, String privateKey) {
        // 获取公钥对象
        RSAPublicKey rsaPublicKey;
        try {
            rsaPublicKey = RsaKeyUtils.getPublicKey(publicKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Get RSA public key error!", e);
        }
        // 获取私钥对象
        RSAPrivateKey rsaPrivateKey;
        try {
            rsaPrivateKey = RsaKeyUtils.getPrivateKey(privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Get RSA public key error!", e);
        }
        // 创建RSA256签名算法
        this.algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
        // 构建JWTVerifier对象
        this.jwtVerifier = JWT.require(this.algorithm)
                .acceptLeeway(10)
                .acceptExpiresAt(5)
                .build();;
    }
}
