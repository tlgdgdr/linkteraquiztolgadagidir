package com.linktera.linkteraquiz.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    @JsonProperty("user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    public UserResponse(User user) {
        this.user = user;
    }

    @JsonProperty("users")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> users;

    public UserResponse(List<User> users) {
        this.users = users;
    }

}