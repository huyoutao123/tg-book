package org.tg.book.common.auth.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.tg.book.common.auth.JwtAlgorithm;
import org.tg.book.common.auth.TokenProvider;
import org.tg.book.common.auth.eneity.AuthContextInfo;

import java.util.Calendar;
import java.util.Date;

public class JwtTokenProvider implements TokenProvider {
    private static final String SUBJECT = "TgAuthSSO";
    private static final String CLAIM_USERNAME="userName";
    private final int expire;
    private final JwtAlgorithm jwtAlgorithm;

    public JwtTokenProvider(int expire, JwtAlgorithm jwtAlgorithm) {
        this.expire = expire;
        this.jwtAlgorithm = jwtAlgorithm;
    }


    @Override
    public String create(AuthContextInfo authContextInfo) {
        Date issuedAt = new Date();

        Calendar expiresAt  = Calendar.getInstance();
        expiresAt.add(Calendar.SECOND, expire);

        return JWT.create()
                // 签发者
                .withIssuer(authContextInfo.getUserId())
                // 主题
                .withSubject(SUBJECT)
                // 签发时间
                .withIssuedAt(issuedAt)
                // 过期时间
                .withExpiresAt(expiresAt.getTime())
                // 在签发时间之前不可用
                .withNotBefore(issuedAt)
                // 自定义 userName
                .withClaim(CLAIM_USERNAME, authContextInfo.getUserName())
                .sign(this.jwtAlgorithm.getAlgorithm());
    }


