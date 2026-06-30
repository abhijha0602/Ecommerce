package com.project.ecom.mappers;

import com.project.ecom.entities.Category;
import com.project.ecom.requests.CategoryRequest;
import com.project.ecom.response.CategoryResponse;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public Category toEntity(final CategoryRequest request){
        return Category.builder().name(request.getName()).description(request.getDescription()).deleted(false).build();
    }


    public CategoryResponse toResponse(final Category entity){
        return CategoryResponse.builder().id(entity.getId()).name(entity.getName()).description(entity.getDescription()).build();
    }
}
