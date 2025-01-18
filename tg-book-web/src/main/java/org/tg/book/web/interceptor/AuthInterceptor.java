package org.tg.book.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.tg.book.common.auth.TokenProvider;
import org.tg.book.common.auth.eneity.AuthContextInfo;
import org.tg.book.common.utils.CsrfTokenUtils;
import org.tg.book.common.utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AuthInterceptor.preHandle:" + request.getServletPath());
        // 校验cookie里带 tgToken，401: 重新登录
        String token = CookieUtils.getToken(request);
        if (StringUtils.isEmpty(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 校验header里带 tgCsrfToken, 400: 非法请求
        String csrfToken = request.getHeader(CsrfTokenUtils.CSRF_TOKEN_NAME);
        if (StringUtils.isEmpty(csrfToken)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        // csrf校验 header和cookie，400: 非法请求
        if (!CsrfTokenUtils.verify(token, csrfToken)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        // 校验token
        AuthContextInfo authInfo = tokenProvider.verify(token);
        if (authInfo == null) {
            // token不正确、失效，401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 校验成功, 保存至认证上下文
        AuthContextInfo.setAuthInfo(authInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("AuthInterceptor.postHandle：" + request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("AuthInterceptor.afterCompletion：" + request.getServletPath());
        // 最后，清除认证信息
        AuthContextInfo.clearAuthInfo();
    }
}
