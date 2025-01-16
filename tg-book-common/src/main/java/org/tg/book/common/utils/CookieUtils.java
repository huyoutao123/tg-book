package org.tg.book.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class CookieUtils {

    public static final String COOKIE_TOKEN_NAME = "tgToken";

    /**
     * 将token写入response的cookie中
     */
    public static void addToken(HttpServletResponse response, String token, boolean secure, int expire) {
        addCookie(response, token, secure, expire);
    }

    private static void addCookie(HttpServletResponse response, String value, boolean secure, int expire) {
        Cookie cookie = new Cookie(CookieUtils.COOKIE_TOKEN_NAME, value);
        cookie.setPath("/");
        // 设置为true，该Cookie无法被js获取
        cookie.setHttpOnly(true);
        // https强烈建议设置为true，这样http请求的cookie就不起作用了
        cookie.setSecure(secure);
        // 相对当前的过期时间，expire=10就代表10秒后过期
        cookie.setMaxAge(expire);
        response.addCookie(cookie);
    }

    /**
     * 从request的cookie中获取token
     */
    public static String getToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            Optional<Cookie> cookieOptional = Arrays.stream(request.getCookies())
                    .filter(p -> p.getName().equals(COOKIE_TOKEN_NAME)).findFirst();
            if (cookieOptional.isPresent()) {
                return cookieOptional.get().getValue();
            }
        }
        return null;
    }


    /**
     * 清除cookie中token
     */
    public static void clearToken(HttpServletResponse response, boolean secure) {
        addCookie(response, null, secure, 0);
    }

}
