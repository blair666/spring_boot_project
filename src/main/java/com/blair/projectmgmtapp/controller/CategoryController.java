package com.blair.projectmgmtapp.controller;


import com.blair.projectmgmtapp.model.Category;
import com.blair.projectmgmtapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository repo;

    @GetMapping("/categories")
    public String listCategories(Model model)
    {
        List<Category> listCategories = repo.findAll();
		model.addAttribute("listCategories", listCategories);
		return "categories";
    }


    @GetMapping("/categories/new")
    public String showCategoryForm(Model model) {
        // Create ServiceType object to hold service type form data
        Category category = new Category();
        model.addAttribute("category", category);
        return "create_category";
    }

    @PostMapping("/categories/save")
    public String saveCategory( Category category) {
        repo.save(category);
        return "redirect:/categories";
    }


}
