package com.blair.projectmgmtapp.service;

import com.blair.projectmgmtapp.model.Category;
import com.blair.projectmgmtapp.model.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private static List<Project> projects = new ArrayList<>();

//    static {
//        Category flooringCategory = new Category("Flooring");
//        Category roofingCategory = new Category("Roofing");
//        Category landscapingCategory = new Category("Landscaping");
//
//        projects.add(new Project(1L, "Project A", 1000.00f, flooringCategory, "Description for Project A", "In Progress", "Vendor A"));
//        projects.add(new Project(2L, "Project B", 2000.00f, roofingCategory, "Description for Project B", "Completed", "Vendor B"));
//        projects.add(new Project(3L, "Project C", 1500.00f, landscapingCategory, "Description for Project C", "Not Started", "Vendor C"));
//    }
static {
    Category flooringCategory = new Category("Flooring");
    Category roofingCategory = new Category("Roofing");
    Category landscapingCategory = new Category("Landscaping");

    Project projectA = new Project();
    projectA.setId(1L);
    projectA.setName("Project A");
    projectA.setPrice(1000.00f);
    projectA.setCategory(flooringCategory);
    projectA.setDescription("Description for Project A");
    projectA.setStatus("In Progress");
    projectA.setVendorName("Vendor A");

    Project projectB = new Project();
    projectB.setId(2L);
    projectB.setName("Project B");
    projectB.setPrice(2000.00f);
    projectB.setCategory(roofingCategory);
    projectB.setDescription("Description for Project B");
    projectB.setStatus("Completed");
    projectB.setVendorName("Vendor B");

    Project projectC = new Project();
    projectC.setId(3L);
    projectC.setName("Project C");
    projectC.setPrice(1500.00f);
    projectC.setCategory(landscapingCategory);
    projectC.setDescription("Description for Project C");
    projectC.setStatus("Not Started");
    projectC.setVendorName("Vendor C");

    projects.add(projectA);
    projects.add(projectB);
    projects.add(projectC);
}


    public List<Project> getAllProjects() {
        return new ArrayList<>(projects);
    }

    public Project saveProject(Project project) {
        project.setId((long) (projects.size() + 1));
        projects.add(project);
        return project;
    }

    public Project getProjectById(Long id) {
        return projects.stream()
                .filter(project -> project.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid project Id: " + id));
    }

    public void deleteProjectById(Long id) {
        projects.removeIf(project -> project.getId().equals(id));
    }
}
