package org.tg.book.service;

import org.tg.book.service.bo.BookBO;

public interface BookService {

    BookBO getBook(Integer id);

    Integer saveBook(BookBO bookBO);
}
