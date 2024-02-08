package com.application.prodevblogs.service.serviceImpl;

import com.application.prodevblogs.exceptions.CategoryAlreadyExistsException;
import com.application.prodevblogs.model.Category;
import com.application.prodevblogs.repository.CategoryRepository;
import com.application.prodevblogs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



        @Override
        public Category createCategory(Category category) {
            try {
                if(!categoryRepository.existsByCategory(category.getCategory())){
                    return categoryRepository.save(category);}
                else {
                    throw new CategoryAlreadyExistsException("The category is already exists");
                }
            } catch (CategoryAlreadyExistsException e) {
                throw new RuntimeException("The category is already exists",e);
            }
        }


        @Override
        public List<Category> getAllCategories() {
                return categoryRepository.findAll();
        }

}
