package org.tg.book.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tg.book.common.auth.JwtAlgorithm;
import org.tg.book.common.auth.TokenProvider;
import org.tg.book.common.auth.impl.JwtRsaAlgorithm;
import org.tg.book.common.auth.impl.JwtTokenProvider;

@Configuration
public class AuthConfig {

    @Bean
    public JwtAlgorithm jwtRsaAlgorithm(@Value("${auth.jwt.rsa.publicKey}") String publicKey, @Value("${auth.jwt.rsa.privateKey}") String privateKey ) {
        return new JwtRsaAlgorithm(publicKey, privateKey);
    }

    @Bean
    public TokenProvider jwtTokenProvider(@Value("${auth.jwt.expire}") int expire, JwtAlgorithm jwtAlgorithm) {
        return new JwtTokenProvider(expire, jwtAlgorithm);
    }
}
