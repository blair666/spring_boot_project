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
    public String saveProject(@ModelAttribute("project") Project project) {
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping
    public String listProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/edit/{id}")
    public String editProjectForm(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);
        List<Category> listCategories = categoryService.getAllCategories();
        model.addAttribute("project", project);
        model.addAttribute("listCategories", listCategories);
        return "edit_project";
    }

    @PostMapping("/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute("project") Project project) {
        project.setId(id); // Ensure the project ID is set
        projectService.updateProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return "redirect:/projects";
    }
}
