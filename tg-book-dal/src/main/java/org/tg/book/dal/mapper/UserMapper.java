package org.tg.book.dal.mapper;

import org.tg.book.dal.po.User;

public interface UserMapper {
    User selectByUserNamePassword(String userName, String password);
}
