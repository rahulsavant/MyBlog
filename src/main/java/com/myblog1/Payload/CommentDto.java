package com.myblog1.Payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private long id;
    @NotEmpty
    @Size(min = 2,message = "name should be of min 2 character")
    private String name;
    @NotEmpty(message = "email must not be empty")
    @Email(message = "Invalid email address")
    private String email;
    @NotEmpty
    @Size(min = 5,message = "name should be of min 5 character")
    private String body;
}
