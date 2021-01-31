package com.linktera.linkteraquiz.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linktera.linkteraquiz.model.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuthorResponse {
    @JsonProperty("author")
    @JsonInclude(Include.NON_NULL)
    private Author author;

    public AuthorResponse(Author author) {
        this.author = author;
    }
    @JsonProperty("authors")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Author> authors;

    public AuthorResponse(List<Author> authors) {
        this.authors = authors;
    }

}
