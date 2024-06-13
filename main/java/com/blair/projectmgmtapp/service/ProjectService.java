package com.blair.projectmgmtapp.service;

import com.blair.projectmgmtapp.model.Category;
import com.blair.projectmgmtapp.model.Project;
import com.blair.projectmgmtapp.model.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private static List<Project> projects = new ArrayList<>();

    static {
        Vendor vendorA = new Vendor();
        vendorA.setName("Vendor A");
        vendorA.setCategory("Flooring");
        vendorA.setZipCode("12345");
        vendorA.setRating(4.5);
        vendorA.setReviews("Excellent");
        vendorA.setPrice(1000.0);
        vendorA.setAddress("123 Street A");
        vendorA.setContactInfo("123-456-7890");

        Vendor vendorB = new Vendor();
        vendorB.setName("Vendor B");
        vendorB.setCategory("Roofing");
        vendorB.setZipCode("67890");
        vendorB.setRating(4.0);
        vendorB.setReviews("Very Good");
        vendorB.setPrice(2000.0);
        vendorB.setAddress("456 Street B");
        vendorB.setContactInfo("987-654-3210");

        Vendor vendorC = new Vendor();
        vendorC.setName("Vendor C");
        vendorC.setCategory("Landscaping");
        vendorC.setZipCode("11223");
        vendorC.setRating(3.5);
        vendorC.setReviews("Good");
        vendorC.setPrice(1500.0);
        vendorC.setAddress("789 Street C");
        vendorC.setContactInfo("555-555-5555");

        projects.add(new Project(1L, "Project A", 1000.00f, new Category("Flooring"), "Description for Project A", "In Progress", vendorA));
        projects.add(new Project(2L, "Project B", 2000.00f, new Category("Roofing"), "Description for Project B", "Completed", vendorB));
        projects.add(new Project(3L, "Project C", 1500.00f, new Category("Landscaping"), "Description for Project C", "Not Started", vendorC));
    }

//    static {
//        projects.add(new Project(1L, "Project A", 1000.00f, new Category("Flooring"), "Description for Project A", "In Progress", vendorA));
//        projects.add(new Project(2L, "Project B", 2000.00f, new Category("Roofing"), "Description for Project B", "Completed", VendorB));
//        projects.add(new Project(3L, "Project C", 1500.00f, new Category("Landscaping"), "Description for Project C", "Not Started", vendorc));
//    }

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
