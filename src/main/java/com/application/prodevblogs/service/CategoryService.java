package com.application.prodevblogs.service;

import com.application.prodevblogs.exceptions.CategoryAlreadyExistsException;
import com.application.prodevblogs.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
   Category createCategory(Category category) throws CategoryAlreadyExistsException;
   List<Category> getAllCategories();
}
