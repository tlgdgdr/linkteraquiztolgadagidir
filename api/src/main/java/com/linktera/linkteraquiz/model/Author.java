package com.linktera.linkteraquiz.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Author {

    private UUID authorId;

    private String name;

    private List<Book> bookList;

}
