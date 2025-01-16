package org.tg.book.dal.mapper;

import org.tg.book.dal.po.Book;

public interface BookMapper {

    Book selectByPrimaryKey(Integer id);

    Integer insert(Book book);

    Integer updateByPrimaryKey(Book book);
}
