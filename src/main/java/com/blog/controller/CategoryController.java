package com.blog.controller;

import com.blog.payloads.APIResponse;
import com.blog.payloads.CategoryDTO;
import com.blog.payloads.UserDto;
import com.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // creating category
    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createCategory(@Validated @RequestBody CategoryDTO categoryDTO){
        CategoryDTO category = this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // updating category
    @PutMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDTO> updatingCategory(@Validated @PathVariable Integer categoryId, @RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTO1 = this.categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(categoryDTO1, HttpStatus.OK);
    }

    // get category by Id
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId){
        CategoryDTO categoryById = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryById, HttpStatus.OK);
    }

    // get all category
    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> allCategory = this.categoryService.getAllCategory();
        return new ResponseEntity<>(allCategory, HttpStatus.OK);
    }

    // delete category by Id
    @DeleteMapping("/category/{id}")
    public ResponseEntity<APIResponse> deleteById(@PathVariable Integer id){
        this.categoryService.deleteCategoryById(id);
      return new ResponseEntity<>(new APIResponse("Category of id " + id + " is deleted successfully !!",true),HttpStatus.OK);
    }
}
