package org.tg.book.dal.po;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private Integer id;
    private String bookName;
    private String bookNo;
    private String bookAuthor;
    private Integer bookType;
    private String bookDesc;
    private String publisher;
    private Date publishDate;
}
