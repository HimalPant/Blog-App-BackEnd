package com.blog.services.serviceImpl;

import com.blog.exception.ResourceNotFoundException;
import com.blog.model.Category;
import com.blog.payloads.CategoryDTO;
import com.blog.repositories.CategoryRepository;
import com.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = this.CategoryDTOToCategory(categoryDTO); // getting category from categoryDTO
        Category createdCategory = this.categoryRepository.save(category); // saving category that we get
        return this.CategoryToCategoryDTO(createdCategory); // again converting created category to CategoryDTO.
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        // so to update we need to have categoryId, that's why we need to extract it from CategoryRepository first
        Category category = this.categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryDetails(categoryDTO.getCategoryDetails());
        return this.CategoryToCategoryDTO(category);
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return this.CategoryToCategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> allCategory = this.categoryRepository.findAll(); // we get all the category from categoryRepository
        List<CategoryDTO> collect = allCategory.stream()
                .map(category -> this.CategoryToCategoryDTO(category)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        this.categoryRepository.delete(category);
    }

    // converting Category to CategoryDTO using model mapper
    public CategoryDTO CategoryToCategoryDTO(Category category){
        CategoryDTO updatedCategoryDTO = this.modelMapper.map(category, CategoryDTO.class);
        return updatedCategoryDTO;
    }

    // converting CategoryDTO to Category using model mapper
    public Category CategoryDTOToCategory(CategoryDTO categoryDTO){
        Category updatedCategory = this.modelMapper.map(categoryDTO, Category.class);
        return updatedCategory;
    }
}
