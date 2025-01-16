package org.tg.book.common.auth.eneity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AuthContextInfo implements Serializable {

    private String userId;
    private String userName;

    private static final ThreadLocal<AuthContextInfo> threadLocal = new ThreadLocal<>();

    /**
     * 获取用户认证信息
     */
    public static AuthContextInfo getAuthInfo() {
        return threadLocal.get();
    }

    /**
     * 清除用户认证信息
     */
    public static void clearAuthInfo() {
        threadLocal.remove();
        System.out.println("clearAuthInfo:");
        System.out.println(getAuthInfo());
    }

    /**
     * 设置用户认证信息
     */
    public static void setAuthInfo(AuthContextInfo authContextInfo) {
        if (authContextInfo == null) {
            throw new IllegalArgumentException("authInfo不能为空");
        }
        threadLocal.set(authContextInfo);
    }
}
