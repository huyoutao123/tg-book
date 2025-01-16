package org.tg.book.web.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.tg.book.service.bo.BookBO;

import java.io.Serializable;
import java.util.Date;

@Data
public class BookVO implements Serializable {
    private Integer id;
    private String bookName;
    private String bookNo;
    private String bookAuthor;
    private Integer bookType;
    private String bookDesc;
    private String publisher;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    public BookBO toBookBO() {
        BookBO bookBO = new BookBO();
        BeanUtils.copyProperties(this, bookBO);
        return bookBO;
    }
}
