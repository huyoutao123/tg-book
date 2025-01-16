package org.tg.book.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookBO implements Serializable {
    private Integer id;
    private String bookName;
    private String bookNo;
    private String bookAuthor;
    private Integer bookType;
    private String bookDesc;
    private String publisher;
    private Date publishDate;
}
