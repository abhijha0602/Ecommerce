package com.project.ecom.controller;

import com.project.ecom.requests.CategoryRequest;
import com.project.ecom.response.CategoryResponse;
import com.project.ecom.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<Void> createCategory(
        @Valid
        @RequestBody
        final CategoryRequest request){
        this.service.create(request);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{category_id}")
    public ResponseEntity<Void> updateCategory(
            @Valid
            @RequestBody
            final CategoryRequest request, @PathVariable("category_id") final String id){
        this.service.update(id,request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable("category_id") final String id){

       // return ResponseEntity.ok(this.service.findById(id));
        return ResponseEntity.ok(this.service.findById(id));
    }

}
