package com.blog.services;

import com.blog.model.Category;
import com.blog.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {

    // create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    // update category
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    // get category by id
    public CategoryDTO getCategoryById(Integer categoryId);

    // get all category
    public List<CategoryDTO> getAllCategory();

    // delete category by categoryId
    public void deleteCategoryById(Integer categoryId);
}
