package com.blair.projectmgmtapp.controller;

import com.blair.projectmgmtapp.model.Category;
import com.blair.projectmgmtapp.model.Project;
import com.blair.projectmgmtapp.service.ProjectService;
import com.blair.projectmgmtapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/new")
    public String createProjectForm(Model model) {
        List<Category> listCategories = categoryService.getAllCategories();
        model.addAttribute("project", new Project());
        model.addAttribute("listCategories", listCategories);
        return "create_project";
    }

    @PostMapping("/save")
    public String saveProject(Project project) {
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "projects";
    }

    @GetMapping("/edit/{id}")
    public String editProjectForm(@PathVariable("id") Long id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        List<Category> listCategories = categoryService.getAllCategories();
        model.addAttribute("listCategories", listCategories);
        return "edit_project";
    }

    @GetMapping("/{id}/delete")
    public String deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProjectById(id);
        return "redirect:/projects";
    }
}

//package com.blair.projectmgmtapp.controller;
//
//import com.blair.projectmgmtapp.model.Category;
//import com.blair.projectmgmtapp.model.Project;
//import com.blair.projectmgmtapp.repository.ProjectRepository;
//import com.blair.projectmgmtapp.repository.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class ProjectController {
//
//    @Autowired
//    private ProjectRepository projectRepo;
//
//    @Autowired
//    private CategoryRepository categoryRepo;
//
//    @GetMapping("/projects/new")
//    public String createProjectForm(Model model) {
//        List<Category> listCategories = categoryRepo.findAll();
//        Project project = new Project();
//        model.addAttribute("project", new Project());
//        model.addAttribute("listCategories", listCategories);
//        return "create_project";
//    }
//
//    @PostMapping("/projects/save")
//    public String saveProject(Project project) {
//        projectRepo.save(project);
//        return "redirect:/projects";
//    }
//
//    // Handler method to list projects and return model and view
//    @GetMapping("/projects")
//    public String listProjects(Model model) {
////        List<Category> listCategories = categoryRepo.findAll();
//        model.addAttribute("projects", projectRepo.findAll());
//        return "projects";
//    }
//
//    @GetMapping("/projects/edit/{id}")
//    public String editProjectForm(@PathVariable("id") Long id, Model model) {
//        Project project = projectRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid project Id:" + id));
//        model.addAttribute("project", project);
//        List<Category> listCategories = categoryRepo.findAll();
//        model.addAttribute("listCategories", listCategories);
//        return "edit_project";
//    }
//
//
//    // Handler method to handle delete project request
//    @GetMapping("/projects/{id}/delete")
//    public String deleteProject(@PathVariable("id") Long id, Model model) {
//        projectRepo.deleteById(id);
//        return "redirect:/projects";
//    }
//}
