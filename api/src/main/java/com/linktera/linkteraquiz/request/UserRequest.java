package com.linktera.linkteraquiz.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linktera.linkteraquiz.model.User;
import lombok.Getter;

@Getter
public class UserRequest {

    @JsonProperty("user")
    private User user;
}
