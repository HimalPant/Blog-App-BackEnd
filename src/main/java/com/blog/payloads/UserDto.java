package com.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 5, max = 50, message = "Name must be between 5 and 20 characters")
    private  String name;

    @NotEmpty(message = "Email Id must not be null !!")
    @Email(message = "Email Id must be valid !!")
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    @Size(min = 5, max = 100, message = "About must be in between 30 and 100 characters !!")
    private String about;
}
