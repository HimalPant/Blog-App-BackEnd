package com.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Integer postId;

    private String title;

    private String content;

    private  String imageName;

    private Date addedDate;

    private CategoryDTO category;

    private UserDto user;
}
