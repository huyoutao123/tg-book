package org.tg.book.dal.mapper.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.tg.book.dal.mapper.BookMapper;
import org.tg.book.dal.po.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookMapperImpl implements BookMapper {

    private final List<Book> bookList = new ArrayList<>();

    @Override
    public Book selectByPrimaryKey(Integer id) {
        return bookList.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Integer insert(Book book) {
        book.setId(bookList.size() + 1);
        bookList.add(book);
        return book.getId();
    }

    @Override
    public Integer updateByPrimaryKey(Book book) {
        Optional<Book> first = bookList.stream().filter(p -> p.getId().equals(book.getId())).findFirst();
        if (first.isPresent()) {
            BeanUtils.copyProperties(book, first.get());
            return book.getId();
        }
        return null;
    }
}
