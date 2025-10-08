package com.vishal.service;

import com.vishal.dto.SalonDTO;
import com.vishal.model.Category;
import com.vishal.repository.CategoryRepository;

import java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category, SalonDTO salongDTO);

    Set<Category> getAllCategoriesBySalon(Long id);

    Category getCategoryById(Long id) throws Exception;

    void deleteCategoryById(Long id, Long salonId) throws Exception;

}
