package com.application.prodevblogs.controller;

import com.application.prodevblogs.exceptions.CategoryAlreadyExistsException;
import com.application.prodevblogs.model.Category;
import com.application.prodevblogs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createCategory(
            @RequestBody Category category) throws RuntimeException {
        try{
            Category createdCategory = categoryService.createCategory(category);
            return new ResponseEntity<>("Category Created Successfully", HttpStatus.CREATED);
        }catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


}
