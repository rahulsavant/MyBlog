package com.myblog1.Payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class PostDto {

    private long id;
    @NotEmpty
    @Size(min = 2,message = "There should be min 2 Character in title")
    private String title;
    @Size(min = 5,message = "There should be min 5 character in description")
    private String description;
    @NotEmpty
    @Size(min = 10,message = "There should be min 10 character in content")
    private String content;
}
