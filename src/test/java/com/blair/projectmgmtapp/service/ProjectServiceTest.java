package com.blair.projectmgmtapp.service;

import com.blair.projectmgmtapp.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        projectService = new ProjectService();
    }

    @Test
    void getAllProjects() {
        List<Project> projects = projectService.getAllProjects();

        // Verify the size of the project list
        assertEquals(3, projects.size());

        // Verify the content of the project list
        Project projectA = projects.get(0);
        assertEquals(1L, projectA.getId());
        assertEquals("Project A", projectA.getName());
        assertEquals(1000.00f, projectA.getPrice());
        assertEquals("Flooring", projectA.getCategory().getName());
        assertEquals("Description for Project A", projectA.getDescription());
        assertEquals("In Progress", projectA.getStatus());
        assertEquals("Vendor A", projectA.getVendorName());

        Project projectB = projects.get(1);
        assertEquals(2L, projectB.getId());
        assertEquals("Project B", projectB.getName());
        assertEquals(2000.00f, projectB.getPrice());
        assertEquals("Roofing", projectB.getCategory().getName());
        assertEquals("Description for Project B", projectB.getDescription());
        assertEquals("Completed", projectB.getStatus());
        assertEquals("Vendor B", projectB.getVendorName());

        Project projectC = projects.get(2);
        assertEquals(3L, projectC.getId());
        assertEquals("Project C", projectC.getName());
        assertEquals(1500.00f, projectC.getPrice());
        assertEquals("Landscaping", projectC.getCategory().getName());
        assertEquals("Description for Project C", projectC.getDescription());
        assertEquals("Not Started", projectC.getStatus());
        assertEquals("Vendor C", projectC.getVendorName());
    }
}