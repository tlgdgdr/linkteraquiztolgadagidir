package com.linktera.linkteraquiz.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class User {

    private UUID userId;

    private String username;

    private List<Book> rentedBook;

}
