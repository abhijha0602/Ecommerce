package com.project.ecom.services.impl;

import com.project.ecom.mappers.CategoryMapper;
import com.project.ecom.repositories.CategoryRepository;
import com.project.ecom.requests.CategoryRequest;
import com.project.ecom.response.CategoryResponse;
import com.project.ecom.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void create(CategoryRequest request) {

    }

    @Override
    public void update(String id, CategoryRequest request) {

    }

    @Override
    public CategoryResponse findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
