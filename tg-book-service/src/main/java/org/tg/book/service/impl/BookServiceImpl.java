package org.tg.book.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tg.book.dal.mapper.BookMapper;
import org.tg.book.dal.po.Book;
import org.tg.book.service.BookService;
import org.tg.book.service.bo.BookBO;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookBO getBook(Integer id) {
        Book book = bookMapper.selectByPrimaryKey(id);
        if (book == null) {
            return null;
        }
        BookBO bookBO = new BookBO();
        BeanUtils.copyProperties(book, bookBO);
        return bookBO;
    }

    @Override
    public Integer saveBook(BookBO bookBO) {
        if (bookBO == null) {
            return null;
        }
        Book book = new Book();
        BeanUtils.copyProperties(bookBO, book);
        if (bookBO.getId() == null) {
            return bookMapper.insert(book);
        } else {
            return bookMapper.updateByPrimaryKey(book);
        }
    }
}
