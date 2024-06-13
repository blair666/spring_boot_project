package com.blair.projectmgmtapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private float price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    private String vendorName;

//create mock data using Java POJOs (Plain Old Java Objects)
//    @Override
//    public String toString() {
//        return "Project{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", price=" + price +
//                ", description='" + description + '\'' +
//                ", status='" + status + '\'' +
//                ", vendorName='" + vendorName + '\'' +
//                '}';
//    }
// Constructor with all required fields


}
