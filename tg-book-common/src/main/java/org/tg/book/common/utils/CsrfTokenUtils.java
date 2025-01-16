package org.tg.book.common.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

public class CsrfTokenUtils {

    public static final String CSRF_TOKEN_NAME = "tgCsrfToken";

    /**
     * 根据token获取csrfToken
     */
    public static String getCsrfToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return "";
        }
        return DigestUtils.md5DigestAsHex(token.getBytes());
    }

    /**
     * 验证token和csrfToken
     */
    public static boolean verify(String token, String csrfToken) {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(csrfToken)) {
            return false;
        }
        return DigestUtils.md5DigestAsHex(token.getBytes()).equals(csrfToken);
    }

}
