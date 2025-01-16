package org.tg.book.common.auth.eneity;

import java.util.Date;

public class AuthContextInfo {
    private String userId;
    private String userName;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
