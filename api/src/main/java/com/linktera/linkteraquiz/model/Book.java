package com.linktera.linkteraquiz.model;

import com.linktera.linkteraquiz.request.AuthorRequest;
import com.linktera.linkteraquiz.response.AuthorResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID bookId;

    private String title;

    private Author author;

    private boolean isRented;

}
