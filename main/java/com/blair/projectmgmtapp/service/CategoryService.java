package com.blair.projectmgmtapp.service;

import com.blair.projectmgmtapp.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private static List<Category> categories = new ArrayList<>();

    static {
        categories.add(new Category("Interior"));
        categories.add(new Category("Electrical"));
        categories.add(new Category("Remodeling"));
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }

    public Category getCategoryById(Long id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id: " + id));
    }
}