    @Override
    public AuthContextInfo verify(String token) {
        DecodedJWT decodedJWT;
        try {
            // 校验token，无效或过期会抛异常
            decodedJWT = this.jwtAlgorithm.getJwtVerifier().verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // 主题不一致，被修改了
        if (!SUBJECT.equals(decodedJWT.getSubject())) {
            return null;
        }
        // 返回userId和userName
        AuthContextInfo authInfo = new AuthContextInfo();
        authInfo.setUserId(decodedJWT.getIssuer());
        authInfo.setUserName(decodedJWT.getClaim(CLAIM_USERNAME).asString());
        return authInfo;
    }
    public static void main(String[] args) throws InterruptedException {
        //String publicKey = "太长，省略，可以到 http://www.metools.info/code/c80.html 自行生成。。。";
        //String privateKey = "太长，省略，可以到 http://www.metools.info/code/c80.html 自行生成。。。";
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArK+NOK/89rNAWeAguHti" +
                "91QpMaHDZ6EaaySu5dyEvw6oUs4t8AiEc6HC7iTl1U2fxvuukk6P3e96V5w+fb+S" +
                "UFUUaO+oocsKOOxwXcfJ1uQorMsEns1PjYB9weOOYYQoE2KY34AE6+zRT3w8uMXX" +
                "pBmazZbPhUP8cGAOimUv4nSIK4n/nwBezEEeFM5dREaxabiDBe9HvOXmu8EfO2/P" +
                "MsE5K9x/GP/wNbE+yzP+rC6rr3mgJNugUmE7BB1Usl7pS1myukiFz+PXoE/nibed" +
                "k5FWzL5jeV8M8F7AZ404DdVhyN5dbLvwAI8jnnJ1nNRVEh5+1H0rvwSSlTAo+Po+" +
                "bwIDAQAB";
        String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCsr404r/z2s0BZ" +
                "4CC4e2L3VCkxocNnoRprJK7l3IS/DqhSzi3wCIRzocLuJOXVTZ/G+66STo/d73pX" +
                "nD59v5JQVRRo76ihywo47HBdx8nW5CisywSezU+NgH3B445hhCgTYpjfgATr7NFP" +
                "fDy4xdekGZrNls+FQ/xwYA6KZS/idIgrif+fAF7MQR4Uzl1ERrFpuIMF70e85ea7" +
                "wR87b88ywTkr3H8Y//A1sT7LM/6sLquveaAk26BSYTsEHVSyXulLWbK6SIXP49eg" +
                "T+eJt52TkVbMvmN5XwzwXsBnjTgN1WHI3l1su/AAjyOecnWc1FUSHn7UfSu/BJKV" +
                "MCj4+j5vAgMBAAECggEBAJ9/djzJsChc4C8jKJW8wWgYQAQrmUR6NOCJfVGqIKIn" +
                "c6kn7p4p/8yduGIlinM9wzoS9OcF0TP4IVQSaFXVP9sa+kMCOQtXchWprQ+xnOfy" +
                "zO7shVP35maYK4+OEtBXNHzTMMgegm02yw1TfvJbKhXT4HvLs9kvNlbFIikJ1PSf" +
                "kRdruq8/SiqDAiwtN4OUn7X3/pIx6b9P7hbO95aNUi1Dxb8xjQA05QVlqA8OwNyq" +
                "ORUHI0ayZI6dmyTA5FUkZZf1tS0PzVLjubBOjZHRSq1a8Eg2qV+e/zDNPkuKQZ3g" +
                "jyy6PamkRlSbfel6+8zacQVC8QRe1AAX68HFe/WKz2ECgYEA2WZySOPBJF85KuK1" +
                "Tv8rgNAoRZZNZbH/0YT2OkBOprOX7bOtvSx+nTZPw0U7nR3nMqnJ9uk/gVfmkbD6" +
                "WzHaSNEpxim2lT+A9jMC5FZcaQxJDHHBpUdMbPssvPGkE8i0XY+rxyQCugVp7+Jg" +
                "mTHISfaZCSBmAG09qtp3Wuk8li0CgYEAy1iv53kChnVvBTckQYHWI5R5ByzPOEum" +
                "EHLo8fvEvUSWaVlDDoPeFw1XtybNVBeyeu/c3HLi7/Z1836PwtpCCAF9XSIq8N/B" +
                "PUR2hKDlg4j3m6BvR25Pu54ORbyevL1LugV+iGVfQ9lWjeV6XeYoN/jGTwSY/Hb+" +
                "dc4rur8sBIsCgYBYlFx2hI460q3JYow7fs7r8mSmTeKFUCyK4yEshO1HESATU0W0" +
                "Mb/5MJr5Vmk+0GNWikXnXAxrGDSzIigwJjTpvIfH3VEuqKxUJF7GSMXoa4AMGQGs" +
                "5UsnkIQfDFotUXbkNFjqkCqoPvJ2Mofng5g3QsoCJPhKrjgVOGSvXx83lQKBgQCG" +
                "0Y8WveFRumxYHd4Y3HdYcajoe+oLngRFJZqSTWV8QwwiXr8Z0Y4e5IbCdKRv26JG" +
                "5d8d/cG+bT54qPGxs7lRy4MNi4jC2OcqssiNWIuy8M2RzgXZaybL8pft3oe0BSE+" +
                "/UOONP+7YU6El5/Qv7bsnTEF1LuFr3M4MfBGSVdqzwKBgQDJBulXrWWQujQlQ+9/" +
                "u7YoCwIr6N/ZL0fpnKtaQ7WfHs7zy6QUhu6skufFJKmWehOD6i+SWBmuhv4PPMCS" +
                "IPhjChIh8AL8AVfSCjrksP0YENOHtbBhSE9bBHdH4u9VBy+6lbErSLl0867Qy4Z4" +
                "LAN0Bjc+5MNy0vMQmqat/EKlHA==";
        // 创建RSA算法
        JwtAlgorithm jwtRsaAlgorithm = new JwtRsaAlgorithm(publicKey, privateKey);
        // 创建JWT提供者：10秒过期 + RSA算法
        TokenProvider jwtTokenProvider = new JwtTokenProvider(10, jwtRsaAlgorithm);
        // 测试保存到Token中的授权信息
        AuthContextInfo authContextInfo = new AuthContextInfo();
        authContextInfo.setUserId("123456");
        authContextInfo.setUserName("admin");
        // 创建token
        String token = jwtTokenProvider.create(authContextInfo);
        System.out.println("token:" + token);
        // 循环校验token何时过期
        while (true) {
            Thread.sleep(2000);
            // 校验token
            AuthContextInfo authInfo = jwtTokenProvider.verify(token);
            if (authInfo == null) {
                break;
            }
            System.out.println("校验ok:" + authInfo.toString());
        }
    }


}
