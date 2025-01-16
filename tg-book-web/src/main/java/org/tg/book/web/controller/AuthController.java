package org.tg.book.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tg.book.common.dto.TgResult;
import org.tg.book.service.impl.AuthService;
import org.tg.book.web.vo.LoginVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public TgResult<String> loginByPassword(@RequestBody LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
        String csrfToken = authService.loginByPassword(loginVO.getUserName(), loginVO.getPassword(), request, response);
        if (StringUtils.isEmpty(csrfToken)) {
            return TgResult.fail("401", "用户名或密码错误!");
        }
        return TgResult.ok(csrfToken);
    }

    @PostMapping("/logout")
    public TgResult<String> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return TgResult.ok();
    }

}
