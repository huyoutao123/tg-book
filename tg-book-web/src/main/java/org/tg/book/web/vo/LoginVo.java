package org.tg.book.web.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVO implements Serializable {
    private String userName;
    private String password;
}
