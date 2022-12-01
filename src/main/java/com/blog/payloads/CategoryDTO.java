package com.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Integer categoryId;

    @NotEmpty
    @Size(min = 4, max = 30, message = "Category name must be in between 4 and 30 characters")
    private String categoryName;

    @NotEmpty
    @Size(min = 10, max = 100, message = "Category Description must not be null")
    private String categoryDetails;
}
