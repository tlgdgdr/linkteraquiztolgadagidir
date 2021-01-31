package com.linktera.linkteraquiz.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linktera.linkteraquiz.model.Author;
import lombok.Getter;

@Getter
public class AuthorRequest {

    @JsonProperty("author")
    private Author author;
}
