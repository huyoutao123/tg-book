package org.tg.book.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tg.book.common.dto.TgResult;
import org.tg.book.service.BookService;
import org.tg.book.service.bo.BookBO;
import org.tg.book.web.vo.BookVO;

@RestController
@RequestMapping("/admin")
public class BookAdminController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public TgResult<BookBO> getBook(@RequestParam("id") Integer id) {
        return TgResult.ok(bookService.getBook(id));
    }

    @PostMapping("/book")
    public TgResult<Integer> saveBook(@RequestBody BookVO bookVO) {
        return TgResult.ok(bookService.saveBook(bookVO.toBookBO()));
    }
}
