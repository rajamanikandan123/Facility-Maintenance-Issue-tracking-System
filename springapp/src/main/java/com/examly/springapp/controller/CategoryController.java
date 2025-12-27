package com.examly.springapp.controller;


import com.examly.springapp.model.Category;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.repository.CategoryRepo;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryRepo categoryRepo;
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category savedCategory = categoryRepo.save(category);

        return new ResponseEntity<Category>(savedCategory,HttpStatus.CREATED);
     }
    @GetMapping
    public ResponseEntity<List<Category>> getallCategory(){

         List<Category> categories=categoryRepo.findAll();
         return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
   public ResponseEntity<Category> getCategoryById(@PathVariable int categoryId){
   return categoryRepo.findById(categoryId)
         .map(c -> new ResponseEntity<Category>(c,HttpStatus.OK))
         .orElse( new ResponseEntity<Category>(HttpStatus.NOT_FOUND));
   }




   }



