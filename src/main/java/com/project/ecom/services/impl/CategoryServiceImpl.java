package com.project.ecom.services.impl;

import com.project.ecom.config.TenantContext;
import com.project.ecom.entities.Category;
import com.project.ecom.mappers.CategoryMapper;
import com.project.ecom.repositories.CategoryRepository;
import com.project.ecom.requests.CategoryRequest;
import com.project.ecom.response.CategoryResponse;
import com.project.ecom.services.CategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
//    @PersistenceContext
//    private EntityManager entityManager;

    @Override
    public void create(CategoryRequest request) {
       checkIfCategoryExistsByName(request.getName());

       final Category category=categoryMapper.toEntity(request);
       this.categoryRepository.save(category);
    }

    private void checkIfCategoryExistsByName(String name) {
         final Optional<Category> category=this.categoryRepository.findByNameIgnoreCase(name);
         if(category.isPresent()){
             throw new RuntimeException("category already exists");
         }
    }

    @Override
    public void update(String id, CategoryRequest request) {

        final Optional<Category> existingCategory=this.categoryRepository.findById(id);
        if(existingCategory.isEmpty()){
            throw new EntityNotFoundException("category not found");
        }
        final Category category=existingCategory.get();
        if(!category.getName().equalsIgnoreCase(request.getName())){
            checkIfCategoryExistsByName(request.getName());
        }
      final Category updateCategory=categoryMapper.toEntity(request);
        updateCategory.setId(id);
        this.categoryRepository.save(updateCategory);
    }

    @Override
    public CategoryResponse findById(String id) {
        return this.categoryRepository.findById(id).map(this.categoryMapper::toResponse).orElseThrow(()-> new EntityNotFoundException("category not found"));
    }

    @Override
    public void delete(String id) {
      final Category category=this.categoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException("category not found"));
      this.categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return this.categoryRepository.findAll().stream().map(this.categoryMapper::toResponse).toList();

//        String tenantId = TenantContext.getCurrentTenant();
//        log.info("Tenant in service: {}", TenantContext.getCurrentTenant());
//
//        Session session = entityManager.unwrap(Session.class);
//
//        session.enableFilter("tenantFilter")
//                .setParameter("tenantId", tenantId);
//
//        return this.categoryRepository.findAll()
//                .stream()
//                .map(this.categoryMapper::toResponse)
//                .toList();


    }
}
