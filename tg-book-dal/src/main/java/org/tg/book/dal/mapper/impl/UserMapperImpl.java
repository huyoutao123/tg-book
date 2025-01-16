package org.tg.book.dal.mapper.impl;

import org.springframework.stereotype.Repository;
import org.tg.book.dal.mapper.UserMapper;
import org.tg.book.dal.po.User;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserMapperImpl implements UserMapper {

    private List<User> userList = Arrays.asList(User.builder().userId(1L)
            .userName("admin")
            // tg_123456使用md5加密后
            .password("2469c47aa7b758eefff3d75939b20c7b")
            .build());

    @Override
    public User selectByUserNamePassword(String userName, String password) {
        return userList.stream().filter(p->p.getUserName().equals(userName) && p.getPassword().equals(password))
                .findFirst().orElse(null);
    }
}

